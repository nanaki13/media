package org.nanaki.model;

public class MediaPath {
	private String path;
	private Media media;
	private int index;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Media getMedia() {
		return media;
	}
	public void setMedia(Media media) {
		this.media = media;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String toString(){
		return path;
	}
	
	
}
