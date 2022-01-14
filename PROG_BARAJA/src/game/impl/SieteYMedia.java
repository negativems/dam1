package game.impl;

import java.util.ArrayList;
import java.util.List;

import game.AbstractGame;
import game.GameMode;
import game.GameType;
import mainapp.Main;
import models.Baraja;
import models.Carta;
import models.player.AbstractPlayer;
import models.player.MachinePlayer;
import utils.JavaUtils;
import utils.TextUtils;

public class SieteYMedia extends AbstractGame {

	private Baraja baraja;
	private int turno = 0;

	public SieteYMedia(GameMode modo) {
		super(GameType.SIETE_Y_MEDIA, modo);
		this.baraja = new Baraja(1, true);
		bienvenida();
		start();
	}

	@Override
	public void bienvenida() {
		System.out.println(TextUtils.TEXT_LINE);
		System.out.println("         BIENVENIDO AL JUEGO");
		System.out.println("          DE LAS 7 Y MEDIA");
		System.out.println(TextUtils.TEXT_LINE + "\n");

		TextUtils.clearConsole(9);
		System.out.println("Presiona intro para empezar...");
		Main.scanner.nextLine();
	}

	@Override
	public void start() {
		List<AbstractPlayer> jugadoresPlantados = new ArrayList<>();

		while (running) {
			AbstractPlayer player = jugadores.get(turno);

			if (!jugadoresPlantados.contains(player)) {
				System.out.println(TextUtils.TEXT_LINE);
				System.out.println("Cartas en la baraja: " + baraja.getNumeroCartas());
				System.out.println("Cartas repartidas: " + (40 - baraja.getNumeroCartas()) + "\n");

				System.out.println("Turno de: " + player.getNombre());
				System.out.println("Puntos: " + player.getValorBaraja7yMedia());
				System.out.println("Mano: " + (player.getMano() == null ? "" : player.getMano()));
				System.out.println(TextUtils.TEXT_LINE + "\n");

				if (!(player instanceof MachinePlayer)) TextUtils.clearConsole(4);
				
				System.out.print("1. Robar\n2. Plantarse\n¿Qué quieres hacer?: ");

				int opcion;
				if (player instanceof MachinePlayer) {
					opcion = ((MachinePlayer) player).eleccionAleatoria(player.getValorBaraja7yMedia());
					System.out.print(opcion);
				} else {
					opcion = JavaUtils.getInteger(Main.scanner.nextLine(), 1, 2);
				}

				switch (opcion) {
				case 1:
					Carta carta = baraja.robar();
					player.getMano().insertaCartaAlFinal(carta);

					System.out.println("Carta robada: " + carta.getNombreCarta() + " (" + carta.getValor7ymedia() + " puntos)");
					System.out.println("Puntos acumulados " + player.getValorBaraja7yMedia());
					break;
				case 2:
					System.out.println(player.getNombre() + " se ha plantado con " + player.getValorBaraja7yMedia() + " puntos...");
					jugadoresPlantados.add(player);
					
					if (jugadoresPlantados.size() == jugadores.size()) {
						AbstractPlayer ganador = jugadores.get(0);
						for (AbstractPlayer jugador : jugadores) {
							if (jugador.getValorBaraja7yMedia() > ganador.getValorBaraja7yMedia()) {
								ganador = jugador;
							}
						}

						System.out.println("\n¡¡¡El ganador es " + ganador.getNombre() + " con un total de " + ganador.getValorBaraja7yMedia() + " puntos!!!");
						this.running = false;
					}
					
					break;
				default:
					break;
				}

				if (running) {
					if (!(player instanceof MachinePlayer)) TextUtils.clearConsole(12);
					System.out.println("Presiona intro para pasar al siguiente turno");
				} else {
					TextUtils.clearConsole(10);
					System.out.println("Juego terminado, presiona intro para continuar.");
					Main.scanner.nextLine();
					return;
				}
				
				Main.scanner.nextLine();

				for (int i = 0; i < jugadores.size(); i++) {
					AbstractPlayer jugador = jugadores.get(i);
					if (jugador.getValorBaraja7yMedia() > 7.5) {
						System.out.println("\n" + jugador.getNombre() + " ha perdido...");
						jugadores.remove(jugadores.indexOf(jugador));
					}
				}

				if (jugadores.size() == 1) {
					System.out.println("¡¡¡" + jugadores.get(0).getNombre() + " ha ganado!!!");
					running = false;
				}
			}
			if (turno != jugadores.size() - 1) turno++;
			else turno = 0;
		}
	}

}
