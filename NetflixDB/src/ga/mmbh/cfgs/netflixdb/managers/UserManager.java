package ga.mmbh.cfgs.netflixdb.managers;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import ga.mmbh.cfgs.netflixdb.dao.AuthDAO;
import ga.mmbh.cfgs.netflixdb.dao.UserDAO;
import ga.mmbh.cfgs.netflixdb.models.Show;
import ga.mmbh.cfgs.netflixdb.models.User;
import ga.mmbh.cfgs.netflixdb.utils.AppUtils;

public class UserManager {

	private final UserDAO userDAO;
	private final AuthDAO authDAO;
	private User session;
	
	public UserManager() {
		this.userDAO = new UserDAO();
		this.authDAO = new AuthDAO();
	}
	
	public boolean login(String username, String password) {
		if (!exists(username, password)) return false;
		session = userDAO.getUser(username);
		return true;
	}
	
	public boolean sendVerification(User user) {
		String code;
		try {
			code = AppUtils.createMD5Hash((int)(Math.random()*(1000000+1)) + "");
			System.out.println("Codigo generado: " + code);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return false;
		}
		
		String[] values = new String[] {
				"'" + user.getId() + "'",
				"'" + code + "'"
		};
		
		return authDAO.insertOne("username, code", values);
	}
	
	public boolean registerUser(User user) {
		String[] values = new String[] {
				"'" + user.getUsername() + "'",
				"'" + user.getPassword() + "'"
		};
		
		return userDAO.insertOne("username, password", values);
	}
	
	public boolean exists(String username, String password) {
		try {
			ResultSet rs = userDAO.getFirst("*", "username = '" + username + "'" + (password != null ? " AND password = '" + password + "'" : ""));
			return rs != null && rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean isNotAuthenticated(int id) {
		try {
			ResultSet rs = authDAO.getFirst("*", "user_id = " + id);
			return rs != null && rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean exists(String username) {
		return exists(username, null);
	}
	
	public User getUser(String username) {
		if (!exists(username)) return null;
		return userDAO.getUser(username);
	}
	
	public boolean hasFavourite(User user, Show show) {
		return user.getFavouritesShows().contains(show);
	}
	
	// Getters & Setters	
	public User getSession() {
		return this.session;
	}

}
