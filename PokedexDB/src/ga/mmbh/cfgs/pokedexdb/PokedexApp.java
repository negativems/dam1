package ga.mmbh.cfgs.pokedexdb;

import ga.mmbh.cfgs.pokedexdb.managers.PokemonManager;
import ga.mmbh.cfgs.pokedexdb.managers.UserManager;
import ga.mmbh.cfgs.pokedexdb.views.LoginView;

public class PokedexApp {
	
	private static PokedexApp instance;
	
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
	
	public static PokedexApp getInstance() {
		return instance;
	}
}
