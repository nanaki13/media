package org.nanaki.model;

public class Episode extends AbstractMedia{
	private Saison saison;

	public Saison getSaison() {
		return saison;
	}

	public void setSaison(Saison saison) {
		this.saison = saison;
	}
	
}
