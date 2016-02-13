package org.nanaki.db;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.nanaki.model.Film;

public class FilmManager extends SQLManager<Film> implements Manager<Film> {

	public static String[] FIELD_NAME = new String[] { "id", "name" };
	public static List<Function<Film, Object>> VALUES = Arrays.asList((f) -> f.getId(),(f) -> f.getName());
	// TODO Auto-generated method stub

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
	public List<Function<Film, Object>> getValuesFunction() {
		return VALUES;
	}

}
