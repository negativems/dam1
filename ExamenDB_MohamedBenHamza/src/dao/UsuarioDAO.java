package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import models.Usuario;

public class UsuarioDAO extends AbstractDAO {	

	public UsuarioDAO() {
		super("users");
	}
	
	public void consulta() {
		final String QUERY = "SELECT username, password FROM " + table;
		try {
			ResultSet rs = statement.executeQuery(QUERY);
			while (rs.next()) {
				// Display values
				System.out.print("Username: " + rs.getString("username"));
				System.out.println(", Password: " + rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean login(Usuario user) {
		String username = user.getUsername();
		String password = user.getPassword();
		
		String query = "SELECT username, password FROM " + table + " WHERE "
					   + "username = '" + username + "' AND password = '" + password + "'";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void register(Usuario user) {
		String username = user.getUsername();
		String password = user.getPassword();
		String insert = "INSERT INTO " + table + "(username, password)\r\n"
						+ "VALUES ('"+ username + "','"+ password +"')";
		
		try {
			statement.executeUpdate(insert);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
