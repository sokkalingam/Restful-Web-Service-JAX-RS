package sokkalingam.restapi.messenger.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import sokkalingam.restapi.messenger.model.Comment;
import sokkalingam.restapi.messenger.service.CommentService;

public class CommentResource {
	
	CommentService commentService = new CommentService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> getComments(@PathParam("profileName") String profile, @PathParam("messageId") long messageId) {
		return new ArrayList<Comment>(commentService.getComments(profile, messageId));
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{commentId}")
	public Comment getComment(@PathParam("profileName") String profile, @PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		return commentService.getComment(profile, messageId, commentId);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Comment postComment(@PathParam("profileName") String profile, @PathParam("messageId") long messageId, Comment comment) {
		return commentService.addComment(profile, messageId, comment);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{commentId}")
	public Comment updateComment(@PathParam("profileName") String profile, @PathParam("messageId") long messageId, @PathParam("commentId") long commentId, Comment comment) {
		return commentService.updateComment(profile, messageId, commentId, comment);
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{commentId}")
	public Comment deleteComment(@PathParam("profileName") String profile, @PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		return commentService.deleteComment(profile, messageId, commentId);
	}
	
	

}
