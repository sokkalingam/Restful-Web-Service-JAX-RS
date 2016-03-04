package sokkalingam.restapi.messenger.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import sokkalingam.restapi.messenger.beans.MessageFilterBean;
import sokkalingam.restapi.messenger.model.Link;
import sokkalingam.restapi.messenger.model.Message;
import sokkalingam.restapi.messenger.service.MessageService;

@Path("/messages")
public class MessageResource {
	
	private MessageService messageService = new MessageService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(
			@BeanParam MessageFilterBean bean) {
		
		if (bean.getYear() > 0)
			return messageService.getMessagesForYear(bean.getYear());
		
		if (bean.getStart() >= 0 && bean.getSize() > 0)
			return messageService.getMessagesForLimit(bean.getStart(), bean.getSize());
		
		return messageService.getAllMessages();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addMessage(Message message, @Context UriInfo uriInfo){
		message = messageService.addMessage(message);
		URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(message.getId())).build();
//		List<Link> links = new ArrayList<Link>();
//		links.add(new Link(getLinkForMessage(message, uriInfo), "self"));
//		links.add(new Link(getLinkForProfile(message, uriInfo), "profile"));
//		links.add(new Link(getLinkForComments(message, uriInfo), "comments"));
//		message.setLinks(links);
		return Response.created(uri).entity(message).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("id") Long id) {
		return messageService.getMessage(id);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("id") long id, Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{id}")
	public void deleteMessage(@PathParam("id") long id) {
		messageService.removeMessage(id);
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
	
	public String getLinkForMessage(Message message, UriInfo uriInfo) {
		return uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(String.valueOf(message.getId()))
				.build().toString();
	}
	
	public String getLinkForProfile(Message message, UriInfo uriInfo) {
		return uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(message.getAuthor())
				.build().toString();
	}
	
	public String getLinkForComments(Message message, UriInfo uriInfo) {
		return uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(String.valueOf(message.getId()))
				.path(MessageResource.class, "getCommentResource")
				.build().toString();
	}
	

}
