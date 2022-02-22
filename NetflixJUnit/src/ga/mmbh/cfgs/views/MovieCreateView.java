package ga.mmbh.cfgs.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ga.mmbh.cfgs.NetflixApp;
import ga.mmbh.cfgs.models.Genre;
import ga.mmbh.cfgs.models.Movie;
import ga.mmbh.cfgs.utils.AppUtils;
import ga.mmbh.cfgs.utils.JavaUtils;
import ga.mmbh.cfgs.utils.RoundedJPanel;
import javax.swing.DefaultComboBoxModel;

public class MovieCreateView {

	private final NetflixApp netflixApp;
	private final int id;
	
	private JFrame frame;
	private JPanel topPanel;
	private JButton backButton, cancelButton, saveMovieButton;
	private JLabel returnTextLabel, movieImageLabel, idLabel, errorLabel, durationLabel, minAgeLabel, genreLabel, directorLabel, URLLabel;
	private JComboBox<String> genreValueComboBox;
	private JTextField nameField, directorField, URLField, minAgeField, durationField;

	/**
	 * Constructor
	 * @wbp.parser.constructor
	 *
	 */
	public MovieCreateView(NetflixApp netflixApp) {
		this.netflixApp = netflixApp;
		this.id = netflixApp.getMovieManager().getMovies().size();
		initialize();
		setListeners();
		frame.setVisible(true);
	}

