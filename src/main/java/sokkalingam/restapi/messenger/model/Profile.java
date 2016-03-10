package sokkalingam.restapi.messenger.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Profile {
	
	private Long id;
	private String profileName;
	private String firstname;
	private String lastname;
	private Date createdAt;
	private Map<Long, Message> messages = new HashMap<Long, Message>();
	
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
	@XmlTransient
	public Map<Long, Message> getMessages() {
		return messages;
	}
	public void setMessages(Map<Long, Message> messages) {
		this.messages = messages;
	}
	
	

}
