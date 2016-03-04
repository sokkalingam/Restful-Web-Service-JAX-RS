package sokkalingam.restapi.messenger.model;

public class Link {
	
	public Link(String link, String ref) {
		super();
		this.link = link;
		this.ref = ref;
	}
	
	private String link;
	private String ref;
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}

}
