package ga.mmbh.cfgs.pokedexdb;

import ga.mmbh.cfgs.pokedexdb.managers.PokemonManager;
import ga.mmbh.cfgs.pokedexdb.managers.UserManager;
import ga.mmbh.cfgs.pokedexdb.views.LoginView;

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
