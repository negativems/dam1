package models;

import java.awt.Color;
import java.awt.Graphics2D;

public class Apple {

   // Apple position and frame size
   private int posX, posY, maxWidth, maxHeight;

   public Apple(int frameWidth, int frameHeight) {
      // Sets the frame size
      this.maxWidth = frameWidth;
      this.maxHeight = frameHeight;

      spawn();
   }

   /**
    * Spawns the apple in a random position
    */
   public void spawn() {
      this.posX = (int) ((int) ((Math.random() * ((maxWidth - 100) / 20))) * 20) + 60;
      this.posY = (int) ((int) ((Math.random() * ((maxHeight - 200) / 20))) * 20) + 60;

      System.out.println(posX + "," + posY);
   }

   /**
    * Draws the apple
    * @param g Graphics2D object to draw the apple
    */
   public void print(Graphics2D g) {
      g.setColor(Color.RED);
      g.fillRect(posX, posY, 20, 20);
   }

   public int getPosX() {
      return posX;
   }

   public int getPosY() {
      return posY;
   }

   public void setMaxWidth(int maxWidth) {
      this.maxWidth = maxWidth;
   }

   public void setMaxHeight(int maxHeight) {
      this.maxHeight = maxHeight;
   }

}