package ga.mmbh.cfgs.netflixdb.models;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.google.common.hash.Hashing;

public class User {

	private final int id;
	private final String username;
	private final String email;
	private final String password;
	private final List<Show> favouritesShowList;
	
	public User(int id, String username, String email, String password) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.favouritesShowList = new ArrayList<Show>();
	}
	
	public int getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	public List<Show> getFavouritesShows() {
		return favouritesShowList;
	}
	
}
