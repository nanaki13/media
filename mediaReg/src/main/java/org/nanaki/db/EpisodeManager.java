package org.nanaki.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.nanaki.model.Episode;
import org.nanaki.model.Saison;
import org.nanaki.model.Serie;

public class EpisodeManager extends SQLManager<Episode>{

	

	public EpisodeManager(Connection connection)
			throws SQLException {
		super(connection);

		// TODO Auto-generated constructor stub
	}
	
	private SaisonManager saisonManager;

	


	public SaisonManager getSerieManager() {
		return saisonManager;
	}

	public void setSaisonManager(SaisonManager serieManager) {
		this.saisonManager = serieManager;
	}

	public static final String[] FIELD_NAME = new String[] {
			"id", "numero","name" };
	public static final String[] FK_NAME = new String[] {
			"id_serie" };

	private static final int[] IDS_INDEX = new int[]{0};

	public static final int ID_SERIE = 0;
	public static String[] SQL_TYPE = new String[] {
			"INTEGER", "INTEGER", "VARCHAR(255)" };

	public static List<FunctionSetter<Episode>> FK_SETTER = 
			Arrays.asList((sa,se)-> sa.setSaison((Saison) se));
	public static List<Function<Episode, Object>> FK_GETTER = 
			Arrays.asList((sa)-> sa.getSaison().getId());
	
	public static List<Function<Episode, Object>> VALUES = Arrays
			.asList((f) -> f.getId(),
					(f) -> f.getNumero(),
					(f) -> f.getName());
	public static List<FunctionSetter<Episode>> SETTERS = Arrays
			.asList((f, o) -> f.setId((Integer) o),
					(f, o) -> f.setNumero((Integer) o),
					(f, o) -> f.setName((String) o));

	
	@Override
	public boolean exists(Episode t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getTable() {
		return "Episode";
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
	public Function<Episode, Object> getGetterValuesFunction(
			int i) {
		return VALUES.get(i);
	}

	@Override
	public boolean isAutoIncrement() {
		return true;
	}

	@Override
	public Supplier<Episode> getSupplier() {
		return Episode::new;
	}

	@Override
	public FunctionSetter<Episode> getSetterValuesFunction(
			int i) {
		return SETTERS.get(i);
	}

	

	@Override
	public String[] getFKName() {
		return FK_NAME;
	}

	@Override
	public FunctionSetter<Episode> getFKSetter(int i) {
		return FK_SETTER.get(i);
	}

	

	@Override
	public Function<Episode, Object> getGetterFKFunction(
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
		return saisonManager;
	}

	@Override
	public void link(Episode t, Object o, String fkName) {
		((Saison) o).getEpisodes().add(t);
	}

}
