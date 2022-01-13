package panels;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 * Necesitamos una clase JPanel que represente nuestro tablero y que a la hora
 * de pintarse le diga a la serpiente que también se pinte. Para ello necesita
 * saber quién está controlando el juego (el frame de tipo MySnakeFrame) y hacer
 * un "override" del método paint.
 * 
 * 
 * @author andres
 *
 */

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private SnakeFrame snakeFrame;

	// comportamiento para "enganchar" el tablero con el frame controlador.
	public void setSnakeFrame(SnakeFrame sf) {
		snakeFrame = sf;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (snakeFrame != null) { // pintamos si tenemos serpiente
			Graphics2D g2d = (Graphics2D) g;
			// configuramos los gráficos para que pinte figuras suaves...
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			// le decimos al controlador que nos de la serpiente para pintarla...
			snakeFrame.getSnake().print(g2d);
			snakeFrame.printApple(g2d);
		}
	}
}
