package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class RegisterView {

	private JFrame frame;
	private JLabel welcomeLabel, usernameLabel;
	private JTextField usernameField, passwordField, password2Field;
	private JLabel passwordLabel, password2Label;
	private JButton registerButton;
	
	/**
	 * Create the application.
	 */
	public RegisterView() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("SansSerif", Font.PLAIN, 11));
		
		frame.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		frame.getContentPane().setBackground(new Color(39, 45, 54));
		frame.getContentPane().setLayout(null);
		
		welcomeLabel = new JLabel("REGISTRO");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setForeground(new Color(255, 255, 255));
		welcomeLabel.setFont(new Font("Rubik", Font.BOLD, 22));
		welcomeLabel.setBounds(107, 25, 244, 30);
		frame.getContentPane().add(welcomeLabel);
		
		usernameLabel = new JLabel("Usuario");
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
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
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RegisterView();
			}
		});
		registerButton.setBounds(107, 358, 244, 23);
		frame.getContentPane().add(registerButton);
		
		passwordLabel = new JLabel("Contraseña");
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		passwordLabel.setBounds(107, 183, 244, 20);
		frame.getContentPane().add(passwordLabel);
		
		passwordField = new JTextField();
		passwordField.setColumns(10);
		passwordField.setBounds(107, 214, 244, 20);
		frame.getContentPane().add(passwordField);
		
		password2Field = new JTextField();
		password2Field.setColumns(10);
		password2Field.setBounds(107, 292, 244, 20);
		frame.getContentPane().add(password2Field);
		
		password2Label = new JLabel("Repetir contraseña");
		password2Label.setForeground(Color.WHITE);
		password2Label.setFont(new Font("Tahoma", Font.BOLD, 12));
		password2Label.setBounds(107, 261, 244, 20);
		frame.getContentPane().add(password2Label);
		
		JLabel labelPickachuImage = new JLabel("");
		labelPickachuImage.setIcon(new ImageIcon(RegisterView.class.getResource("/assets/img/pikachu.png")));
		labelPickachuImage.setBounds(331, 11, 93, 93);
		frame.getContentPane().add(labelPickachuImage);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new LoginView();
			}
		});
		btnNewButton.setEnabled(false);
		btnNewButton.setOpaque(false);
		btnNewButton.setBorder(BorderFactory.createEmptyBorder());
		btnNewButton.setIcon(new ImageIcon(RegisterView.class.getResource("/assets/img/return.png")));
		btnNewButton.setBounds(26, 29, 16, 16);
		frame.getContentPane().add(btnNewButton);
		frame.setBounds(100, 100, 450, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
