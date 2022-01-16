package models;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Snake {

	private ArrayList<Square> squareList;
	private int direction;

	public Snake() {
		squareList = new ArrayList<Square>();
		squareList.add(new Square(60, 60, 20, Color.GREEN));
		direction = Square.DOWN;
	}

	
	/**
	 * Grow up the snake
	 */
	public void grow() {
		Square antiguaCabeza = squareList.get(0);
		Square nuevaCabeza = new Square(antiguaCabeza.getX(), antiguaCabeza.getY(), antiguaCabeza.getLado(), antiguaCabeza.getColor());
		nuevaCabeza.changeDirection(direction);
		squareList.add(0, nuevaCabeza);
	}
	
	
	/**
	 * Move the snake by growing up and removing the last square
	 */
	public void move(int height, int width) {
		grow();
		squareList.remove(squareList.size() - 1);
		
		if (isOut(height, width)) {
			Square head = squareList.get(0);
			if (head.getX() > width) {
				head.teleport(0, head.getY());
			} else if (head.getX() < 0) {
				head.teleport(width, head.getY());
			} else if (head.getY() > height - 100) {
				System.out.println("from " + head.getX() + "," + head.getY());
				head.teleport(head.getX(), 0);
				System.out.println("to " + head.getX() + "," + head.getY());
				System.out.println("height: " + height);
			} else if (head.getY() < 0) {
				head.teleport(head.getX(), height - 100);
			}
		}
	}

	
	/**
	 * Checks if the snake is dead
	 * @param height The height of the table
	 * @param width The width of the table
	 * @return true if the snake is dead by checking if is collapsing or is out the table
	 */
	public boolean isDead() {
		return isCollapsing();
	}

	
	/**
	 * Checks if the snake is collapsing
	 * @return true if is collapsing with itselfs
	 */
	private boolean isCollapsing() {
		Square cabeza = squareList.get(0);

		for (int i = 4; i < squareList.size(); i++) {
			if (squareList.get(i).isOverOf(cabeza)) {
				return true;				
			}
		}

		return false;
	}

	
	/**
	 * Checks if the snake's head is out of the table
	 * @param height The height of the frame
	 * @param width The width of the frame
	 * @return true if the snake is out
	 */
	private boolean isOut(int height, int width) {
		Square cabeza = squareList.get(0);

		return (cabeza.getX() < 0 || cabeza.getX() > width || cabeza.getY() < 0 || cabeza.getY() > height - 200);
	}

	
	/**
	 * Prints all the squares from the snakes body
	 * @param g Graphics2D object
	 */
	public void print(Graphics2D g) {
		for (int i = 0; i < squareList.size(); i++) {
			squareList.get(i).print(g);
		}
	}

	
	/**
	 * Changes the direction of the snake
	 * @param key The keyboard key
	 */
	public void changeDirection(int key) {
		if ((key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) && direction != Square.RIGHT) {
			direction = Square.LEFT;
		} else if ((key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) && direction != Square.UP) {
			direction = Square.DOWN;
		} else if ((key == KeyEvent.VK_W || key == KeyEvent.VK_UP) && direction != Square.DOWN) {
			direction = Square.UP;
		} else if ((key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) && direction != Square.LEFT) {
			direction = Square.RIGHT;
		}
	}

	
	/**
	 * Points based on the snake's squares.
	 * @return int
	 */
	public int getPoints() {
		return squareList.size();
	}
	
	
	/**
	 * Gets list of the squares from the snake's body
	 * @return
	 */
	public ArrayList<Square> getSquareList() {
		return squareList;
	}

}