package models;

public class Animal {
	
	protected final String name;
	private final int peso;
	
	public Animal(String name) {
		this.name = name;
		this.peso = (int) (Math.random() * 50);
	}
	
	public String toString() {
		return name;
	}
	
}
