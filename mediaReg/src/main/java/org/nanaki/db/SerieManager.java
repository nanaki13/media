package org.nanaki.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.nanaki.model.Saison;
import org.nanaki.model.Serie;

public class SerieManager extends SQLManager<Serie> {

	

	public SerieManager(Connection connection)
			throws SQLException {
		super(connection);

		// TODO Auto-generated constructor stub
	}
	
	private SaisonManager saisonManager;

	public static final String[] FIELD_NAME = new String[] {
			"id", "name" };
	public static final String[] FK_NAME = null;

	private static final int[] IDS_INDEX =null;
	public static String[] SQL_TYPE = new String[] {
			"INTEGER", "VARCHAR(255) " };

	public static List<FunctionSetter<Serie>> FK_SETTER = null;
	public static List<Function<Serie, Object>> FK_GETTER = null;

	
	public static List<Function<Serie, Object>> VALUES = Arrays
			.asList((f) -> f.getId(),
					(f) -> f.getName());
	public static List<FunctionSetter<Serie>> SETTERS = Arrays
			.asList((f, o) -> f.setId((Integer) o),
					(f, o) -> f.setName((String) o));

	
	@Override
	public boolean exists(Serie t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getTable() {
		return "Serie";
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
	public Function<Serie, Object> getGetterValuesFunction(
			int i) {
		return VALUES.get(i);
	}

	@Override
	public boolean isAutoIncrement() {
		return true;
	}

	@Override
	public Supplier<Serie> getSupplier() {
		return Serie::new;
	}

	@Override
	public FunctionSetter<Serie> getSetterValuesFunction(
			int i) {
		return SETTERS.get(i);
	}


	@Override
	public String[] getFKName() {
		return FK_NAME;
	}

	@Override
	public FunctionSetter<Serie> getFKSetter(int i) {
		return FK_SETTER.get(i);
	}

	

	@Override
	public Function<Serie, Object> getGetterFKFunction(
			int i) {
		return FK_GETTER.get(i);
	}

	@Override
	public int[] getIdsIndex() {
		return IDS_INDEX;
	}

	@Override
	public SQLManager<?> getManager(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void link(Serie t, Object o, String fkName) {
		// TODO Auto-generated method stub
		
	}
	
	public void fillSaison(Serie e) throws SQLException{
		List<Saison> by = saisonManager.getBy(saisonManager.getFKName()[SaisonManager.ID_SERIE], e.getId());
		e.setSaisons(by);
	}

}
