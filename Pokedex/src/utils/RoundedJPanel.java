package utils;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class RoundedJPanel extends JPanel {

	private int cornerWidth = 40;
	private int cornerHeight = 40;
	
	public RoundedJPanel (int cornerWidth, int cornerHeight) {
		this.cornerWidth = cornerWidth;
		this.cornerHeight = cornerHeight;
	}
	
    @Override
    protected void paintComponent(Graphics g) {
       super.paintComponent(g);
       Dimension arcs = new Dimension(cornerWidth, cornerHeight); //Border corners arcs {width,height}, change this to whatever you want
       int width = getWidth();
       int height = getHeight();
       Graphics2D graphics = (Graphics2D) g;
       graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


       //Draws the rounded panel with borders.
       graphics.setColor(getBackground());
       graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
       graphics.setColor(getForeground());
       graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
    }
	
}
