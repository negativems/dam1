package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import dao.UsuarioDAO;
import models.Usuario;

public class LoginView {

	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton, registerButton;
	private UsuarioDAO usuarioDAO;

	/**
	 * Create the application.
	 */
	public LoginView() {
		initialize();
		loadListeners();
		this.usuarioDAO = new UsuarioDAO();
		frame.repaint();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		usernameField = new JTextField();
		usernameField.setText("charmander");
		usernameField.setBounds(109, 65, 186, 20);
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(109, 112, 186, 20);
		frame.getContentPane().add(passwordField);

		loginButton = new JButton("Login");
		loginButton.setBounds(109, 182, 89, 23);
		frame.getContentPane().add(loginButton);

		registerButton = new JButton("Registrar");
		registerButton.setBounds(208, 182, 89, 23);
		frame.getContentPane().add(registerButton);
	}

	private void performLogin() {
		String username = usernameField.getText();
		String password = new String(passwordField.getPassword());
		Usuario usuario = new Usuario(0, username, password);
		if (usuarioDAO.login(usuario)) {
			JOptionPane.showMessageDialog(loginButton, "Login correcto Bob.");
			new MatriculaView();
			frame.dispose();
		} else {
			JOptionPane.showMessageDialog(loginButton, "Ah ah aaaah... login incorrecto...");
		}
	}
	
	private void loadListeners() {
		loginButton.addActionListener(event -> performLogin());
		passwordField.addActionListener(event -> performLogin());
		registerButton.addActionListener(event -> {
			new RegisterView();
			frame.dispose();
		});
	}
}
