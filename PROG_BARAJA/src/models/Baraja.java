package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baraja {

	protected List<Carta> listaCartas = new ArrayList<>();

	public Baraja(int tipoBaraja, boolean barajar) {
		switch (tipoBaraja) {
		case 1:
			generarBaraja(40);
			break;
		case 2:
			generarBaraja(80);
			break;
		default:
			break;
		}
		
		if (barajar) barajar();
	}

	public Baraja(int tipoBaraja) {
		this(tipoBaraja, false);
	}

	public Baraja() {
		this(1, false);
	}

	// Métodos

	/**
	 * Devuelve la lista de las cartas
	 * 
	 */
	public List<Carta> getCartas() {
		return listaCartas;
	}

	
	/**
	 * Genera la baraja con la cantidad de cartas que definas.
	 * 
	 * @param cantidad Cantidad de cartas en la baraja
	 */
	private void generarBaraja(int cantidad) {
		for (int i = 1; i <= cantidad; i++) {
			Carta carta = new Carta(i);
			listaCartas.add(carta);
		}
	}

	/**
	 * Mezcla aleatoriamente las cartas de la baraja.
	 */
	public void barajar() {
		Collections.shuffle(listaCartas);
	}

	/**
	 * Corta la baraja.
	 * 
	 * @param posicion
	 */
	public void cortar(int posicion) {
		for (int i = posicion; i < listaCartas.size(); i++) {
			Carta carta = listaCartas.get(i);
			listaCartas.remove(i);
			listaCartas.add(0, carta);
		}
	}

	/**
	 * Roba la primera carta de la baraja.
	 */
	public Carta robar() {
		Carta carta = listaCartas.get(0);
		listaCartas.remove(0);
		return carta;
	}

	/**
	 * Inserta una carta al final de la baraja.
	 * 
	 * @param carta Objeto Carta
	 */
	public void insertaCartaAlFinal(Carta carta) {
		listaCartas.add(carta);
	}

	/**
	 * Inserta una carta al final de la baraja.
	 * 
	 * @param id Id de la carta
	 */
	public void insertaCartaAlFinal(int id) {
		insertaCartaAlFinal(new Carta(id));
	}

	/**
	 * Inserta una carta al final de la baraja.
	 * 
	 * @param carta Objeto Carta
	 */
	public void insertaCartaAlPrincipio(Carta carta) {
		listaCartas.add(0, carta);
	}

	/**
	 * Inserta una carta al final de la baraja.
	 * 
	 * @param id Id de la carta
	 */
	public void insertaCartaAlPrincipio(int id) {
		insertaCartaAlPrincipio(new Carta(id));
	}

	/**
	 * Nos dice cuántas cartas quedan en la baraja.
	 */
	public int getNumeroCartas() {
		return listaCartas.size();
	}

	/**
	 * Muestra si la baraja está vacía o aún quedan cartas.
	 */
	public boolean isVacia() {
		return listaCartas.isEmpty();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Carta carta : listaCartas) {
			builder.append(carta.getNombreCarta()).append(", ");
		}
		
		return builder.toString();
	}

}
