package org.nanaki.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.nanaki.model.Personne;
import org.nanaki.util.Strings;

public abstract class SQLManager<T> implements Manager<T> {
	protected Connection connection;
	private PreparedStatement preparedStatementInsert;
	private PreparedStatement preparedStatementUpdate;

	private boolean multiStatement;
	private String[] allField;
	private Function<T, ?>[] allGetter;
	protected int[] idsIndex;
	private String[] allTypes;
	private SmartMap<Object, T> cache = new SmartMap<>();
	private Supplier<? extends T> supplier;

	public SQLManager(Connection connection)
			throws SQLException {
		this.connection = connection;

	}

	public void init() throws SQLException {

		allField = concat(getFieldNames(), getFKName());
		allTypes = initType();
		allGetter = allGetter();
		idsIndex = getIdsIndex();
		supplier = getSupplier();
		if (idsIndex == null) {
			idsIndex = new int[] { getIdIndex() };
		}
		createTable();
		preparedStatementInsert = connection
				.prepareStatement(makeInsert());
		preparedStatementUpdate = connection
				.prepareStatement(makeUpdate());
	}

	private String[] initType() {
		String[] type = new String[allField.length];
		for (int i = 0; i < allField.length; i++) {
			if (i < getFieldNames().length) {
				type[i] = getSQLType()[i];
			} else {
				type[i] = getManager(i - allField.length)
						.getSQLType()[getManager(
								i - allField.length)
										.getIdIndex()];
			}
		}
		return type;
	}

	private Function<T, ?>[] allGetter() {
		@SuppressWarnings("unchecked")
		Function<T, ?>[] ret = new Function[allField.length];
		int l = getFieldNames().length;
		for (int i = 0; i < allField.length; i++) {
			if (i < l) {
				ret[i] = getGetterValuesFunction(i);
			} else {
				ret[i] = getGetterFKFunction(i - l);
			}
		}
		return ret;
	}

	@Override
	public boolean save(T t) throws SQLException {
		int j;
		for (int i = 0; i < allGetter.length; i++) {
			if (notAutoIndex(i)) {
				preparedStatementInsert.setObject(
						autoIncrement(i) + 1,
						allGetter[i].apply(t));
			}
		}

		if (multiStatement) {
			preparedStatementInsert.addBatch();
			return false;
		} else {
			return preparedStatementInsert.execute();
		}

	}

	private boolean notAutoIndex(int i) {
		return !isAutoIncrement() || i != getIdIndex();
	}

	private boolean notIdIndex(int i) {

		for (int j = 0; j < idsIndex.length; j++) {
			if (i == j) {
				return false;
			}
		}

		return true;
	}

	private int autoIncrement(int i) {
		if (isAutoIncrement()) {
			if (i >= getIdIndex())
				return i - 1;
		}
		return i;

	}

	@Override
	public void saveAll(List<T> ts) throws SQLException {
		multiStatement = true;
		try {
			try {
				Manager.super.saveAll(ts);
			} catch (Exception e) {
				throw (SQLException) e;
			}
		} finally {
			multiStatement = false;
		}

	}

	@Override
	public void updateAll(List<T> ts) throws SQLException {
		multiStatement = true;
		try {
			try {
				Manager.super.updateAll(ts);
			} catch (Exception e) {
				throw (SQLException) e;
			}
		} finally {
			multiStatement = false;
		}
	}

	@Override
	public boolean delete(T t) throws SQLException {
		try(PreparedStatement statement = connection
				.prepareStatement(makeDeletePrepared());){
			for (int i = 0; i < idsIndex.length; i++) {
				statement.setObject(i + 1,
						allGetter[idsIndex[i]]);
			}
			return statement.execute();
		}
		
		
		

	}
	
	private String makeDeletePrepared() {
		StringBuilder t = new StringBuilder();
		t.append("DELETE FROM " + getTable() + " WHERE ");
		t.append(concat(idsIndex, " = ?", " AND "));
		return t.toString();
	}

