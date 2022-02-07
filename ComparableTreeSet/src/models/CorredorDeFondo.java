package models;

import org.joda.time.Interval;

public class CorredorDeFondo implements Comparable<CorredorDeFondo>{

	private final String name;
	private final Interval tiempoMarca;
	
	public CorredorDeFondo(String name, Interval interval) {
		this.name = name;
		this.tiempoMarca = interval;
	}

	public double getDifference() {
		return tiempoMarca.getEndMillis() - tiempoMarca.getStartMillis();
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public int compareTo(CorredorDeFondo o) {
		return (int) (getDifference() - o.getDifference());
	}
	
}
