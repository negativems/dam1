package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.UserDAO;
import models.Usuario;

public class LoginView {

	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordPField;
	private JButton loginButton, registerButton;
	private UserDAO userDAO;

	/**
	 * Create the application.
	 */
	public LoginView() {
		this.userDAO = new UserDAO();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setText("charmander");
		usernameField.setBounds(109, 65, 186, 20);
		usernameField.setColumns(10);
		frame.getContentPane().add(usernameField);
		
		passwordPField = new JPasswordField();
		passwordPField.setBounds(109, 112, 186, 20);
		frame.getContentPane().add(passwordPField);
		
		loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = new String(passwordPField.getPassword());
				Usuario usuario = new Usuario(0, username, password);
				boolean loginCorrecto = userDAO.login(usuario);
				if(loginCorrecto) {
					JOptionPane.showMessageDialog(loginButton, "Login correcto Bob.");
					new MatriculaView();
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(loginButton, "Ah ah aaaah... login incorrecto...");
				}
			}
		});
		
		loginButton.setBounds(109, 182, 89, 23);
		frame.getContentPane().add(loginButton);
		
		registerButton = new JButton("Registrar");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RegisterView();
				frame.dispose();
			}
		});
		registerButton.setBounds(208, 182, 89, 23);
		frame.getContentPane().add(registerButton);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.repaint();
	}
}
