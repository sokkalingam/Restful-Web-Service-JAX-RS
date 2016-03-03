package sokkalingam.restapi.messenger.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Profile {
	
	private Long id;
	private String profileName;
	private String firstname;
	private String lastname;
	private Date createdAt;
	
	public Profile() {
		
	}
	
	public Profile(Long id, String profileName, String firstname, String lastname, Date createdAt) {
		super();
		this.id = id;
		this.profileName = profileName;
		this.firstname = firstname;
		this.lastname = lastname;
		this.createdAt = createdAt;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	

}
