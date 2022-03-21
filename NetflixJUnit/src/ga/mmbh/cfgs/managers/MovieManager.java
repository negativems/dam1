package ga.mmbh.cfgs.managers;

import java.util.ArrayList;
import java.util.List;

import ga.mmbh.cfgs.models.Genre;
import ga.mmbh.cfgs.models.Movie;

public class MovieManager {

	private final List<Movie> movies;

	public MovieManager() {
		this.movies = new ArrayList<>();
		addMovie(new Movie(0, "Scary Movie", 18, "Brendan Eich", Genre.DRAMA, 124, "https://cdn.hobbyconsolas.com/sites/navi.axelspringer.es/public/styles/480/public/media/image/2020/10/scary-movie-2000-2113037.jpg"));
	}
	
	public boolean addMovie(Movie movie) {
		return movies.add(movie);
	}
	
	public boolean removeMovie(Movie movie) {
		return movies.remove(movie);
	}
	
	// Validation checks
	public boolean isAllAudienceMovie(Movie movie) {
		return movie.getMinAge() < 7;
	}
	
	public boolean isAdultMovie(Movie movie) {
		return movie.getMinAge() >= 18;
	}
	
	public boolean isValidGenre(String genre) {
		try {
			Genre.valueOf(genre);
			return true;
		} catch(Exception e) {
			// Ignore catch
		}
		
		return false;
	}
	
	public boolean checkMovieNameLength(String name) {
		return name.length() > 30;
	}
	
	// Exists checks
	public boolean existsMovieName(String name) {
		return movies.stream().anyMatch(movie -> movie.getName().equalsIgnoreCase(name));
	}
	
	public boolean existsMovieId(int id) {
		return movies.stream().anyMatch(movie -> movie.getId() == id);
	}

	// Getters
	public List<Movie> getMovies() {
		return this.movies;
	}
}
