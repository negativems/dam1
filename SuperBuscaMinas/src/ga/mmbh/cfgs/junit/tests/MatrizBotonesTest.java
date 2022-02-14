package ga.mmbh.cfgs.junit.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import ga.mmbh.cfgs.models.BotonMina;
import ga.mmbh.cfgs.models.BotonMina.Estado;
import ga.mmbh.cfgs.models.BotonMina.Valor;
import ga.mmbh.cfgs.models.Coordenadas;
import ga.mmbh.cfgs.models.MatrizBotones;
import ga.mmbh.cfgs.models.Util.Posicion;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MatrizBotonesTest {

	/**
	 * Comprueba si las adyacentes de todas las
	 * casillas existen/hay boton
	 */
	@Test
	@Order(1)
	public void testHayBoton() {
		// Creo una matriz de 5x5
		MatrizBotones matriz = new MatrizBotones(5, 5);
		int[][] matrizPrueba = new int[][] { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 }, { 4, 0 }, { 4, 1 } };

		// Pongo seis minas donde yo quiero para controlar los test
		for (int i = 0; i < 6; i++) {
			BotonMina boton = matriz.getBoton(matrizPrueba[i][0], matrizPrueba[i][1]);
			boton.setValor(Valor.MINA);

			boolean lateralSuperior = boton.getFil() == 0;
			boolean lateralIzquierdo = boton.getCol() == 0;
			boolean lateralInferior = boton.getFil() == matriz.getSize() - 1;
			boolean lateralDerecho = boton.getCol() == matriz.getSize() - 1;

			if (lateralSuperior) {
				assertTrue(matriz.hayBoton(boton, Posicion.INF));
			}

			if (lateralInferior) {
				assertTrue(matriz.hayBoton(boton, Posicion.SUP));
			}

			if (lateralIzquierdo) {
				assertTrue(matriz.hayBoton(boton, Posicion.DER));
			}

			if (lateralDerecho) {
				assertTrue(matriz.hayBoton(boton, Posicion.IZQ));
			}

			if (!lateralSuperior && !lateralDerecho && !lateralInferior && !lateralIzquierdo) {
				assertTrue(matriz.hayBoton(boton, Posicion.SUP_IZQ));
				assertTrue(matriz.hayBoton(boton, Posicion.SUP));
				assertTrue(matriz.hayBoton(boton, Posicion.SUP_DER));
				assertTrue(matriz.hayBoton(boton, Posicion.DER));
				assertTrue(matriz.hayBoton(boton, Posicion.INF_DER));
				assertTrue(matriz.hayBoton(boton, Posicion.INF));
				assertTrue(matriz.hayBoton(boton, Posicion.INF_IZQ));
				assertTrue(matriz.hayBoton(boton, Posicion.IZQ));
			}
		}
	}

	@Test
	@Order(2)
	public void testMuestraMinas() {
		System.out.println("\nMétodo MuestraMinas");
		MatrizBotones matriz = new MatrizBotones(5, 5, 3);
		matriz.muestraMinas();

		for (Coordenadas coordenada : matriz.getListaPosicionMinas()) {
			assertEquals(matriz.getBoton(coordenada).getEstado(), Estado.MINA);
		}

		for (int x = 0; x < matriz.getSize(); x++) {
			for (int z = 0; z < matriz.getSize(); z++) {
				BotonMina boton = matriz.getBoton(x, z);
				System.out.print("[Val=" + boton.getValor() + ", Estado=" + boton.getEstado() + "]  \t");
			}
			
			System.out.println();
		}
	}
	
	@Test
	@Order(3)
	public void testRecursivoDestapaBotonesAdyacentes() {
		System.out.println("\nMétodo RecursivoDestapaBotonesAdyacentes");
		MatrizBotones matriz = new MatrizBotones(5, 5);
		
		// Prueba 1: Simulo el click en un botón con valor NUMERO (2, 0) por ejemplo, por lo que no debería destapar adyacentes
		matriz.getBoton(2, 0).cambiarAspecto(Estado.NUMERO);
		matriz.recursivoDestapaBotonesAdyacentes(matriz.getBoton(2, 0));
		
		// Prueba 2: Simulo el click en un botón con valor VACIO (3,3) por ejemplo, por lo que debe destapar
		matriz.getBoton(3, 3).cambiarAspecto(Estado.PULSADO);
		matriz.recursivoDestapaBotonesAdyacentes(matriz.getBoton(3, 3));
		
		for (int x = 0; x < matriz.getSize(); x++) {
			for (int z = 0; z < matriz.getSize(); z++) {
				BotonMina boton = matriz.getBoton(x, z);
				System.out.print("[Val=" + boton.getValor() + ", Estado=" + boton.getEstado() + "]  \t");
			}
			
			System.out.println();
		}

		System.out.print("\n*************** Fin Prueba MatrizBotones ***************");
	}

}
