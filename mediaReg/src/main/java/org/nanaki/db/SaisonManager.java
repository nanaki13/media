package org.nanaki.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.nanaki.model.Saison;
import org.nanaki.model.Serie;

public class SaisonManager extends SQLManager<Saison>{

	

	public SaisonManager(Connection connection)
			throws SQLException {
		super(connection);

		// TODO Auto-generated constructor stub
	}
	
	private SerieManager serieManager;

	


	public SerieManager getSerieManager() {
		return serieManager;
	}

	public void setSerieManager(SerieManager serieManager) {
		this.serieManager = serieManager;
	}
	public static final String TABLE = "Saison";
	public static final String[] FIELD_NAME = new String[] {
			"id", "numero" };
	public static final String[] FK_NAME = new String[] {
			"id_serie" };

	private static final int[] IDS_INDEX = new int[]{0};

	public static final int ID_SERIE = 0;
	public static String[] SQL_TYPE = new String[] {
			"INTEGER", "INTEGER" };

	public static List<FunctionSetter<Saison>> FK_SETTER = 
			Arrays.asList((sa,se)-> sa.setSerie((Serie) se));
	public static List<Function<Saison, Object>> FK_GETTER = 
			Arrays.asList((sa)-> sa.getSerie().getId());
	
	public static List<Function<Saison, Object>> VALUES = Arrays
			.asList((f) -> f.getId(),
					(f) -> f.getNumero());
	public static List<FunctionSetter<Saison>> SETTERS = Arrays
			.asList((f, o) -> f.setId((Integer) o),
					(f, o) -> f.setNumero((Integer) o));

	
	@Override
	public boolean exists(Saison t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getTable() {
		return TABLE;
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
	public Function<Saison, Object> getGetterValuesFunction(
			int i) {
		return VALUES.get(i);
	}

	@Override
	public boolean isAutoIncrement() {
		return true;
	}

	@Override
	public Supplier<Saison> getSupplier() {
		return Saison::new;
	}

	@Override
	public FunctionSetter<Saison> getSetterValuesFunction(
			int i) {
		return SETTERS.get(i);
	}

	

	@Override
	public String[] getFKName() {
		return FK_NAME;
	}

	@Override
	public FunctionSetter<Saison> getFKSetter(int i) {
		return FK_SETTER.get(i);
	}

	

	@Override
	public Function<Saison, Object> getGetterFKFunction(
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
		return serieManager;
	}

	@Override
	public void link(Saison t, Object o, String fkName) {
		((Serie) o).getSaisons().add(t);
		
	}

}
