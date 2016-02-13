package org.nanaki.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.nanaki.model.Film;

public class FilmManager extends SQLManager<Film> implements Manager<Film> {

	public FilmManager(Connection connection) throws SQLException {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	public static String[] FIELD_NAME = new String[] { "id", "name" };
	public static String[] SQL_TYPE = new String[] { "INT", "VARCHAR(255) " };
	public static List<Function<Film, Object>> VALUES = Arrays.asList((f) -> f.getId(),(f) -> f.getName());

	@Override
	public boolean exists(Film t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getTable() {
		return "FILM";
	}

	@Override
	public String[] getFieldNames() {
		return FIELD_NAME;
	}

	@Override
	public int getIdIndex() {
		return 0;
	}
	@Override
	public String[] getSQLType() {
		return SQL_TYPE;
	}

	@Override
	public Function<Film, Object> getValuesFunction(int i) {
		return VALUES.get(i);
	}

	

	

}
