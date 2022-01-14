package main;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import listener.ButtonsListener;
import models.Apple;
import models.Controller;
import panels.GamePanel;
import panels.SnakeFrame;

public class MainApp {

	public static void main(String[] args) throws InterruptedException {
		
		int contador;
		SnakeFrame frame;
		GamePanel tablero;
		JPanel mainPanel, botonera;
		JLabel puntos, puntosNum;
		JButton start, pause;
		Controller miControlador;

		// 1. Crear el frame.
		frame = new SnakeFrame();

		// asignamos el tamaño a nuestra ventana, y hacemos que se cierre cuando nos
		// pulsan la X de cerrar la ventana		
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 3. Ahora creamos los componentes y los ponemos en la frame (ventana).

		// El panel de fondo. Rellena el frame, y sirve de contenedor del tablero y de
		// la botonera.
		mainPanel = new JPanel(new BorderLayout());

		// Creates apple
		frame.createApple(frame.getWidth(), frame.getHeight());
		
		// Ahora creamos el tablero. Recordamos: no deja de ser un panel un poquito
		// "especial"
		tablero = new GamePanel();

		// Les damos las propiedades a nuestro tablero. Su color, tamaño y borde
		tablero.setBorder(BorderFactory.createLineBorder(Color.black));
		tablero.setBackground(new java.awt.Color(100, 125, 110));
		tablero.setSize(600, 400);

		// Le damos un enlace al tablero para que sepa quién es su frame (ventana) y así
		// sepa quién contiene la serpiente y quién controla el juego...
		tablero.setSnakeFrame(frame);

		// Ahora el turno de la botonera. Tendrá los dos botones y las etiquetas de texto
		botonera = new JPanel();
		botonera.setBorder(BorderFactory.createLineBorder(Color.black));
		botonera.setBackground(new java.awt.Color(150, 150, 150));

		// Ahora definimos las dos etiquetas para los puntos.
		puntos = new JLabel();
		puntos.setText("Puntos: ");
		puntos.setBackground(new java.awt.Color(190, 190, 190));

		puntosNum = new JLabel();
		puntosNum.setText("0");
		puntosNum.setBackground(new java.awt.Color(190, 190, 190));

		// turno de los botones de empezar y pausar/continuar
		start = new JButton();
		start.setSize(50, 20);
		start.setText("Start");
		start.addActionListener(new ButtonsListener(frame, tablero));

		pause = new JButton();
		pause.setSize(50, 20);
		pause.setText("Pause");
		pause.addActionListener(new ButtonsListener(frame, tablero));

		// Preparamos el control del teclado
		miControlador = new Controller();
		miControlador.setSnakeFrame(frame); // le damos al controlador de teclado un enlace el frame principal
		tablero.addKeyListener(miControlador); // le decimos al tablero que el teclado es cosa de nuestro controlador
		tablero.setFocusable(true); // permitimos que el tablero pueda coger el foco.

		// Añadimos los componentes uno a uno, cada uno en su contenedor, y al final el
		// panel principal se añade al frame principal.
		botonera.add(start);
		botonera.add(pause);
		botonera.add(puntos);
		botonera.add(puntosNum);

		mainPanel.add(botonera, BorderLayout.PAGE_END);
		mainPanel.add(tablero, BorderLayout.CENTER);
		frame.getContentPane().add(mainPanel);

		frame.setVisible(true); // activamos la ventana principal para que sea "pintable"

		contador = 0; // nuestro control de los pasos del tiempo. Cada vez que contador cuenta un paso, pasan 10ms

		// Map size menu
		String sizeSelection = (String) JOptionPane.showInputDialog(
				frame,
				"Seleccione una opción",
				"Tamaño del mapa",
				JOptionPane.QUESTION_MESSAGE,
				null,
				new Object[] {"Normal", "Grande", "Inmenso"},
				"Fácil"
		);

		int mapSize = (sizeSelection.equals("Normal") ? 600 : sizeSelection.equals("Grande") ? 800 : 1000);
		
		frame.setSize(mapSize, mapSize);
		frame.updateAppleFrameSize(mapSize, mapSize);
		
		// Difficulty menu
		String difSelection = (String) JOptionPane.showInputDialog(
				frame,
				"Seleccione una opción",
				"Dificultad",
				JOptionPane.QUESTION_MESSAGE,
				null,
				new Object[] {"Fácil", "Normal", "Dificil", "Imposible"},
				"Fácil"
		);
		
		int difficulty = (difSelection.equals("Fácil") ? 10 : difSelection.equals("Normal") ? 5 : difSelection.equals("Dificil") ? 2 : difSelection.equals("Imposible") ? 1 : 20);
		
		while (true) {
			if (contador % difficulty == 0) {
				// Cada 600ms crecemos y reseteamos el contador a los 200 y 400 ms nos movemos...
				if (contador == 60) {
					contador = 0;
				} else {
					contador++;
					frame.move();
				}
				
				// Comentando el checkStatus hacemos que la serpiente nunca muera
				frame.checkStatus(tablero.getHeight(), tablero.getWidth());
			} else {
				// Cada vez que no hay que moverse o crecer, simplemente contamos...
				contador++;
			}

			// hemos terminado?? mostramos msg
			if (frame.showFin()) {
				JOptionPane.showMessageDialog(frame, "Fin de la partida, has conseguido " + puntosNum.getText() + " puntos");
			}

			// Repintamos
			tablero.repaint();

			// Esperamos para dar tiempo al thread de repintado a pintar.
			Thread.sleep(10);
		}
	}

}
