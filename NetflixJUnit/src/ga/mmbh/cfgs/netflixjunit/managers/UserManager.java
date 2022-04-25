package ga.mmbh.cfgs.netflixjunit.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import ga.mmbh.cfgs.netflixjunit.NetflixApp;
import ga.mmbh.cfgs.netflixjunit.models.User;

@SuppressWarnings("unused")
public class UserManager {

	private final NetflixApp netflixApp;
	private User session;

	private List<User> usersList = new ArrayList<>();

	public UserManager(NetflixApp netflixApp) {
		this.netflixApp = netflixApp;
		register("admin", "admin");
		register("test", "test");
	}

	public boolean login(String username, String password) {
		if (!exists(username, password)) return false;
		this.session = new User(username, password);
		return true;
	}

	public boolean register(String username, String password) {
		if (exists(username, password)) return false;
		return usersList.add(new User(username, password));
	}

	public boolean exists(String username, String password) {
		return usersList.stream().anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password));
	}

	public boolean exists(String username) {
		return exists(username, null);
	}

	public boolean isValidLength(String password) {
		return password.length() >= 8 && password.length() <= 15;
	}
	
	public boolean isUsernameEmail(String username) {
		Pattern pattern = Pattern.compile("^(.+)@(.+)$");
		return pattern.matcher(username).matches();
	}

	public boolean isValidPassword(String password) {
		return hasUpperCase(password) && hasLowerCase(password) && hasLowerCase(password) && hasSpecialCharacter(password);
	}

	public boolean hasUpperCase(String password) {
		String upperTest = "(.*[A-Z].*)";
		return password.matches(upperTest);
	}
	
	public boolean hasLowerCase(String password) {
		String lowerTest = "(.*[a-z].*)";
		return password.matches(lowerTest);
	}
	
	public boolean hasNumber(String password) {
		String numberTest = "(.*[0-9].*)";
		return password.matches(numberTest);
	}
	
	public boolean hasSpecialCharacter(String password) {
		String specialTest = "(.*[@,#,$,%].*$)";
		return password.matches(specialTest);
	}
	
	// Getters & Setters
	public List<User> getUsers() {
		return usersList;
	}

}
