package models;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Snake {

   // The list of the snake parts, every square is a part of the snake body and it
   // contains the position of the square and the size
   private ArrayList<Square> squareList;

   // The direction of the snake
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
      Square lastHead = squareList.get(0);
      Square newHead = new Square(lastHead.getX(), lastHead.getY(), lastHead.getSide(), lastHead.getColor());
      newHead.changeDirection(direction);
      squareList.add(0, newHead);
   }

   /**
    * Move the snake.
    * Grows up the snake and removes the last square (the tail) of the snake.
    * If the snake goes out of bounds, it is teleported to the opposite edge of the
    * screen.
    * 
    * @param height The height of the table
    * @param width  The width of the table
    */
   public void move(int height, int width) {
      grow();
      squareList.remove(squareList.size() - 1);

      if (isOut(height, width)) {
         Square head = squareList.get(0);
         if (head.getX() >= width - 20) {
            head.teleport(0, head.getY());
         } else if (head.getX() < 0) {
            head.teleport(width - 20, head.getY());
         } else if (head.getY() >= height - 80) {
            System.out.println("from " + head.getX() + "," + head.getY());
            head.teleport(head.getX(), 0);
            System.out.println("to " + head.getX() + "," + head.getY());
            System.out.println("height: " + height);
         } else if (head.getY() < 0) {
            head.teleport(head.getX(), height - 80);
         }
      }
   }

   /**
    * Checks if the snake is dead.
    * Check if is collapsing or is out the table.
    * 
    * @param height The height of the table
    * @param width  The width of the table
    * @return true if the snake is dead
    */
   public boolean isDead() {
      // TODO: Remove the method isCollapsing if it will not be used in the future
      return isCollapsing();
   }

   /**
    * Checks if the snake is collapsing
    * 
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
    * 
    * @param height The height of the frame
    * @param width  The width of the frame
    * @return true if the snake is out
    */
   private boolean isOut(int height, int width) {
      Square cabeza = squareList.get(0);

      return (cabeza.getX() < 0 || cabeza.getX() > width || cabeza.getY() < 0 || cabeza.getY() > height - 200);
   }

   /**
    * Prints all the squares from the snakes body
    * 
    * @param g Graphics2D object
    */
   public void print(Graphics2D g) {
      for (int i = 0; i < squareList.size(); i++) {
         squareList.get(i).print(g);
      }
   }

   /**
    * Changes the direction of the snake
    * 
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
    * 
    * @return int
    */
   public int getPoints() {
      return squareList.size();
   }

   /**
    * Gets list of the squares from the snake's body
    * 
    * @return
    */
   public ArrayList<Square> getSquareList() {
      return squareList;
   }

}