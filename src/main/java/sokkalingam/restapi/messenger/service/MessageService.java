package sokkalingam.restapi.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import sokkalingam.restapi.messenger.database.DatabaseClass;
import sokkalingam.restapi.messenger.model.Message;

public class MessageService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	/**Get all messages
	 * @return
	 */
	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}
	
	/**Get messages for a given year
	 * @param year
	 * @return
	 */
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
	
	/**Get messages for the given limit
	 * @param start
	 * @param size
	 * @return
	 */
	public List<Message> getMessagesForLimit(int start, int size) {
		if (start >=0 && size > 0)
			return getAllMessages().subList(start, start + size);
		return new ArrayList<Message>();
	}
	
	/**Get message for message id
	 * @param id
	 * @return
	 */
	public Message getMessage(long id) {
		return messages.get(id);
	}
	
	/**Add a message
	 * @param message
	 * @return
	 */
	public Message addMessage(Message message) {
		message.setId((long) messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	/**Update a message
	 * @param message
	 * @return
	 */
	public Message updateMessage(Message message) {
		if (message.getId() == null || message.getId() <= 0)
			return null;
		return messages.put(message.getId(), message);
	}
	
	/**Remove a message
	 * @param id
	 * @return
	 */
	public Message removeMessage(Long id) {
		return messages.remove(id);
	}

}
