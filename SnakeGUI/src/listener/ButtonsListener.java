package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import panels.SnakeFrame;
import panels.GamePanel;

public class ButtonsListener implements ActionListener {

	private SnakeFrame snakeFrame;
	private GamePanel gamePanel;
	private JLabel pointsLabel;
	
	public ButtonsListener(SnakeFrame snakeFrame, GamePanel gamePanel) {
		this.snakeFrame = snakeFrame;
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (((JButton)ae.getSource()).getText() == "Start") {
			snakeFrame.reset();
			gamePanel.requestFocus();
		} else {
			snakeFrame.changeRunningStatus();
			gamePanel.requestFocus();
		}
	}

}
