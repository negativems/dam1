package ga.mmbh.cfgs.netflixdb.views;

import javax.swing.JFrame;

import ga.mmbh.cfgs.netflixdb.graphic.frames.CustomFrame;

public class ForgotPasswordView {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public ForgotPasswordView() {
		initialize();
		loadListeners();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new CustomFrame();
		
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private void loadListeners() {
		
	}
}
