package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class SeasonPK implements Serializable {
	private Integer seasonNumber;
	private Integer seriesKey;
	/**
	 * @return the seasonNumber
	 */
	@Column(name = "season_number")
	public Integer getSeasonNumber() {
		return seasonNumber;
	}
	/**
	 * @param seasonNumber the seasonNumber to set
	 */
	public void setSeasonNumber(Integer seasonNumber) {
		this.seasonNumber = seasonNumber;
	}
	/**
	 * @return the seriesKey
	 */
	@Column(name = "series_key")
	public Integer getSeriesKey() {
		return seriesKey;
	}
	/**
	 * @param seriesKey the seriesKey to set
	 */
	public void setSeriesKey(Integer seriesKey) {
		this.seriesKey = seriesKey;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seasonNumber == null) ? 0 : seasonNumber.hashCode());
		result = prime * result + ((seriesKey == null) ? 0 : seriesKey.hashCode());
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
		SeasonPK other = (SeasonPK) obj;
		if (seasonNumber == null) {
			if (other.seasonNumber != null)
				return false;
		} else if (!seasonNumber.equals(other.seasonNumber))
			return false;
		if (seriesKey == null) {
			if (other.seriesKey != null)
				return false;
		} else if (!seriesKey.equals(other.seriesKey))
			return false;
		return true;
	}
	
	
	
	
	
}
