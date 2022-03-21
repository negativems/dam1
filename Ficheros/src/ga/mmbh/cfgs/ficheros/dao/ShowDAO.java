package ga.mmbh.cfgs.ficheros.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ga.mmbh.cfgs.ficheros.models.Show;

public class ShowDAO extends AbstractDAO {
	
	public ShowDAO() {
		super("Pokemons");
	}
	
    /**
     * Gets all pokemons from database
     * @deprecated
     * This method is no longer acceptable to compute time between versions.
     * Use #getFromDatabase(int startingId, int limit) instead.
     */
    @Deprecated
	public List<Show> getAllFromDatabase() {
    	List<Show> result = new ArrayList();
		ResultSet rs = getAll("show_id, type, title, director, cast, country, date_added, release_year, rating, duration, listed_in, description");
		
		try {
			while (rs.next()) {
				
				int id = rs.getInt("show_id");
				String type = rs.getString("type");
				String title = rs.getString("title");
				String director = rs.getString("director");
				String cast = rs.getString("cast");
				String country = rs.getString("country");
				String dateAdded = rs.getString("date_added");
				String releaseYear = rs.getString("release_year");
				String rating = rs.getString("rating");
				String duration = rs.getString("duration");
				String listedIn = rs.getString("listed_in");
				String description = rs.getString("description");
				
				Show show = new Show(id, type, title, director, cast, country, dateAdded, releaseYear, rating, duration, listedIn, description);
				result.add(show);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
