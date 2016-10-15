package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
@Entity
@Table(name="media_file")
public class MediaFilePath implements Serializable{
	private MediaFilePathPK id;
	private String path;
	private Media media;
	
	
	public MediaFilePath(Media media, int number) {
		super();
		this.media = media;
		id = new MediaFilePathPK(media, number);
	}
	public MediaFilePath() {
		super();
		this.media = media;
	}

	/**
	 * @return the id
	 */
	@EmbeddedId
	public MediaFilePathPK getId() {
		return id;
	}
	
	@ManyToOne
	@MapsId("media_id")
	public Media getMedia(){
		return media;
	}
	
	/**
	 * @param media the media to set
	 */
	public void setMedia(Media media) {
		this.media = media;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(MediaFilePathPK id) {
		this.id = id;
	}
	/**
	 * @return the path
	 */
	@Column(name="path")
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MediaFilePath other = (MediaFilePath) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
