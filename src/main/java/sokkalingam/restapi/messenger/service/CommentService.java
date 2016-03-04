package sokkalingam.restapi.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import sokkalingam.restapi.messenger.database.DatabaseClass;
import sokkalingam.restapi.messenger.model.Comment;
import sokkalingam.restapi.messenger.model.Message;

public class CommentService {
	
	Map<Long, Message> messages = DatabaseClass.getMessages();
	Map<Long, Comment> commentMap = null;
	
	public List<Comment> getComments(long messageId) {
		Message message = messages.get(messageId);
		if (message != null) {
			commentMap = message.getComments();
			return new ArrayList<Comment>(commentMap.values());
		}
		return new ArrayList<Comment>();
	}
	
	public Comment getComment(long messageId, long commentId) {
		return messages.get(messageId).getComments().get(commentId);
	}
	
	public Comment addComment(long messageId, Comment comment) {
		Message message = messages.get(messageId);
		Map<Long, Comment> commentMap = null;
		if (message != null) {
			commentMap = message.getComments();
			comment.setId(Long.valueOf(commentMap.size() + 1));
			commentMap.put(comment.getId(), comment);
			return comment;
		}
		return null;
	}
	
	public Comment updateComment(long messageId, long commentId, Comment comment) {
		Map<Long, Comment> commentMap = messages.get(messageId).getComments();
		comment.setId(commentId);
		commentMap.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment deleteComment(long messageId, long commentId) {
		return messages.get(messageId).getComments().remove(commentId);
	}

}
