package sokkalingam.restapi.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sokkalingam.restapi.messenger.model.Message;
import sokkalingam.restapi.messenger.model.Profile;

public class MessageService {
	
	private ProfileService profileService;
	private Profile profile;
	private Map<Long, Message> messages;
	
	public MessageService() {
		profileService = new ProfileService();
	}
	
	/**Get all messages
	 * @return
	 */
	public List<Message> getAllMessages(String profile) {
		return new ArrayList<Message>(getMessages(profile).values());
	}
	
	/**Get messages for a given year
	 * @param year
	 * @return
	 */
	public List<Message> getMessagesForYear(String profile, int year) {
		Calendar cal = Calendar.getInstance();
		List<Message> messages = new ArrayList<Message>();
		for (Message message : getAllMessages(profile)) {
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
	public List<Message> getMessagesForLimit(String profile, int start, int size) {
		if (start >=0 && size > 0)
			return getAllMessages(profile).subList(start, start + size);
		return new ArrayList<Message>();
	}
	
	/**Get message for message id
	 * @param id
	 * @return
	 */
	public Message getMessage(String profile, long id) {
		return getMessages(profile).get(id);
	}
	
	/**Add a message
	 * @param message
	 * @return
	 */
	public Message addMessage(String profile, Message message) {
		if (message == null)
			return null;
		messages = getMessages(profile);
		if (messages == null)
			return null;
		message.setProfileName(profile);
		message.setId((long) messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	/**Update a message
	 * @param message
	 * @return
	 */
	public Message updateMessage(String profile, Message message) {
		if (message.getId() == null || message.getId() <= 0)
			return null;
		messages = getMessages(profile);
		message.setProfileName(profile);
		if (messages.containsKey(message.getId()))
			return messages.put(message.getId(), message);
		return null;
	}
	
	/**Remove a message
	 * @param id
	 * @return
	 */
	public Message removeMessage(String profile, Long id) {
		messages = getMessages(profile);
		return messages.remove(id);
	}
	
	public Map<Long, Message> getMessages(String profile) {
		this.profile = profileService.getProfile(profile);
		if (this.profile != null)
			return this.profile.getMessages();
		return null;
	}
}
