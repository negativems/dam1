package models.player;

import models.Baraja;
import models.Carta;

public abstract class AbstractPlayer {

	protected Baraja mano = new Baraja(0);
	
	private String nombre;
	
	public AbstractPlayer(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Baraja getMano() {
		return mano;
	}
	
	public double getValorBaraja7yMedia() {
		double resultado = 0.0;
		for (Carta carta : mano.getCartas()) {
			resultado += carta.getValor7ymedia();
		}
	
		return resultado;
	}
}
