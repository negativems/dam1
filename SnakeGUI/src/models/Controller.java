package models;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import panels.SnakeFrame;

/**
 * This class is an implementation of KeyListener which handles keyboard events.
 * When a key is pressed, the keyPressed method is called. It extracts the ASCII
 * keycode of the pressed key and passes it to the SnakeFrame controller to
 * update the snake direction.
 */
public class Controller implements KeyListener {

   // Reference to the SnakeFrame controller
   private SnakeFrame snakeFrame;

   // Invoked when a key has been pressed.
   @Override
   public void keyPressed(KeyEvent e) {
      int key = e.getKeyCode();
      if (snakeFrame != null) {
         snakeFrame.changeDirection(key);
      }
   }

   /**
    * Sets the SnakeFrame controller.
    *
    * @param sf the SnakeFrame controller
    */
   public void setSnakeFrame(SnakeFrame sf) {
      snakeFrame = sf;
   }

   // Not implemented, no need to override
   @Override
   public void keyReleased(KeyEvent arg0) {
   }

   // Not implemented, no need to override
   @Override
   public void keyTyped(KeyEvent arg0) {
   }
}