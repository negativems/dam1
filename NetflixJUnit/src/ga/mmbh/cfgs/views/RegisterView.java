package ga.mmbh.cfgs.views;

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

import ga.mmbh.cfgs.NetflixApp;
import ga.mmbh.cfgs.managers.UserManager;
import ga.mmbh.cfgs.utils.AppUtils;

public class RegisterView {

	private final NetflixApp netflixApp;
	
	private JFrame frame;
	private JLabel welcomeLabel, usernameLabel;
	private JTextField usernameField;
	private JPasswordField passwordField, password2Field;
	private JLabel passwordLabel, password2Label, labelPickachuImage, errorLabel;
	private JButton registerButton, backButton;
	
	public RegisterView(NetflixApp pokedexApp) {
		this.netflixApp = pokedexApp;
		initialize();
		setListeners();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(AppUtils.getNetflixFont("Medium", 12));
		frame.setFont(new Font("Rubik", Font.PLAIN, 14));
		frame.getContentPane().setBackground(AppUtils.BACKGROUND_COLOR);
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 500, 600);
		frame.setVisible(true);
		
		frame.getContentPane().setBackground(new Color(39, 45, 54));
		frame.getContentPane().setLayout(null);
		
		welcomeLabel = new JLabel("REGISTRO");
		welcomeLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setForeground(new Color(255, 255, 255));
		welcomeLabel.setBounds(107, 25, 244, 30);
		frame.getContentPane().add(welcomeLabel);
		
		usernameLabel = new JLabel("Usuario");
		usernameLabel.setForeground(new Color(255, 255, 255));
		usernameLabel.setBounds(125, 100, 250, 25);
		frame.getContentPane().add(usernameLabel);
		
		usernameField = new JTextField();
		usernameField.setBounds(125, 135, 250, 25);
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		registerButton = new JButton("Registrarme");
		registerButton.setBackground(new Color(176, 196, 222));
		registerButton.setForeground(new Color(0, 0, 0));
		registerButton.setBounds(125, 400, 250, 23);
		frame.getContentPane().add(registerButton);
		
		passwordLabel = new JLabel("Contraseña");
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setBounds(125, 180, 250, 25);
		frame.getContentPane().add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(125, 215, 250, 25);
		frame.getContentPane().add(passwordField);
		
		password2Label = new JLabel("Repetir contraseña");
		password2Label.setForeground(Color.WHITE);
		password2Label.setBounds(125, 260, 250, 20);
		frame.getContentPane().add(password2Label);
		
		password2Field = new JPasswordField();
		password2Field.setColumns(10);
		password2Field.setBounds(125, 295, 250, 25);
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
				new LoginView(netflixApp);
			}
		});
		
		backButton.setEnabled(false);
		backButton.setOpaque(false);
		backButton.setBorder(BorderFactory.createEmptyBorder());
		backButton.setIcon(new ImageIcon(new File("resources/return.png").getAbsolutePath()));
		backButton.setBounds(10, 22, 38, 33);
		frame.getContentPane().add(backButton);
		
		errorLabel = new JLabel("");
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setForeground(Color.RED);
		errorLabel.setBounds(0, 350, 500, 25);
		frame.getContentPane().add(errorLabel);
		
		frame.setBounds(100, 100, 450, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setListeners() {
		password2Field.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			    if (event.getSource() == passwordField) registerButton.doClick();
			}
		});
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new LoginView(netflixApp);
			}
		});
		
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());
				String password2 = new String(password2Field.getPassword());
				
				UserManager userManager = netflixApp.getUserManager();
				
				if (!password.equals(password2)) {
					errorLabel.setText("Las contraseñas no coinciden");
					return;
				}
				
				if (!userManager.isValidLength(password)) {
					errorLabel.setText("La contraseña debe de ser entre 8 y 15 carácteres");
					return;
				}
				
				if (!userManager.hasUpperCase(password)) {
					errorLabel.setText("La contraseña debe contener al menos una mayúscula");
					return;
				}
				
				if (!userManager.hasLowerCase(password)) {
					errorLabel.setText("La contraseña debe contener al menos una minúscula");
					return;
				}
				
				if (!userManager.hasNumber(password)) {
					errorLabel.setText("La contraseña debe contener al menos un número");
					return;
				}
				
				if (!userManager.hasSpecialCharacter(password)) {
					errorLabel.setText("La contraseña debe contener un carácter especial '@' '#' '$' ó '%'");
					return;
				}
				
				if (!userManager.register(username, password)) {
					errorLabel.setText("Ese usuario ya existe");
					return;
				}
				
				frame.dispose();
				new MainView(netflixApp);
			}
		});
	}
}
