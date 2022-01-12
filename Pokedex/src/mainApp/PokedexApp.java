package mainApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import combat.CombatGame;
import models.Pokemon;
import models.User;
import views.LoginView;

public class PokedexApp {

	private static List<Pokemon> pokemons = new ArrayList<>();
	private static List<User> usersList = new ArrayList<>();
	public static User user;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		usersList.add(new User("test", "test"));
		addPokemons();
		
		System.out.print("1. Abrir pokedex.\n2. Combatir\n¿Deseas combatir or abrir la pokedex?: ");
		
		boolean running = true;
		while (running) {
			String actionString = scanner.nextLine();
			switch (actionString) {
			case "1":
				System.out.println("Abriendo pokedex...");
				new LoginView();
				running = false;
				break;
			case "2":
				running = false;
				new CombatGame();
				break;
			default:
				System.out.println("Opción inválida, vuelve a intentarlo");
				break;
			}
		}
		
		scanner.close();
	}
	
	// Methods
	private static void addPokemons() {
		pokemons.add(new Pokemon(1, "Bulbasur", "Este Pokémon nace con una semilla en el lomo, que brota con el paso del tiempo.", "Semilla", "Espesura", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png", 0.7F, 6.9F));
		pokemons.add(new Pokemon(2, "Ivysaur", "Cuando le crece bastante el bulbo del lomo, pierde la capacidad de erguirse sobre las patas traseras.", "Semilla", "Espesura", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/002.png", 1F, 13F));
		pokemons.add(new Pokemon(3, "Venusaur", "La planta florece cuando absorbe energía solar, lo cual le obliga a buscar siempre la luz del sol.", "Semilla", "Espesura", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/003.png", 2F, 100F));
		pokemons.add(new Pokemon(4, "Charmander", "Prefiere las cosas calientes. Dicen que cuando llueve le sale vapor de la punta de la cola.", "Fuego", "Lagartija", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/004.png", 2F, 100F));
	}
	
	
	// Getters and Setters
	public static List<Pokemon> getPokemons() {
		return pokemons;
	}

	public static List<User> getUsersList() {
		return usersList;
	}

}
