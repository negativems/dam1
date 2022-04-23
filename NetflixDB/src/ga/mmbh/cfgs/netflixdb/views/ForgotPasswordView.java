package ga.mmbh.cfgs.netflixdb.views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ga.mmbh.cfgs.netflixdb.NetflixApp;
import ga.mmbh.cfgs.netflixdb.graphic.frames.CustomFrame;
import ga.mmbh.cfgs.netflixdb.graphic.panels.TitlePanel;
import ga.mmbh.cfgs.netflixdb.models.User;
import ga.mmbh.cfgs.netflixdb.utils.AppUtils;
import ga.mmbh.cfgs.netflixdb.utils.EmailUtils;

public class ForgotPasswordView {

	private NetflixApp netflixApp;

	private CustomFrame frame;
	private TitlePanel titlePanel;
	private JTextField emailField, codeField, passwordField, password2Field;
	private JLabel emailLabel, codeLabel, passwordLabel, password2Label, errorLabel;
	private JButton sendButton, confirmButton;
	
	private String verificationCode;
	private boolean codeConfirmed = false;
	private User user = null;

	/**
	 * Create the application.
	 */
	public ForgotPasswordView() {
		this.netflixApp = NetflixApp.getInstance();
		initialize();
		createListeners();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new CustomFrame();
		frame.setBounds((1920 / 2) - (500 / 2), (1080 / 2) - (700 / 2), 500, 350);

		titlePanel = new TitlePanel("CONTRASEÑA OLVIDADA", AppUtils.BACKGROUND_COLOR, Color.WHITE, 500, 100);
		frame.getContentPane().add(titlePanel);

		emailLabel = new JLabel("Introduce tu correo electrónico");
		emailLabel.setForeground(Color.WHITE);
		emailLabel.setBounds(110, 150, 280, 20);
		frame.getContentPane().add(emailLabel);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(110, 180, 280, 30);
		frame.getContentPane().add(emailField);
		
		codeLabel = new JLabel("Introduce el código que te hemos enviado");
		codeLabel.setForeground(Color.WHITE);
		codeLabel.setBounds(110, 230, 280, 25);
		
		codeField = new JTextField();
		codeField.setColumns(10);
		codeField.setBounds(110, 260, 170, 30);
		
		confirmButton = new JButton("Comprobar");
		confirmButton.setBackground(AppUtils.ACCENT_COLOR);
		confirmButton.setBorder(null);
		confirmButton.setForeground(Color.WHITE);
		confirmButton.setBounds(290, 260, 100, 30);
		
		passwordLabel = new JLabel("Introduce la nueva contraseña");
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setBounds(110, 130, 280, 25);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(110, 150, 280, 30);
		
		password2Label = new JLabel("Vuelve a introducir la contraseña");
		password2Label.setForeground(Color.WHITE);
		password2Label.setBounds(110, 190, 280, 25);
		
		password2Field = new JPasswordField();
		password2Field.setColumns(10);
		password2Field.setBounds(110, 210, 280, 30);
		
		sendButton = new JButton("Enviar");
		sendButton.setBackground(AppUtils.ACCENT_COLOR);
		sendButton.setBorder(null);
		sendButton.setForeground(Color.WHITE);
		sendButton.setBounds(110, 270, 280, 30);
		frame.getContentPane().add(sendButton);
		
		errorLabel = new JLabel("", SwingConstants.CENTER);
		errorLabel.setForeground(AppUtils.ERROR_COLOR);
		errorLabel.setBounds(110, 240, 260, 25);
		frame.getContentPane().add(errorLabel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * Create buttons listeners
	 */
	public void createListeners() {
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				errorLabel.setText("");
				String email = emailField.getText();
				
				if (!netflixApp.getUserManager().existsEmail(email)) {
					errorLabel.setText("Esa cuenta no existe");
					return;
				}
				
				user = netflixApp.getUserManager().getUserByEmail(email);
				errorLabel.setBounds(110, 300, 260, 25);
				frame.getContentPane().remove(sendButton);
				frame.getContentPane().add(codeLabel);
				frame.getContentPane().add(codeField);
				frame.getContentPane().add(confirmButton);
				frame.repaint();
				
				verificationCode = (int) (Math.random() * (1000000) + 1) + "";
				System.out.println(verificationCode);
				EmailUtils.send(email, "NetflixDB - Contraseña olvidada", "Al parecer has olvidado tu contraseña, usa este código para reasignarla: " + verificationCode);
			}
		});
		
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				errorLabel.setText("");
				if (!codeConfirmed) {
					String code = codeField.getText();
					if (!verificationCode.equals(code)) {
						errorLabel.setText("¡Ese código no es válido!");
						return;
					}
					
					codeConfirmed = true;
					
					frame.getContentPane().remove(emailLabel);
					frame.getContentPane().remove(emailField);
					frame.getContentPane().remove(codeLabel);
					frame.getContentPane().remove(codeField);
					
					confirmButton.setText("Reestablecer contraseña");
					confirmButton.setBounds(110, 280, 280, 30);
					
					frame.getContentPane().add(passwordLabel);
					frame.getContentPane().add(password2Label);
					frame.getContentPane().add(passwordField);
					frame.getContentPane().add(password2Field);
					frame.repaint();
					
					return;
				}
				
				netflixApp.getUserManager().changePassword(user.getId(), passwordField.getText());
				
				frame.dispose();
				new LoginView();
			}
		});
	}
}
