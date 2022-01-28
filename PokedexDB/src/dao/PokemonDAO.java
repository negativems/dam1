package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Gender;
import models.Pokemon;

public class PokemonDAO extends AbstractDAO {
	
	public PokemonDAO() {
		super("Pokemons");
	}
	
    /**
     * Gets all pokemons from database
     * @deprecated
     * This method is no longer acceptable to compute time between versions.
     * Use #getFromDatabase(int startingId, int limit) instead.
     *
     */
    @Deprecated
	public List<Pokemon> getAllFromDatabase() {
    	List<Pokemon> result = new ArrayList();
		ResultSet rs = getAll("PokemonID, Name, Description, Specie, Ability, ImageURL, Height, Weight, Gender");
		
		try {
			while (rs.next()) {
				int id = rs.getInt("PokemonID");
				String name = rs.getString("Name");
				String description = rs.getString("Description");
				String specie = rs.getString("Specie");
				String ability = rs.getString("ability");
				String imageURL = rs.getString("ImageURL");
				float height = rs.getFloat("height");
				float weight = rs.getFloat("weight");
				boolean gender = rs.getBoolean("gender");
				Pokemon pokemon = new Pokemon(id, name, description, specie, ability, imageURL, height, weight, (gender ? Gender.HEMBRA : Gender.MACHO));
				result.add(pokemon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
