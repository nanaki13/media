package org.nanaki.db;

import org.nanaki.model.Film;

public class FilmManager extends SQLManager<Film> implements Manager<Film> {

	@Override
	public void update(Film t) {
		// TODO Auto-generated method stub
		
	}

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
	public String getParameters() {
		
		return "id, name";
	}

	@Override
	public String getValues(Film t) {
		return t.getId()+", "+t.getName();
	}

	

}
