package mainApp;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import listener.ButtonsListener;
import models.Controller;
import panels.GamePanel;
import panels.SnakeFrame;

public class MainApp {

	public static void main(String[] args) throws InterruptedException {
		
		int timeCounter;
		SnakeFrame frame;
		GamePanel tablero;
		JPanel mainPanel, bottomPanel;
		JLabel pointsTextLabel, pointsLabel;
		JButton startButton, pauseButton;
		Controller keyboardController;

		frame = new SnakeFrame();
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel = new JPanel(new BorderLayout());

		frame.createApple(frame.getWidth(), frame.getHeight());
		
		tablero = new GamePanel();
		tablero.setBorder(BorderFactory.createLineBorder(Color.black));
		tablero.setBackground(new java.awt.Color(100, 125, 110));
		tablero.setSize(600, 400);
		tablero.setSnakeFrame(frame);

		pointsTextLabel = new JLabel();
		pointsTextLabel.setText("Puntos: ");
		pointsTextLabel.setBackground(new java.awt.Color(190, 190, 190));

		pointsLabel = new JLabel();
		pointsLabel.setText("0");
		pointsLabel.setBackground(new java.awt.Color(190, 190, 190));

		// Start and pause buttons
		startButton = new JButton();
		startButton.setSize(50, 20);
		startButton.setText("Start");
		startButton.addActionListener(new ButtonsListener(frame, tablero));

		pauseButton = new JButton();
		pauseButton.setSize(50, 20);
		pauseButton.setText("Pause");
		pauseButton.addActionListener(new ButtonsListener(frame, tablero));

		// Setup keyboard controller
		keyboardController = new Controller();
		keyboardController.setSnakeFrame(frame);
		
		tablero.addKeyListener(keyboardController);
		tablero.setFocusable(true);

		// JPanel for points and buttons
		bottomPanel = new JPanel();
		bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		bottomPanel.setBackground(new java.awt.Color(150, 150, 150));
		
		// Adds points and buttons to bottomPanel
		bottomPanel.add(startButton);
		bottomPanel.add(pauseButton);
		bottomPanel.add(pointsTextLabel);
		bottomPanel.add(pointsLabel);

		mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
		mainPanel.add(tablero, BorderLayout.CENTER);
		frame.getContentPane().add(mainPanel);
		frame.setVisible(true);

		timeCounter = 0; // This controls the time, every change are 10ms.

		// Map size menu
		String[] mapSizes = new String[] {"Normal", "Grande", "Inmenso"};
		String sizeSelection = (String) JOptionPane.showInputDialog(
				frame,
				"Seleccione una opción",
				"Tamaño del mapa",
				JOptionPane.QUESTION_MESSAGE,
				null,
				mapSizes,
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
			if (timeCounter % difficulty == 0) {
				if (timeCounter == 60) {
					timeCounter = 0;
				} else {
					timeCounter++;
					frame.move();
				}
				
				// Removing this line, makes snake invincible (Exercise 4)
				frame.checkStatus();
			} else {
				timeCounter++;
			}

			if (frame.showEnd()) {
				JOptionPane.showMessageDialog(frame, "Fin de la partida, has conseguido " + pointsLabel.getText() + " puntos");
			}

			tablero.repaint();

			Thread.sleep(10);
		}
	}

}
