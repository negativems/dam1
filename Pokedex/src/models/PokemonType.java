package models;

public enum PokemonType {
	
	ACERO,
	VOLADOR,
	AGUA,
	HIELO,
	PLANTA,
	BICHO,
	ELECTRICO,
	NORMAL,
	ROCA,
	TIERRA,
	FUEGO,
	LUCHA,
	HADA,
	PSIQUICO,
	VENENO,
	DRAGON,
	FANTASMA,
	SINIESTRO;
	
	private double efectividad = 0.5;
	
	public double getEfectividad() {
		return efectividad;
	}
	
}
