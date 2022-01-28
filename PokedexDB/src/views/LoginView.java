package views;

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

import mainApp.PokedexApp;
import models.User;
import utils.AppUtils;

public class LoginView {

	private final PokedexApp pokedexApp;
	
	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel titleLabel, welcomeLabel, usernameLabel, passwordLabel;
	private JButton registerButton, loginButton;
	private JLabel errorLabel;
	private Font font;

	/**
	 * Create the application.
	 */
	public LoginView(PokedexApp pokedexApp) {
		this.pokedexApp = pokedexApp;
		font = AppUtils.getPokemonFont();
		initialize();
		createListeners();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.getContentPane().setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		
		frame.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		frame.getContentPane().setBackground(AppUtils.BACKGROUND_COLOR);
		frame.getContentPane().setLayout(null);
		
		titleLabel = new JLabel("POKEDEX");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(new Color(69, 174, 255));
		titleLabel.setFont(font.deriveFont(Font.BOLD, 30F));
		titleLabel.setBounds(108, 11, 244, 58);
		frame.getContentPane().add(titleLabel);
		
		welcomeLabel = new JLabel("Bienvenido, inicia sesión");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setForeground(new Color(255, 255, 255));
		welcomeLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		welcomeLabel.setBounds(108, 94, 244, 30);
		frame.getContentPane().add(welcomeLabel);
		
		registerButton = new JButton("Registrarme");
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		registerButton.setBounds(339, 425, 85, 25);
		frame.getContentPane().add(registerButton);
		
		usernameLabel = new JLabel("Usuario");
		usernameLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		usernameLabel.setForeground(AppUtils.TEXT_COLOR);
		usernameLabel.setBounds(108, 150, 244, 20);
		frame.getContentPane().add(usernameLabel);
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(108, 181, 244, 30);
		frame.getContentPane().add(usernameField);
		
		passwordLabel = new JLabel("Contraseña");
		passwordLabel.setForeground(AppUtils.TEXT_COLOR);
		passwordLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
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
		errorLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
		errorLabel.setForeground(AppUtils.ERROR_COLOR);
		errorLabel.setBounds(108, 367, 244, 25);
		frame.getContentPane().add(errorLabel);
		
		frame.setBounds(100, 100, 450, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Create buttons listeners
	 */
	public void createListeners() {
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());
				
				if (!pokedexApp.getUserManager().login(username, password)) {
					errorLabel.setText("Usuario o contraseña erróneos");
					return;
				}

				frame.setVisible(false);
				new PokemonView(pokedexApp);
			}
		});
		
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new RegisterView(pokedexApp);
			}
		});		
	}	
}
