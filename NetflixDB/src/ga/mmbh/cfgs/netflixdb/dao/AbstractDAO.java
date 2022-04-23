package ga.mmbh.cfgs.netflixdb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDAO {
	
	protected final String table, databaseURL, username, password, database = "Netflix";
	
	public AbstractDAO(String table) {
		this.table = table;
		databaseURL = "jdbc:mysql://localhost/" + database;
		username = "root";
		password = "";
	}
	
	public boolean isConnected() {
		return query("SELECT * FROM " + table + " LIMIT 1") != null;
	}
	
	// Gets data from database
	public ResultSet query(String query) {
		try {
			Connection connection = DriverManager.getConnection(databaseURL, username, password);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultSet getAll(String columns) {
		return query("SELECT " + columns + " FROM " + table);
	}
	
	public ResultSet getAll(String columns, String where) {
		return query("SELECT " + columns + " FROM " + table + " WHERE (" + where + ")");
	}
	
	public ResultSet getFirst(String columns) {
		return query("SELECT " + columns + " FROM " + table + " LIMIT 1");
	}
	
	public ResultSet getFirst(String columns, String where) {
		return query("SELECT " + columns + " FROM " + table + " WHERE (" + where + ") LIMIT 1");
	}
	
	// Inserts data to database
	public boolean insert(String query) {
		try {
			Connection conn = DriverManager.getConnection(databaseURL, username, password);
			Statement stmt = conn.createStatement();
			return stmt.executeUpdate(query) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean insertOne(String columns, String[] values) {
		try {
			Connection conn = DriverManager.getConnection(databaseURL, username, password);
			Statement stmt = conn.createStatement();
			
			String insert = "INSERT INTO " + table + "(" + columns + ") VALUES (" + String.join(", ", values) + ")";
			return stmt.executeUpdate(insert) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean insert(String columns, List<String[]> valuesList) {
		String insertValues = "";
		
		for (int i = 0; i < valuesList.size(); i++) {
			String[] values = valuesList.get(i);
			String line = "(";
			for (int z = 0; z < values.length; z++) {
				String value = values[z];
				value = value + (z < values.length - 1 ? "," : "");
				line += value;
			}
			insertValues += line + ")" + (i < valuesList.size() - 1 ? "," : "");
		}
		
		String insert = "INSERT INTO " + table + "(" + columns + ") VALUES " + insertValues;
		
		try {
			Connection conn = DriverManager.getConnection(databaseURL, username, password);
			Statement stmt = conn.createStatement();
			return stmt.executeUpdate(insert) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean insertOrUpdate(String columns, List<String[]> valuesList, String where) {
		String insertValues = "";
		
		for (int i = 0; i < valuesList.size(); i++) {
			String[] values = valuesList.get(i);
			String line = "(";
			for (int z = 0; z < values.length; z++) {
				String value = values[z];
				value = value + (z < values.length - 1 ? "," : "");
				line += value;
			}
			insertValues += line + ")" + (i < valuesList.size() - 1 ? "," : "");
		}
		
		String insert = "INSERT INTO " + table + "(" + columns + ") VALUES " + insertValues + " ON DUPLICATE KEY UPDATE " + where;
		
		try {
			Connection conn = DriverManager.getConnection(databaseURL, username, password);
			Statement stmt = conn.createStatement();
			return stmt.executeUpdate(insert) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// Update
	public void update(String set) {
		try {
			Connection conn = DriverManager.getConnection(databaseURL, username, password);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + table + " SET " + set);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(String set, String where) {
		try {
			Connection conn = DriverManager.getConnection(databaseURL, username, password);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + table + " SET " + set + " WHERE (" + where + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Delete
	public void delete() {
		try {
			Connection conn = DriverManager.getConnection(databaseURL, username, password);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + table);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(String where) {
		try {
			Connection conn = DriverManager.getConnection(databaseURL, username, password);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + table + " WHERE " + where);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public String getDatabase() {
		return this.database;
	}
	
	public String getTable() {
		return this.table;
	}
}
