package models;

import java.util.List;

public class Trainer {

	private String name;
	private Team team;
	
	public Trainer(String name) {
		this.name = name;
		this.team = new Team();
	}
	
	public void addPokemonToTeam(Pokemon pokemon) {
		team.addPokemon(pokemon);
	}
	
	/**
	 * Checks if the trainer can fight
	 * @return true if the trainer has any pokemon alive
	 */
	public boolean canFight() {
		List<Pokemon> pokemons = team.getPokemons();
		
		for (Pokemon pokemon : pokemons) {
			if (pokemon.getHealth() > 0) return true;
		}
		
		return false;
	}
	
	// Getters & Setters
	public void setName() {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Team getTeam() {
		return team;
	}
	
}
