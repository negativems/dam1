package mainApp;

import java.util.Scanner;

import models.Fight;
import models.Movement;
import models.Pokemon;
import models.PokemonType;
import models.State;
import models.Trainer;
import utils.PrintUtils;
import utils.Storage;

public class MainApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		setupMovements();		
		
		System.out.println("---------------------------\n");
		System.out.println("Bienvenido al combate pokemon\n");
		System.out.println("---------------------------\n");
		
		PrintUtils.clearConsole(8);
		
		System.out.print("Adapta la pantalla y presiona intro para comenzar la batalla...");
		
		scanner.nextLine();
		
		Trainer trainer1 = new Trainer("Cantimplora");
		Trainer trainer2 = new Trainer("Pepito");
		trainer1.addPokemonToTeam(new Pokemon(1, 49, 49, 65, 65, 45, 103, 33, "Bulbasaur", PokemonType.GRASS, PokemonType.POISON));
		trainer2.addPokemonToTeam(new Pokemon(4, 52, 43, 60, 50, 65, 146, 50, "Skarmory", PokemonType.STEEL, PokemonType.FLYING));
		
		new Fight(trainer1, trainer2);
		
		scanner.close();
	}
	
	private static void setupMovements() {
		// Grass
		Storage.MOVEMENTS.add(new Movement("Tacle", PokemonType.GRASS, 56, 40, State.NONE, 1));
		Storage.MOVEMENTS.add(new Movement("Absorb", PokemonType.GRASS, 40, 20, State.NONE, 1));
		Storage.MOVEMENTS.add(new Movement("Sludge Bomb", PokemonType.POISON, 40, 20, State.POISONED, 0.03D));
		
		// Steel
		Storage.MOVEMENTS.add(new Movement("Drill Peck", PokemonType.FLYING, 32, 80, State.NONE, 1));
		Storage.MOVEMENTS.add(new Movement("Gyro Ball", PokemonType.STEEL, 26, 30, State.NONE, 1));
		Storage.MOVEMENTS.add(new Movement("Metal Claw", PokemonType.STEEL, 56, 50, State.NONE, 0.95));
	}
}
