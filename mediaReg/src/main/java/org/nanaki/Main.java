package org.nanaki;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.nanaki.db.FilmManager;
import org.nanaki.db.Manager;
import org.nanaki.db.PathManager;
import org.nanaki.model.Film;
import org.nanaki.model.MediaPath;

public class Main {

	public static void main(String[] args) throws Exception {
//		Film film = new Film();
//		File f1 = new File("/home/jonathan/Vidéos/data/Jupiter.avi");
//		File f2 = new File("/home/jonathan/Vidéos/data/arrow/s_04/Arrow.S04E01.FASTSUB.VOSTFR.HDTV.XviD-ZT.zone-telechargement.com.avi");
//		film.setPaths(Arrays.asList(f1.toPath(),f2.toPath()));
//		film.play();
		
		Class.forName("org.sqlite.JDBC");
	      Connection c = DriverManager.getConnection("jdbc:sqlite:test.db");
	      Manager<Film> fm = new FilmManager(c);
	      PathManager pm = new PathManager(c);
	      Film t = new Film();
//	      t.setId(2);
	      
	      t.setName("TOqsdqsdTA");
//		fm.update(t );
		fm.save(t);
		
	      MediaPath mp = new MediaPath();
	      mp.setIndex(0);
	      mp.setMedia(t);
	      
	      pm.save(mp);
	      
	      Film t = new Film();
//	      t.setId(2);
	      
	      t.setName("TOqsdqsdTA");
//		fm.update(t );
		fm.save(t);
//		fm.delete(t);
		List<Film> by = fm.getBy("name", "TOqsdqsdTA");
		List<Film> all = fm.getAll();
		System.out.println(all);
	      ResultSet executeQuery = c.createStatement().executeQuery("select * from FILM");
	      print(executeQuery);
	      c.close();

	}

	private static void print(ResultSet executeQuery) throws SQLException {
		int columnCount = executeQuery.getMetaData().getColumnCount();
		while (executeQuery.next()){
			for(int i = 0 ; i < columnCount  ; i++){
				 System.out.println(executeQuery.getMetaData().getColumnLabel(i+1)+" = " + executeQuery.getObject(i+1));
			}
			
		}
		
	}

}
