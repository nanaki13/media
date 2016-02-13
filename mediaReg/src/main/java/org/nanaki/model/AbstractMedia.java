package org.nanaki.model;

import java.nio.file.Path;
import java.util.List;

public abstract class AbstractMedia extends AbstractNomableAndIdentifiable<Integer> implements Media,Identifiable<Integer> {


	protected List<Realisateur> realisateurs;
	protected List<Path> paths;
	protected List<Acteur> acteurs;
	public static String DEFAULT_LECTEUR = "vlc";
	

	@Override
	public List<Realisateur> getRealisateurs() {
		return realisateurs;
	}

	@Override
	public List<Acteur> getActeurs() {
		return acteurs;
	}

	@Override
	public List<Path> getPaths() {
		return paths;
	}


	public void setRealisateurs(List<Realisateur> realisateurs) {
		this.realisateurs = realisateurs;
	}

	public void setPaths(List<Path> paths) {
		this.paths = paths;
	}

	public void setActeurs(List<Acteur> acteurs) {
		this.acteurs = acteurs;
	}

	@Override
	public String getLecteurCommande() {
		return DEFAULT_LECTEUR;
	}
	

}
