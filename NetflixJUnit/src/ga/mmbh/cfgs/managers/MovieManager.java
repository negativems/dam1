package ga.mmbh.cfgs.managers;

import java.util.ArrayList;
import java.util.List;

import ga.mmbh.cfgs.NetflixApp;
import ga.mmbh.cfgs.models.Genre;
import ga.mmbh.cfgs.models.Movie;

public class MovieManager {

	private final List<Movie> movies;
   
   public MovieManager() {
      this.movies = new ArrayList<>();
      addMovie(new Movie(1, "Scary Movie", 18, "Francisco Franco", Genre.DRAMA, 124, "https://cdn.hobbyconsolas.com/sites/navi.axelspringer.es/public/styles/480/public/media/image/2020/10/scary-movie-2000-2113037.jpg?itok=KcKl34XK"));
   }

   public void addMovie(Movie movie) {
		movies.add(movie);
	}
	
	// Getters & Setters
	public List<Movie> getMovies() {
		return this.movies;
	}
}
