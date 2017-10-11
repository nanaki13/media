package org.jbonnet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.jbonnet.orm.Filler;
import org.jbonnet.orm.SqlLiteTypeMapping;
import org.jbonnet.orm.TypeMapping;
import org.jbonnet.stream.StreamUtils;

public class DbConnection {

	private Connection connection;
	private Filler filler;

	/**
	 * @throws IOException 
	 * @throws SQLException 
	 * 
	 */
	public DbConnection() throws IOException, SQLException {
		super();
		StreamUtils.inputStreamToFile(DbConnection.class.getResourceAsStream("/db"), "./db");
		connection = DriverManager.getConnection("jdbc:sqlite:./db");
		filler = new Filler(connection);
		
		
	}
	
	

	public List<Fish> getAllFish() throws SQLException{
		return filler.getResultList(Fish.class);
	}
	
	public List<Water> getAllWaters() throws SQLException{
		return filler.getResultList(Water.class);
	}
	
	public List<Pecheur> getAllPecheur() throws SQLException{
		return filler.getResultList(Pecheur.class);
	}


	
	
	
	
	
}
