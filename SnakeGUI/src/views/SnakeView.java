package views;

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

public class SnakeView {

   SnakeFrame frame;
   GamePanel table;
   JPanel mainPanel, bottomPanel;
   JLabel pointsTextLabel, pointsLabel;
   JButton startButton, pauseButton;
   Controller keyboardController;
   int timeCounter;

   public SnakeView() {
      try {
         initialize();
      } catch (InterruptedException e) {
         // ignore
      }
   }

   /**
    * Initialize the contents of the frame.
    * Also creates the game loop that runs the game
    * 
    * @throws InterruptedException When the thread is interrupted
    */
   private void initialize() throws InterruptedException {
      frame = new SnakeFrame();
      frame.setSize(600, 600);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      mainPanel = new JPanel(new BorderLayout());

      frame.createApple(frame.getWidth(), frame.getHeight());

      table = new GamePanel();
      table.setBorder(BorderFactory.createLineBorder(Color.black));
      table.setBackground(new java.awt.Color(100, 125, 110));
      table.setSize(600, 600);
      table.setSnakeFrame(frame);

      pointsTextLabel = new JLabel();
      pointsTextLabel.setText("Points: ");
      pointsTextLabel.setBackground(new java.awt.Color(190, 190, 190));

      pointsLabel = new JLabel();
      pointsLabel.setText("0");
      pointsLabel.setBackground(new java.awt.Color(190, 190, 190));

      // Start and pause buttons
      startButton = new JButton();
      startButton.setSize(50, 20);
      startButton.setText("Start");
      startButton.addActionListener(new ButtonsListener(frame, table));

      pauseButton = new JButton();
      pauseButton.setSize(50, 20);
      pauseButton.setText("Pause");
      pauseButton.addActionListener(new ButtonsListener(frame, table));

      // Setup keyboard controller
      keyboardController = new Controller();
      keyboardController.setSnakeFrame(frame);

      table.addKeyListener(keyboardController);
      table.setFocusable(true);

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
      mainPanel.add(table, BorderLayout.CENTER);
      frame.getContentPane().add(mainPanel);
      frame.setVisible(true);

      // Track the time elapsed in the game loop, every 10ms the snake moves and this
      // increments by 1
      timeCounter = 0;

      // Map size menu
      String[] mapSizes = new String[] { "Normal", "Big", "Huge" };
      String sizeSelection = (String) JOptionPane.showInputDialog(
            frame,
            "Select a map size",
            "Map size",
            JOptionPane.QUESTION_MESSAGE,
            null,
            mapSizes,
            "Normal");

      int mapSize = (sizeSelection.equals("Normal") ? 600 : sizeSelection.equals("Big") ? 800 : 1000);
      frame.setSize(mapSize, mapSize);
      frame.updateAppleFrameSize(mapSize, mapSize);

      // Difficulty menu
      String difSelection = (String) JOptionPane.showInputDialog(
            frame,
            "Select a difficulty",
            "Difficulty",
            JOptionPane.QUESTION_MESSAGE,
            null,
            new Object[] { "Easy", "Normal", "Hard", "Impossible" },
            "Easy");

      // Difficulty determines the speed of the snake in the game, higher -> slower
      int difficulty = 20;

      if (difSelection.equals("Easy")) {
         difficulty = 10;
      } else if (difSelection.equals("Normal")) {
         difficulty = 5;
      } else if (difSelection.equals("Hard")) {
         difficulty = 2;
      } else if (difSelection.equals("Impossible")) {
         difficulty = 1;
      }

      while (true) {
         if (timeCounter % difficulty == 0) {
            if (timeCounter == 60) {
               timeCounter = 0;
            } else {
               timeCounter++;
               frame.move();
               pointsLabel.setText(frame.getSnake().getPoints() + "");
            }

            // Removing this line, makes snake invincible (Exercise 4)
            frame.checkStatus();
         } else {
            timeCounter++;
         }

         if (frame.showEnd()) {
            JOptionPane.showMessageDialog(frame, "End of the game, you got " + pointsLabel.getText() + " points!");
         }

         table.repaint();
         Thread.sleep(10);
      }

   }

}
