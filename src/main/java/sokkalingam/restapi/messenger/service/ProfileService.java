package sokkalingam.restapi.messenger.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import sokkalingam.restapi.messenger.database.DatabaseClass;
import sokkalingam.restapi.messenger.model.Profile;

public class ProfileService {

	
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService() {
		profiles.put("lings24", new Profile(1L, "lings24", "Sokkalingam", "Subramanian", new Date()));
		profiles.put("brahmi", new Profile(2L, "brahmi", "Brahmi", "Reddy", new Date()));
	}
	
	/**Get all profiles
	 * @return
	 */
	public List<Profile> getAllProfiles() {
		return new ArrayList<Profile>(profiles.values());
	}
	
	/**Get profile
	 * @param userName
	 * @return
	 */
	public Profile getProfile(String userName) {
		return profiles.get(userName);
	}
	
	/**Add profile
	 * @param profile
	 * @return
	 */
	public Profile addProfile(Profile profile) {
		profile.setId((long) profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	/**Update profile
	 * @param profile
	 * @return
	 */
	public Profile updateProfile(Profile profile) {
		if (profile.getId() == null || profile.getId() <= 0)
			return null;
		return profiles.put(profile.getProfileName(), profile);
	}
	
	/**Remove profile
	 * @param userName
	 * @return
	 */
	public Profile removeProfile(String userName) {
		return profiles.remove(userName);
	}



}
