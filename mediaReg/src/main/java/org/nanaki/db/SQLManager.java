package org.nanaki.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.nanaki.model.Film;
import org.nanaki.model.MediaPath;
import org.nanaki.util.Strings;

public abstract class SQLManager<T> implements Manager<T> {
	protected Connection connection;
	private PreparedStatement preparedStatementInsert;
	private PreparedStatement preparedStatementUpdate;
	
	private boolean multi;

	public SQLManager(Connection connection) throws SQLException {
		this.connection = connection;
		createTable();
		preparedStatementInsert = connection.prepareStatement(makeInsert());
		preparedStatementUpdate = connection.prepareStatement(makeUpdate());
	}

	@Override
	public boolean save(T t) throws SQLException {
		int j;
		for (int i = 0; i < getFieldNames().length; i++) {

			if (i != getIdIndex()) {
				preparedStatementInsert.setObject(autoIncrement(i) + 1, getGetterValuesFunction(i).apply(t));
			}
		}
		for (int i = 0; i < getFieldNames().length; i++) {

			if (i != getIdIndex()) {
				preparedStatementInsert.setObject(autoIncrement(i) + 1, getGetterValuesFunction(i).apply(t));
			}
		}

		if (multi) {
			preparedStatementInsert.addBatch();
			return false;
		} else {
			return preparedStatementInsert.execute();
		}

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
		multi = true;
		try {
			try {
				Manager.super.saveAll(ts);
			} catch (Exception e) {
				throw (SQLException) e;
			}
		} finally {
			multi = false;
		}

	}

	@Override
	public void updateAll(List<T> ts) throws SQLException {
		multi = true;
		try {
			try {
				Manager.super.updateAll(ts);
			} catch (Exception e) {
				throw (SQLException) e;
			}
		} finally {
			multi = false;
		}
	}

	@Override
	public boolean delete(T t) throws SQLException {
		Statement statement = connection.createStatement();
		return statement.execute("DELETE FROM " + getTable() + " WHERE " + getFieldNames()[getIdIndex()] + " = "
				+ getGetterValuesFunction(getIdIndex()).apply(t));
	}

	@Override
	public boolean update(T t) throws SQLException {
		int fCount = getFieldNames().length;
		for (int i = 0; i < getFieldNames().length; i++) {
			preparedStatementUpdate.setObject(i + 1, getGetterValuesFunction(i).apply(t));
		}
		preparedStatementUpdate.setObject(fCount + 1, getGetterValuesFunction(getIdIndex()).apply(t));
		return preparedStatementUpdate.executeUpdate() == 1;

	}

	private String makeInsert() {
		String[] concat = concat(getFieldNames(), getFKName());
		if (!isAutoIncrement()) {
			return "INSERT INTO " + getTable() + " \n(" + Strings.implode(concat, ", ") + ")" + "VALUES " + "("
					+ Strings.implode("?", ", ", getFieldNames().length) + ")";
		} else {
			return "INSERT INTO " + getTable() + " \n(" + Strings.implode(withouId(concat), ", ") + ")"
					+ "VALUES " + "(" + Strings.implode("?", ", ", getFieldNames().length - 1) + ")";
		}

	}

	private String[] withouId(String[] fieldNames) {
		String[] field = new String[fieldNames.length - 1];
		int j;
		for (int i = 0; i < field.length; i++) {
			if (i >= getIdIndex()) {
				j = i + 1;
			} else {
				j = i;
			}
			field[i] = fieldNames[j];
		}
		return field;
	}

	private String makeUpdate() {
		return "UPDATE " + getTable() + " SET " + preparedUpdateField() + " WHERE " + getFieldNames()[getIdIndex()]
				+ " =  ? ;";
	}

	public boolean createTable() throws SQLException {
		try (Statement create = connection.createStatement()) {
			return create.execute(makeCreate());
		}
	}

	public boolean dropTable() throws SQLException {
		try (Statement create = connection.createStatement()) {
			return create.execute("DROP TABLE " + getTable());
		}

	}

