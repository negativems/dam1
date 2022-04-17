package ga.mmbh.cfgs.netflixdb.models;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.google.common.hash.Hashing;

public class User {

	private final int id;
	private final String username;
	private final String password;
	private final List<Show> favouritesShowList;
	
	public User(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.favouritesShowList = new ArrayList<Show>();
	}
	
	public int getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		String encoded = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
		System.out.println(encoded);
		return encoded;
	}
	
	public String getUnencodedPassword() {
		return password;
	}
	
	public List<Show> getFavouritesShows() {
		return favouritesShowList;
	}
	
}
