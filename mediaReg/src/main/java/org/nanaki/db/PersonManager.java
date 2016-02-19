package org.nanaki.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.nanaki.model.Acteur;
import org.nanaki.model.Film;
import org.nanaki.model.Personne;
import org.nanaki.model.Personne;

public class PersonManager extends SQLManager<Personne> {

	public PersonManager(Connection connection)
			throws SQLException {
		super(connection);
		// TODO Auto-generated constructor stub
	}
	
	public Relation acteurFilm;
	public FilmManager filmManager;

	public static String[] FIELD_NAME = new String[] { 
			"id",
			"nom", 
			"prenom" };

	public static String[] SQL_TYPE = new String[] {
			"INTEGER", 
			"VARCHAR(255)",
			"VARCHAR(255)" };
	public static List<Function<Personne, Object>> VALUES = Arrays
			.asList((f) -> f.getId(),
					(f) -> f.getNom(),
					(f) -> f.getPrenom());
	public static List<FunctionSetter<Personne>> SETTERS = Arrays
			.asList((f, o) -> f.setId((Integer) o),
					(f, o) -> f.setNom((String) o),
					(f, o) -> f.setPrenom((String) o));

	@Override
	public String getTable() {
		return "Personne";
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
	public Function<Personne, Object> getGetterValuesFunction(
			int i) {
		return VALUES.get(i);
	}

	@Override
	public boolean isAutoIncrement() {
		return true;
	}

	@Override
	public Supplier<Personne> getSupplier() {
		return Personne::new;
	}

	@Override
	public FunctionSetter<Personne> getSetterValuesFunction(
			int i) {
		return SETTERS.get(i);
	}

	@Override
	public String[] getFKName() {
		return null;
	}

	@Override
	public FunctionSetter<Personne> getFKSetter(int i) {
		return null;
	}

	@Override
	public SQLManager<?> getManager(int i) {
		return null;
	}

	@Override
	public Function<Personne, Object> getGetterFKFunction(
			int i) {
		return null;
	}

	@Override
	public int[] getIdsIndex() {
		return new int[] { 0 };
	}

	@Override
	public void link(Personne t, Object o, String fkName) {
		// TODO Auto-generated method stub

	}
	
	public void fillFilm(Acteur a) throws SQLException{
		List<Film> all = acteurFilm.getAll(a, this, filmManager);
		a.setFilm(all);
	}

	

}
