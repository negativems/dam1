package ga.mmbh.cfgs.netflixdb.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ga.mmbh.cfgs.netflixdb.NetflixApp;
import ga.mmbh.cfgs.netflixdb.graphic.frames.CustomFrame;
import ga.mmbh.cfgs.netflixdb.utils.AppUtils;

public class LoginView {

	private final NetflixApp ficherosApp;

	private CustomFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel titleLabel, welcomeLabel, usernameLabel, passwordLabel;
	private JButton registerButton, loginButton;
	private JLabel errorLabel;

	/**
	 * Create the application.
	 */
	public LoginView(NetflixApp ficherosApp) {
		this.ficherosApp = ficherosApp;
		initialize();
		createListeners();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new CustomFrame();
		frame.getContentPane().setBackground(AppUtils.DARK_BACKGROUND);

		titleLabel = new JLabel("");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(0, 0, 434, 112);

//		try {
//			URL url = new URL("https://1000marcas.net/wp-content/uploads/2020/01/Logo-Netflix.png");
//		    BufferedImage bufferedImage = ImageIO.read(url);
//		    Image image = new ImageIcon(bufferedImage).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
//		    titleLabel.setIcon(new ImageIcon(image));
//		} catch (IOException editMovieButton) {
//			editMovieButton.printStackTrace();
//		}

		frame.getContentPane().add(titleLabel);

		welcomeLabel = new JLabel("Bienvenido, inicia sesión");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setForeground(new Color(255, 255, 255));
		welcomeLabel.setBounds(108, 109, 244, 30);
		frame.getContentPane().add(welcomeLabel);

		registerButton = new JButton("Registrarme");

		registerButton.setBounds(339, 425, 85, 25);
		frame.getContentPane().add(registerButton);

		usernameLabel = new JLabel("Usuario");
		usernameLabel.setForeground(AppUtils.TEXT_COLOR);
		usernameLabel.setBounds(108, 150, 244, 20);
		frame.getContentPane().add(usernameLabel);

		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(108, 181, 244, 30);
		frame.getContentPane().add(usernameField);

		passwordLabel = new JLabel("Contraseña");
		passwordLabel.setForeground(AppUtils.TEXT_COLOR);
		passwordLabel.setBounds(108, 226, 244, 20);
		frame.getContentPane().add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(108, 257, 244, 30);
		frame.getContentPane().add(passwordField);

		loginButton = new JButton("Iniciar Sesión");
		loginButton.setBackground(new Color(176, 196, 222));
		loginButton.setBounds(108, 311, 244, 30);
		frame.getContentPane().add(loginButton);

		errorLabel = new JLabel("");
		errorLabel.setForeground(AppUtils.ERROR_COLOR);
		errorLabel.setBounds(108, 367, 244, 25);
		frame.getContentPane().add(errorLabel);

		frame.setBounds(100, 100, 450, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.changeFont(frame);
	}

	/**
	 * Create buttons listeners
	 */
	public void createListeners() {
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());

				if (!ficherosApp.getUserManager().login(username, password)) {
					errorLabel.setText("Usuario o contraseña erróneos");
					return;
				}

				frame.setVisible(false);
				new HomeView(ficherosApp);
			}
		});

		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new RegisterView(ficherosApp);
			}
		});
	}
}
