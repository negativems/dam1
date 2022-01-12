package listener;

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
    private int lado;
    
    //Ahora el color
    private int colorCuadrado;


    //comportamiento

    //creación
    public Square(int pX,int pY,int l, int cc) {
        posX = pX;
        posY = pY;
        lado = l;
        colorCuadrado = cc;
    }

    //un cuadrado se mueve arriba, abajo, derecha e izquierda
    public void moverse(int direction) {
        switch (direction) {
            case Square.UP:
            	posY -= lado;   // 1 es arriba
                break;
            case Square.DOWN:
            	posY += lado;   // 2 es abajo
                break;
            case Square.LEFT:
            	posX -= lado;   // 3 es izquierda
                break;
            case Square.RIGHT:
            	posX += lado;   // 4 es derecha
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
    	return lado;
	}
    
    public int getColor() {
    	return colorCuadrado;
    }

    //Un cuadrado tiene que saber pintarse
    public void print(Graphics2D g) {
    	g.setColor(new Color(colorCuadrado));
    	//g.drawRect(posX, posY, lado, lado);
    	g.fillRect(posX, posY, lado, lado);
		//g.fillOval(posX, posY, lado, lado);
		
    }
}
