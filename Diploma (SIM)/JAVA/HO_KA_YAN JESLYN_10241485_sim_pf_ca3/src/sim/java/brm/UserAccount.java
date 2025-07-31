package sim.java.brm;

import java.util.ArrayList;

/**
 * @author Jeslyn
 *
 */
public class UserAccount {
	private int userId;
	private String userFirstName;
	private String userLastName;
	private String userCreateDate;
	private ArrayList<String> userGenrePreferencesList;

	public UserAccount(int userId, String userFirstName, String userLastName, String userCreateDate) {
		this.userId = userId;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userCreateDate = userCreateDate;
		this.userGenrePreferencesList = new ArrayList<>();
	}

	public void addGenre(String genre) {
		this.userGenrePreferencesList.add(genre);
	}

	public void removeGenre(String genre) {
		this.userGenrePreferencesList.remove(genre);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserCreateDate() {
		return userCreateDate;
	}

	public void setUserCreateDate(String userCreateDate) {
		this.userCreateDate = userCreateDate;
	}

	public ArrayList<String> getUserGenrePreferencesList() {
		return userGenrePreferencesList;
	}

	public void setUserGenrePreferencesList(ArrayList<String> userGenrePreferencesList) {
		this.userGenrePreferencesList = userGenrePreferencesList;
	}

}
