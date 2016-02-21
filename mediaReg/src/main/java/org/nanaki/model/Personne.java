package org.nanaki.model;

import java.util.List;

public class Personne extends AbstractIndentifiable<Integer> {

	protected String nom;
	protected String prenom;
	private List<? extends Media> mediasActeur;
	private List<? extends Media> mediasRealisateur;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public boolean isActeur(){
		return mediasActeur != null && !mediasActeur.isEmpty();
	}public boolean isRealisateur(){
		return mediasRealisateur != null && !mediasRealisateur.isEmpty();
	}
	public List<? extends Media> getMediasActeur() {
		return mediasActeur;
	}
	public void setMediasActeur(List<? extends Media> mediasActeur) {
		this.mediasActeur = mediasActeur;
	}
	public List<? extends Media> getMediasRealisateur() {
		return mediasRealisateur;
	}
	public void setMediasRealisateur(
			List<Media> mediasRealisateur) {
		this.mediasRealisateur = mediasRealisateur;
	}
	
	
	
}
