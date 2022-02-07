package models;

import java.util.List;

public class CajaSchrodinger<T extends Animal> {

	private T animal;
	
	public void introducir(Animal animal) {
		this.animal = (T) animal;
	}
	
	public boolean observar() {
		return Math.random() <= 0.5;
	}
	
}
