package ga.mmbh.cfgs.managers;

import java.util.ArrayList;
import java.util.List;

import ga.mmbh.cfgs.NetflixApp;
import ga.mmbh.cfgs.models.User;

public class UserManager {

   private final NetflixApp netflixApp;
   private User session;
   
	private List<User> usersList = new ArrayList<>();
	
	public UserManager(NetflixApp netflixApp) {
		this.netflixApp = netflixApp;
	}
	
	public boolean login(String username, String password) {
		if (!exists(username, password)) return false;
		session = new User(username, password);
		return true;
	}
	
	public boolean registerUser(User user) {
       return usersList.add(user);
	}
	
	public boolean exists(String username, String password) {
		return usersList.stream().anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password));
	}
	
	public boolean exists(String username) {
		return exists(username, null);
	}
	
	// Getters & Setters
	public List<User> getUsers() {
		return usersList;
	}
	
}
