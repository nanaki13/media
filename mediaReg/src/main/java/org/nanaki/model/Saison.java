package org.nanaki.model;

import java.util.ArrayList;
import java.util.List;

public class Saison extends AbstractIndentifiable<Integer>{
	private List<Episode> episodes = new ArrayList<>();
	private Serie serie;
	private int numero;
	public List<Episode> getEpisodes() {
		return episodes;
	}
	public void setEpisodes(List<Episode> episodes) {
		this.episodes = episodes;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Serie getSerie() {
		return serie;
	}
	public void setSerie(Serie serie) {
		this.serie = serie;
	}
	
	
	
}
