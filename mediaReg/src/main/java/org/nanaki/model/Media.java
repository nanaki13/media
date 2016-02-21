package org.nanaki.model;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface Media extends Nomable,Identifiable<Integer> {

	public List<Personne> getRealisateurs();
	public List<Personne> getActeurs();
	public List<MediaPath> getPaths();
	public String getLecteurCommande();
	default public void play() throws IOException, InterruptedException{
		for(MediaPath p : getPaths()){
			ProcessBuilder processBuilder = new ProcessBuilder(getLecteurCommande(), p.toString());
			Process start = processBuilder.start();
			start.waitFor();
			
		}
	}
}
