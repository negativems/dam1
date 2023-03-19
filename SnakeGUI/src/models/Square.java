package models;

import java.awt.Color;
import java.awt.Graphics2D;

public class Square {
	
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    private int x, y, side;
    private Color color;

    /**
     * Create a snake square
     * @param x The X cordinate
     * @param y The Y cordinate
     * @param side The size of the square
     * @param color The color of the square
     */
    public Square(int x,int y,int side, int color) {
    	this.x = x;
        this.y = y;
        this.side = side;
        this.color = new Color(color);
    }
    
    public Square(int posX, int posY, int side, Color color) {
        this.x = posX;
        this.y = posY;
        this.side = side;
        this.color = color;
    }

    /**
     * Change the square direction
     * this method is used only in the head of the snake
     * @param direction The direction of the square (UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4)
     */
    public void changeDirection(int direction) {
        switch (direction) {
            case Square.UP:
            	y -= side;
                break;
            case Square.DOWN:
            	y += side;
                break;
            case Square.LEFT:
            	x -= side;
                break;
            case Square.RIGHT:
            	x += side;
            	break;
        }
    }

    /**
     * Checks if the square is over other square
     * @param square
     * @return true if the square is over other square
     */
    public boolean isOverOf(Square square) {
        return (square.getX() == x && square.getY() == y);
    }
    
    /**
     * Teleports to a coordinates
     * @param x position
     * @param y position
     */
    public void teleport(int x, int y) {
    	this.x = x;
    	this.y = y;
    }
    
    /**
     * Print the square
     * @param g Graphics2D object
     */
    public void print(Graphics2D g) {
    	g.setColor(color);
    	g.fillRect(x, y, side, side);
    }

    // Getters && Setters
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getSide() {
    	return side;
	}
    
    public Color getColor() {
    	return color;
    }
}
