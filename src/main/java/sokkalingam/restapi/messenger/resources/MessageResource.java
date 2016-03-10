package sokkalingam.restapi.messenger.resources;

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
import javax.ws.rs.core.MediaType;

import sokkalingam.restapi.messenger.beans.MessageFilterBean;
import sokkalingam.restapi.messenger.model.Message;
import sokkalingam.restapi.messenger.service.MessageService;

public class MessageResource {
	
	private MessageService messageService = new MessageService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@PathParam("profileName") String profileName,
			@BeanParam MessageFilterBean bean) {
		
		if (bean.getYear() > 0)
			return messageService.getMessagesForYear(profileName, bean.getYear());
		
		if (bean.getStart() >= 0 && bean.getSize() > 0)
			return messageService.getMessagesForLimit(profileName, bean.getStart(), bean.getSize());
		
		return messageService.getAllMessages(profileName);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Message addMessage(@PathParam("profileName") String profileName, Message message){
		return messageService.addMessage(profileName, message);
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("profileName") String profileName, @PathParam("id") Long id) {
		return messageService.getMessage(profileName, id);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("profileName") String profileName, @PathParam("id") long id, Message message) {
		message.setId(id);
		return messageService.updateMessage(profileName, message);
	}
	
	@DELETE
	@Path("/{id}")
	public void deleteMessage(@PathParam("profileName") String profileName, @PathParam("id") long id) {
		messageService.removeMessage(profileName, id);
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}	

}
