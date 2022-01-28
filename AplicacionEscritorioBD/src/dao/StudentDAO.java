package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Student;

public class StudentDAO extends AbstractDAO {

	private TeacherDAO profeDAO;
	
	public StudentDAO() {
		this.profeDAO = new TeacherDAO();
	}

	public Student first() {
		final String query = "SELECT nombre, apellidos, ciclo, calificacionmedia FROM alumnos limit 1";
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				var id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				String ciclo = rs.getString("ciclo");
				double media = rs.getDouble("calificacionmedia");
				Student a = new Student(id, nombre, apellidos, ciclo, media);
				return a;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Student> getAll() {
		final String query = "SELECT id, nombre, apellidos, ciclo, calificacionmedia, idprofe1, idprofe2 FROM alumnos";
		var alumnos = new ArrayList<Student>();
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				var id = rs.getInt("id");
				var nombre = rs.getString("nombre");
				var apellidos = rs.getString("apellidos");
				var ciclo = rs.getString("ciclo");
				var media = rs.getDouble("calificacionmedia");
				Student a = new Student(id, nombre, apellidos, ciclo, media);
				a.setProfe1(profeDAO.getOne(rs.getInt("idprofe1")));
				a.setProfe2(profeDAO.getOne(rs.getInt("idprofe2")));				
				alumnos.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alumnos;
	}
	
	public void insert(Student a) {
		final String INSERT = "INSERT INTO alumnos(`nombre`,`apellidos`,`ciclo`,`calificacionMedia`"
				+ ",idProfe1, idProfe2)\r\n"
				+ "VALUES('"+a.getNombre()+"','"+a.getApellidos()+"','"+a.getCiclo()+"',"+a.getMedia()
				+ ","+a.getProfe1().getId()+","
				+ (a.getProfe2() != null 
						? String.valueOf(a.getProfe2().getId()) 
						: "NULL") 
						+");";
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(INSERT);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Student a) {
		final String DELETE = "delete from alumnos where id = " + a.getId();
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(DELETE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Student a) {
		final String UPDATE = "UPDATE alumnos\r\n"
				+ "SET\r\n"
				+ "nombre = '"+a.getNombre()+"',\r\n"
				+ "apellidos = '"+a.getApellidos()+"',\r\n"
				+ "ciclo = '"+a.getCiclo()+"',\r\n"
				+ "calificacionMedia = "+a.getMedia()+"\r\n"
				+ "WHERE id = "+a.getId()+";";
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(UPDATE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
