package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the "series" database table.
 * 
 */
@Entity
@Table(name="series")
@NamedQuery(name="Sery.findAll", query="SELECT s FROM Sery s")
public class Sery implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private List<Season> seasons;

	public Sery() {
	}


	@Column(name="id")
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
//	@TableGenerator(name="seriesIdGen", table="sqlite_sequence",
//    pkColumnName="name", valueColumnName="seq",
//    pkColumnValue="series")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer  id) {
		this.id = id;
	}


	@Column(name="name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the seasons
	 */
	@OneToMany(mappedBy="series",cascade=CascadeType.ALL)
	public List<Season> getSeasons() {
		return seasons;
	}


	/**
	 * @param seasons the seasons to set
	 */
	public void setSeasons(List<Season> seasons) {
		this.seasons = seasons;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Sery [id=" + id + ", name=" + name + "]";
	}
	
	

}