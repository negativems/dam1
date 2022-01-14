package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {

	private final String DB_URL = "jdbc:mysql://localhost/pokedex";
	private final String USER = "root";
	private final String PASS = "root";

	public boolean login(String username, String password) {
		try {
			Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement statement = connection.createStatement();
			String query = "SELECT username, password FROM users WHERE (username = '" + username + "' AND password = '" + password + "');";
			ResultSet rs = statement.executeQuery(query);
			
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
