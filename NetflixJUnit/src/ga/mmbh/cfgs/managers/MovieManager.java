package ga.mmbh.cfgs.managers;

import java.util.ArrayList;
import java.util.List;

import ga.mmbh.cfgs.dao.MovieDAO;
import ga.mmbh.cfgs.models.Movie;
import ga.mmbh.cfgs.pokedexdb.models.Gender;
import ga.mmbh.cfgs.pokedexdb.models.Pokemon;

public class MovieManager {

	private final MovieDAO movieDAO;
	private final List<Movie> movies;
	
	public MovieManager() {
		this.movieDAO = new MovieDAO();
		this.movies = movieDAO.getAllFromDatabase();
	}
	
	public void addMovie(Movie movie) {
		movies.add(movie);
	}
	
	public void saveMovies() {	
		List<String[]> values = new ArrayList<>();
		
		for (Movie movie : movies) {
			String id = movie.getId() + "";
			String name = "'" + movie.getName() + "'";
			String minAge = movie.getMinAge() + "";
			String director = "'" + movie.getDirector() + "'";
			String genre = "'" + movie.getGenre().name() + "'";
			String duration = movie.getDuration() + "";
			String imageURL = "'" + movie.getImageURL() + "'";
			String[] valuesArray = new String[] {id, name, minAge, director, genre, duration, imageURL};
			values.add(valuesArray);
		}
		
		movieDAO.delete("DELETE FROM " + movieDAO.getTable());
		movieDAO.insert("PokemonID, Name, Description, Specie, Ability, ImageURL, Height, Weight, Gender", values);
	}
	
	// Getters & Setters
	public List<Pokemon> getPokemons() {
		return this.movies;
	}
}
