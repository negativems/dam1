package ga.mmbh.cfgs;

import ga.mmbh.cfgs.managers.PokemonManager;
import ga.mmbh.cfgs.managers.UserManager;
import ga.mmbh.cfgs.views.LoginView;

public class PokedexDB {
	
	private static PokedexDB instance;
	
	private PokemonManager pokemonManager;
	private UserManager userManager;
	
	public PokedexDB() {
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
	
	public static PokedexDB getInstance() {
		return instance;
	}
}
