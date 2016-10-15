package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "job" database table.
 * 
 */
@Entity
@Table(name="job")
@NamedQuery(name="Job.findAll", query="SELECT j FROM Job j")
public class Job implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;

	public Job() {
	}

	@Id
	@Column(name="id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
//	@TableGenerator(name="jobIdGen", table="sqlite_sequence",
//    pkColumnName="name", valueColumnName="seq",
//    pkColumnValue="job")
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

}