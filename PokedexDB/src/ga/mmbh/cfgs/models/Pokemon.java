package ga.mmbh.cfgs.models;

import java.util.ArrayList;
import java.util.List;

import ga.mmbh.cfgs.utils.AppUtils;

public class Pokemon {

	protected int id;
	protected String name, description, specie, ability, imageURL;
	protected Gender gender;
	protected final List<PokemonType> types = new ArrayList<>();
	protected float height, weight;
	
	/**
	 * Constructor for Pokemon
	 * @param id Pokemons id
	 * @param name Pokemons name
	 * @param description Pokemons description
	 * @param specie Pokemons category
	 * @param ability Pokemons ability
	 * @param imageURL Pokemons image URL
	 * @param height Pokemon height
	 * @param weight Pokemon weight
	 */
	public Pokemon(int id, String name, String description, String specie, String ability, String imageURL, float height, float weight, Gender gender) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.specie = specie;
		this.ability = ability;
		this.imageURL = imageURL;
		this.height = height;
		this.weight = weight;
		this.gender = gender;
	}

	public void addType(PokemonType type) {
		types.add(type);
	}
	
	/**
	 * Get a string with the pokemon types
	 * @return String
	 */
	public String getFormattedTypes() {
		if (types.isEmpty()) return "";
		
		String result = "";
		for (PokemonType type : types) {
			result = result + type + ", ";
		}
		
		return result.substring(0, result.length() - 2);
	}

	public String getFormattedId() {
		return AppUtils.getFormattedPokemonId(id);
	}
	
	// Getters & Setters
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getSpecie() {
		return specie;
	}
	
	public void setSpecie(String specie) {
		this.specie = specie;
	}

	public String getAbility() {
		return ability;
	}

	public void setAbility(String ability) {
		this.ability = ability;
	}
	
	public String getImageURL() {
		return imageURL;
	}
	
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public List<PokemonType> getTypes() {
		return types;
	}

	public float getHeight() {
		return height;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
