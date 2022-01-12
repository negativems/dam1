package models;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import listener.Square;

public class Snake {
	// Constantes. No hay

	// ****** Atributos. Estado

	// Cuerpo de la serpiente, es una lista de cuadrados
	private ArrayList<Square> squareList;

	// Dirección inicial del movimiento
	private int direction;

	// ****** Métodos -- Comportamientos

	// Creación
	public Snake() {
		// creamos la lista de cuadrados.
		squareList = new ArrayList<Square>();

		// añadimos el primero de los cuadrados...
		squareList.add(new Square(60, 60, 20, (int) (Math.random() * 16000000)));

		// siempre hacia abajo al principio
		direction = Square.DOWN;
	}

	// Moverse. Una serpiente sabe moverse
	public void moverse() {
		Square nuevaCabeza;
		Square antiguaCabeza;

		// Primero cogemos la cabeza y la duplicamos
		antiguaCabeza = squareList.get(0);
		nuevaCabeza = new Square(antiguaCabeza.getX(), antiguaCabeza.getY(), antiguaCabeza.getLado(),
				antiguaCabeza.getColor());

		// movemos la cabeza a su nueva posición
		nuevaCabeza.moverse(direction);

		// la añadimos a la lista
		squareList.add(0, nuevaCabeza);

		// borramos el último cuadrado por la cola (pop del basic)
		squareList.remove(squareList.size() - 1);
	}

	public void crecer() {
		Square nuevaCabeza;
		Square antiguaCabeza;

		// Primero cogemos la cabeza y la duplicamos
		antiguaCabeza = squareList.get(0);
		nuevaCabeza = new Square(antiguaCabeza.getX(), antiguaCabeza.getY(), antiguaCabeza.getLado(),
				antiguaCabeza.getColor());
		// movemos la cabeza a su nueva posición
		nuevaCabeza.moverse(direction);

		// la añadimos a la lista
		squareList.add(0, nuevaCabeza);

		// ahora no borramos la última y hemos crecido...
	}

	// la serpiente se muere porque se toca a si misma o porque se ha salido del tablero
	public boolean estaMuerta(int iAlto, int iAncho) {
		boolean resultado;

		resultado = (seEstaTocandoEllaMisma() || seHaSalido(iAlto, iAncho));

		return resultado;
	}

	// la cabeza, está tocando alguna parte de su cuerpo??
	private boolean seEstaTocandoEllaMisma() {
		int iCont;
		Square cabeza;

		cabeza = squareList.get(0);

		// la cabeza podrá tocar como mucho, el quinto cuadrado en adelante de su
		// cuerpo...
		// por eso el cuadrado 1, 2 y 3 no lo comprobamos
		for (iCont = 4; iCont < squareList.size(); iCont++) {
			if (squareList.get(iCont).estaEncimaDe(cabeza)) // oh oh, hemos chocado...
				return true;
		}

		return false;
	}

	// nos hemos salido de los límites del tablero???
	private boolean seHaSalido(int iAlto, int iAncho) {
		// Hacemos las comprobaciones sobre la cabeza
		Square cabeza = squareList.get(0);

		return (cabeza.getX() < 0 || cabeza.getX() > iAncho || cabeza.getY() < 0 || cabeza.getY() > iAlto);
	}

	// la serpiente también sabe pintarse
	public void print(Graphics2D g) {
		// pintamos desde el cuadrado 0 hasta el último. Cuidado, aquí con el "<"
		// evitamos
		// tener que poner el "-1" que poniamos en el for del BASIC
		for (int i = 0; i < squareList.size(); i++) {
			squareList.get(i).print(g);
		}
	}

	// controlamos el cambio de dirección
	public void changeDirection(int key) {
		if (key == KeyEvent.VK_A) {
			direction = Square.LEFT;
		} else if (key == KeyEvent.VK_S) {
			direction = Square.DOWN;
		} else if (key == KeyEvent.VK_W) {
			direction = Square.UP;
		} else if (key == KeyEvent.VK_D) {
			direction = Square.RIGHT;
		}
	}

	// los puntos se corresponden con el tamaño de nuestra serpiente
	public int getPoints() {
		return squareList.size();
	}

}