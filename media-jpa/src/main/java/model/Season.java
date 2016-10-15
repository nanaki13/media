package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the "season" database table.
 * 
 */
@Entity
@Table(name = "season")
@NamedQuery(name = "Season.findAll", query = "SELECT s FROM Season s")
public class Season implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private SeasonPK id;
	private Sery series;
	
	private List<MediaEpisode> episodes;

	public Season() {
	}

	@MapsId("seriesKey")
	@ManyToOne
	@JoinColumn(name = "series_key", referencedColumnName = "id")
	public Sery getSeries() {
		return series;
	}



	/**
	 * @param sery
	 *            the sery to set
	 */
	public void setSeries(Sery sery) {
		this.series = sery;
		id.setSeriesKey(sery.getId());
	}

	/**
	 * @return the episodes
	 */
	@OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
	public List<MediaEpisode> getEpisodes() {
		return episodes;
	}

	/**
	 * @param episodes the episodes to set
	 */
	public void setEpisodes(List<MediaEpisode> episodes) {
		this.episodes = episodes;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	@EmbeddedId
	public SeasonPK getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(SeasonPK id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		Season other = (Season) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Season [name=" + name + ", id=" + id + ", series=" + series + "]";
	}

}