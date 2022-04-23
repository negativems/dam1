package ga.mmbh.cfgs.netflixdb.managers;

import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.common.hash.Hashing;

import ga.mmbh.cfgs.netflixdb.dao.AuthDAO;
import ga.mmbh.cfgs.netflixdb.dao.UserDAO;
import ga.mmbh.cfgs.netflixdb.models.Show;
import ga.mmbh.cfgs.netflixdb.models.User;
import ga.mmbh.cfgs.netflixdb.utils.EmailUtils;

public class UserManager {

	private final UserDAO userDAO;
	private final AuthDAO authDAO;
	private User session;
	
	public UserManager() {
		this.userDAO = new UserDAO();
		this.authDAO = new AuthDAO();
	}
	
	public boolean login(String username, String password) {
		if (!existsUsername(username)) return loginEmail(username, password);
		
		User user = userDAO.getUser(username, false);
		if (!user.getPassword().equals(password)) return false;
		session = user;
		return true;
	}
	
	public boolean loginEmail(String email, String password) {
		if (!existsEmail(email)) return false;
		
		User user = userDAO.getUser(email, true);
		if (!user.getPassword().equals(password)) return false;
		session = user;
		return true;
	}
	
	public boolean checkAuth(int userId, String code) {
		ResultSet rs = authDAO.getFirst("user_id", "code = '" + code + "'");
		try {
			if (rs != null && rs.next()) {
				authDAO.delete("user_id = " + userId);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean sendVerification(User user) {
		String code = (int) (Math.random() * (1000000) + 1) + "";
		System.out.println("Código generado: " + code);
		
		String[] values = new String[] {
				"'" + user.getId() + "'",
				"'" + code + "'"
		};
		
		EmailUtils.send("negativems1@gmail.com", "NetflixDB - Código de verificación", "Tu código de verificación es: " + code);
		return authDAO.insertOne("user_id, code", values);
	}
	
	public boolean registerUser(String username, String email, String password) {
		String[] values = new String[] {
				"'" + username + "'",
				"'" + email + "'",
				"'" + password + "'"
		};
		
		return userDAO.insertOne("username, email, password", values);
	}
	
	public boolean existsUsername(String username) {
		try {
			ResultSet rs = userDAO.getFirst("*", "username = '" + username + "'");
			return rs != null && rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean existsEmail(String email) {
		try {
			ResultSet rs = userDAO.getFirst("*", "email = '" + email + "'");
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
	
	public User getUser(String username) {
		if (!existsUsername(username)) return null;
		return userDAO.getUser(username, false);
	}
	
	public User getUserByEmail(String email) {
		if (!existsEmail(email)) return null;
		return userDAO.getUser(email, true);
	}
	
	public boolean hasFavourite(User user, Show show) {
		return user.getFavouritesShows().contains(show);
	}
	
	public void changePassword(int id, String password) {
		String encodedPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
		userDAO.update("password = '" + encodedPassword + "'", "id = " + id);
	}
	
	// Getters & Setters	
	public User getSession() {
		return this.session;
	}

}
