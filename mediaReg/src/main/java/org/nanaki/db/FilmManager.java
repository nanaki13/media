package org.nanaki.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.nanaki.model.Film;

public class FilmManager extends SQLManager<Film> implements Manager<Film> {

	public FilmManager(Connection connection) throws SQLException {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	public static String[] FIELD_NAME = new String[] { "id", "name" };
	
	public static String[] SQL_TYPE = new String[] { "INTEGER", "VARCHAR(255) " };
	public static List<Function<Film, Object>> VALUES = Arrays.asList((f) -> f.getId(), (f) -> f.getName());
	public static List<FunctionSetter<Film>> SETTERS = Arrays.asList(
			(f,o) -> f.setId((Integer) o),
			(f,o) -> f.setName((String) o));

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
	public Function<Film, Object> getGetterValuesFunction(int i) {
		return VALUES.get(i);
	}

	@Override
	public boolean isAutoIncrement() {
		return true;
	}

	@Override
	public Supplier<Film> getSupplier() {
		return Film::new;
	}

	@Override
	public FunctionSetter<Film> getSetterValuesFunction(int i) {
		return SETTERS.get(i);
	}

	@Override
	public String[] getFKName() {
		return null;
	}


	
	@Override
	public FunctionSetter<Film> getFKSetter(int i) {
		return null;
	}

	@Override
	public SQLManager<?> getManager(int i) {
		return null;
	}



}
