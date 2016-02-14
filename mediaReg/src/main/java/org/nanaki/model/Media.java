package org.nanaki.model;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface Media extends Nomable,Identifiable<Integer> {

	public List<Realisateur> getRealisateurs();
	public List<Acteur> getActeurs();
	public List<Path> getPaths();
	public String getLecteurCommande();
	default public void play() throws IOException, InterruptedException{
		for(Path p : getPaths()){
			ProcessBuilder processBuilder = new ProcessBuilder(getLecteurCommande(), p.toString());
			Process start = processBuilder.start();
			start.waitFor();
			
		}
	}
}
