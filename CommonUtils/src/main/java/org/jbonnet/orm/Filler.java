package org.jbonnet.orm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.jbonnet.bean.ObjectIOInterface;

public class Filler {
	private Connection connection;
	
	private String sqlBase;
	private String sqlCondition = "";
	private PreparedStatement preparedStatement;
	
	
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
	
	

	
}