	private String concat(int[] indexField,
			String apendToField, String sepField) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < indexField.length; i++) {
			b.append(allField[indexField[i]])
					.append(apendToField);
			if (i != indexField.length - 1) {
				b.append(sepField);
			}
		}
		return b.toString();
	}

	@Override
	public boolean update(T t) throws SQLException {
		int fCount = allGetter.length;
		for (int i = 0; i < allGetter.length; i++) {
			preparedStatementUpdate.setObject(i + 1,
					allGetter[i].apply(t));
		}

		for (int i = 0; i < idsIndex.length; i++) {
			preparedStatementUpdate.setObject(
					fCount + i + 1,
					allGetter[idsIndex[i]].apply(t));
		}
		return preparedStatementUpdate.executeUpdate() == 1;

	}

	private String makeInsert() {
		if (!isAutoIncrement()) {
			return "INSERT INTO " + getTable() + " \n("
					+ Strings.implode(allField, ", ") + ")"
					+ "VALUES " + "(" + Strings.implode("?",
							", ", allField.length)
					+ ")";
		} else {
			return "INSERT INTO " + getTable() + " \n("
					+ Strings.implode(withouId(allField),
							", ")
					+ ")" + "VALUES " + "("
					+ Strings.implode("?", ", ",
							allField.length
									- idsIndex.length)
					+ ")";
		}

	}

	private String[] withouId(String[] fieldNames) {
		String[] field = new String[fieldNames.length
				- idCount()];
		for (int i = 0, j = 0; i < field.length; i++) {
			if (!notIdIndex(i)) {
				j++;
			}
			field[i] = fieldNames[i + j];
		}
		return field;
	}

	private int idCount() {
		return idsIndex.length;
	}

	private String makeUpdate() {
		return "UPDATE " + getTable() + " SET "
				+ preparedUpdateField() + " WHERE "
				+ concat(idsIndex, " = ? ", " AND ");
	}

	public boolean createTable() throws SQLException {
		try (Statement create = connection
				.createStatement()) {
			return create.execute(makeCreate());
		}
	}

	public boolean dropTable() throws SQLException {
		try (Statement create = connection
				.createStatement()) {
			return create
					.execute("DROP TABLE " + getTable());
		}

	}

	private String makeCreate() {
		StringBuilder bl = new StringBuilder(
				"CREATE TABLE IF NOT EXISTS ");
		bl.append(getTable()).append(" (");
		String[] fk = getFKName();

		int idIndex = getIdIndex();
		for (int i = 0; i < allField.length; i++) {
			bl.append(allField[i]).append(" ")
					.append(allTypes[i]);
			if (idsIndex.length == 1 && idIndex == i) {
				bl.append(" PRIMARY KEY");
				if (isAutoIncrement()) {
					bl.append(" AUTOINCREMENT");
				}
			}
			if (i != allField.length - 1) {
				bl.append(", ");
			}
			bl.append("\n");
		}
		if (fk != null) {
			bl.append(",");
			for (int i = 0; i < fk.length; i++) {
				SQLManager<?> manager = getManager(i);
				bl.append("FOREIGN KEY(").append(fk[i])
						.append(") REFERENCES ")
						.append(manager.getTable())
						.append("(")
						.append(manager
								.getFieldNames()[manager
										.getIdIndex()])
						.append(")");
				;
				if (i != fk.length - 1) {
					bl.append(", ");
				}
				bl.append("\n");
			}
		}
		if (idsIndex.length != 1) {
			bl.append(",\nPRIMARY KEY (");
			for (int i = 0; i < idsIndex.length; i++) {
				bl.append(allField[idsIndex[i]]);
				if (i != idsIndex.length - 1) {
					bl.append(", ");
				}
			}
			bl.append(")\n");
		}

		bl.append(" ) ");
		return bl.toString();
	}

	private String preparedUpdateField() {
		StringBuilder bl = new StringBuilder();
		for (int i = 0; i < allField.length; i++) {
			bl.append(allField[i]).append(" = ? ");
			if (i != allField.length - 1) {
				bl.append(" , ");
			}
		}
		return bl.toString();
	}

	public abstract String getTable();

	public abstract int getIdIndex();

	public abstract int[] getIdsIndex();

	public abstract boolean isAutoIncrement();

	public abstract String[] getFieldNames();

	public abstract String[] getSQLType();

	public abstract String[] getFKName();

	public abstract Supplier<? extends T> getSupplier();

	public abstract Function<T, Object> getGetterValuesFunction(
			int i);

	public abstract Function<T, Object> getGetterFKFunction(
			int i);

	public abstract FunctionSetter<T> getSetterValuesFunction(
			int i);

	public abstract FunctionSetter<T> getFKSetter(int i);

	public abstract SQLManager<?> getManager(int i);
	public abstract void link(T t, Object o, String fkName);

	@Override
	public boolean exists(T t) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

	@Override
	public boolean isMultipleStatement() {
		return true;
	}

	@Override
	public boolean finishSaveAll() throws SQLException {
		return preparedStatementInsert
				.executeBatch().length > 0;
	}

	@Override
	public boolean finishUpdateAll() throws SQLException {
		return preparedStatementUpdate
				.executeBatch().length > 0;
	}

	@Override
	public List<T> getBy(String propName, Object f)
			throws SQLException {
		List<T> l = new ArrayList<>();
		try (PreparedStatement statement = connection
				.prepareStatement("SELECT "
						+ Strings.implode(allField, ", ")
						+ " FROM " + getTable() + " WHERE "
						+ propName + " = ?")) {
			statement.setObject(1, f);
			ResultSet executeQuery = statement
					.executeQuery();
			while (executeQuery.next()) {
				T t = read(executeQuery);
				
				l.add(t);
			}

		}
		return l;

	}

	private String[] concat(String[] fieldNames,
			String[] fkName) {
		int l1 = 0;
		int l2 = 0;
		if (fieldNames != null) {
			l1 = fieldNames.length;
		}
		if (fkName != null) {
			l2 = fkName.length;
		}
		String[] res = new String[l1 + l2];
		for (int i = 0; i < l1; i++) {
			res[i] = fieldNames[i];
		}
		for (int i = l1; i < l1 + l2; i++) {
			res[i] = fkName[i - l1];
		}
		return res;
	}

	@Override
	public List<T> getAll() throws SQLException {
		List<T> l = new ArrayList<>();
		try (PreparedStatement statement = connection
				.prepareStatement("SELECT "
						+ Strings.implode(allField, ", ")
						+ " FROM " + getTable())) {
			ResultSet executeQuery = statement
					.executeQuery();
			while (executeQuery.next()) {
				T t = read(executeQuery);

				l.add(t);
			}

		}
		return l;
	}

	private T read(ResultSet executeQuery)
			throws SQLException {
		T t = supplier.get();
		for (int i = 0; i < getFieldNames().length; i++) {
			Object object = executeQuery.getObject(i + 1);
			FunctionSetter<T> setterValuesFunction = getSetterValuesFunction(
					i);
			setterValuesFunction.set(t, object);
		}

		if (haveForeignKey()) {
			for (int i = 0; i < getFKName().length; i++) {
				Object object = executeQuery.getObject(
						i + 1 + getFieldNames().length);
				Manager<?> m = getManager(i);
				Object o;
				try {
					o = m.getById(object);
					link(t,o,getFKName()[i]);
				} catch (Exception e) {
					throw new SQLException(e);
				}
				FunctionSetter<T> setter = getFKSetter(i);
				setter.set(t, o);
			}
		}
		return t;
	}

	

	private boolean haveForeignKey() {
		return getFKName() != null
				&& getFKName().length != 0;
	}

	@Override
	public <U extends T> U getById(Object object) throws SQLException {
		
		String select = "SELECT "
				+ Strings.implode(allField, ", ") + " FROM "
				+ getTable() + " WHERE "
				+ concat(idsIndex, " = ? ", " AND");
		try(PreparedStatement prepareStatement = connection
				.prepareStatement(select);){
			for (int i = 0; i < idsIndex.length; i++) {
				prepareStatement.setObject(i + 1, object);
			}
			ResultSet executeQuery = prepareStatement
					.executeQuery();
			if (executeQuery.next()) {
				T read = read(executeQuery);
				return (U) read;
			}
		}
		

		return null;
	}

	public String[] getAllFields() {
		return allField;
	}

	public String[] getAllTypes() {
		return allTypes;
	}
	
	public void close() throws SQLException{
		preparedStatementInsert.close();
		preparedStatementUpdate.close();
	}

	@Override
	public List<T> getAll(List<Object> ids)
			throws SQLException {
		List<T> l = new ArrayList<>();
		try (PreparedStatement statement = connection
				.prepareStatement("SELECT "
						+ Strings.implode(allField, ", ")
						+ " FROM " + getTable()+ " WHERE "+allField[idsIndex[0]]+" IN "+"( "+Strings.implode("?", ", ", ids.size())+" )") ) {
			int count = 1;
			for (Object id : ids) {
				statement.setObject(count, id);
				count++;
			}
			ResultSet executeQuery = statement
					.executeQuery();
			while (executeQuery.next()) {
				T t = read(executeQuery);

				l.add(t);
			}

		}
		return l;
	}

	public void setSupplier(Supplier<? extends T> supplier) {
		this.supplier = supplier;
	}
	
	
	

}
