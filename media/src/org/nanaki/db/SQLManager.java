package org.nanaki.db;

import java.sql.Connection;

public  abstract class SQLManager<T> implements Manager<T> {
	protected Connection connection;
	private String insert;
	public SQLManager() {
		
	}
	@Override
	public void save(T t) {
		insert = "INSERT INTO " + getTable()+"("+getParameters()+")"+ "VALUES "+"("+getValues(t)+")";
		
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
