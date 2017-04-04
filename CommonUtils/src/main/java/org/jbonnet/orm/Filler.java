package org.jbonnet.orm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.jbonnet.bean.ObjectIOInterface;

public class Filler {
	private Connection connection;
	
	private String sqlBase;
	private String sqlCondition = "";
	private PreparedStatement preparedStatement;
	
	private TypeMapping mapping;
	
	
	public Filler(Connection connection) {
		this.connection = connection;
	}

	private void initSatement() throws SQLException{
		preparedStatement = connection.prepareStatement(sqlBase+sqlCondition);
	}

	/**
	 * @return the preparedStatement
	 */
	public PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}
	
	public<T> List<T> getResultList(Class<T> clazz,Supplier<T> newT) throws SQLException{
		ObjectIOInterface instance = ObjectIOInterface.Factory.getInstance(clazz);
		prepareQueryBase(instance);
		initSatement();
		ResultSet executeQuery = preparedStatement.executeQuery();
		List<T> ret = new ArrayList<>();
		while(executeQuery.next()){
			
			T t = newT.get();
			for(String s : instance.getFields()){
				instance.setTo(s, t, executeQuery.getObject(s));
			}
			ret.add(t);
			
		}
		preparedStatement.close();
		return ret;
	}

	private void prepareQueryBase(ObjectIOInterface instance) {
		sqlBase = instance.getFields().stream().collect(Collectors.joining(", ", "SELECT ", " FROM "+instance.getClassName()));
		System.out.println(sqlBase);
	}
	
	public boolean entityTableExists(Class<?> instance) throws SQLException{
		try(ResultSet tables = connection.getMetaData().getTables(null, null, instance.getSimpleName(), null);){
			return tables.next();

		}
		
	}

	public void createentityTable(Class<?> class1, TypeMapping typeMapping) throws SQLException {
		createEntityTable(class1, typeMapping,(String[]) null);
		
	}

	public void createEntityTable(Class<?> class1, TypeMapping typeMapping, String ... ids) throws SQLException {
		StringBuilder  builder = new StringBuilder();
		ObjectIOInterface instance = ObjectIOInterface.Factory.getInstance(class1);
		builder.append("CREATE TABLE ").append(instance.getClassName()).append("( \n\t");
		Iterator<String> iterator = instance.getFields().iterator();
		while (iterator.hasNext()) {
			String next = iterator.next();
			Class<?> type = instance.getType(next);
			String apply = typeMapping.apply(instance.getType(next));
			if(apply == null){
				throw new SQLException("no type found for field : "+next+" class : "+type);
			}
			builder.append(next).append(" ").append(typeMapping.apply(type));
			if(iterator.hasNext()){
				builder.append(",\n\t");
			}
		}
		if(ids!=null){
			builder.append(
					Arrays.asList(ids).stream()
					.collect(Collectors.joining(",", ",\n\tPRIMARY KEY (", ")\n")));
		}
		builder.append(");");
		try(Statement s = connection.createStatement(); ){
			s.execute(builder.toString());
		}
		
	}
	
	

	
}
