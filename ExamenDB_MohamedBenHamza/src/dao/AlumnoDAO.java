package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Alumno;

public class AlumnoDAO extends AbstractDAO{

	private ProfeDAO profeDAO;

	public AlumnoDAO() {
		super("alumnos");
		this.profeDAO = new ProfeDAO();
	}

	public Alumno first() {
		String query = "SELECT nombre, apellidos, ciclo, calificacionmedia "
					   + "FROM " + table + " limit 1";
		
		try {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				var id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				String ciclo = rs.getString("ciclo");
				double media = rs.getDouble("calificacionmedia");
				Alumno alumno = new Alumno(id, nombre, apellidos, ciclo, media);
				return alumno;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Alumno> getAll() {
		final String QUERY = "SELECT id, nombre, apellidos, ciclo, calificacionmedia" + ",idprofe1, idprofe2  "
				+ "FROM " + table;
		var alumnos = new ArrayList<Alumno>();
		try {
			ResultSet rs = statement.executeQuery(QUERY);
			while (rs.next()) {
				var id = rs.getInt("id");
				var nombre = rs.getString("nombre");
				var apellidos = rs.getString("apellidos");
				var ciclo = rs.getString("ciclo");
				var media = rs.getDouble("calificacionmedia");
				Alumno a = new Alumno(id, nombre, apellidos, ciclo, media);
				a.setProfe1(profeDAO.get(rs.getInt("idprofe1")));
				a.setProfe2(profeDAO.get(rs.getInt("idprofe2")));
				alumnos.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alumnos;
	}

	public void insert(Alumno a) {
		String name = a.getNombre();
		String lastName = a.getApellidos();
		String ciclo = a.getCiclo();
		String media = a.getMedia() + "";
		String profe1 = a.getProfe1().getId() + "";
		String profe2 = a.getProfe2() != null ? String.valueOf(a.getProfe2().getId()) : "NULL";
		
		String insert = "INSERT INTO " + table
				+ "(nombre, apellidos, ciclo, calificacionMedia, idProfe1, idProfe2)\r\n"
				+ "VALUES('"
				+ name + "','" + lastName + "','" + ciclo + "'," + media + "," + profe1 + "," + profe2
				+ ");";
		
		try {
			statement.executeUpdate(insert);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Alumno a) {
		try {
			statement.executeUpdate("DELETE from " + table + " WHERE id = " + a.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Alumno a) {
		String id = a.getId() + "";
		String name = a.getNombre();
		String lastName = a.getApellidos();
		String ciclo = a.getCiclo();
		String media = a.getMedia() + "";
		String profe1 = a.getProfe1().getId() + "";
		String profe2 = a.getProfe2() != null ? String.valueOf(a.getProfe2().getId()) : "NULL";
		
		final String update = "UPDATE alumnos\r\n" + "SET\r\n" + "nombre = '" + name + "',\r\n"
				+ "apellidos = '" + lastName + "',\r\n" + "ciclo = '" + ciclo + "',\r\n"
				+ "calificacionMedia = " + media + ",\r\n" + "idprofe1 = " + profe1 + ",\r\n"
				+ "idprofe2 = " + profe2 + "\r\n"
				+ "WHERE id = " + id + ";";
		
		try {
			statement.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
