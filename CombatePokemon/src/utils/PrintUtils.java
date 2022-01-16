package utils;

public class PrintUtils {

	public static void clearConsole(int spaces) {
		for (int i = 0; i < spaces; i++) {
			System.out.println();
		}
	}
	
	public static void clearConsole() {
		clearConsole(12);
	}
	
}
