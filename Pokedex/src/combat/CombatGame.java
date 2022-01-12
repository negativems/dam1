package combat;

import java.util.Scanner;

import mainApp.PokedexApp;
import models.Pokemon;
import models.Trainer;
import utils.TextUtils;

public class CombatGame {

	private final Trainer trainer1, trainer2;
	private Trainer turn;
	private boolean running = true;
	
	public CombatGame() {
		this.trainer1 = new Trainer("Jugador 1");
		this.trainer2 = new Trainer("Jugador 2");
		this.turn = trainer1;
		start();
	}
	
	private void choosePokemons() {
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < 2; i++) {
			TextUtils.clearConsole();
			Trainer trainer = i == 0 ? trainer1 : trainer2;
			
			for (int j = 0; j < PokedexApp.getPokemons().size(); j++) {
				Pokemon pokemon = PokedexApp.getPokemons().get(j);
				System.out.println((j + 1) + ". " + pokemon.getName());
			}
			System.out.print(trainer.getName() + " elige tu pokemon: ");
			int pokemonInput = Integer.parseInt(scanner.nextLine());
			trainer.getTeam().getPokemons().add(PokedexApp.getPokemons().get(pokemonInput));
			TextUtils.clearConsole();
		}
		scanner.close();
	}
	
	private void start() {
		Scanner scanner = new Scanner(System.in);
		while (running) {
			choosePokemons();
			
			System.out.println(TextUtils.TEXT_LINE);
			System.out.println("\n¡COMIENZA EL COMBATE POKEMON!\n");
			System.out.println("\t" + trainer1.getTeam() + "\n\t  vs\n\t" + trainer2.getTeam() + "\n");
			System.out.println(TextUtils.TEXT_LINE);
			TextUtils.clearConsole(5);
			System.out.println("Presiona intro para continuar...");
			scanner.nextLine();
			
			TextUtils.clearConsole(5);
			System.out.println(TextUtils.TEXT_LINE);
			System.out.println("\nTurno de " + turn.getName() + "\n");
			System.out.println(TextUtils.TEXT_LINE);
			
			for (int i = 0; i < ; i++) {
				
			}
			System.out.println("Elige el ataque que quieres efectuar:");
			
			turn = turn == trainer1 ? trainer2 : trainer1;
			running = false;
		}
		
		scanner.close();
	}
}
