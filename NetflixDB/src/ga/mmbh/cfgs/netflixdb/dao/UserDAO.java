package ga.mmbh.cfgs.netflixdb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import ga.mmbh.cfgs.netflixdb.models.User;

public class UserDAO extends AbstractDAO {
	
	public UserDAO() {
		super("users");
	}

	public boolean login(User user) {
		String columns = "username, password";
		String where = "username = '" + user.getUsername() + "' AND password = '" + user.getPassword() + "'";
		ResultSet rs = getFirst(columns, where);
		
		try {
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean register(User user) {
		String[] values = new String[] {user.getUsername(), user.getUnencodedPassword()};
		return insertOne("username, password", values);
	}
	
	public User getUser(String username) {
		String columns = "id, password";
		String where = "username = '" + username + "'";
		ResultSet rs = getFirst(columns, where);
		
		try {
			if (rs.next()) {
				int id = rs.getInt("id");
				String password = rs.getString("password");
				return new User(id, username, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
