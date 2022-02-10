package ga.mmbh.cfgs.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ga.mmbh.cfgs.models.Movie;

public class MovieDAO extends AbstractDAO {
	
	public MovieDAO() {
		super("Pokemons");
	}
	
    /**
     * Gets all movies from database
     * @deprecated
     * This method is no longer acceptable to compute time between versions.
     * Use #getFromDatabase(int startingId, int limit) instead.
     *
     */
    @Deprecated
	public List<Movie> getAllFromDatabase() {
    	List<Movie> result = new ArrayList();
		ResultSet rs = getAll("ID, name, min_age, director, genre, duration, image_url");
		
		try {
			while (rs.next()) {
				int ID = rs.getInt("ID");
				String name = rs.getString("name");
				int minAge = rs.getInt("min_age");
				String director = rs.getString("director");
				String genre = rs.getString("genre");
				long duration = rs.getLong("duration");
				String imageURL = rs.getString("image_url");
				Movie movie = new Movie(ID, name, minAge, director, genre, duration, imageURL);
				result.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
