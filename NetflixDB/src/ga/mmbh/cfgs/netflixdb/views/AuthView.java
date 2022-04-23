package ga.mmbh.cfgs.netflixdb.views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ga.mmbh.cfgs.netflixdb.NetflixApp;
import ga.mmbh.cfgs.netflixdb.graphic.frames.CustomFrame;
import ga.mmbh.cfgs.netflixdb.graphic.panels.TitlePanel;
import ga.mmbh.cfgs.netflixdb.utils.AppUtils;

public class AuthView {

	private NetflixApp netflixApp;

	private CustomFrame frame;
	private TitlePanel titlePanel;
	private JTextField codeField;
	private JLabel infoLabel, errorLabel;
	private JButton verifyButton;

	private int userId;

	/**
	 * Create the application.
	 */
	public AuthView(int userId) {
		this.userId = userId;
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
		frame.getContentPane().add(titlePanel);

		codeField = new JTextField();
		codeField.setColumns(10);
		codeField.setBounds(120, 180, 260, 30);
		frame.getContentPane().add(codeField);

		infoLabel = new JLabel("<html>Introduce el código que te hemos<br>enviado a tu correo electrónico</html>", SwingConstants.CENTER);
		infoLabel.setForeground(Color.WHITE);
		infoLabel.setBounds(120, 250, 260, 25);
		frame.getContentPane().add(infoLabel);
		
		verifyButton = new JButton("Comprobar código");
		verifyButton.setBackground(AppUtils.ACCENT_COLOR);
		verifyButton.setBorder(null);
		verifyButton.setForeground(Color.WHITE);
		verifyButton.setBounds(120, 380, 260, 30);
		frame.getContentPane().add(verifyButton);
		
		errorLabel = new JLabel("", SwingConstants.CENTER);
		errorLabel.setForeground(AppUtils.ERROR_COLOR);
		errorLabel.setBounds(120, 350, 260, 25);
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
				
				if (!netflixApp.getUserManager().checkAuth(userId, code)) {
					errorLabel.setText("Ese código no es válido");
					return;
				}
				
				frame.dispose();
				new LoginView();
			}
		});
	}
}
