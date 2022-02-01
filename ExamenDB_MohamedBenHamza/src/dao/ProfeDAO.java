package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Alumno;
import models.Profesor;

public class ProfeDAO extends AbstractDAO{
	
	public ProfeDAO() {
		super("profesores");
	}
	
	public ArrayList<Profesor> getAll() {
		final String query = "SELECT id, nombre FROM " + table;
		var profesores = new ArrayList<Profesor>();
		
		try {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				var id = rs.getInt("id");
				var nombre = rs.getString("nombre");
				Profesor p = new Profesor(id, nombre);
				profesores.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return profesores;
	}
	
	/**
	 * Busca el Profesor por su ID y si no lo encuentra devuelve NULL.
	 * @param idProfe
	 * @return
	 */
	public Profesor get(int idProfe) {
		final String QUERY = "SELECT id, nombre "
				+ "FROM profesores where id = " + idProfe;
		try {
			ResultSet rs = statement.executeQuery(QUERY);
			while (rs.next()) {
				var id = rs.getInt("id");
				var nombre = rs.getString("nombre");
				
				
				
				Profesor p = new Profesor(id, nombre);
				return p;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void insert(Profesor profesor) {
		int id = profesor.getId();
		String name = profesor.getNombre();
		
		String insert = "INSERT INTO " + table + "(id, nombre)\r\n"
				+ "VALUES('"
				+ id + "', '" + name + "'"
				+ ");";
		
		try {
			statement.executeUpdate(insert);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
