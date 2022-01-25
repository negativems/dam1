package dao;

//CTRL + SHIFT + O
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Usuario;
import utils.EmailUtils;

public class UserDAO {
	final String DB_URL = "jdbc:mysql://localhost/prog_alumnos";
	final String USER = "root";
	final String PASS = "root";
	

	public void consulta() {
		final String QUERY = "SELECT username, password FROM users";
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(QUERY);
			while (rs.next()) {
				// Display values
				System.out.print("Username: " + rs.getString("username"));
				System.out.println(", Password: " + rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean login(Usuario usuario) {
		String username = usuario.getUsername();
		if (username.contains("@") && !EmailUtils.validate(username)) {
			return false;
		}
		
		final String QUERY = "SELECT username, password FROM users "+
							"where username = '" + usuario.getUsername() + "' and "+
							"password = '" + usuario.getPassword() + "'";
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(QUERY);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean register(Usuario usuario) {
		String username = usuario.getUsername();
		if (username.contains("@") && !EmailUtils.validate(username)) {
			return false;
		}
		
		final String INSERT = "INSERT INTO users (username,password)"
				+ " VALUES ('"+ usuario.getUsername() + "','"+ usuario.getPassword() +"');";
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(INSERT);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
