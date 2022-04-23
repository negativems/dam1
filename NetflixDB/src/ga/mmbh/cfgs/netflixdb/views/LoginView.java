package ga.mmbh.cfgs.netflixdb.views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.google.common.hash.Hashing;

import ga.mmbh.cfgs.netflixdb.NetflixApp;
import ga.mmbh.cfgs.netflixdb.graphic.frames.CustomFrame;
import ga.mmbh.cfgs.netflixdb.managers.UserManager;
import ga.mmbh.cfgs.netflixdb.models.User;
import ga.mmbh.cfgs.netflixdb.utils.AppUtils;

public class LoginView {

	private NetflixApp netflixApp;

	private CustomFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel welcomeLabel, usernameLabel, passwordLabel, registerLabel, errorLabel;
	private JButton registerButton, loginButton, forgotPasswordButton;

	/**
	 * Create the application.
	 */
	public LoginView() {
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

		welcomeLabel = new JLabel("Bienvenido, inicia sesión");
		welcomeLabel.setFont(AppUtils.getTitleFont().deriveFont(18F));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setForeground(new Color(255, 255, 255));
		welcomeLabel.setBounds(120, 50, 260, 30);
		frame.getContentPane().add(welcomeLabel);

		usernameLabel = new JLabel("Usuario o Correo");
		usernameLabel.setForeground(AppUtils.TEXT_COLOR);
		usernameLabel.setBounds(120, 150, 360, 20);
		frame.getContentPane().add(usernameLabel);

		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(120, 180, 260, 30);
		frame.getContentPane().add(usernameField);

		passwordLabel = new JLabel("Contraseña");
		passwordLabel.setForeground(AppUtils.TEXT_COLOR);
		passwordLabel.setBounds(120, 240, 260, 20);
		frame.getContentPane().add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(120, 270, 260, 30);
		frame.getContentPane().add(passwordField);

		registerLabel = new JLabel("¿No tienes cuenta?");
		registerLabel.setBounds(185, 350, 120, 25);
		registerLabel.setForeground(Color.WHITE);
		registerLabel.setBorder(null);
		frame.getContentPane().add(registerLabel);
		
		registerButton = new JButton("¡Registrate!");
		registerButton.setBounds(300, 350, 80, 25);
		registerButton.setForeground(AppUtils.ACCENT_COLOR);
		registerButton.setBackground(null);
		registerButton.setBorder(null);
		frame.getContentPane().add(registerButton);
		
		loginButton = new JButton("Iniciar Sesión");
		loginButton.setBackground(AppUtils.ACCENT_COLOR);
		loginButton.setBorder(null);
		loginButton.setForeground(Color.WHITE);
		loginButton.setBounds(120, 380, 260, 30);
		frame.getContentPane().add(loginButton);
		
		forgotPasswordButton = new JButton("¡He olvidad mi contraseña!");
		forgotPasswordButton.setBounds(220, 420, 160, 30);
		forgotPasswordButton.setForeground(Color.WHITE);
		forgotPasswordButton.setBackground(null);
		forgotPasswordButton.setBorder(null);
		frame.getContentPane().add(forgotPasswordButton);

		errorLabel = new JLabel("");
		errorLabel.setForeground(AppUtils.ERROR_COLOR);
		errorLabel.setBounds(120, 420, 260, 25);
		frame.getContentPane().add(errorLabel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * Create buttons listeners
	 */
	public void createListeners() {
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());
				String encodedPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
				
				if (!netflixApp.getDatabaseManager().isConnected()) {
					errorLabel.setText("La base de datos no está conectada");
					return;
				}
				
				UserManager userManager = netflixApp.getUserManager();
				User user = userManager.existsUsername(username) ? user = userManager.getUser(username) : null;
				if (user == null && userManager.existsEmail(username)) {
					user = userManager.getUserByEmail(username);
				}
				
				if (user == null) {
					errorLabel.setText("No existe es usuario o ese correo");
					return;
				}
				
				if (netflixApp.getUserManager().isNotAuthenticated(user.getId())) {
					frame.dispose();
					new AuthView(user.getId());
					return;
				}
				
				if (!netflixApp.getUserManager().login(username, encodedPassword)) {
					errorLabel.setText("Usuario o contraseña erróneos");
					return;
				}
				
				frame.setVisible(false);
				new HomeView();
			}
		});

		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new RegisterView();
			}
		});
		
		forgotPasswordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new ForgotPasswordView();
			}
		});
	}
}
