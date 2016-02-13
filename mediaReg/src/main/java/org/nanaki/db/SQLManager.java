package org.nanaki.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.function.Function;

import org.nanaki.util.Strings;

public abstract class SQLManager<T> implements Manager<T> {
	protected Connection connection;
	private PreparedStatement preparedStatementInsert;
	private PreparedStatement preparedStatementUpdate;

	public SQLManager(Connection connection) throws SQLException {
		this.connection = connection;
		preparedStatementInsert = connection.prepareStatement(makeInsert());
		System.out.println(makeUpdate());
		preparedStatementUpdate = connection.prepareStatement(makeUpdate());
	}

	@Override
	public void save(T t) throws SQLException {
		preparedStatementInsert.clearParameters();
		for (int i = 0; i < getFieldNames().length; i++) {
			preparedStatementInsert.setObject(i + 1, getValuesFunction(i).apply(t));
		}
		preparedStatementInsert.executeUpdate();
	}

	@Override
	public void update(T t) throws SQLException {
//		System.out.println("++ " +preparedStatementInsert.isClosed());
		preparedStatementUpdate.clearParameters();
//		System.out.println("++ " +preparedStatementUpdate.isClosed());
		int fCount = getFieldNames().length ; 
		for (int i = 0; i < getFieldNames().length; i++) {
//			System.out.println(getValuesFunction(i).apply(t));
			System.out.println(i + 1);
			System.out.println("value : "+getValuesFunction(i).apply(t));
			preparedStatementUpdate.setObject(i + 1 , getValuesFunction(i).apply(t));
		}
//		System.out.println(fCount + 1);
		System.out.println(fCount + 1);
		System.out.println("value : "+getValuesFunction(fCount - 1).apply(t));
		preparedStatementUpdate.setObject(fCount + 1, getValuesFunction(getIdIndex()).apply(t));
		System.out.println("++++   " + preparedStatementUpdate.executeUpdate());

	}

	private String makeInsert() {
		return "INSERT INTO " + getTable() + " \n(" + Strings.implode(getFieldNames(), ", ") + ")" + "VALUES " + "("
				+ Strings.implode("?", ", ", getFieldNames().length) + ")";
	}

	private String makeUpdate() {
		return "UPDATE " + getTable() + " SET " + preparedUpdateField() + " WHERE " + getFieldNames()[getIdIndex()]
				+ " =  ? ;";
	}
	
	public void createTable() throws SQLException{
		try(Statement create = connection.createStatement()){
			create.execute(makeCreate());
		}
	}
	
	public boolean dropTable() throws SQLException {
		try(Statement create = connection.createStatement()){
			return create.execute("DROP TABLE "+getTable());
		}
		
	}

	private String makeCreate() {
		StringBuilder bl = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
		bl.append(getTable()).append(" (");
		String[] fields = getFieldNames();
		String[] types = getSQLType();
		int idIndex = getIdIndex();
		for (int i = 0; i < fields.length; i++) {
			bl.append(fields[i]).append(" ").append(types[i]);
			if (idIndex == i) {
				bl.append(" PRIMARY KEY");
			}
			if (i != fields.length - 1) {
				bl.append(", ");
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

	public abstract String[] getFieldNames();


	public abstract String[] getSQLType();

	public abstract Function<T, Object> getValuesFunction(int i);

	@Override
	public boolean exists(T t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMultipleStatement() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean finishSaveAll() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean finishUpdateAll() {
		// TODO Auto-generated method stub
		return false;
	}

}
