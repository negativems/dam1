package models;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Apple {

	// Cuerpo de la manzana
	private int posX, posY, maxWidth, maxHeight;

	// Creación
	public Apple(int frameHeight, int frameWidth) {
		// creamos la lista de cuadrados.
		this.maxHeight = frameHeight;
		this.maxWidth = frameWidth;
		
		this.posX = (int) (Math.random()) * maxWidth;
		this.posY = (int) (Math.random()) * maxHeight;
	}

	public void respawn() {
		this.posX = (int) (Math.random() * maxWidth);
		this.posY = (int) (Math.random() * maxHeight);
	}
	
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


}