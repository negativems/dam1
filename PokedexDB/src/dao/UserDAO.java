package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.User;

public class UserDAO {

	private final String DB_URL = "jdbc:mysql://localhost/pokedex";
	private final String USER = "root";
	private final String PASS = "root";

	public boolean login(User user) {
		try {
			Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement statement = connection.createStatement();
			String query = "SELECT username, password FROM users WHERE (username = '" + user.getUsername() + "' AND password = '" + user.getPassword() + "');";
			ResultSet rs = statement.executeQuery(query);
			
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean register(User user) {
		try {
			Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement statement = connection.createStatement();
			String query = "INSERT INTO pokedex.users (username, password) VALUES ('" + user.getUsername() + "', '" + user.getPassword() + "');";
			return statement.executeUpdate(query) != 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
