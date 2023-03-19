package panels;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 * 
 * Represents the game board panel that needs to be painted with the snake and
 * apple.
 * 
 * Inherits from JPanel and overrides the paint method.
 */
public class GamePanel extends JPanel {

   private static final long serialVersionUID = 1L;

   private SnakeFrame snakeFrame;

   /**
    * 
    * Set the SnakeFrame that controls the game.
    * 
    * @param sf The SnakeFrame that controls the game.
    */
   public void setSnakeFrame(SnakeFrame sf) {
      snakeFrame = sf;
   }

   /**
    * 
    * Overrides the paint method to paint the snake and apple in the board.
    * 
    * @param g The Graphics object used to draw on the panel.
    */
   @Override
   public void paint(Graphics g) {
      super.paint(g);
      if (snakeFrame != null) {
         Graphics2D g2d = (Graphics2D) g;
         g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         snakeFrame.getSnake().print(g2d);
         snakeFrame.printApple(g2d);
      }
   }
}