package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "media_type" database table.
 * 
 */
@Entity
@Table(name="media_type")
@NamedQuery(name="MediaType.findAll", query="SELECT m FROM MediaType m")
public class MediaType implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;

	public MediaType() {
	}

	@Id
	@Column(name="id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Column(name="name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}