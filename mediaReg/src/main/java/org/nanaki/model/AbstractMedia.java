package org.nanaki.model;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMedia extends AbstractNomableAndIdentifiable<Integer> implements Media,Identifiable<Integer> {


	protected List<Personne> realisateurs;
	protected List<MediaPath> paths = new ArrayList<>();
	protected List<Personne> acteurs;
	public static String DEFAULT_LECTEUR = "vlc";
	

	@Override
	public List<Personne> getRealisateurs() {
		return realisateurs;
	}

	@Override
	public List<Personne> getActeurs() {
		return acteurs;
	}

	@Override
	public List<MediaPath> getPaths() {
		return paths;
	}


	public void setRealisateurs(List<Personne> realisateurs) {
		this.realisateurs = realisateurs;
	}

	public void setPaths(List<MediaPath> paths) {
		this.paths = paths;
	}

	public void setActeurs(List<Personne> acteurs) {
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
