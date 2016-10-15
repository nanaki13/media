package org.nanaki.model;

public class Role extends AbstractNomableAndIdentifiable<Integer> {
	private Media media;
	private Personne interprete;
	public Media getMedia() {
		return media;
	}
	public void setMedia(Media media) {
		this.media = media;
	}
	public Personne getInterprete() {
		return interprete;
	}
	public void setInterprete(Personne interprete) {
		this.interprete = interprete;
	}
	
}
