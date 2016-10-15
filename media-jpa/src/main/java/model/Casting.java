package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "casting" database table.
 * 
 */
@Entity
@Table(name="casting")
@NamedQuery(name="Casting.findAll", query="SELECT c FROM Casting c")
public class Casting implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Media media;
	
	private Person person;
	private String role;

	public Casting() {
	}
	
	/**
	 * @return the id
	 */
	@Id
	@Column(name="id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

	@ManyToOne
	@JoinColumn(name="media_key")
	public Media getMedia() {
		return this.media;
	}

	public void setMedia(Media movieKey) {
		this.media = movieKey;
	}
	
	@ManyToOne
	@JoinColumn(name="person_key")
	public Person getPerson() {
		return this.person;
	}

	
	


	/**
	 * @return the role
	 */
	@Column(name="role")
	public String getRole() {
		return role;
	}


	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}


}