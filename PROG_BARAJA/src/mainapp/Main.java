package mainapp;

import java.util.Scanner;

import game.AbstractGame;
import game.GameMode;
import game.impl.SieteYMedia;
import utils.JavaUtils;
import utils.TextUtils;

public class Main {
	
	public static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println(TextUtils.TEXT_LINE);
		System.out.println("     BIENVENIDO AL CASINO");
		System.out.println(TextUtils.TEXT_LINE + "\n");
		
		// System.out.println("¿A qué quieres jugar?\n1. Siete y media");
		// int juego = JavaUtils.getInteger(scanner.nextLine(), 1, 1);
		
		System.out.print("1. 1VS1\n2. La compu\n¿Contra quién quieres jugar al juego de las 7 y media?: ");
		int tipoJuego = JavaUtils.getInteger(scanner.nextLine(), 1, 2);
		
		GameMode gameMode = tipoJuego == 1 ? GameMode.PVP : GameMode.SOLITARIO;
		
		AbstractGame game = new SieteYMedia(gameMode);
		game.start();
	}
}
