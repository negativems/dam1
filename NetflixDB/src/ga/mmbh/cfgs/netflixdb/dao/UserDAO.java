package ga.mmbh.cfgs.netflixdb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import ga.mmbh.cfgs.netflixdb.models.User;

public class UserDAO extends AbstractDAO {
	
	public UserDAO() {
		super("users");
	}
	
	public boolean login(User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		String email = user.getEmail();
		
		String columns = "username, email, password";
		String where = "username = '" + username + "' AND password = '" + password + "'" + " AND email = '" + email + "'";
		ResultSet rs = getFirst(columns, where);
		
		try {
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean register(User user) {
		String[] values = new String[] {user.getUsername(), user.getEmail(), user.getPassword()};
		return insertOne("username, email, password", values);
	}
	
	public User getUser(String username, boolean isEmail) {
		String columns = "id, email, password";
		String where = (isEmail ? "email" : "username") + " = '" + username + "'";
		ResultSet rs = getFirst(columns, where);
		
		try {
			if (rs.next()) {
				int id = rs.getInt("id");
				String email = rs.getString("email");
				String password = rs.getString("password");
				return new User(id, username, email, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
