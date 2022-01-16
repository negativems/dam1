package models;

import java.util.Scanner;

import utils.PrintUtils;
import utils.Storage;

public class Fight {

	private Trainer trainer1, trainer2, turn;
	private Pokemon pokemon1, pokemon2;
	private boolean fighting, showedHeader;
	
	public Fight(Trainer trainer1, Trainer trainer2) {
		this.trainer1 = trainer1;
		this.trainer2 = trainer2;
		pokemon1 = trainer1.getTeam().getPokemons().get(0);
		pokemon2 = trainer2.getTeam().getPokemons().get(0);
		fighting = true;
		start();
	}
	
	private void start() {
		Scanner scanner = new Scanner(System.in);
		while (fighting) {
			Trainer trainer = null;
			if (turn == null) {
				trainer = (pokemon1.getSpeed() > pokemon2.getSpeed() ? trainer1 : trainer2);
			} else {
				trainer = turn == trainer1 ? trainer2 : trainer1;
			}
			
			turn = trainer;
			Trainer trainerVictim = trainer == trainer1 ? trainer2 : trainer1;
			Pokemon attacker = trainer == trainer1 ? pokemon1 : pokemon2;
			Pokemon victim = attacker == pokemon1 ? pokemon2 : pokemon1;
			
			if (!showedHeader) {
				System.out.println("---------------------------");
				System.out.println(" " + attacker.getName() + "  VS  " + victim.getName());
				System.out.println("---------------------------\n");
				PrintUtils.clearConsole(10);
				
				showedHeader = true;
				System.out.print("Presiona intro para continuar...");			
				scanner.nextLine();
			}
			
			// Applies state if the pokemon has any state active
			State attackerState = attacker.getState();
			int damageByState = 0;
			if (attacker.getState() != null) {
				// TODO: make the other states
				switch (attackerState) {
					// A poisoned Pokémon will take damage equal to 1/16 of its maximum HP every turn
					case POISONED:
						double poisonDamage = attacker.getHealth() * (1.0D / 16.0D);
						attacker.setHealth((int) (attacker.getHealth() - poisonDamage));
						attacker.setState(null);
						damageByState = (int) poisonDamage;
						break;
					case BADLY_POISONED:
					case BURNED:
					case CONFUSED:
					case FLINCH:
					case FROZEN:
					case INFATUATION:
					case LEECH_SEED:
					case NONE:
					case PARALYZED:
					default:
						break;
				}
			}
			
			System.out.println("---------------------------");
			System.out.println("Turno de " + trainer.getName());
			System.out.println(attacker.getName() + (attacker.hasState() ? "(" + attacker.getState().name().toLowerCase() + ")" : "") + ":");
			System.out.println("  Ataque: " + attacker.getAttack());
			System.out.println("  Defensa: " + attacker.getDefense());
			System.out.println("  Vida: " + attacker.getHealth() + "/" + attacker.getMaxHealth() + " HP" + (damageByState != 0 ? " (-" + damageByState + "HP del veneno)" : ""));
			System.out.println("  Tipo: " + attacker.getType().name().toLowerCase() + (attacker.getType2() != null ? ", " + attacker.getType2().name().toLowerCase() : ""));
			System.out.println("---------------------------\n");
			
			PrintUtils.clearConsole(3);
			
			if (attacker.getHealth() < 0) {
				System.out.println("El pokemon no puede continuar la batalla, " + trainerVictim.getName() + " gana la batalla.");
				System.out.print("Presion intro para continuar...");
				scanner.nextLine();
				fighting = false;
				return;
			}
			
			// Shows all movements			
			int totalMovementsWithoutPP = 0;
			for (int i = 0; i < attacker.getMovements().size(); i++) {
				Movement movement = attacker.getMovements().get(i);
				if (movement.getActualPP() <= 0) totalMovementsWithoutPP++;
				System.out.println((i + 1) + ". " + movement.getName() + " (" + movement.getActualPP() + "/" + movement.getMaxPP() + "PP)" + (movement.hasState() ? " (" + movement.getState().name().toLowerCase() + ")" : ""));
			}
			
			if (totalMovementsWithoutPP == attacker.getMovements().size()) {
				System.out.println("\nNo te quedan movimientos con PP, pasas al siguiente turno...");
				System.out.println("Presion intro para continuar");
				scanner.nextLine();
			}
			
			System.out.print("Elige el ataque que quieres realizar: ");
			int atackNumber = Integer.parseInt(scanner.nextLine());
			
			Movement movement = attacker.getMovements().get(atackNumber - 1);
			
			// Checks if the pokemon can use that movement
			while (movement.getActualPP() == 0) {
				System.out.println("No puedes usar ese movimiento");
				System.out.print("Elige otro ataque: ");
				atackNumber = Integer.parseInt(scanner.nextLine());
				movement = attacker.getMovements().get(atackNumber - 1);
			}
			
			double damage = victim.receiveAttack(movement);
			movement.setActualPP(movement.getActualPP() - 1);
			
			// Attack
			try {
				System.out.println("Atacando con " + movement.getName() + "...");
				Thread.sleep(2000);
				System.out.println(victim.getName() + " (-" + damage + ")");
				Thread.sleep(2000);
				State movementState = movement.getState();
				if (movementState != State.NONE) {
					if (movement.getProbability() < Math.random()) {
						String stateName = movement.getState().name().toLowerCase();
						System.out.println(attacker.getName() + " ha aplicado " + stateName + " a " + victim.getName());
						victim.setState(movementState);
					}
				}
			} catch (InterruptedException e) {
				// ignore
			}
			
			PrintUtils.clearConsole();
		}
		
		scanner.close();
		end();
	}
	
	private void end() {
		
	}
}
