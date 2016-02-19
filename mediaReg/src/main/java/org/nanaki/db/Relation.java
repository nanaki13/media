package org.nanaki.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Relation {


	private List<SQLManager<?>> managers;
	private Table table;
	private PreparedStatement prepareStatement;
	private Connection connection;
	private ManagerColumns[] managerColumnss;
	private static class ManagerColumns{
		SQLManager<?> manager;
		SQLCol[] sqlCols;
	}
	public Relation(List<SQLManager<?>> managers) {
		super();
		this.managers = managers;
		connection = managers.get(0).connection;
		StringBuilder tableName = new StringBuilder();
		int j = 0;
		SQLCol[] sqlCols = new SQLCol[countIds()];
		managerColumnss = new ManagerColumns[managers.size()];
		SQLCol sqlCol;
		for (SQLManager<?> m : managers) {
			int[] idsIndex = m.getIdsIndex();
			ManagerColumns managerColumns = new ManagerColumns();
			managerColumnss[j] = managerColumns;
			tableName.append(m.getTable());
			sqlCol = new SQLCol();
			managerColumns.manager = m;
			managerColumns.sqlCols = new SQLCol[idsIndex.length];
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
				managerColumns.sqlCols[i] = sqlCol;
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

	public <T> List<T> getAll(Object t, SQLManager<?> tManager,
			SQLManager<T> distantManager) throws SQLException {
		int[] ids =  tManager.idsIndex;
		prepareStatement = connection.prepareStatement(select(tManager));
		for(int i =0 ; i  < ids.length ; i++ ){
			int id = ids[i];
			Function<Object, Object> function = (Function<Object, Object>) tManager.getGetterValuesFunction(id);
			Object idObject = function.apply(t);
			prepareStatement.setObject(i + 1, idObject);
		}
		ResultSet executeQuery = prepareStatement.executeQuery();
		List<Object> idsDistant = new ArrayList<>();
		while(executeQuery.next()){
			
				SQLCol[] column = getColumn(distantManager);
				if(column.length == 1){
					Object object = executeQuery.getObject(column[0].getName());
					idsDistant.add( object);
				}	
		}
		List<T> all = distantManager.getAll(idsDistant);
		return all;
		
		
	}

	private String select(SQLManager<?> tManager) {
		SQLCol[] cols = getColumn(tManager);
		return table.selectWherePrepared(cols);
	}

	private SQLCol[] getColumn(SQLManager<?> tManager) {
		for(ManagerColumns columns : managerColumnss){
			if(tManager.equals(columns.manager)){
				return columns.sqlCols;
			}
		}
		return null;
	}

}
