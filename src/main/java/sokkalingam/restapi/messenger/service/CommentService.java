package sokkalingam.restapi.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import sokkalingam.restapi.messenger.model.Comment;
import sokkalingam.restapi.messenger.model.Message;

public class CommentService {
	
	MessageService messageService;
	Message message = null;
	Map<Long, Comment> commentMap = null;
	
	public CommentService() {
		messageService = new MessageService();
	}
	
	/**Get all comments
	 * @param messageId
	 * @return
	 */
	public List<Comment> getComments(String profile, long messageId) {
		message = messageService.getMessage(profile, messageId);
		if (message != null) {
			commentMap = message.getComments();
			return new ArrayList<Comment>(commentMap.values());
		}
		return new ArrayList<Comment>();
	}
	
	/**Get a comment that belongs to a message
	 * @param messageId
	 * @param commentId
	 * @return
	 */
	public Comment getComment(String profile, long messageId, long commentId) {
		message = messageService.getMessage(profile, messageId);
		if (message != null)
			return message.getComments().get(commentId);
		return null;
	}
	
	/**Add a comment to a message
	 * @param messageId
	 * @param comment
	 * @return
	 */
	public Comment addComment(String profile, long messageId, Comment comment) {
		message = messageService.getMessage(profile, messageId);
		
		if (message != null) {
			commentMap = message.getComments();
			comment.setId(Long.valueOf(commentMap.size() + 1));
			commentMap.put(comment.getId(), comment);
			return comment;
		}
		return null;
	}
	
	/**Update comment for a message
	 * @param messageId
	 * @param commentId
	 * @param comment
	 * @return
	 */
	public Comment updateComment(String profile, long messageId, long commentId, Comment comment) {
		message = messageService.getMessage(profile, messageId);
		
		if (message != null) {
			commentMap = message.getComments();
			comment.setId(commentId);
			commentMap.put(comment.getId(), comment);
			return comment;
		}
		
		return null;
	}
	
	/**Delete a comment from a message
	 * @param messageId
	 * @param commentId
	 * @return
	 */
	public Comment deleteComment(String profile, long messageId, long commentId) {
		message = messageService.getMessage(profile, messageId);
		if (message != null) {
			commentMap = message.getComments();
			return commentMap.remove(commentId);
		}
		return null;
	}

}
