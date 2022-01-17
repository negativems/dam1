package managers;

import java.util.ArrayList;
import java.util.List;

import models.User;

public class UserManager {

	private List<User> usersList = new ArrayList<>();
	private User loggedUser;
	
	public UserManager() {
		addUser(new User("test", "test"));
		addUser(new User("pikachu", "1234"));
	}
	
	public void addUser(User user) {
		usersList.add(user);
	}
	
	// Getters & Setters
	public List<User> getUsers() {
		return usersList;
	}
	
	public void setLoggedUser(User user) {
		this.loggedUser = user;
	}
	
}
