package models;

public class Student {
	private int id;
	private String nombre;
	private String apellidos;
	private String ciclo;
	private double media;
	private Teacher profe1;
	private Teacher profe2;
	
	public Student(int id, String nombre, String apellidos, String ciclo, double media) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.ciclo = ciclo;
		this.media = media;
	}

	public int getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	public Teacher getProfe1() {
		return profe1;
	}

	public void setProfe1(Teacher profe1) {
		this.profe1 = profe1;
	}

	public Teacher getProfe2() {
		return profe2;
	}

	public void setProfe2(Teacher profe2) {
		this.profe2 = profe2;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Alumno [nombre=" + nombre + ", apellidos=" + apellidos + ", ciclo=" + ciclo + ", media=" + media + "]";
	}
}
