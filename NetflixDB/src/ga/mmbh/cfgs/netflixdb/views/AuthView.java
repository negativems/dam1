package ga.mmbh.cfgs.netflixdb.views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ga.mmbh.cfgs.netflixdb.NetflixApp;
import ga.mmbh.cfgs.netflixdb.graphic.frames.CustomFrame;
import ga.mmbh.cfgs.netflixdb.graphic.panels.TitlePanel;
import ga.mmbh.cfgs.netflixdb.utils.AppUtils;

public class AuthView {

	private NetflixApp netflixApp;

	private CustomFrame frame;
	private TitlePanel titlePanel;
	private JTextField codeField;
	private JLabel welcomeLabel, errorLabel;
	private JButton verifyButton;

	/**
	 * Create the application.
	 */
	public AuthView() {
		this.netflixApp = NetflixApp.getInstance();
		initialize();
		createListeners();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new CustomFrame();
		frame.setBounds((1920 / 2) - (500 / 2), (1080 / 2) - (700 / 2), 500, 600);

		titlePanel = new TitlePanel("VERIFICACIÓN", Color.RED, Color.WHITE, 500, 100);
		frame.getContentPane().add(welcomeLabel);

		codeField = new JTextField();
		codeField.setColumns(10);
		codeField.setBounds(120, 180, 260, 30);
		frame.getContentPane().add(codeField);
		
		verifyButton = new JButton("Iniciar Sesión");
		verifyButton.setBackground(AppUtils.ACCENT_COLOR);
		verifyButton.setBorder(null);
		verifyButton.setForeground(Color.WHITE);
		verifyButton.setBounds(120, 380, 260, 30);
		frame.getContentPane().add(verifyButton);

		errorLabel = new JLabel("");
		errorLabel.setForeground(AppUtils.ERROR_COLOR);
		errorLabel.setBounds(120, 367, 260, 25);
		frame.getContentPane().add(errorLabel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * Create buttons listeners
	 */
	public void createListeners() {
		verifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String code = codeField.getText();
				
				if (!netflixApp.getDatabaseManager().isConnected()) {
					errorLabel.setText("La base de datos no está conectada.");
					return;
				}
				
				frame.setVisible(false);
				new HomeView();
			}
		});
	}
}
