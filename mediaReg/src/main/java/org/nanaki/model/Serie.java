package org.nanaki.model;

import java.util.ArrayList;
import java.util.List;

public class Serie extends AbstractNomableAndIdentifiable<Integer>{
	private List<Saison> saisons = new ArrayList<>();
	public List<Saison> getSaisons() {
		return saisons;
	}
	public void setSaisons(List<Saison> saisons) {
		this.saisons = saisons;
	}
	
	
}