	/**
	 * Initialize the view
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		frame.getContentPane().setBackground(AppUtils.BACKGROUND_COLOR);
		frame.setBounds(100, 100, 500, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Top rounded panel
		topPanel = new RoundedJPanel(40, 40);
		topPanel.setBounds(0, -22, 484, 298);
		topPanel.setBackground(AppUtils.ACCENT_COLOR);
		topPanel.setBorder(null);
		topPanel.setOpaque(false);
		frame.getContentPane().add(topPanel);
		topPanel.setLayout(null);

		// Back button
		backButton = new JButton("");
		backButton.setIcon(new ImageIcon(MovieCreateView.class.getResource("/return.png")));
		backButton.setBounds(10, 39, 32, 28);
		backButton.setBackground(AppUtils.BACKGROUND_COLOR);
		backButton.setOpaque(false);
		backButton.setBorder(null);
		topPanel.add(backButton);

		// Back button text
		returnTextLabel = new JLabel("Netflix > Añadir película");
		returnTextLabel.setForeground(Color.WHITE);
		returnTextLabel.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 18));
		returnTextLabel.setBounds(69, 33, 243, 34);
		topPanel.add(returnTextLabel);

		// Movie's ID
		idLabel = new JLabel("#" + id);
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setForeground(Color.WHITE);
		idLabel.setBounds(415, 39, 59, 28);
		topPanel.add(idLabel);
		
		// Movie's name
		nameField = new JTextField("Nombre");
		nameField.setBounds(10, 240, 465, 26);
		nameField.setBackground(AppUtils.ACCENT_COLOR);
		nameField.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 16));
		nameField.setForeground(Color.WHITE);
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		topPanel.add(nameField);
		
		// Movie's image
		movieImageLabel = new JLabel("");
		movieImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		movieImageLabel.setBounds(79, 78, 316, 190);
		topPanel.add(movieImageLabel);
		
		// Genre
		genreLabel = new JLabel("Género");
		genreLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		genreLabel.setForeground(Color.WHITE);
		genreLabel.setBounds(101, 440, 140, 25);
		frame.getContentPane().add(genreLabel);
		
		// String[] genreList = Arrays.asList(Genre.values()).stream().map(Genre::name).toArray(String[]::new);
		genreValueComboBox = new JComboBox<String>();
		genreValueComboBox.setModel(new DefaultComboBoxModel(Genre.values()));
		genreValueComboBox.setBounds(251, 440, 66, 25);
		genreValueComboBox.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		genreValueComboBox.setForeground(Color.WHITE);
		genreValueComboBox.setBackground(AppUtils.BACKGROUND_COLOR);
		frame.getContentPane().add(genreValueComboBox);

		// Duration
		durationLabel = new JLabel("Duración");
		durationLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		durationLabel.setForeground(Color.WHITE);
		durationLabel.setBounds(101, 320, 140, 25);
		frame.getContentPane().add(durationLabel);
		
		durationField = new JTextField("120");
		durationField.setForeground(Color.WHITE);
		durationField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		durationField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		durationField.setBackground(new Color(39, 45, 54));
		durationField.setBounds(251, 320, 140, 25);
		frame.getContentPane().add(durationField);

		// Weight
		minAgeLabel = new JLabel("Edad recomendada");
		minAgeLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		minAgeLabel.setForeground(Color.WHITE);
		minAgeLabel.setBounds(101, 360, 140, 25);
		frame.getContentPane().add(minAgeLabel);

		// Specie
		directorLabel = new JLabel("Director");
		directorLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		directorLabel.setForeground(Color.WHITE);
		directorLabel.setBounds(101, 400, 140, 25);
		frame.getContentPane().add(directorLabel);

		directorField = new JTextField("Christopher Nolan");
		directorField.setForeground(Color.WHITE);
		directorField.setBackground(AppUtils.BACKGROUND_COLOR);
		directorField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		directorField.setBounds(251, 400, 140, 25);
		directorField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		frame.getContentPane().add(directorField);

		// Cancel button
		cancelButton = new JButton("Cancelar");
		cancelButton.setForeground(Color.WHITE);
		cancelButton.setBackground(Color.RED);
		cancelButton.setBounds(358, 530, 80, 23);
		frame.getContentPane().add(cancelButton);

		// Save button
		saveMovieButton = new JButton("Guardar");
		saveMovieButton.setForeground(Color.WHITE);
		saveMovieButton.setBackground(AppUtils.GREEN_COLOR);
		saveMovieButton.setBounds(268, 530, 80, 23);
		frame.getContentPane().add(saveMovieButton);
		
		// URL
		URLLabel = new JLabel("URL");
		URLLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		URLLabel.setForeground(Color.WHITE);
		URLLabel.setBounds(52, 530, 46, 23);
		frame.getContentPane().add(URLLabel);
		
		URLField = new JTextField("");
		URLField.setBounds(87, 530, 171, 23);
		frame.getContentPane().add(URLField);
		
		// Error label
		errorLabel = new JLabel("");
		errorLabel.setVerticalAlignment(SwingConstants.TOP);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setForeground(AppUtils.ERROR_COLOR);
		errorLabel.setBounds(0, 493, 484, 26);
		frame.getContentPane().add(errorLabel);
		
		minAgeField = new JTextField("14");
		minAgeField.setForeground(Color.WHITE);
		minAgeField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		minAgeField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		minAgeField.setBackground(new Color(39, 45, 54));
		minAgeField.setBounds(251, 360, 140, 25);
		frame.getContentPane().add(minAgeField);
	}

	public void setListeners() {
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new MainView(netflixApp);
			}
		});

		saveMovieButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (!JavaUtils.isInteger(durationField.getText()) || !JavaUtils.isInteger(durationField.getText())) {
					errorLabel.setText("Datos erróneos, la duración debe ser un entero");
					return;
				}
				
				if (!JavaUtils.isInteger(minAgeField.getText()) || !JavaUtils.isInteger(minAgeField.getText())) {
					errorLabel.setText("Datos erróneos, la edad mínima debe de ser un entero");
					return;
				}
				
				try {
					URL URL = new URL(URLField.getText());
					BufferedImage bufferedImage = ImageIO.read(URL);
					Image image = new ImageIcon(bufferedImage).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
					movieImageLabel.setIcon(new ImageIcon(image));
				} catch (IOException e) {
					errorLabel.setText("Error en el campo URL");
					return;
				}		
				
				String name = nameField.getText();
				int minAge = Integer.parseInt(minAgeField.getText());
				String director = directorField.getText();
				Genre genre = (Genre) genreValueComboBox.getSelectedItem();
				String imageURL = URLField.getText();
				int duration = Integer.parseInt(durationField.getText());
				
				Movie movie = new Movie(id, name, minAge, director, genre, duration, imageURL);
				netflixApp.getMovieManager().addMovie(movie);
				
				frame.dispose();
				new MainView(netflixApp, id);
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new MainView(netflixApp);
			}
		});
	}
}
