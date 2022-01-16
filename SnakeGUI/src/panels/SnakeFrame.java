package panels;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;

import models.Apple;
import models.Snake;

public class SnakeFrame extends JFrame {	
	
	private static final long serialVersionUID = 1L;

	private Snake snake;
	private Apple apple;
	
	private boolean playing;
	private boolean paused;
	
	private boolean showFinal;
	private boolean displayed;

	public SnakeFrame() {
		snake = new Snake();
		playing = false;
		showFinal = false;
		displayed = false;
		paused = false;
	}
	
	public boolean showEnd() {
		boolean result = false;
		
		if (showFinal && !displayed) {
			result = true;
			displayed = true;
		}
		
		return result;
	}
	
	public void grow() {
		if (playing && !paused) {
			snake.grow();
		}
	}
	
	public void createApple(int x, int y) {
		apple = new Apple(x, y);
	}
	
	public void updateAppleFrameSize(int width, int height) {
		apple.setMaxWidth(width);
		apple.setMaxHeight(height);
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
			snake.grow();
		}
	}
	
	public void move() {
		if (playing && !paused) {
			snake.move(getHeight(), getWidth());
			checkApple();
		}
	}
	
	
	public void reset() {
		snake = new Snake();  //nueva y flamante serpiente
		apple = new Apple(getHeight(), getWidth());
		playing = true;           //estamos jugando a partir de ¡YA!
		showFinal = false;     //ni estamos al final ni mucho menos
		displayed = false;         //hemos mostrado el msg de final
		paused = false;          //Y todavía nadie ha pulsado el pause, todavía...
	}
	
	
	public void checkStatus() {
		if (playing && !paused) {
			if (snake.isDead()) {
				//acabamos de matarnos. Hay que mostrar msg al final y ya no jugamos...
				playing = false;
				showFinal = true;
				displayed = false;
			}
		}
	}
	
	public void changeRunningStatus() {
		if (playing) paused = !paused;
	}
	
	public void changeDirection(int key) {
		snake.changeDirection(key);
	}
	
	public Snake getSnake() {
		return snake;
	}
}
