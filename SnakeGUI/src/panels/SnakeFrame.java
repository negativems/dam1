package panels;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;

import models.Apple;
import models.Snake;

/**
 * Clase MySnakeFrame
 * 
 * Este es el primer ejemplo de comentarios de documentación que vemos en JAVA
 * Como se puede apreciar son un bloque de comentarios, no sólo una línea
 * 
 * Aquí explicamos qué sentido tiene la clase
 * 
 * Esta clase hereda de JFrame. JFrame es una ventana equivalente a un Form en Gambas3
 * Nuestra ventana principal además del comportamiento y estado natural a una ventana gráfica
 * controlará todo el estado del juego. Como nuestro "game loop" está codificado como 
 * "run forever" tenemos que tener algún objeto que controle si el juego está pausado, empezado, terminado
 * en todo momento, y que a su vez le diga a la serpiente que se resetee, que se mueva, crezca, etc.
 * Para controlar el estado se usan "semáforos". Estos no son nada más que booleanos. Cuando están a false
 * sería el equivalente a "semáforo rojo" y cuando están a true sería el equivalente a "semáforo verde"
 * Estos semáforos se ponen en una serie de "if" que permiten controlar al objeto MySnakeFrame si se 
 * ejecuta alguna acción o no en la serpiente.
 * 
 * @author andres
 *
 */

public class SnakeFrame extends JFrame {	
	
	private static final long serialVersionUID = 1L;

	private Snake snake;
	private Apple apple;
	
	//semáforos para indicar que estamos jugando o no
	private boolean playing;
	private boolean paused;
	
	//semáforos para mostrar mensaje al final, sólo una vez.
	private boolean showFinal;
	private boolean displayed;
	
	
	//**** Comportamientos
	
	//Constructor
	public SnakeFrame() {
		snake = new Snake();
		playing = false;
		showFinal = false;
		displayed = false;
		paused = false;
	}
	
	
	//si alguien necesita nuestra serpiente, se la proporcionamos.
	public Snake getSnake() {
		return snake;
	}
	
	
	//tenemos que mostrar la ventanita de final de partida??? Sólo una vez...
	public boolean showFin() {
		boolean result;
		
		result = false;
		if (showFinal && !displayed) { //estamos al final de una partida y no hemos mostrado nada
			result = true;  //activamos el resultado para que se muestre la ventana
			displayed = true;   //ya no dejamos que se muestre más la próxima vez...
		}
		
		return result;
	}
	
	//toca crecer sólo si estamos en una partida activa y no estamos pausados...
	public void grow() {
		if (playing && !paused) {
			snake.grow();
		}
	}
	
	public void createApple(int x, int y) {
		apple = new Apple(x, y);
	}
	
	public void updateAppleFrameSize(int x, int y) {
		apple.setMaxWidth(x);
		apple.setMaxHeight(y);
		apple.spawn();
	}
	
	public void printApple(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		apple.print(g2d);
	}
	
	public void checkApple() {
		if (apple.getPosX() == snake.getSquareList().get(0).getX() && apple.getPosY() == snake.getSquareList().get(0).getY()) {
			apple.spawn();
		}
	}
	
	//toca moverse sólo si estamos en una partida activa y no estamos pausados...
	public void move() {
		if (playing && !paused) {
			snake.move();
			checkApple();
		}
	}
	
	
	//han pulsado el botón de start, hay que ponerlo todo en orden.
	public void reset() {
		snake = new Snake();  //nueva y flamante serpiente
		apple = new Apple(getHeight(), getWidth());
		playing = true;           //estamos jugando a partir de ¡YA!
		showFinal = false;     //ni estamos al final ni mucho menos
		displayed = false;         //hemos mostrado el msg de final
		paused = false;          //Y todavía nadie ha pulsado el pause, todavía...
	}
	
	
	//Hay que ver si la serpiente sigue viva, pero sólo si estamos jugando y no en modo pausa...
	public void checkStatus(int hight, int width) {
		if (playing && !paused) {
			if (snake.estaMuerta(hight, width)) {
				//acabamos de matarnos. Hay que mostrar msg al final y ya no jugamos...
				playing = false;
				showFinal = true;
				displayed = false;
			}
		}
	}
	
	
	//sólo pausamos / continuamos si estamos jugando.
	public void changeRunningStatus() {
		if (playing) {
			paused = !paused;
		}
	}
	
	//nos han pulsado tecla, cambiamos de dirección.
	public void changeDirection(int key) {
		snake.changeDirection(key);
	}
}
