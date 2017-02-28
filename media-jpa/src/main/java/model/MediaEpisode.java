package model;

import java.io.Serializable;
import java.util.List;
import java.util.jar.Attributes.Name;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * The persistent class for the "media_series" database table.
 * 
 */
@Entity
@Table(name = "episode")
@PrimaryKeyJoinColumn(name = "media_key")
// @DiscriminatorValue(value="1")
public class MediaEpisode extends Media implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Season season;
	
	
	private Integer episodeNumber;

	public MediaEpisode() {
	}

	public MediaEpisode(MediaType mediaType) {
		setMediaType(mediaType);
	}

	public MediaEpisode(String string) {
		name = string;
	}

	/**
	 * @return the season
	 */

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "season_number_key",referencedColumnName= "season_number"), @JoinColumn(name = "series_key",referencedColumnName="series_key") })
	
	public Season getSeason() {
		return season;
	}

	/**
	 * @param season
	 *            the season to set
	 */
	public void setSeason(Season season) {
		this.season = season;
	}

	/**
	 * @return the episodeNumber
	 */
	@Column(name = "episode_number")
	public Integer getEpisodeNumber() {
		return episodeNumber;
	}

	/**
	 * @param episodeNumber
	 *            the episodeNumber to set
	 */
	public void setEpisodeNumber(Integer episodeNumber) {
		this.episodeNumber = episodeNumber;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MediaEpisode [season=" + season + ", episodeNumber=" + episodeNumber + "]";
	}

	public void addAll(List<MediaFilePath> tmpSave) {
		if(getId() != null){
			for(MediaFilePath m : tmpSave){
				m.getId().setMediaId(getId());
			}
		}
		getPaths().addAll(tmpSave);
		
	}
	

}