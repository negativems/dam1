package ga.mmbh.cfgs.pokedexdb;

public class Calculadora {
	
	public static int sumar(int n1, int n2) {
		return n1 + n2;
	}
	
	public static int restar(int n1, int n2) {
		return n1 - n2;
	}
	
	public static int multiplicar(int n1, int n2) {
		return n1 * n2;
	}
	
	public static int dividir(int n1, int n2) {
		if (n2 == 0) return 0;
		return n1 / n2;
	}
	
	public static boolean esPositivo(int n) {
		return n > 0;
	}
	
	public static boolean esPrimo(int n) {
		  if (n == 0 || n == 1 || n == 4) {
		    return false;
		  }
		  
		  for (int i = 2; i < n / 2; i++) {
		    if (n % i == 0) return false;
		  }
		  
		  return true;
	}
	
}
