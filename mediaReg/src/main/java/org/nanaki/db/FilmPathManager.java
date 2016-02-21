package org.nanaki.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.nanaki.model.Film;
import org.nanaki.model.Media;
import org.nanaki.model.MediaPath;

public class FilmPathManager extends SQLManager<MediaPath>
		implements Manager<MediaPath> {

	private FilmManager filmManager;

	public FilmPathManager(Connection connection)
			throws SQLException {
		super(connection);

		// TODO Auto-generated constructor stub
	}

	public static final String[] FIELD_NAME = new String[] {
			"index_", "path" };
	public static final String[] FK_NAME = new String[] {
			"idFilm" };

	private static final int[] IDS_INDEX = new int[] { 0,
			2 };
	public static String[] SQL_TYPE = new String[] {
			"INTEGER", "VARCHAR(255) " };

	public static List<FunctionSetter<MediaPath>> FK_SETTER = Arrays
			.asList((f, o) -> f.setMedia((Media) o));
	public static List<Function<MediaPath, Object>> FK_GETTER = Arrays
			.asList((f) -> f.getMedia().getId());

	
	public static List<Function<MediaPath, Object>> VALUES = Arrays
			.asList((f) -> f.getIndex(),
					(f) -> f.getPath());
	public static List<FunctionSetter<MediaPath>> SETTERS = Arrays
			.asList((f, o) -> f.setIndex((Integer) o),
					(f, o) -> f.setPath((String) o));

	public FilmManager getFilmManager() {
		return filmManager;
	}

	public void setFilmManager(FilmManager filmManager) {
		this.filmManager = filmManager;
	}

	@Override
	public boolean exists(MediaPath t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getTable() {
		return "MediaPath";
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
	public Function<MediaPath, Object> getGetterValuesFunction(
			int i) {
		return VALUES.get(i);
	}

	@Override
	public boolean isAutoIncrement() {
		return false;
	}

	@Override
	public Supplier<MediaPath> getSupplier() {
		return MediaPath::new;
	}

	@Override
	public FunctionSetter<MediaPath> getSetterValuesFunction(
			int i) {
		return SETTERS.get(i);
	}

	@Override
	public MediaPath getById(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getFKName() {
		return FK_NAME;
	}

	@Override
	public FunctionSetter<MediaPath> getFKSetter(int i) {
		return FK_SETTER.get(i);
	}

	@Override
	public FilmManager getManager(int i) {
		return filmManager;
	}

	@Override
	public Function<MediaPath, Object> getGetterFKFunction(
			int i) {
		return FK_GETTER.get(i);
	}

	@Override
	public int[] getIdsIndex() {
		return IDS_INDEX;
	}

	@Override
	public void link(MediaPath t, Object o, String fkName) {
		if( o != null && t != null){
			o = ((Film) o).getPaths().add(t);
		}
	}

}
