package ga.mmbh.cfgs.pokedexdb;

import java.util.TreeSet;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import ga.mmbh.cfgs.pokedexdb.models.CorredorDeFondo;

public class MainApp {

	public static void main(String[] args) {
		DateTime now = DateTime.now();
		
		TreeSet<CorredorDeFondo> listaCorredores = new TreeSet<CorredorDeFondo>();
		Interval intervalo1 = new Interval(now.getMillis(), now.getMillis() + 1265);
		Interval intervalo2 = new Interval(now.getMillis(), now.getMillis() + 2643);
		Interval intervalo3 = new Interval(now.getMillis(), now.getMillis() + 5574);
		
		listaCorredores.add(new CorredorDeFondo("Sonic", intervalo1));
		listaCorredores.add(new CorredorDeFondo("Dash (el de los increibles)", intervalo2));
		listaCorredores.add(new CorredorDeFondo("Flash", intervalo3));
		
		for (CorredorDeFondo corredor : listaCorredores) {
			String name = corredor.getName();
			double difference = corredor.getDifference() / 1000;
			System.out.println("¡El corredor " + name + " ha terminado en tan solo "+ difference + " segundos!");
		}
	}
	
}
