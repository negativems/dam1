package mainApp;

import java.util.ArrayList;
import java.util.List;

import models.Animal;
import models.CajaSchrodinger;
import models.impl.Cerdo;
import models.impl.Gato;
import models.impl.Toro;

public class MainApp {

	public static void main(String[] args) {
		List<Animal> animales = new ArrayList<Animal>();
		animales.add(new Gato());
		animales.add(new Cerdo());
		animales.add(new Toro());
		animales.add(new Gato());
		animales.add(new Cerdo());
		
		CajaSchrodinger<Animal> caja = new CajaSchrodinger<>();
		int i = 0;
		for (Animal animal : animales) {
			caja.introducir(animal);
			
			if (!caja.observar()) {
				animales.set(i, null);
			}
			
			i++;
		}
		
		for (Animal animal : animales) {
			System.out.println(animal);
		}
	}
	
}
