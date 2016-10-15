package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.beans.factory.annotation.Configurable;

@Embeddable
public class MediaFilePathPK implements Serializable {
	
	private Integer number;
	private Integer mediaId;

	
	/**
	 * @return the number
	 */
	@Column(name="number")
	public Integer getNumber() {
		return number;
	}


	/**
	 * @param number the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}


	/**
	 * @return the mediaId
	 */
	@Column(name="media_id")
	public Integer getMediaId() {
		return mediaId;
	}


	/**
	 * @param mediaId the mediaId to set
	 */
	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mediaId == null) ? 0 : mediaId.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		MediaFilePathPK other = (MediaFilePathPK) obj;
		if (mediaId == null) {
			if (other.mediaId != null)
				return false;
		} else if (!mediaId.equals(other.mediaId))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}


	public MediaFilePathPK() {
	}


	public MediaFilePathPK(Media media,int number) {
		mediaId = media.getId();
		this.number = number;
	}
}