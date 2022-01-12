package models;

import java.util.ArrayList;
import java.util.List;

public class Team {

	private final List<Pokemon> teamList;
	
	public Team() {
		this.teamList = new ArrayList<>();
	}
	
	public List<Pokemon> getPokemons() {
		return teamList;
	}

	@Override
	public String toString() {
		List<String> pokemonNames = new ArrayList<>();
		
		for (Pokemon pokemon : teamList) {
			pokemonNames.add(pokemon.getName());
		}
		
		return String.join(", ", pokemonNames);
	}
	
}
