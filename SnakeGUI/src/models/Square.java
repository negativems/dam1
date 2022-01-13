package models;

import java.awt.Color;
import java.awt.Graphics2D;

public class Square {
	
	//Constantes. Son las direcciones en las que nuestro cuadrado puede moverse
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    //Atributos -- Estado

    //Necesitamos la posición del cuadrado marcado por su esquina superior izq.
    private int posX;
    private int posY;
    
    //También necesitamos el lado del cuadrado
    private int side;
    
    //Ahora el color
    private Color color;


    //comportamiento

    //creación
    public Square(int posX,int posY,int side, int color) {
    	this.posX = posX;
        this.posY = posY;
        this.side = side;
        this.color = new Color(color);
    }
    
    public Square(int posX, int posY, int side, Color color) {
        this.posX = posX;
        this.posY = posY;
        this.side = side;
        this.color = color;
    }

    //un cuadrado se mueve arriba, abajo, derecha e izquierda
    public void moverse(int direction) {
        switch (direction) {
            case Square.UP:
            	posY -= side;   // 1 es arriba
                break;
            case Square.DOWN:
            	posY += side;   // 2 es abajo
                break;
            case Square.LEFT:
            	posX -= side;   // 3 es izquierda
                break;
            case Square.RIGHT:
            	posX += side;   // 4 es derecha
            	break;
        }
    }

    //un cuadrado puede estar colisionando con otro
    public boolean estaEncimaDe(Square otroC) {
    	//en nuestro caso, sólo comprobamos la esquina superior izq 
    	//almacenada en las posiciones X e Y. No hay otra posibilidad.
        return (otroC.getX() == posX && otroC.getY() == posY);
    }

    //Métodos de obtención de datos
    public int getX(){
        return posX;
    }
    
    public int getY(){
        return posY;
    }
    
    public int getLado() {
    	return side;
	}
    
    public Color getColor() {
    	return color;
    }

    //Un cuadrado tiene que saber pintarse
    public void print(Graphics2D g) {
    	g.setColor(color);
    	//g.drawRect(posX, posY, lado, lado);
    	g.fillRect(posX, posY, side, side);
		//g.fillOval(posX, posY, lado, lado);
		
    }
}
