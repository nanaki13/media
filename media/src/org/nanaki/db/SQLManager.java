package org.nanaki.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.nanaki.util.Strings;

public  abstract class SQLManager<T> implements Manager<T> {
	protected Connection connection;
	private PreparedStatement preparedStatementInsert;
	private PreparedStatement preparedStatementUpdate;
	public SQLManager() {
		
	}
	@Override
	public void save(T t) throws SQLException {
		preparedStatementInsert = connection.prepareStatement(makeInsert(t));
		
		
	}
	private String makeInsert(T t) {
		
		return "INSERT INTO " + getTable()+"("+Strings.implode(getFieldNames(),", ")+")"+ "VALUES "+"("+Strings.implode(getValues(t),", ")+")";
	}
	public abstract String getTable();
	public abstract String[] getFieldNames();
	public abstract String[] getValues(T t);
	@Override
	public void update(T t) {
		// TODO Auto-generated method stub
		
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
