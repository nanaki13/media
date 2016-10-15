package org.nanaki.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.nanaki.model.Saison;
import org.nanaki.model.Film;
import org.nanaki.model.Media;
import org.nanaki.model.Personne;
import org.nanaki.model.Role;

public class RoleManager extends SQLManager<Role> {

	private SQLManager<Film> managerFilm;
	private SQLManager<Personne> managerPersonne;

	public RoleManager(Connection connection)
			throws SQLException {
		super(connection);

		// TODO Auto-generated constructor stub
	}

	public static final String[] FIELD_NAME = new String[] {
			"id", "name" };
	public static final String[] FK_NAME = new String[] {
			"id_film", "id_personne" };

	private static final int[] IDS_INDEX = new int[] { 0, 2,
			3 };
	public static String[] SQL_TYPE = new String[] {
			"INTEGER", "VARCHAR(255) " };

	public static List<FunctionSetter<Role>> FK_SETTER = Arrays
			.asList((e, o) -> e.setMedia((Media) o),
					(e, o) -> e.setInterprete((Personne) o));
	public static List<Function<Role, Object>> FK_GETTER = Arrays
			.asList((e) -> e.getMedia().getId(),
					(e) -> e.getInterprete().getId());

	public static List<Function<Role, Object>> VALUES = Arrays
			.asList((f) -> f.getId(), (f) -> f.getName());
	public static List<FunctionSetter<Role>> SETTERS = Arrays
			.asList((f, o) -> f.setId((Integer) o),
					(f, o) -> f.setName((String) o));

	@Override
	public boolean exists(Role t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getTable() {
		return "Role";
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
	public Function<Role, Object> getGetterValuesFunction(
			int i) {
		return VALUES.get(i);
	}

	@Override
	public boolean isAutoIncrement() {
		return false;
	}

	@Override
	public Supplier<Role> getSupplier() {
		return Role::new;
	}

	@Override
	public FunctionSetter<Role> getSetterValuesFunction(
			int i) {
		return SETTERS.get(i);
	}

	@Override
	public String[] getFKName() {
		return FK_NAME;
	}

	@Override
	public FunctionSetter<Role> getFKSetter(int i) {
		return FK_SETTER.get(i);
	}

	@Override
	public Function<Role, Object> getGetterFKFunction(
			int i) {
		return FK_GETTER.get(i);
	}

	@Override
	public int[] getIdsIndex() {
		return IDS_INDEX;
	}

	@Override
	public SQLManager<?> getManager(int i) {
		if(i == 0){
			return managerFilm;
		}else{
			return managerPersonne;
		}
	}

	@Override
	public void link(Role t, Object o, String fkName) {
		// TODO Auto-generated method stub

	}

	public SQLManager<Film> getManagerFilm() {
		return managerFilm;
	}

	public void setManagerFilm(SQLManager<Film> managerFilm) {
		this.managerFilm = managerFilm;
	}

	public SQLManager<Personne> getManagerPersonne() {
		return managerPersonne;
	}

	public void setManagerPersonne(
			SQLManager<Personne> managerPersonne) {
		this.managerPersonne = managerPersonne;
	}
	

}
