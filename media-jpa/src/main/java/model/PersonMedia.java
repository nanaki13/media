//package model;
//
//import java.io.Serializable;
//import javax.persistence.*;
//
//
///**
// * The persistent class for the "person_media" database table.
// * 
// */
//@Entity
//@Table(name="person_media")
//@NamedQuery(name="PersonMedia.findAll", query="SELECT p FROM PersonMedia p")
//public class PersonMedia implements Serializable {
//	private static final long serialVersionUID = 1L;
//	private Integer jobKey;
//	private Integer mediaKey;
//	private Integer personKey;
//
//	public PersonMedia() {
//	}
//
//
//	@Column(name="job_key")
//	public Integer getJobKey() {
//		return this.jobKey;
//	}
//
//	public void setJobKey(Integer  jobKey) {
//		this.jobKey = jobKey;
//	}
//
//
//	@Column(name="media_key")
//	public Integer getMediaKey() {
//		return this.mediaKey;
//	}
//
//	public void setMediaKey(Integer  mediaKey) {
//		this.mediaKey = mediaKey;
//	}
//
//
//	@Column(name="person_key")
//	public Integer getPersonKey() {
//		return this.personKey;
//	}
//
//	public void setPersonKey(Integer  personKey) {
//		this.personKey = personKey;
//	}
//
//}