package org.nanaki;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.nanaki.db.EpisodeManager;
import org.nanaki.db.EpisodePathManager;
import org.nanaki.db.SaisonManager;
import org.nanaki.db.SerieManager;
import org.nanaki.model.Episode;
import org.nanaki.model.Media;
import org.nanaki.model.MediaPath;
import org.nanaki.model.Saison;
import org.nanaki.model.Serie;

public class Main {

	public static void main(String[] args)
			throws Exception {

		Class.forName("org.sqlite.JDBC");
		Connection c = DriverManager
				.getConnection("jdbc:sqlite:test.db");
		ResultSet executeQuery = c.createStatement()
				.executeQuery("select * from Serie");
		print(executeQuery);
		executeQuery.close();
		executeQuery = c.createStatement()
				.executeQuery("select * from Saison");
		print(executeQuery);
		executeQuery.close();
		executeQuery = c.createStatement()
				.executeQuery("select * from Episode");
		print(executeQuery);
		executeQuery.close();
		SaisonManager saisonManager = new SaisonManager(c);
		SerieManager serieManager = new SerieManager(c);
		saisonManager.setSerieManager(serieManager);
		serieManager.dropTable();
		saisonManager.dropTable();
		serieManager.init();
		saisonManager.init();
		Saison saison = new Saison();
		saison.setNumero(1);
		Serie serie = new Serie();
		
		serie.setName("Game of throne");
		serieManager.save(serie);
//		serie = serieManager.getBy("name", "Game of throne").get(0);
		saison.setSerie(serie);
		saisonManager.save(saison);
		MediaPath m = new MediaPath();
		m.setIndex(0);
		Episode media = new Episode();
		media.setName("Coucou");
		media.setSaison(saison);
		media.setNumero(1);
		m.setMedia(media);
		m.setPath("test");
		EpisodeManager episodeManager = new EpisodeManager(c);
		episodeManager.setSaisonManager(saisonManager);
		EpisodePathManager episodePathManager = new EpisodePathManager(c);
		episodePathManager.setEpisodeManager(episodeManager);
		episodeManager.dropTable();
		episodeManager.init();
		episodePathManager.dropTable();
		episodePathManager.init();
		
		episodeManager.save(media);
		episodePathManager.save(m);
		
		m = episodePathManager.getBy("path", "test").get(0);
		c.close();

	}

	private static void print(ResultSet executeQuery)
			throws SQLException {
		int columnCount = executeQuery.getMetaData()
				.getColumnCount();
		while (executeQuery.next()) {
			for (int i = 0; i < columnCount; i++) {
				System.out.println(executeQuery
						.getMetaData().getColumnLabel(i + 1)
						+ " = "
						+ executeQuery.getObject(i + 1));
			}

		}
		executeQuery.close();

	}

}
