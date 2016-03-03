package sokkalingam.restapi.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import sokkalingam.restapi.messenger.database.DatabaseClass;
import sokkalingam.restapi.messenger.model.Message;

public class MessageService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService() {
		
	}
	
	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getMessagesForYear(int year) {
		Calendar cal = Calendar.getInstance();
		List<Message> messages = new ArrayList<Message>();
		for (Message message : getAllMessages()) {
			cal.setTime(message.getCreatedAt());
			if (cal.get(Calendar.YEAR) == year)
				messages.add(message);
		}
		return messages;
	}
	
	public List<Message> getMessagesForLimit(int start, int size) {
		if (start >=0 && size > 0)
			return getAllMessages().subList(start, start + size);
		return new ArrayList<Message>();
	}
	
	public Message getMessage(long id) {
		return messages.get(id);
	}
	
	public Message addMessage(Message message) {
		message.setId((long) messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if (message.getId() == null || message.getId() <= 0)
			return null;
		return messages.put(message.getId(), message);
	}
	
	public Message removeMessage(Long id) {
		return messages.remove(id);
	}

}
