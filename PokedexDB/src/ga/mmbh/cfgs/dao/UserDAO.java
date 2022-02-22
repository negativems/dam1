package ga.mmbh.cfgs.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import ga.mmbh.cfgs.models.User;

public class UserDAO extends AbstractDAO {
	
	public UserDAO() {
		super("users");
	}

	public boolean login(User user) {
		String columns = "username, password";
		String where = "username = " + user.getUsername() + " AND password = " + user.getPassword();
		ResultSet rs = getFirst(columns, where);
		
		try {
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean register(User user) {
		String[] values = new String[] {user.getUsername(), user.getPassword()};
		return insertOne("username, password", values);
	}

}
