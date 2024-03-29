package ga.mmbh.cfgs.pokedexdb.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ga.mmbh.cfgs.pokedexdb.PokedexDB;
import ga.mmbh.cfgs.pokedexdb.models.User;

public class RegisterView {

	private final PokedexDB pokedexApp;
	
	private JFrame frame;
	private JLabel welcomeLabel, usernameLabel;
	private JTextField usernameField;
	private JPasswordField passwordField, password2Field;
	private JLabel passwordLabel, password2Label, labelPickachuImage, errorLabel;
	private JButton registerButton, backButton;
	
	public RegisterView(PokedexDB pokedexApp) {
		this.pokedexApp = pokedexApp;
		initialize();
		setListeners();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("SansSerif", Font.PLAIN, 12));
		frame.getContentPane().setBackground(new Color(39, 45, 54));
		frame.getContentPane().setLayout(null);
		
		welcomeLabel = new JLabel("REGISTRO");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setForeground(new Color(255, 255, 255));
		welcomeLabel.setFont(new Font("Rubik", Font.BOLD, 22));
		welcomeLabel.setBounds(107, 25, 244, 30);
		frame.getContentPane().add(welcomeLabel);
		
		usernameLabel = new JLabel("Usuario");
		usernameLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		usernameLabel.setForeground(new Color(255, 255, 255));
		usernameLabel.setBounds(107, 107, 244, 20);
		frame.getContentPane().add(usernameLabel);
		
		usernameField = new JTextField();
		usernameField.setBounds(107, 138, 244, 20);
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		registerButton = new JButton("Registrarme");
		registerButton.setBackground(new Color(176, 196, 222));
		registerButton.setForeground(new Color(0, 0, 0));
		registerButton.setFont(new Font("SansSerif", Font.PLAIN, 10));
		registerButton.setBounds(107, 358, 244, 23);
		frame.getContentPane().add(registerButton);
		
		passwordLabel = new JLabel("Contraseña");
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setBounds(107, 183, 244, 20);
		frame.getContentPane().add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(107, 214, 244, 20);
		frame.getContentPane().add(passwordField);
		
		password2Label = new JLabel("Repetir contraseña");
		password2Label.setForeground(Color.WHITE);
		password2Label.setBounds(107, 261, 244, 20);
		frame.getContentPane().add(password2Label);
		
		password2Field = new JPasswordField();
		password2Field.setColumns(10);
		password2Field.setBounds(107, 292, 244, 20);
		frame.getContentPane().add(password2Field);
		
		labelPickachuImage = new JLabel("");
		labelPickachuImage.setIcon(new ImageIcon(new File("resources/pikachu.png").getAbsolutePath()));
		labelPickachuImage.setBounds(331, 11, 93, 93);
		frame.getContentPane().add(labelPickachuImage);
		
		backButton = new JButton("");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
				new LoginView(pokedexApp);
			}
		});
		
		backButton.setEnabled(false);
		backButton.setOpaque(false);
		backButton.setBorder(BorderFactory.createEmptyBorder());
		backButton.setIcon(new ImageIcon(new File("resources/return.png").getAbsolutePath()));
		backButton.setBounds(10, 22, 38, 33);
		frame.getContentPane().add(backButton);
		
		errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);
		errorLabel.setBounds(107, 420, 244, 14);
		frame.getContentPane().add(errorLabel);
		
		frame.setBounds(100, 100, 450, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setListeners() {
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new LoginView(pokedexApp);
			}
		});
		
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());
				String password2 = new String(password2Field.getPassword());
				
				if (!password.equals(password2)) {
					errorLabel.setText("Las contraseñas no coinciden");
					return;
				}
				
				if (!pokedexApp.getPokemonManager().isConnected()) {
					errorLabel.setText("La base de datos no está conectada.");
					return;
				}
				
				register(username, password);
				frame.dispose();
				new LoginView(pokedexApp);
			}
		});
	}
	
	public void register(String username, String password) {
		pokedexApp.getUserManager().registerUser(new User(username, password));
	}
}