	private String makeCreate() {
		StringBuilder bl = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
		bl.append(getTable()).append(" (");
		String[] fields = getFieldNames();
		String[] types = getSQLType();
		String[] fk = getFKName();

		int idIndex = getIdIndex();
		for (int i = 0; i < fields.length; i++) {
			bl.append(fields[i]).append(" ").append(types[i]);
			if (idIndex == i) {
				bl.append(" PRIMARY KEY");
				if (isAutoIncrement()) {
					bl.append(" AUTOINCREMENT");
				}
			}
			if (i != fields.length - 1) {
				bl.append(", ");
			}
		}
		if(fk != null){
			for (int i = 0; i < fk.length; i++) {
				SQLManager<?> manager = getManager(i);
				bl.append(fk[i]).append(" ").append(manager.getSQLType()[i]).append(" FOREIGN KEY REFERENCES ")
				.append(manager.getTable()).append("(").append(manager.getFieldNames()[manager.getIdIndex()]).append(")");;
				if (i != fields.length - 1) {
					bl.append(", ");
				}
			}		
		}
		bl.append(" ) ");
		return bl.toString();
	}

	private String preparedUpdateField() {
		String[] fieldNames = getFieldNames();
		StringBuilder bl = new StringBuilder();
		for (int i = 0; i < fieldNames.length; i++) {
			bl.append(fieldNames[i]).append(" = ? ");
			if (i != fieldNames.length - 1) {
				bl.append(" , ");
			}
		}
		return bl.toString();
	}

	public abstract String getTable();

	public abstract int getIdIndex();

	public abstract boolean isAutoIncrement();

	public abstract String[] getFieldNames();

	public abstract String[] getSQLType();

	public abstract String[] getFKName();
	



	public abstract Supplier<T> getSupplier();

	public abstract Function<T, Object> getGetterValuesFunction(int i);

	public abstract FunctionSetter<T> getSetterValuesFunction(int i);
	public abstract FunctionSetter<T> getFKSetter(int i);
	public abstract  SQLManager<?> getManager(int i);

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
		return preparedStatementInsert.executeBatch().length > 0;
	}

	@Override
	public boolean finishUpdateAll() throws SQLException {
		return preparedStatementUpdate.executeBatch().length > 0;
	}

	@Override
	public List<T> getBy(String propName, Object f) throws SQLException {
		List<T> l = new ArrayList<>();
		try (PreparedStatement statement = connection
				.prepareStatement("SELECT " + Strings.implode(concat(getFieldNames(), getFKName()), ", ") + " FROM "
						+ getTable() + " WHERE " + propName + " = ?")) {
			statement.setObject(1, f);
			ResultSet executeQuery = statement.executeQuery();
			while (executeQuery.next()) {
				T t = getSupplier().get();
				for (int i = 0; i < getFieldNames().length; i++) {
					Object object = executeQuery.getObject(i + 1);
					FunctionSetter<T> setterValuesFunction = getSetterValuesFunction(i);
					setterValuesFunction.set(t, object);
				}
				if (haveForeignKey()) {
					for(int i = 0 ; i < getFKName().length ; i++){
						Object object = executeQuery.getObject(i + getFieldNames().length);
						Manager<?> m = getManager(i);
						Object o = m.getById(object);
						FunctionSetter<T> setter = getFKSetter(i);
						setter.set(t, o);
					}
				}
				l.add(t);
			}

		}
		return l;

	}

	private String[] concat(String[] fieldNames, String[] fkName) {
		int l1 = fieldNames.length;
		int l2 = fkName.length;
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
				.prepareStatement("SELECT " + Strings.implode(concat(getFieldNames(), getFKName()), ", ") + " FROM " + getTable())) {
			ResultSet executeQuery = statement.executeQuery();
			while (executeQuery.next()) {
				T t = getSupplier().get();
				for (int i = 0; i < getFieldNames().length; i++) {
					Object object = executeQuery.getObject(i + 1);
					FunctionSetter<T> setterValuesFunction = getSetterValuesFunction(i);
					setterValuesFunction.set(t, object);
				}

				if (haveForeignKey()) {
					for(int i = 0 ; i < getFKName().length ; i++){
						Object object = executeQuery.getObject(i + getFieldNames().length);
						Manager<?> m = getManager(i);
						Object o = m.getById(object);
						FunctionSetter<T> setter = getFKSetter(i);
						setter.set(t, o);
					}
				}
				l.add(t);
			}

		}
		return l;
	}

	

	

	

	private boolean haveForeignKey() {
		return getFKName()!=null && getFKName().length != 0;
	}

	@Override
	public T getById(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
