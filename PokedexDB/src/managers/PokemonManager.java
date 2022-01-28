package managers;

import java.util.ArrayList;
import java.util.List;

import dao.PokemonDAO;
import models.Gender;
import models.Pokemon;

public class PokemonManager {

	private final PokemonDAO pokemonDAO;
	private final List<Pokemon> pokemons;
	
	public PokemonManager() {
		this.pokemonDAO = new PokemonDAO();
		this.pokemons = pokemonDAO.getAllFromDatabase();
	}
	
	public void addPokemon(Pokemon pokemon) {
		pokemons.add(pokemon);
	}
	
	public void savePokemons() {	
		List<String[]> values = new ArrayList<>();
		
		for (Pokemon pokemon : pokemons) {
			String id = pokemon.getId() + "";
			String name = "'" + pokemon.getName() + "'";
			String description = "'" + pokemon.getDescription() + "'";
			String specie = "'" + pokemon.getSpecie() + "'";
			String ability = "'" + pokemon.getAbility() + "'";
			String imageURL = "'" + pokemon.getImageURL() + "'";
			String height = pokemon.getHeight() + "";
			String weight = pokemon.getWeight() + "";
			String gender = pokemon.getGender() == Gender.MACHO ? "0" : "1";
			String[] valuesArray = new String[] {id, name, description, specie, ability, imageURL, height, weight, gender};
			values.add(valuesArray);
		}
		
		pokemonDAO.delete("DELETE cFROM " + pokemonDAO.getTable());
		pokemonDAO.insert("PokemonID, Name, Description, Specie, Ability, ImageURL, Height, Weight, Gender", values);
	}
	
	// Getters & Setters
	public List<Pokemon> getPokemons() {
		return this.pokemons;
	}
}
