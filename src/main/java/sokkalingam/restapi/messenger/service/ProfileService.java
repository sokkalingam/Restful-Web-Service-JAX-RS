package sokkalingam.restapi.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.UriInfo;

import sokkalingam.restapi.messenger.database.DatabaseClass;
import sokkalingam.restapi.messenger.model.Link;
import sokkalingam.restapi.messenger.model.Profile;
import sokkalingam.restapi.messenger.resources.ProfileResource;

public class ProfileService {

	
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService() {
		
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
	public Profile addProfile(Profile profile, UriInfo uriInfo) {
		profile.getLinks().addAll(_getLinks(profile, uriInfo));
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
	
	private List<Link> _getLinks(Profile profile, UriInfo uriInfo) {
		List<Link> links = new ArrayList<Link>();
		String profileLink = uriInfo.getAbsolutePathBuilder().path(profile.getProfileName()).build().toString();
		links.add(new Link(profileLink, "self"));
		String messagesLink = uriInfo.getAbsolutePathBuilder().path(ProfileResource.class, "getMessageResource")
				.resolveTemplate("profileName", profile.getProfileName()).build().toString();
		links.add(new Link(messagesLink, "messages"));
		return links;
	}



}
