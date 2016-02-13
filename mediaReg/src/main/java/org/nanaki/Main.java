package org.nanaki;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import org.nanaki.db.FilmManager;
import org.nanaki.model.Film;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, SQLException {
//		Film film = new Film();
//		File f1 = new File("/home/jonathan/Vidéos/data/Jupiter.avi");
//		File f2 = new File("/home/jonathan/Vidéos/data/arrow/s_04/Arrow.S04E01.FASTSUB.VOSTFR.HDTV.XviD-ZT.zone-telechargement.com.avi");
//		film.setPaths(Arrays.asList(f1.toPath(),f2.toPath()));
//		film.play();
		Film film = new Film();
		film.setId(0);
		film.setName("toto");
		Class.forName("org.sqlite.JDBC");
		Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db");
		try(FilmManager filmManager =new FilmManager(connection)){
			filmManager.createTable();
			filmManager.save(film);
			try(Statement s = filmManager.getConnection().createStatement()){
				ResultSet executeQuery = s.executeQuery("select * from film");
				print(executeQuery);
			}
		}
		
		

	}

	private static void print(ResultSet executeQuery) throws SQLException {
		ResultSetMetaData metaData = executeQuery.getMetaData();
		while (executeQuery.next()) {
			for(int i = 0 ; i < metaData.getColumnCount() ; i++){
				System.out.println(executeQuery.getObject(i+1));
			}
			
		}
		
	}

}
