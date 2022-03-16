package ga.mmbh.cfgs.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ga.mmbh.cfgs.NetflixApp;
import ga.mmbh.cfgs.utils.AppUtils;

public class LoginView {

	private final NetflixApp netflixApp;

	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel titleLabel, welcomeLabel, usernameLabel, passwordLabel, errorLabel;
	private JButton registerButton, loginButton;

	/**
	 * Create the application.
	 */
	public LoginView(NetflixApp netflixApp) {
		this.netflixApp = netflixApp;
		initialize();
		createListeners();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
		frame = new JFrame();
		frame.getContentPane().setFont(AppUtils.getNetflixFont("Medium", 12));
		frame.setFont(new Font("Rubik", Font.PLAIN, 14));
		frame.getContentPane().setBackground(AppUtils.BACKGROUND_COLOR);
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 450, 500);
		frame.setVisible(true);
		
		titleLabel = new JLabel("");
		Image image = new ImageIcon(new File("resources/netflix-logo.png").getAbsolutePath()).getImage();
		titleLabel.setIcon(new ImageIcon(image));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(0, 10, 500, 100);
		frame.getContentPane().add(titleLabel);
		
		welcomeLabel = new JLabel("Bienvenido, inicia sesión");
		welcomeLabel.setFont(new Font("Rubik", Font.PLAIN, 18));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setForeground(new Color(255, 255, 255));
		welcomeLabel.setBounds(125, 109, 250, 30);
		frame.getContentPane().add(welcomeLabel);
		
		usernameLabel = new JLabel("Usuario");
		usernameLabel.setForeground(AppUtils.TEXT_COLOR);
		usernameLabel.setBounds(125, 170, 250, 20);
		frame.getContentPane().add(usernameLabel);
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(125, 200, 250, 30);
		frame.getContentPane().add(usernameField);
		
		passwordLabel = new JLabel("Contraseña");
		passwordLabel.setForeground(AppUtils.TEXT_COLOR);
		passwordLabel.setBounds(125, 250, 250, 20);
		frame.getContentPane().add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(125, 280, 250, 30);
		frame.getContentPane().add(passwordField);
		
		loginButton = new JButton("Iniciar Sesión");
		loginButton.setBackground(new Color(176, 196, 222));
		loginButton.setBounds(125, 350, 250, 30);
		frame.getContentPane().add(loginButton);
		
		errorLabel = new JLabel("");
		errorLabel.setForeground(AppUtils.ERROR_COLOR);
		errorLabel.setBounds(125, 390, 250, 25);
		frame.getContentPane().add(errorLabel);
		
		registerButton = new JButton("Registrarme");
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		registerButton.setBounds(275, 425, 100, 25);
		frame.getContentPane().add(registerButton);
		
		JLabel registerLabel = new JLabel("¿Nuevo usuario?");
		registerLabel.setForeground(Color.WHITE);
		registerLabel.setBounds(125, 425, 150, 25);
		frame.getContentPane().add(registerLabel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.repaint();
	}
	
	/**
	 * Create buttons listeners
	 */
	public void createListeners() {
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			    if (event.getSource() == passwordField) loginButton.doClick();
			}
		});
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());
				
				if (!netflixApp.getUserManager().login(username, password)) {
					errorLabel.setText("Ese usuario no existe");
					return;
				}
				
				frame.setVisible(false);
				new MainView(netflixApp);
			}
		});
		
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new RegisterView(netflixApp);
			}
		});
	}	
}
