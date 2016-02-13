package org.nanaki.db;

import java.nio.file.String;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.nanaki.model.MediaPath;


public class StringManager extends SQLManager<MediaPath> implements Manager<String> {


	public MediaPathManager(Connection connection) throws SQLException {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	public static String[] FIELD_NAME = new String[] { "idFilm", "name" };
	public static String[] FK_NAME = new String[] { "idFilm" };
	public static String[] FK_DEST = new String[] { "Film.id" };
	
	public static String[] SQL_TYPE = new String[] { "INTEGER", "VARCHAR(255) " };
	public static List<Function<MediaPath, Object>> VALUES = Arrays.asList((f) -> f.getId(), (f) -> f.getName());
	public static List<FunctionSetter<MediaPath>> SETTERS = Arrays.asList(
			(f,o) -> f.setId((Integer) o),
			(f,o) -> f.setName((String) o));

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
	public Function<MediaPath, Object> getGetterValuesFunction(int i) {
		return VALUES.get(i);
	}

	@Override
	public boolean isAutoIncrement() {
		return true;
	}

	@Override
	public Supplier<MediaPath> getSupplier() {
		return MediaPath::new;
	}

	@Override
	public FunctionSetter<MediaPath> getSetterValuesFunction(int i) {
		return SETTERS.get(i);
	}



	
	

}
