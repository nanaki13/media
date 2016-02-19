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
import org.nanaki.db.Pair;
import org.nanaki.db.PathManager;
import org.nanaki.db.PersonManager;
import org.nanaki.db.Relation;
import org.nanaki.db.SQLManager;
import org.nanaki.db.SerieManager;
import org.nanaki.model.Acteur;
import org.nanaki.model.Film;
import org.nanaki.model.MediaPath;
import org.nanaki.model.Personne;
import org.nanaki.model.Serie;

public class Main {

	public static void main(String[] args) throws Exception {
//		Film film = new Film();
//		File f1 = new File("/home/jonathan/Vidéos/data/Jupiter.avi");
//		File f2 = new File("/home/jonathan/Vidéos/data/arrow/s_04/Arrow.S04E01.FASTSUB.VOSTFR.HDTV.XviD-ZT.zone-telechargement.com.avi");
//		film.setPaths(Arrays.asList(f1.toPath(),f2.toPath()));
//		film.play();
		
		Class.forName("org.sqlite.JDBC");
	      Connection c = DriverManager.getConnection("jdbc:sqlite:test.db");
	      ResultSet executeQuery = c.createStatement().executeQuery("select * from Personne");
	      print(executeQuery);
	      executeQuery.close();
	      FilmManager fm = new FilmManager(c);
//	      PathManager pm = new PathManager(c);
	     
//	      fm.dropTable();
//	    pm.dropTable();
	      PersonManager manager = new PersonManager(c);
	      manager.init();
	      Acteur p = new Acteur();
	      p.setNom("jonathan");
	      p.setPrenom("bonnet");
	      manager.save(p);
	      manager.setSupplier(Acteur::new);
	      p = manager.getById(1);
	      fm.init();
//	      pm.setFilmManager(fm);
//	      pm.init();
//	      fm.dropTable();
//	      pm.dropTable();
//	      fm.createTable();
//	      pm.createTable();
	      Film t = new Film();
	      t.getActeurs();
//	      t.setId(1);
//	      
	      t.setName("TOqsqsxqdqsdTA");
//		fm.update(t );
		fm.save(t);
		t = fm.getById(1);
		System.out.println(t);
		manager.close();
		fm.close();
		Relation relation = new Relation(Arrays.asList(manager,fm));

//		relation.drop(c);
		relation.create(c);
//		relation.prepareInsert();
//		relation.addToInsert(p,t);
//		relation.doInsert();
		List<Personne> all = relation.getAll(t, fm, manager);
		System.out.println(all);
		manager.filmManager = fm;
		manager.acteurFilm = relation;
		manager.setSupplier(Acteur::new);
		manager.fillFilm(p);
		System.out.println(p);
//	      MediaPath mp = new MediaPath();
//	      mp.setIndex(0);
//	      mp.setMedia(byId);
//	      mp.setPath("/FQQF/QF");
//	      pm.update(mp);
//	      mp = pm.getBy("idFilm", 1).get(0);
//	      System.out.println(mp);
//	      System.out.println(mp.getMedia());
//	      System.out.println(mp.getMedia().getPaths().get(0));
	      
//	      SerieManager manager = new SerieManager(c);
//	      manager.init();
//	      manager.dropTable();
//	      manager.createTable();
//	      Serie s = new Serie();
//	      s.setName("coucaou");
//	      manager.save(s);
//	      List<Serie> by = manager.getBy("name", "coucaou");
//	      System.out.println(by);
//	      Film t = new Film();
//	      t.setId(2);
	      
//	      t.setName("TOqsdqsdTA");
//		fm.update(t );
//		fm.save(t);
//		fm.delete(t);
//		List<Film> by = fm.getBy("name", "TOqsdqsdTA");
//		List<Film> all = fm.getAll();
//		System.out.println(all);
//	      ResultSet executeQuery = c.createStatement().executeQuery("select * from MediaPath");
//	      print(executeQuery);
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
