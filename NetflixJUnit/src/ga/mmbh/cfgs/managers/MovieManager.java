package ga.mmbh.cfgs.managers;

import java.util.ArrayList;
import java.util.List;

import ga.mmbh.cfgs.NetflixApp;
import ga.mmbh.cfgs.models.Movie;

public class MovieManager {

	private final List<Movie> movies;
   
   public MovieManager() {
      this.movies = new ArrayList<>();
   }

   public void addMovie(Movie movie) {
		movies.add(movie);
	}
	
	// Getters & Setters
	public List<Movie> getMovies() {
		return this.movies;
	}
}
