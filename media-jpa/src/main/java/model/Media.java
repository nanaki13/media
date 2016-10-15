package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 * The persistent class for the "media" database table.
 * 
 */
@Entity
@Table(name="media")
@Inheritance(strategy=InheritanceType.JOINED)
//@DiscriminatorColumn(name="media_type_key",discriminatorType=DiscriminatorType.INTEGER)
@NamedQuery(name="Media.findAll", query="SELECT m FROM Media m")
public abstract class Media implements Serializable {
	private static final long serialVersionUID = 1L;
	private Calendar dateRealisation;
	private Integer id;
	private MediaType mediaType;
//	private int mediaTypeKey;
	protected String name;
	
	private List<MediaFilePath> paths ;
	

	public Media() {
	}

	

	/**
	 * @return the paths
	 */
	@OneToMany(mappedBy="media",cascade=CascadeType.ALL)
	public List<MediaFilePath> getPaths() {
		if(paths == null){
			paths = new ArrayList<>();
		}
		return paths;
	}



	/**
	 * @param paths the paths to set
	 */
	public void setPaths(List<MediaFilePath> paths) {
		this.paths = paths;
	}



	@Column(name="date_realisation")
	@Temporal(TemporalType.DATE)
	public Calendar getDateRealisation() {
		return this.dateRealisation;
	}

	public void setDateRealisation(Calendar dateRealisation) {
		this.dateRealisation = dateRealisation;
	}

	@Id
	@Column(name="id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
//	@TableGenerator(name="mediaIdGen", table="sqlite_sequence",
//    pkColumnName="name", valueColumnName="seq",
//    pkColumnValue="media")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne()
	@JoinColumn(name="media_type_key")
	public MediaType getMediaType() {
		return this.mediaType;
	}

	public void setMediaType(MediaType mediaTypeKey) {
		this.mediaType = mediaTypeKey;
	}
	
//	@Column(name="media_type_key")
//	public Integer getMediaTypeKey() {
//		return this.mediaTypeKey;
//	}
//
//	public void setMediaTypeKey(Integer  mediaTypeKey) {
//		this.mediaTypeKey = mediaTypeKey;
//	}


	@Column(name="name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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
		Media other = (Media) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}