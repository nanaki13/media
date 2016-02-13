package org.nanaki.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.nanaki.model.Film;

public class FilmManager extends SQLManager<Film> implements Manager<Film> {

	public FilmManager(Connection connection) throws SQLException {
		super(connection);
		
	}



	private static final int fieldCount = 2;
	private static final String[] FIELDS_NAME = new String[]{"id","name"};
	private static final String[] SQL_TYPES = new String[]{"INT","VARCHAR(255)"};
	

	

	@Override
	public String getTable() {
		return "FILM";
	}

	@Override
	public String[] getFieldsName() {	
		return FIELDS_NAME;
	}
	
	@Override
	public String[] getSqlTypes() {
		return SQL_TYPES;
	}

	@Override
	public Object[] getValues(Film t) {
		return new Object[]{t.getId(),t.getName()};
	}

	@Override
	public boolean isMultipleStatement() {
		return false;
	}

	@Override
	public boolean finishSaveAll() {
		return false;
	}

	@Override
	public boolean finishUpdateAll() {
		return false;
	}

	@Override
	public int getFieldCount() {
		return fieldCount;
	}

	

	@Override
	public int getIdInex() {
		return 0;
	}

	

	

	

}
