package models;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {

	protected int id;
	protected String name, description, category, ability, imageURL, soundURL;
	protected List<PokemonType> types;
	protected float height, weight;
	
	/**
	 * Constructor for Pokemon
	 * @param id Identificador del pokemon
	 * @param name Nombre del pokemon
	 * @param description Descripción del pokemon
	 * @param category Categoria del pokemon
	 * @param ability Habilidad del pokemon
	 * @param imageURL Dirección URL de la imagen del pokemon
	 * @param height Altura del pokemon
	 * @param weight Peso 
	 */
	public Pokemon(int id, String name, String description, String category, String ability,
					String imageURL, float height, float weight) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.category = category;
		this.ability = ability;
		this.imageURL = imageURL;
		this.height = height;
		this.weight = weight;
		this.types = new ArrayList<>();
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

	public String getCategory() {
		return category;
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

	public float getHeight() {
		return height;
	}

	public float getWeight() {
		return weight;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
