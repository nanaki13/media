package org.nanaki.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import org.nanaki.model.Film;
import org.nanaki.model.Personne;

public class Relation {


	private List<SQLManager<?>> managers;
	private Table table;
	private PreparedStatement prepareStatement;
	private Connection connection;

	public Relation(List<SQLManager<?>> managers) {
		super();
		this.managers = managers;
		connection = managers.get(0).connection;
		StringBuilder tableName = new StringBuilder();
		int j = 0;
		SQLCol[] sqlCols = new SQLCol[countIds()];
		SQLCol sqlCol;
		for (SQLManager<?> m : managers) {
			int[] idsIndex = m.getIdsIndex();
			tableName.append(m.getTable());
			sqlCol = new SQLCol();
			for (int i = 0; i < idsIndex.length; i++) {
				
				sqlCol.setName(
						m.getAllFields()[idsIndex[i]]+"_"+m.getTable());
				sqlCol.setType(
						m.getAllTypes()[idsIndex[i]]);
				sqlCol.setFk(true);
				sqlCol.setTableFk(m.getTable());
				sqlCol.setTableColumnFk(m.getAllFields()[idsIndex[i]]);
				sqlCol.setIsId(true);
				sqlCols[j] = sqlCol;
			}
			if (j != managers.size() - 1) {
				tableName.append("_");
			}
			j++;
		}
		table = new Table(tableName.toString(), sqlCols);

	}

	public void create(Connection con) throws SQLException {

		try (Statement createStatement = con
				.createStatement()) {
			createStatement.execute(table.create());
		}
	}

	public void drop(Connection con) throws SQLException {

		try (Statement createStatement = con
				.createStatement()) {
			createStatement.execute(table.drop());
		}
	}

	private int countIds() {
		int nb = 0;
		for (SQLManager<?> le : managers) {
			nb += le.getIdsIndex().length;
		}
		return nb;
	}

	public List<SQLManager<?>> getManagers() {
		return managers;
	}

	public void setManagers(List<SQLManager<?>> managers) {
		this.managers = managers;
	}
	
	public void prepareInsert() throws SQLException{
		prepareStatement = connection.prepareStatement(table.insert());
	}

	public void addToInsert(Object... relationElement) throws SQLException {
//		Object[] value = new Object[managers.size()];
			int count = 1;
			for (int i = 0; i < relationElement.length; i++) {
				Object object = relationElement[i];
				SQLManager<?> sqlManager = managers
				.get(i);
				for(int j = 0 ; j < sqlManager.getIdsIndex().length ;  j++){
					Object value = ((Function<Object, Object>) sqlManager.getGetterValuesFunction(sqlManager.getIdsIndex()[j]))
									.apply(object);
					prepareStatement.setObject(count, value);
				}
				
				
				count++;
							
			}
		prepareStatement.addBatch();
		
		

	}
	public void doInsert() throws SQLException{
		prepareStatement.executeBatch();
	}

	public void getAll(Object t, SQLManager<?> tManager,
			SQLManager<?> distantManager) {
		int[] ids =  tManager.idsIndex;
		prepareStatement = connection.prepareStatement(select(tManager));l
		for(int id : ids){
			Function<Object, Object> function = (Function<Object, Object>) tManager.getGetterValuesFunction(id);
			Object idObject = function.apply(t);
			prepareStatement.setObject(objectIndice, idObject);
		}
		
	}

}
