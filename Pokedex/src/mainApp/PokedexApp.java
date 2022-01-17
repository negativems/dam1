package mainApp;

import managers.PokemonManager;
import managers.UserManager;
import views.LoginView;

public class PokedexApp {
	
	public static PokedexApp instance;
	
	private PokemonManager pokemonManager;
	private UserManager userManager;
	
	public PokedexApp() {
		instance = this;
		
		pokemonManager = new PokemonManager();
		userManager = new UserManager();
		
		new LoginView(this);
	}
	
	// Getters & Setters
	public PokemonManager getPokemonManager() {
		return pokemonManager;
	}

	public UserManager getUserManager() {
		return userManager;
	}
}
