package views;

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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;

import mainApp.PokedexApp;
import models.Gender;
import models.Pokemon;
import utils.AppUtils;
import utils.ImageUtils;
import utils.JavaUtils;
import utils.RoundedJPanel;

public class PokemonCreateView {

	private final PokedexApp pokedexApp;

	private final int pokemonId;
	
	private JFrame frame;
	private JPanel topPanel;
	private JButton backButton;
	private JButton cancelButton, savePokemonButton;
	private JSpinner genderValueSpinner;
	private JLabel returnTextLabel, pokemonImageLabel, errorLabel, heightLabel, weightLabel, genderLabel, specieLabel, abilityLabel;
	private JTextField nameField, heightField, specieField, abilityField, weightField, URLField, descriptionField;

	/**
	 * Constructor
	 */
	public PokemonCreateView(PokedexApp pokedexApp) {
		this.pokedexApp = pokedexApp;
		this.pokemonId = pokedexApp.getPokemonManager().getPokemons().size();
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
		backButton.setIcon(new ImageIcon(PokemonCreateView.class.getResource("/assets/img/return.png")));
		backButton.setBounds(10, 39, 32, 28);
		backButton.setBackground(AppUtils.TRANSPARENT_COLOR);
		backButton.setOpaque(false);
		backButton.setBorder(null);
		topPanel.add(backButton);

		// Back button text
		returnTextLabel = new JLabel("Pokedex > Crear");
		returnTextLabel.setForeground(Color.WHITE);
		returnTextLabel.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 18));
		returnTextLabel.setBounds(69, 33, 190, 34);
		topPanel.add(returnTextLabel);

		// Pokemon's name
		nameField = new JTextField("Nombre Pokémon");
		nameField.setBackground(AppUtils.TRANSPARENT_COLOR);
		nameField.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 16));
		nameField.setForeground(Color.WHITE);
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setBounds(140, 299, 220, 26);
		frame.getContentPane().add(nameField);

		// Pokemon image
		pokemonImageLabel = new JLabel("");
		pokemonImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pokemonImageLabel.setBounds(79, 78, 316, 190);

		try {
			URL url = new URL("https://assets.pokemon.com/assets/cms2/img/pokedex/full/");
			BufferedImage bufferedImage = ImageIO.read(url);
			Image image = new ImageIcon(bufferedImage).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
			pokemonImageLabel.setIcon(new ImageIcon(image));
		} catch (IOException editPokemonButton) {
			editPokemonButton.printStackTrace();
		}

		topPanel.add(pokemonImageLabel);
		
		descriptionField = new JTextField("\"Descripción\"");
		descriptionField.setFont(new Font("Franklin Gothic Medium", Font.ITALIC, 16));
		descriptionField.setForeground(Color.WHITE);
		descriptionField.setHorizontalAlignment(SwingConstants.CENTER);
		descriptionField.setBounds(10, 198, 464, 55);
		topPanel.add(descriptionField);

		// Stats
		// Height
		heightLabel = new JLabel("Altura");
		heightLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		heightLabel.setForeground(Color.WHITE);
		heightLabel.setBounds(52, 372, 46, 26);
		frame.getContentPane().add(heightLabel);

		heightField = new JTextField("1.0");
		heightField.setForeground(Color.WHITE);
		heightField.setBackground(AppUtils.BACKGROUND_COLOR);
		heightField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		heightField.setBounds(113, 372, 66, 25);
		frame.getContentPane().add(heightField);

		// Weight
		weightLabel = new JLabel("Peso");
		weightLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		weightLabel.setForeground(Color.WHITE);
		weightLabel.setBounds(52, 409, 38, 25);
		frame.getContentPane().add(weightLabel);

		weightField = new JTextField("1.0");
		weightField.setForeground(Color.WHITE);
		weightField.setBackground(AppUtils.BACKGROUND_COLOR);
		weightField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		weightField.setBounds(113, 409, 66, 25);
		frame.getContentPane().add(weightField);

		// Gender
		genderLabel = new JLabel("Género");
		genderLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		genderLabel.setForeground(Color.WHITE);
		genderLabel.setBounds(52, 445, 59, 25);
		frame.getContentPane().add(genderLabel);

		// Specie
		specieLabel = new JLabel("Especie");
		specieLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		specieLabel.setForeground(Color.WHITE);
		specieLabel.setBounds(282, 372, 73, 25);
		frame.getContentPane().add(specieLabel);

		specieField = new JTextField("Planta");
		specieField.setForeground(Color.WHITE);
		specieField.setBackground(AppUtils.BACKGROUND_COLOR);
		specieField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		specieField.setBounds(365, 372, 73, 25);
		frame.getContentPane().add(specieField);

		// Type
		abilityLabel = new JLabel("Habilidad");
		abilityLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		abilityLabel.setForeground(Color.WHITE);
		abilityLabel.setBounds(282, 409, 73, 25);
		frame.getContentPane().add(abilityLabel);

		abilityField = new JTextField("Habilidad");
		abilityField.setForeground(Color.WHITE);
		abilityField.setBackground(AppUtils.BACKGROUND_COLOR);
		abilityField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		abilityField.setBounds(365, 409, 73, 25);
		frame.getContentPane().add(abilityField);

		// Error label
		errorLabel = new JLabel("");
		errorLabel.setVerticalAlignment(SwingConstants.TOP);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setForeground(Color.WHITE);
		errorLabel.setBounds(0, 493, 484, 26);
		frame.getContentPane().add(errorLabel);

		cancelButton = new JButton("Cancelar");
		cancelButton.setForeground(Color.WHITE);
		cancelButton.setBackground(Color.RED);
		cancelButton.setBounds(358, 530, 80, 23);
		frame.getContentPane().add(cancelButton);

		savePokemonButton = new JButton("Guardar");
		savePokemonButton.setForeground(Color.WHITE);
		savePokemonButton.setBackground(AppUtils.GREEN_COLOR);
		savePokemonButton.setBounds(268, 530, 80, 23);
		frame.getContentPane().add(savePokemonButton);

		genderValueSpinner = new JSpinner();
		genderValueSpinner.setForeground(Color.WHITE);
		genderValueSpinner.setBackground(AppUtils.BACKGROUND_COLOR);
		genderValueSpinner.setModel(new SpinnerListModel(new String[] { "macho", "hembra" }));
		genderValueSpinner.setBounds(113, 445, 66, 25);
		frame.getContentPane().add(genderValueSpinner);
		
		URLField = new JTextField("https://assets.pokemon.com/assets/cms2/img/pokedex/full/");
		URLField.setBounds(52, 530, 206, 23);
		frame.getContentPane().add(URLField);
	}

	public void setListeners() {
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new PokemonView(pokedexApp);
			}
		});

		savePokemonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				int pokedexListSize = pokedexApp.getPokemonManager().getPokemons().size();

				String name = nameField.getText();
				String description = descriptionField.getText();
				String specie = specieField.getText();
				String ability = abilityField.getText();
				String URL = URLField.getText();
				String heightString = heightField.getText();
				String weightString = weightField.getText();
				Object genderObject = genderValueSpinner.getValue();
				
				if (!JavaUtils.isFloat(heightString) || !JavaUtils.isFloat(weightString) || !JavaUtils.isString(genderObject)) {
					errorLabel.setText("Datos erróneos, comprueba la altura, peso o género");
					return;
				}
				
				float height = Float.parseFloat(heightString);				
				float weight = Float.parseFloat(weightString);				
				String gender = String.valueOf(genderObject);			
				
				Pokemon pokemon = new Pokemon(pokedexListSize, name, description, specie, ability, URL, weight, height, Gender.valueOf(gender));

				pokedexApp.getPokemonManager().getPokemons().set(pokedexListSize, pokemon);
				new PokemonView(pokedexApp, pokemon.getId());
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new PokemonView(pokedexApp);
			}
		});
	}
}
