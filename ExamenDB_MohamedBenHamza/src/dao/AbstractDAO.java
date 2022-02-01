package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDAO {
	
	protected final String database = "prog_alumnos";
	protected final String table;
	private final String url = "jdbc:mysql://localhost/" + database;
	private final String username = "root";
	private final String password = "root";
	
	protected Connection connection;
	protected Statement statement;
	
	public AbstractDAO(String table) {
		this.table = table;
		
		try {
			this.connection = DriverManager.getConnection(url, username, password);
			this.statement = connection.createStatement();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
