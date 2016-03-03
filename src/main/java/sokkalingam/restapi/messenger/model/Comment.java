package sokkalingam.restapi.messenger.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Comment {

	private Long id;
	private String comment;
	private String author;
	private Date createdAt;
	
	public Comment () {
		this.createdAt = new Date();
	}
	
	public Comment(Long id, String comment, String author) {
		super();
		this.id = id;
		this.comment = comment;
		this.author = author;
		this.createdAt = new Date();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
