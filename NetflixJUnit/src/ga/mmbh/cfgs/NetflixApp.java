package ga.mmbh.cfgs;

import ga.mmbh.cfgs.managers.MovieManager;
import ga.mmbh.cfgs.managers.UserManager;
import ga.mmbh.cfgs.views.LoginView;

public class NetflixApp {
	
	private static NetflixApp instance;
	
	private MovieManager pokemonManager;
	private UserManager userManager;
	
	public NetflixApp() {
		instance = this;
		
		pokemonManager = new MovieManager();
		userManager = new UserManager();
		
		new LoginView(this);
	}
	
	// Getters & Setters
	public MovieManager getPokemonManager() {
		return pokemonManager;
	}

	public UserManager getUserManager() {
		return userManager;
	}
	
	public static NetflixApp getInstance() {
		return instance;
	}
}
