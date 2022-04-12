package ga.mmbh.cfgs.pokedexdb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ga.mmbh.cfgs.pokedexdb.models.Gender;
import ga.mmbh.cfgs.pokedexdb.models.Pokemon;

public class PokemonDAO extends AbstractDAO {
	
	public PokemonDAO() {
		super("Pokemons");
	}
	
    /**
     * Gets all pokemons from database
     * @deprecated
     * This method is no longer acceptable to compute time between versions.
     * Use #getFromDatabase(int startingId, int limit) instead.
     */
    @Deprecated
	public List<Pokemon> getAllFromDatabase() {
    	List<Pokemon> result = new ArrayList();
		ResultSet rs = getAll("pokemon_id, name, description, specie, ability, image_url, height, weight, gender");
		
		try {
			while (rs.next()) {
				int id = rs.getInt("pokemon_id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String specie = rs.getString("specie");
				String ability = rs.getString("ability");
				String imageURL = rs.getString("image_url");
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
