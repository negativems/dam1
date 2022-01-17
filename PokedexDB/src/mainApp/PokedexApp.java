package mainApp;

import java.util.ArrayList;
import java.util.List;

import managers.PokemonManager;
import managers.UserManager;
import models.Gender;
import models.Pokemon;
import models.User;
import views.LoginView;

public class PokedexApp {

	private static PokemonManager pokemonManager;
	private static UserManager userManager;
	
	public static void main(String[] args) {
		pokemonManager = new PokemonManager();
		userManager = new UserManager();
		new LoginView();
	}

}
