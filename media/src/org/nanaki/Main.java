package org.nanaki;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.nanaki.model.Film;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		Film film = new Film();
		File f1 = new File("/home/jonathan/Vidéos/data/Jupiter.avi");
		File f2 = new File("/home/jonathan/Vidéos/data/arrow/s_04/Arrow.S04E01.FASTSUB.VOSTFR.HDTV.XviD-ZT.zone-telechargement.com.avi");
		film.setPaths(Arrays.asList(f1.toPath(),f2.toPath()));
		film.play();

	}

}
