package junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import pk_SuperBuscaMinas.BotonMina;
import pk_SuperBuscaMinas.BotonMina.Estado;
import pk_SuperBuscaMinas.BotonMina.Valor;
import pk_SuperBuscaMinas.Coordenadas;
import pk_SuperBuscaMinas.MatrizBotones;
import pk_SuperBuscaMinas.Util.Posicion;

public class JUnitTest {
	
	/**
	 * Comprueba el método cambiarAspecto de la clase BotonMina ejecutando
	 * el método con todos los parámetros posibles del Enum y comprueba
	 * con un assertEquals que el aspecto sea el que se espera
	 */
	@Test
	public void cambiarAspecto() {
		BotonMina boton = new BotonMina();
		System.out.println("*************** Inicio Prueba BotonMina ***************");
		
		for (Estado estado : Estado.values()) {
			System.out.println("Botón inicial:\t[Val=" + boton.getValor().name() + ", Estado=" + boton.getEstado() + "]");
			System.out.println("Nuevo estado:\t" + boton.getEstado());
			boton.cambiarAspecto(estado);
			System.out.println("Botón final:\t[Val=" + boton.getValor().name() + ", Estado=" + boton.getEstado() + "]\n");
			assertEquals(estado, boton.getEstado());
		}
		
		System.out.println("*************** Fin Prueba BotonMina ***************");
	}
	
	
	/**
	 * 
	 */
	@Test
	public void matrizBotones() {
		// Creo una matriz de 5x5
		MatrizBotones matriz = new MatrizBotones(5, 5);
		int[][] matrizPrueba = new int[][]{{0,0}, {0,1}, {0,2}, {1,0}, {4,0}, {4,1}};
		
		// Pongo seis minas donde yo quiero para controlar los test
		for (int i = 0; i < 6; i++) {
			BotonMina boton = matriz.getBoton(matrizPrueba[i][0], matrizPrueba[i][1]);
			boton.setValor(Valor.MINA);
			
			// Comprueba el método testHayBoton
			assertTrue(matriz.hayBoton(boton, Posicion.DER));
			assertTrue(matriz.hayBoton(boton, Posicion.INF));
			assertTrue(matriz.hayBoton(boton, Posicion.INF_DER));
			
			// Los siguientes asserts deben de dar error en los botones que
			// tengan botones en la adyacente correspondiente ya que comprueba
			// lo contrario
			assertTrue(!matriz.hayBoton(boton, Posicion.INF_IZQ));
			assertTrue(!matriz.hayBoton(boton, Posicion.IZQ));
			assertTrue(!matriz.hayBoton(boton, Posicion.SUP));
			assertTrue(!matriz.hayBoton(boton, Posicion.SUP_DER));
			assertTrue(!matriz.hayBoton(boton, Posicion.SUP_IZQ));

			// 
		}
		
	}
}
