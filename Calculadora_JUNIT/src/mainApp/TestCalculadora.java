package mainApp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestCalculadora {

	@Test
	public void testSuma() {
		int resultado = Calculadora.sumar(2, 3);
		int esperado = 5;
		
		assertEquals(esperado, resultado);
	}
	
	@Test
	public void testResta() {
		assertEquals(2, Calculadora.restar(5, 3));
	}
	
	@Test
	public void testResta2() {
		assertEquals(-5, Calculadora.restar(2, 7));
	}
	
	@Test
	public void testMultiplicacion() {
		assertEquals(15, Calculadora.multiplicar(5, 3));
	}
	
	@Test
	public void testMultiplicacion2() {
		assertEquals(18, Calculadora.multiplicar(-9, -2));
	}
	
	@Test
	public void testMultiplicacion3() {
		assertEquals(0, Calculadora.multiplicar(-9, 0));
	}
	
	@Test
	public void testDivision() {
		assertEquals(1, Calculadora.dividir(5, 5));
	}
	
	@Test
	public void testDivision2() {
		assertEquals(-1, Calculadora.dividir(-5, 5));
	}
	
	@Test
	public void testDivision3() {
		assertEquals(0, Calculadora.dividir(10, 0));
	}
	
	@Test
	public void testPositivo() {	
		assertTrue(Calculadora.esPositivo(10));
	}
	
	@Test
	public void testPositivo2() {
		// Da fallo porque no es positivo
		assertTrue(Calculadora.esPositivo(-10));
	}
	
	@Test
	public void testPrimo() {	
		assertTrue(Calculadora.esPrimo(7));
	}
	
	@Test
	public void testPrimo2() {
		// Da fallo porque 10 no es primo
		assertTrue(Calculadora.esPrimo(10));
	}
	
	@Test
	public void testPrimo3() {
		assertTrue(Calculadora.esPrimo(-3));
	}

}
