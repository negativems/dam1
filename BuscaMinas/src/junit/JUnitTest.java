package junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import mainApp.BotonMina;
import mainApp.Coordenadas;
import mainApp.MatrizBotones;
import mainApp.BotonMina.Estado;
import mainApp.BotonMina.Valor;
import mainApp.Util.Posicion;

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
			
			boolean lateralSuperior = boton.getY() == 0;
			boolean lateralIzquierdo = boton.getX() == 0;
			boolean lateralInferior = boton.getY() == matriz.getSize() - 1;
			boolean lateralDerecho = boton.getX() == matriz.getSize() - 1;
			
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
				assertTrue(matriz.hayBoton(boton, Posicion.SUP));
				assertTrue(matriz.hayBoton(boton, Posicion.DER));
				assertTrue(matriz.hayBoton(boton, Posicion.INF));
				assertTrue(matriz.hayBoton(boton, Posicion.IZQ));
			}
			
		}
		
	}
}
