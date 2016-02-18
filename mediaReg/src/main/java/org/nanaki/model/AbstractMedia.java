package org.nanaki.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMedia extends AbstractNomableAndIdentifiable<Integer> implements Media,Identifiable<Integer> {


	protected List<Realisateur> realisateurs;
	protected List<MediaPath> paths = new ArrayList<>();
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
	public List<MediaPath> getPaths() {
		return paths;
	}


	public void setRealisateurs(List<Realisateur> realisateurs) {
		this.realisateurs = realisateurs;
	}

	public void setPaths(List<MediaPath> paths) {
		this.paths = paths;
	}

	public void setActeurs(List<Acteur> acteurs) {
		this.acteurs = acteurs;
	}

	@Override
	public String getLecteurCommande() {
		return DEFAULT_LECTEUR;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName()+" [realisateurs=" + realisateurs + ", paths=" + paths + ", acteurs=" + acteurs + ", name=" + name
				+ ", id=" + id + "]";
	}
	

}
