package models;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {

	protected int id;
	protected String name, description, specie, ability, imageURL, soundURL;
	protected Gender gender;
	protected List<PokemonType> types;
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
	public Pokemon(int id, String name, String description, String specie, String ability,
					String imageURL, float height, float weight, Gender gender) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.specie = specie;
		this.ability = ability;
		this.imageURL = imageURL;
		this.height = height;
		this.weight = weight;
		this.gender = gender;
		this.types = new ArrayList<>();
	}

	public void addType(PokemonType type) {
		types.add(type);
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getSpecie() {
		return specie;
	}

	public String getAbility() {
		return ability;
	}

	public String getImageURL() {
		return imageURL;
	}

	public String getSoundURL() {
		return soundURL;
	}

	public List<PokemonType> getTypes() {
		return types;
	}
	
	public String getFormattedTypes() {
		if (types.isEmpty()) return "";
		
		String result = "";
		for (PokemonType type : types) {
			result = result + type + ", ";
		}
		
		return result.substring(0, result.length() - 2);
	}

	public float getHeight() {
		return height;
	}

	public float getWeight() {
		return weight;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
