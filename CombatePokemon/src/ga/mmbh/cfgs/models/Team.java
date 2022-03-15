package models;

import java.util.ArrayList;
import java.util.List;

public class Team {

	private List<Pokemon> pokemons;
	
	public Team() {
		this.pokemons = new ArrayList<>();
	}
	
	
	public void addPokemon(Pokemon pokemon) {
		pokemons.add(pokemon);
	}
	
	// Getters & Setters	
	public List<Pokemon> getPokemons() {
		return pokemons;
	}
	
	
}
