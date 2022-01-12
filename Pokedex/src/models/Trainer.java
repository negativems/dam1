package models;

public class Trainer {

	private final String name;
	private final Team team;
	
	public Trainer(String nombre) {
		this.name = nombre;
		this.team = new Team();
	}
	
	// Methods
	public double getEfectividad(PokemonType pokemonType) {
		return pokemonType.getEfectividad();
	}
	
	// Getters & Setters
	public String getName() {
		return name;
	}
	
	public Team getTeam() {
		return team;
	}
	
}
