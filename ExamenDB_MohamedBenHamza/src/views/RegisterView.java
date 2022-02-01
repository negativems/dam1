package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.UsuarioDAO;
import models.Usuario;

public class RegisterView {

	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordPField, confirmPasswordPField;
	private JLabel registerLabel, usernameLabel, passwordLabel, confirmPasswordLabel;
	private JButton registerButton;
	
	private UsuarioDAO usuarioDAO;

	/**
	 * Create the application.
	 */
	public RegisterView() {
		initialize();
		loadListeners();
		this.usuarioDAO = new UsuarioDAO();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		registerLabel = new JLabel("Registro de usuarios");
		registerLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		registerLabel.setBounds(96, 11, 242, 37);
		frame.getContentPane().add(registerLabel);
		
		usernameField = new JTextField();
		usernameField.setBounds(184, 82, 180, 20);
		usernameField.setColumns(10);
		frame.getContentPane().add(usernameField);
		
		passwordPField = new JPasswordField();
		passwordPField.setBounds(184, 113, 180, 20);
		frame.getContentPane().add(passwordPField);
		
		confirmPasswordPField = new JPasswordField();
		confirmPasswordPField.setBounds(184, 144, 180, 20);
		frame.getContentPane().add(confirmPasswordPField);
		
		usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(23, 82, 108, 14);
		frame.getContentPane().add(usernameLabel);
		
		passwordLabel = new JLabel("Contrase\u00F1a");
		passwordLabel.setBounds(23, 113, 117, 14);
		frame.getContentPane().add(passwordLabel);
		
		confirmPasswordLabel = new JLabel("Confirmar contrase\u00F1a");
		confirmPasswordLabel.setBounds(21, 144, 153, 14);
		frame.getContentPane().add(confirmPasswordLabel);
		
		registerButton = new JButton("Registrar");
		registerButton.setBounds(153, 201, 89, 23);
		frame.getContentPane().add(registerButton);
	}
	
	private void loadListeners() {
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = new String(passwordPField.getPassword());
				String confirmarPassword = new String(confirmPasswordPField.getPassword());
				if(password.equals(confirmarPassword)) {
					if(!username.isEmpty() && !password.isEmpty() && !confirmarPassword.isEmpty()) {
						//TODO 2. En el registro, consigue que valide que la contraseña tenga al menos 8 caracteres (1 pto.)
						if (password.length() < 8) {
							JOptionPane.showMessageDialog(registerLabel, "¡La contraseñas debe tener al menos 8 carácteres!");
							return;
						}
						
						// Comprueba que tenga una mayuscula
						boolean hasUpper = false;
						boolean hasLower = false;
						boolean hasNumber = false;
						boolean hasSymbol = false;
						for (char c : password.toCharArray()) {
							if (!hasUpper && Character.isUpperCase(c)) {
								hasUpper = true;
							}
							if (!hasLower && Character.isLowerCase(c)) {
								hasLower = true;
							}
							if (!hasNumber && Character.isDigit(c)) {
								hasNumber = true;
							}
							
							switch (c) {
							case '\'':
							case 'º':
							case 'ª':
							case '!':
							case '"':
							case '·':
							case '$':
							case '%':
							case '&':
							case '/':
							case '(':
							case ')':
							case '=':
							case '\\':
							case '|':
							case '#':
							case ']':
								hasSymbol = true;
								break;
							default:
								hasSymbol = false;
								break;
							}
						}
						
						if (!hasUpper) {
							JOptionPane.showMessageDialog(registerLabel, "¡La contraseñas debe contener mínimo una mayúscula!");
							return;
						}
						
						if (!hasLower) {
							JOptionPane.showMessageDialog(registerLabel, "¡La contraseñas debe contener mínimo una minúscula!");
							return;
						}
						
						if (!hasNumber) {
							JOptionPane.showMessageDialog(registerLabel, "¡La contraseñas debe contener mínimo un número!");
							return;
						}
						
						if (!hasSymbol) {
							JOptionPane.showMessageDialog(registerLabel, "¡La contraseñas debe contener mínimo un símbolo!");
							return;
						}
						
						Usuario user = new Usuario(0, username, password);
						usuarioDAO.register(user);
						JOptionPane.showMessageDialog(registerButton, "Usuario registrado correctamente");
						new LoginView();
						frame.dispose();
					} else {
						JOptionPane.showMessageDialog(registerLabel, "Rellena todos los campos");
					}
				} else {
					JOptionPane.showMessageDialog(registerLabel, "Las contraseñas no coinciden");
				}
			}
		});
	}
}
