package org.nanaki.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

import org.nanaki.util.Strings;

public abstract class SQLManager<T> implements Manager<T> {
	protected Connection connection;
	protected Object[] values;
	private PreparedStatement preparedStatementInsert;
	private PreparedStatement preparedStatementUpdate;

	public SQLManager(Connection connection) throws SQLException {
		preparedStatementInsert = connection.prepareStatement(makeInsert());
		preparedStatementUpdate = connection.prepareStatement(makeUpdate());
	}

	@Override
	public void save(T t) throws SQLException {
		for (int i = 0; i < getFieldNames().length; i++) {
			preparedStatementInsert.setObject(i, getValuesFunction(i).apply(t));
		}
		preparedStatementInsert.executeUpdate();
	}

	private String makeInsert() {
		return "INSERT INTO " + getTable() + " \n(" + Strings.implode(getFieldNames(), ", ") + ")" + "VALUES " + "("
				+ Strings.implode("?", ", ", getFieldNames().length) + ")";
	}

	private String makeUpdate() {
		return "UPDATE " + getTable() + " SET " + preparedUpdateField() + " WHERE " + getFieldNames()[getIdIndex()]+" = ?";
	}
	
	private String makeCreate() {
		StringBuilder bl = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
		bl.append(getTable()).append(" (");
		getFieldNames();
		for(int i = 0 ; i < getFieldNames().length ; i++){
			bl.append("")
		}
	}

	private String preparedUpdateField() {
		String[] fieldNames = getFieldNames();
		StringBuilder bl = new StringBuilder();
		for(int i = 0 ; i < fieldNames.length ; i++ ){
			bl.append(fieldNames[i]).append(" = ?");
			if(i != fieldNames.length - 1 ){
				bl.append(", ");
			}
		}
		return bl.toString();
	}

	public abstract String getTable();

	public abstract int getIdIndex();

	public abstract String[] getFieldNames();

	public abstract String[] getValues(T t);

	public abstract Function<T, Object> getValuesFunction(int i);

	@Override
	public void update(T t) throws SQLException {
		for (int i = 0; i < getFieldNames().length; i++) {
			
			preparedStatementUpdate.setObject(i, getValuesFunction(i).apply(t));
		}
		preparedStatementUpdate.executeUpdate();

	}

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
