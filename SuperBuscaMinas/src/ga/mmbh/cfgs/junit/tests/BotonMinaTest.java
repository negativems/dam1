package ga.mmbh.cfgs.junit.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ga.mmbh.cfgs.models.BotonMina;
import ga.mmbh.cfgs.models.BotonMina.Estado;

public class BotonMinaTest {
	
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
	
}
