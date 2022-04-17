package ga.mmbh.cfgs.netflixdb.graphic.panels;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {

  private Image backgroundImage;

  public BackgroundPanel(String fileName) throws IOException {
	  backgroundImage = ImageIO.read(new File(fileName));
  }

  public void paintComponent(Graphics graphics) {
    graphics.drawImage(backgroundImage, 0, 0, this);
    super.paintComponent(graphics);
  }
}