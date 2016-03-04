package sokkalingam.restapi.messenger.resources.helper;

import javax.ws.rs.core.UriInfo;

import sokkalingam.restapi.messenger.model.Message;
import sokkalingam.restapi.messenger.resources.CommentResource;
import sokkalingam.restapi.messenger.resources.MessageResource;
import sokkalingam.restapi.messenger.resources.ProfileResource;

public class ResourceHelper {
	
	public static String getLinkForMessage(Message message, UriInfo uriInfo) {
		return uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(String.valueOf(message.getId()))
				.build().toString();
	}
	
	public static String getLinkForProfile(Message message, UriInfo uriInfo) {
		return uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(message.getAuthor())
				.build().toString();
	}
	
	public static String getLinkForComments(Message message, UriInfo uriInfo) {
		return uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class, "getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId", message.getId())
				.build().toString();
	}

}
