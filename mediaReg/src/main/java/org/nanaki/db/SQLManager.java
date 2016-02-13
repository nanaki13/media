package org.nanaki.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class SQLManager<T> implements Manager<T>, AutoCloseable {
	protected Connection connection;
	private PreparedStatement prepareStatementInsert;
	private PreparedStatement prepareStatementUpdate;

	public abstract String getTable();

	public abstract String[] getFieldsName();

	public abstract String[] getSqlTypes();

	public abstract Object[] getValues(T t);

	public abstract int getFieldCount();

	public abstract int getIdInex();

	public SQLManager(Connection connection) throws SQLException {
		this.connection = connection;
		init();

	}

	public void init() throws SQLException {
		System.out.println(makeInsert());
		prepareStatementInsert = connection.prepareStatement(makeInsert());
		System.out.println(makeUpdate());
		prepareStatementUpdate = connection.prepareStatement(makeUpdate());
	}

	private String makeUpdate() {
		return "UPDATE " + getTable() + " SET " + getNamedUpdate() + " WHERE " + getFieldsName()[getIdInex()] + "= ?";
	}

	private String getNamedUpdate() {
		StringBuilder bl = new StringBuilder();
		String[] fieldsName = getFieldsName();
		for (int i = 0; i < fieldsName.length; i++) {
			bl.append(fieldsName[i] + " = ? ");
			if (i != fieldsName.length - 1) {
				bl.append(", ");
			}
		}
		return bl.toString();
	}

	private String makeInsert() {
		return "INSERT INTO " + getTable() + "(" + implode(getFieldsName(), ",") + ")" + "VALUES " + "("
				+ repeat("?", ",", getFieldCount()) + ")";
	}

	private String implode(String[] object, String sep) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < object.length; i++) {
			stringBuilder.append(object[i]);
			if (i != object.length - 1) {
				stringBuilder.append(sep);
			}
		}
		return stringBuilder.toString();
	}

	private static String repeat(String string, String string2, int fieldCount) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < fieldCount; i++) {
			stringBuilder.append(string);
			if (i != fieldCount - 1) {
				stringBuilder.append(string2);
			}
		}
		return stringBuilder.toString();
	}

	public void dropTable() throws SQLException{
		try (Statement statement = connection.createStatement()) {
			System.out.println(createStatement());
			statement.execute("DROP TABLE "+getTable());
		}
	}
	public void createTable() throws SQLException {
		try (Statement statement = connection.createStatement()) {
			System.out.println(createStatement());
			statement.execute(createStatement());
		}
	}

	private String createStatement() {
		StringBuilder builder = new StringBuilder();
		builder.append("CREATE TABLE IF NOT EXISTS ").append(getTable()).append(" (\n").append(getCreation())
				.append("\n)");
		return builder.toString();
	}

	private String getCreation() {
		StringBuilder builder = new StringBuilder();
		String[] fieldsName = getFieldsName();
		String[] sqlType = getSqlTypes();
		for (int i = 0; i < fieldsName.length; i++) {
			
			builder.append(fieldsName[i]).append(' ').append(sqlType[i]);
			if( i == getIdInex()){
				builder.append("PRIMARY KEY");
			}
			if (i != fieldsName.length - 1) {
				builder.append(" ,\n");
			}
		}

		return builder.toString();
	}

	@Override
	public void save(T t) throws RuntimeException {
		Object[] l = getValues(t);
		try {
			for (int i = 1; i < l.length +1; i++) {

				prepareStatementInsert.setObject(i, l[i - 1]);
				

			}
			prepareStatementInsert.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void update(T t) {
		Object[] l = getValues(t);
		try {
			for (int i = 0; i < l.length; i++) {

				prepareStatementUpdate.setObject(i, l[i]);

			}
			prepareStatementUpdate.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean exists(T t) {
		// TODO Auto-generated method stub
		return false;
	}

	public void close() throws SQLException {
		connection.close();
	}

	public Connection getConnection() {
		return connection;
	}
	

}
