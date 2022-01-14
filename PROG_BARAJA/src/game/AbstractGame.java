package game;

import java.util.ArrayList;
import java.util.List;

import mainapp.Main;
import models.Baraja;
import models.player.AbstractPlayer;
import models.player.HumanPlayer;
import models.player.MachinePlayer;

public abstract class AbstractGame {

	private GameType tipoJuego;
	private GameMode modoJuego;
	private int maxJugadores;

	protected Baraja baraja;
	protected List<AbstractPlayer> jugadores = new ArrayList<>();
	protected boolean running = true;

	public AbstractGame(GameType tipoJuego, GameMode modoJuego) {
		this.tipoJuego = tipoJuego;
		this.modoJuego = modoJuego;

		// System.out.print("¿Cuantos jugadores van a haber? (minimo 2): ");
		// this.maxJugadores = JavaUtils.getInteger(Main.scanner.nextLine(), 2);
		this.maxJugadores = 2;
		
		definirJugadores();

		// System.out.println(TextUtils.TEXT_LINE);
		// System.out.println("Juego: " + tipoJuego.name().toLowerCase().replaceAll("_", " "));
		// System.out.println("Modo de juego: " + modoJuego.name().toLowerCase());
		// System.out.println("Cantidad jugadores: " + maxJugadores);
		// System.out.println(TextUtils.TEXT_LINE);
	}

	public abstract void bienvenida();

	public abstract void start();

	public void definirJugadores() {
		if (modoJuego == GameMode.SOLITARIO) {
			jugadores.add(new MachinePlayer());
		}
		
		int cantidadJugadores = jugadores.size();
		for (int i = 0; i < maxJugadores - cantidadJugadores; i++) {
			System.out.print("Escribe el nombre del jugador " + (i + 1) + ": ");
			String nombre = Main.scanner.nextLine();
			while (nombre.length() == 0) {
				System.out.print("Nombre no válido, vuelve a escribirlo: ");
				nombre = Main.scanner.nextLine();
			}
			
			jugadores.add(new HumanPlayer(nombre));
		}
	}

	public void finish() {

	}
}
