package ga.mmbh.cfgs.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;

import ga.mmbh.cfgs.NetflixApp;
import ga.mmbh.cfgs.utils.AppUtils;
import ga.mmbh.cfgs.utils.JavaUtils;
import ga.mmbh.cfgs.utils.RoundedJPanel;

public class MovieCreateView {

	private final NetflixApp netflixApp;
	
	private JFrame frame;
	private JPanel topPanel;
	private JButton backButton, cancelButton, saveMovieButton;
	private JSpinner genreValueSpinner;
	private JLabel returnTextLabel, movieImageLabel, errorLabel, durationLabel, minAgeLabel, genreLabel, directorLabel, URLLabel;
	private JTextField nameField, heightField, specieField, weightField, URLField;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Constructor
	 */
	public MovieCreateView(NetflixApp netflixApp) {
		this.netflixApp = netflixApp;
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

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		// Top rounded panel
		topPanel = new RoundedJPanel(40, 40);
		topPanel.setBounds(0, -22, screen.width, 298);
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

		// Pokemon's name
		nameField = new JTextField("Nombre");
		nameField.setBounds(10, 242, 465, 26);
		nameField.setBackground(AppUtils.ACCENT_COLOR);
		nameField.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 16));
		nameField.setForeground(Color.WHITE);
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		topPanel.add(nameField);
		
		// Pokemon image
		movieImageLabel = new JLabel("");
		movieImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		movieImageLabel.setBounds(79, 78, 316, 190);
		topPanel.add(movieImageLabel);
		
				heightField = new JTextField("1.0");
				heightField.setBounds(370, 245, 66, 25);
				topPanel.add(heightField);
				heightField.setForeground(Color.WHITE);
				heightField.setBackground(AppUtils.BACKGROUND_COLOR);
				heightField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
				heightField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
				
						weightField = new JTextField("1.0");
						weightField.setBounds(370, 282, 66, 25);
						topPanel.add(weightField);
						weightField.setForeground(Color.WHITE);
						weightField.setBackground(AppUtils.BACKGROUND_COLOR);
						weightField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
						weightField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
						
						genreValueSpinner = new JSpinner();
						genreValueSpinner.setBounds(370, 318, 66, 25);
						topPanel.add(genreValueSpinner);
						genreValueSpinner.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
						genreValueSpinner.setForeground(Color.WHITE);
						genreValueSpinner.setBackground(AppUtils.BACKGROUND_COLOR);
						genreValueSpinner.setModel(new SpinnerListModel(new String[] { "macho", "hembra" }));

		// Stats
		// Height
		durationLabel = new JLabel("Duración");
		durationLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		durationLabel.setForeground(Color.WHITE);
		durationLabel.setBounds(52, 309, 140, 26);
		frame.getContentPane().add(durationLabel);

		// Weight
		minAgeLabel = new JLabel("Edad recomendada");
		minAgeLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		minAgeLabel.setForeground(Color.WHITE);
		minAgeLabel.setBounds(52, 346, 140, 25);
		frame.getContentPane().add(minAgeLabel);

		// Gender
		genreLabel = new JLabel("Género");
		genreLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		genreLabel.setForeground(Color.WHITE);
		genreLabel.setBounds(52, 382, 140, 25);
		frame.getContentPane().add(genreLabel);

		// Specie
		directorLabel = new JLabel("Especie");
		directorLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		directorLabel.setForeground(Color.WHITE);
		directorLabel.setBounds(52, 426, 140, 25);
		frame.getContentPane().add(directorLabel);

		specieField = new JTextField("Planta");
		specieField.setForeground(Color.WHITE);
		specieField.setBackground(AppUtils.BACKGROUND_COLOR);
		specieField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		specieField.setBounds(202, 427, 73, 25);
		specieField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		frame.getContentPane().add(specieField);

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
		errorLabel.setForeground(Color.WHITE);
		errorLabel.setBounds(0, 493, 484, 26);
		frame.getContentPane().add(errorLabel);
		
		JSpinner genderValueSpinner = new JSpinner();
		genderValueSpinner.setForeground(Color.WHITE);
		genderValueSpinner.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		genderValueSpinner.setBackground(new Color(39, 45, 54));
		genderValueSpinner.setBounds(202, 382, 66, 25);
		frame.getContentPane().add(genderValueSpinner);
		
		textField = new JTextField("Planta");
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		textField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		textField.setBackground(new Color(39, 45, 54));
		textField.setBounds(202, 346, 73, 25);
		frame.getContentPane().add(textField);
		
		textField_1 = new JTextField("Planta");
		textField_1.setForeground(Color.WHITE);
		textField_1.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		textField_1.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		textField_1.setBackground(new Color(39, 45, 54));
		textField_1.setBounds(202, 313, 73, 25);
		frame.getContentPane().add(textField_1);
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
				String name = nameField.getText();
				String description = descriptionField.getText();
				String specie = specieField.getText();
				String ability = abilityField.getText();
				String URLId = URLField.getText();
				String URLPath = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + URLId + ".png";
				String heightString = heightField.getText();
				String weightString = weightField.getText();
				Object genderObject = genreValueSpinner.getValue();
				
				if (!JavaUtils.isFloat(heightString) || !JavaUtils.isFloat(weightString) || !JavaUtils.isString(genderObject) || !JavaUtils.isInteger(URLId)) {
					errorLabel.setText("Datos erróneos, comprueba la altura, peso, género o el id");
					return;
				}
				
				int pokemonId = Integer.parseInt(URLId);
				float height = Float.parseFloat(heightString);
				float weight = Float.parseFloat(weightString);
				String gender = String.valueOf(genderObject).toUpperCase();
				
				try {
					URL URL = new URL(URLPath);
					BufferedImage bufferedImage = ImageIO.read(URL);
					Image image = new ImageIcon(bufferedImage).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
					movieImageLabel.setIcon(new ImageIcon(image));
				} catch (IOException e) {
					errorLabel.setText("No se ha encontrado el pokemon con ese ID");
					return;
				}		
				
				frame.dispose();
				
				URLPath = AppUtils.getFormattedPokemonId(pokemonId);
				Pokemon pokemon = new Pokemon(pokemonId, name, description, specie, ability, URLPath, weight, height, Gender.valueOf(gender));
				netflixApp.getPokemonManager().getPokemons().add(pokemon);
				new MainView(netflixApp, pokemon.getId());
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
