package panels;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;

import models.Apple;
import models.Snake;

/**
 * A JFrame that contains the Snake game.
 * This class controls the game, updates the snake and apple and checks for
 * collisions. It also provides methods to reset the game, move the snake, grow
 * the snake, draw the apple and change the snake direction.
 */
public class SnakeFrame extends JFrame {
   private static final long serialVersionUID = 1L;

   private Snake snake;
   private Apple apple;

   private boolean playing;
   private boolean paused;

   private boolean showFinal;
   private boolean displayed;

   /**
    * Creates a new instance of SnakeFrame, initializes the game state,
    * and starts a new game.
    */
   public SnakeFrame() {
      snake = new Snake();
      playing = false;
      showFinal = false;
      displayed = false;
      paused = false;
   }

   /**
    * Check if the game is over and if the final screen has been displayed.
    * 
    * @return true if the game is over and the final screen has not been displayed,
    *         false otherwise.
    */
   public boolean showEnd() {
      boolean result = false;

      if (showFinal && !displayed) {
         result = true;
         displayed = true;
      }

      return result;
   }

   /**
    * Makes the snake one square bigger. This method is called when the snake eats
    * the apple.
    * It will only grow the snake if the game is currently being played and is not
    * paused.
    */
   public void grow() {
      if (playing && !paused) {
         snake.grow();
      }
   }

   /**
    * Creates a new Apple instance and sets its position to the given coordinates.
    * 
    * @param x The x-coordinate of the apple.
    * @param y The y-coordinate of the apple.
    */
   public void createApple(int x, int y) {
      apple = new Apple(x, y);
   }

   /**
    * Updates the size of the frame for the apple.
    * 
    * @param width  The new width of the frame.
    * @param height The new height of the frame.
    */
   public void updateAppleFrameSize(int width, int height) {
      apple.setMaxWidth(width);
      apple.setMaxHeight(height);
      apple.spawn();
   }

   /**
    * Draws the apple on the screen. This method is called when the panel is
    * painted.
    * 
    * @param g The Graphics object used to paint the apple.
    */
   public void printApple(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      apple.print(g2d);
   }

   /**
    * Checks if the snake has eaten the apple. If the apple is at the same position
    * as the head of the snake, the snake grows and a new apple is spawned.
    */
   public void checkApple() {
      if (apple.getPosX() == snake.getSquareList().get(0).getX()
            && apple.getPosY() == snake.getSquareList().get(0).getY()) {
         apple.spawn();
         snake.grow();
      }
   }

   /**
    * Moves the snake
    * The snake moves one square in the direction it is facing
    * it needs to know the frame size to know when it has hit the wall
    */
   public void move() {
      if (playing && !paused) {
         snake.move(getHeight(), getWidth());
         checkApple();
      }
   }

   /**
    * Resets the game
    * This is called when the user presses the start button.
    */
   public void reset() {
      snake = new Snake();
      apple = new Apple(getHeight(), getWidth());
      playing = true;
      showFinal = false;
      displayed = false;
      paused = false;
   }

   /**
    * Checks if the snake is dead
    * If so, the game is over.
    */
   public void checkStatus() {
      if (playing && !paused) {
         if (snake.isDead()) {
            playing = false;
            showFinal = true;
            displayed = false;
         }
      }
   }

   /**
    * Change running status between playing and paused
    */
   public void changeRunningStatus() {
      if (playing)
         paused = !paused;
   }

   /**
    * Changes the snake direction
    * 
    * @param key The pressed key
    */
   public void changeDirection(int key) {
      snake.changeDirection(key);
   }

   /**
    * Gets the snake
    * 
    * @return The snake
    */
   public Snake getSnake() {
      return snake;
   }
}