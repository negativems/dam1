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

public class RegisterView {

	private final NetflixApp netflixApp;

	private CustomFrame frame;
	private JTextField usernameField, emailField;
	private JPasswordField passwordField, passwordField2;
	private JLabel welcomeLabel, usernameLabel, emailLabel, passwordLabel, passwordLabel2;
	private JButton registerButton;
	private JLabel errorLabel;

	/**
	 * Create the application.
	 */
	public RegisterView() {
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
		
		welcomeLabel = new JLabel("Registro");
		welcomeLabel.setFont(AppUtils.getTitleFont().deriveFont(18F));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setForeground(new Color(255, 255, 255));
		welcomeLabel.setBounds(120, 50, 260, 30);
		frame.getContentPane().add(welcomeLabel);
		
		usernameLabel = new JLabel("Usuario");
		usernameLabel.setForeground(AppUtils.TEXT_COLOR);
		usernameLabel.setBounds(120, 120, 360, 20);
		frame.getContentPane().add(usernameLabel);
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(120, 150, 260, 30);
		frame.getContentPane().add(usernameField);
		
		emailLabel = new JLabel("Correo");
		emailLabel.setForeground(AppUtils.TEXT_COLOR);
		emailLabel.setBounds(120, 210, 360, 20);
		frame.getContentPane().add(emailLabel);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(120, 240, 260, 30);
		frame.getContentPane().add(emailField);
		
		passwordLabel = new JLabel("Contraseña");
		passwordLabel.setForeground(AppUtils.TEXT_COLOR);
		passwordLabel.setBounds(120, 300, 260, 20);
		frame.getContentPane().add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(120, 330, 260, 30);
		frame.getContentPane().add(passwordField);
		
		passwordLabel2 = new JLabel("Verifica la contraseña");
		passwordLabel2.setForeground(AppUtils.TEXT_COLOR);
		passwordLabel2.setBounds(120, 390, 260, 20);
		frame.getContentPane().add(passwordLabel2);
		
		passwordField2 = new JPasswordField();
		passwordField2.setColumns(10);
		passwordField2.setBounds(120, 420, 260, 30);
		frame.getContentPane().add(passwordField2);
		
		registerButton = new JButton("Registrate");
		registerButton.setBackground(AppUtils.ACCENT_COLOR);
		registerButton.setBorder(null);
		registerButton.setBounds(120, 490, 260, 30);
		frame.getContentPane().add(registerButton);
		
		errorLabel = new JLabel("");
		errorLabel.setForeground(AppUtils.ERROR_COLOR);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setBounds(120, 530, 260, 25);
		frame.getContentPane().add(errorLabel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * Create buttons listeners
	 */
	public void createListeners() {
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String email = emailField.getText();
				String password = new String(passwordField.getPassword());
				String password2 = new String(passwordField2.getPassword());
				
				UserManager userManager = netflixApp.getUserManager();
				if (userManager.existsUsername(username)) {
					errorLabel.setText("Ese nombre ya está registrado");
					return;
				}
				
				if (userManager.existsEmail(email)) {
					errorLabel.setText("Ese correo ya está en uso");
					return;
				}
				
				if (!password.equals(password2)) {
					errorLabel.setText("Las contraseñas no coinciden");
					return;
				}
				
				if (!netflixApp.getDatabaseManager().isConnected()) {
					errorLabel.setText("La base de datos no está conectada");
					return;
				}
				
				String encodedPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
				if (!userManager.registerUser(username, email, encodedPassword)) {
					errorLabel.setText("Ha ocurrido un error al intentar registrarte");
					return;
				}
				
				User user = userManager.getUser(username);
				userManager.sendVerification(user);
				
				frame.dispose();
				new AuthView(user.getId());
			}
		});
	}
}
