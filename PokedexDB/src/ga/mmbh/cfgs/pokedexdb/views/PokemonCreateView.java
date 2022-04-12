package ga.mmbh.cfgs.pokedexdb.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;

import ga.mmbh.cfgs.pokedexdb.PokedexDB;
import ga.mmbh.cfgs.pokedexdb.models.Gender;
import ga.mmbh.cfgs.pokedexdb.models.Pokemon;
import ga.mmbh.cfgs.pokedexdb.utils.AppUtils;
import ga.mmbh.cfgs.pokedexdb.utils.ImageUtils;
import ga.mmbh.cfgs.pokedexdb.utils.JavaUtils;
import ga.mmbh.cfgs.pokedexdb.utils.RoundedJPanel;

public class PokemonCreateView {

	private final PokedexDB pokedexApp;
	
	private JFrame frame;
	private JPanel topPanel;
	private JButton backButton;
	private JButton cancelButton, savePokemonButton;
	private JSpinner genderSpinner;
	private JLabel returnTextLabel, errorLabel, heightLabel, weightLabel, genderLabel, specieLabel, abilityLabel, URLIdLabel;
	private JTextField nameField, heightField, specieField, abilityField, weightField, pokemonIdField;
	private JTextArea descriptionArea;
	
	/**
	 * Constructor
	 */
	public PokemonCreateView(PokedexDB pokedexApp) {
		this.pokedexApp = pokedexApp;
		initialize();
		setListeners();
		frame.setVisible(true);
	}

	/**
	 * Initialize the view
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		frame.getContentPane().setBackground(AppUtils.BACKGROUND_COLOR);
		frame.setBounds(100, 100, 500, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Top rounded panel
		topPanel = new RoundedJPanel(40, 40);
		topPanel.setBounds(0, -15, 500, 370);
		topPanel.setBackground(AppUtils.ACCENT_COLOR);
		topPanel.setBorder(null);
		topPanel.setOpaque(false);
		frame.getContentPane().add(topPanel);
		topPanel.setLayout(null);

		// Back button
		backButton = new JButton("");
		backButton.setIcon(new ImageIcon(new File("resources/return.png").getAbsolutePath()));
		backButton.setBounds(10, 39, 32, 28);
		backButton.setBackground(AppUtils.BACKGROUND_COLOR);
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
		nameField.setBounds(125, 150, 250, 25);
		nameField.setBackground(AppUtils.ACCENT_COLOR);
		nameField.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 14));
		nameField.setForeground(Color.WHITE);
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		topPanel.add(nameField);
		
		// Description
		descriptionArea = new JTextArea("Descripción");
		descriptionArea.setBounds(50, 200, 401, 130);
		descriptionArea.setBackground(AppUtils.ACCENT_COLOR);
		descriptionArea.setFont(new Font("Franklin Gothic Medium", Font.ITALIC, 14));
		descriptionArea.setForeground(Color.WHITE);
		descriptionArea.setBackground(AppUtils.ACCENT_COLOR);
		descriptionArea.setLineWrap(true);
		descriptionArea.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		topPanel.add(descriptionArea);

		// Stats
		// Height
		heightLabel = new JLabel("Altura");
		heightLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		heightLabel.setForeground(Color.WHITE);
		heightLabel.setBounds(50, 430, 80, 25);
		frame.getContentPane().add(heightLabel);

		heightField = new JTextField("1.0");
		heightField.setForeground(Color.WHITE);
		heightField.setBackground(AppUtils.BACKGROUND_COLOR);
		heightField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		heightField.setBounds(130, 430, 80, 25);
		heightField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		frame.getContentPane().add(heightField);

		// Weight
		weightLabel = new JLabel("Peso");
		weightLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		weightLabel.setForeground(Color.WHITE);
		weightLabel.setBounds(50, 480, 80, 25);
		frame.getContentPane().add(weightLabel);

		weightField = new JTextField("1.0");
		weightField.setForeground(Color.WHITE);
		weightField.setBackground(AppUtils.BACKGROUND_COLOR);
		weightField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		weightField.setBounds(130, 480, 80, 25);
		weightField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		frame.getContentPane().add(weightField);

		// Gender
		genderLabel = new JLabel("Género");
		genderLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		genderLabel.setForeground(Color.WHITE);
		genderLabel.setBounds(280, 480, 80, 25);
		frame.getContentPane().add(genderLabel);
		
		genderSpinner = new JSpinner();
		genderSpinner.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		genderSpinner.setForeground(Color.WHITE);
		genderSpinner.setBackground(AppUtils.BACKGROUND_COLOR);
		genderSpinner.setModel(new SpinnerListModel(new String[] { "macho", "hembra" }));
		genderSpinner.setBounds(360, 480, 80, 25);
		frame.getContentPane().add(genderSpinner);

		// Specie
		specieLabel = new JLabel("Especie");
		specieLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		specieLabel.setForeground(Color.WHITE);
		specieLabel.setBounds(280, 380, 80, 25);
		frame.getContentPane().add(specieLabel);

		specieField = new JTextField("Planta");
		specieField.setHorizontalAlignment(SwingConstants.LEFT);
		specieField.setForeground(Color.WHITE);
		specieField.setBackground(AppUtils.BACKGROUND_COLOR);
		specieField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		specieField.setBounds(360, 380, 80, 25);
		specieField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		frame.getContentPane().add(specieField);

		// Ability
		abilityLabel = new JLabel("Habilidad");
		abilityLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		abilityLabel.setForeground(Color.WHITE);
		abilityLabel.setBounds(280, 430, 80, 25);
		frame.getContentPane().add(abilityLabel);

		abilityField = new JTextField("Fuego");
		abilityField.setHorizontalAlignment(SwingConstants.LEFT);
		abilityField.setForeground(Color.WHITE);
		abilityField.setBackground(AppUtils.BACKGROUND_COLOR);
		abilityField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		abilityField.setBounds(360, 430, 80, 25);
		abilityField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		frame.getContentPane().add(abilityField);

		// Cancel button
		cancelButton = new JButton("Cancelar");
		cancelButton.setForeground(Color.WHITE);
		cancelButton.setBackground(Color.RED);
		cancelButton.setBounds(350, 550, 90, 23);
		frame.getContentPane().add(cancelButton);

		// Save button
		savePokemonButton = new JButton("Guardar");
		savePokemonButton.setForeground(Color.WHITE);
		savePokemonButton.setBackground(AppUtils.GREEN_COLOR);
		savePokemonButton.setBounds(250, 550, 90, 23);
		frame.getContentPane().add(savePokemonButton);
		
		// URL
		URLIdLabel = new JLabel("ID");
		URLIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		URLIdLabel.setForeground(Color.WHITE);
		URLIdLabel.setBounds(50, 380, 80, 25);
		frame.getContentPane().add(URLIdLabel);
		
		pokemonIdField = new JTextField("00x");
		pokemonIdField.setHorizontalAlignment(SwingConstants.LEFT);
		pokemonIdField.setBackground(AppUtils.BACKGROUND_COLOR);
		pokemonIdField.setForeground(Color.WHITE);
		pokemonIdField.setBounds(130, 380, 80, 25);
		pokemonIdField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		frame.getContentPane().add(pokemonIdField);
		
		// Error label
		errorLabel = new JLabel("");
		errorLabel.setVerticalAlignment(SwingConstants.TOP);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setForeground(Color.WHITE);
		errorLabel.setBounds(0, 493, 484, 26);
		frame.getContentPane().add(errorLabel);
	}

	public void setListeners() {
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new PokemonView(pokedexApp);
			}
		});

		savePokemonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {			
				String idString = pokemonIdField.getText();
				String name = nameField.getText();
				String description = descriptionArea.getText();
				String specie = specieField.getText();
				String ability = abilityField.getText();
				String heightString = heightField.getText();
				String weightString = weightField.getText();
				String imageURL = "";
				Object gender = genderSpinner.getValue();
				
				if (!JavaUtils.isFloat(heightString, weightString) || !JavaUtils.isString(gender) || !JavaUtils.isInteger(idString)) {
					errorLabel.setText("Comprueba que todos los campos sean correctos.");
					return;
				}
				
				int id = Integer.parseInt(idString);
				idString = ImageUtils.getFormattedId(id); // Converts to formatted id, e.j: 5 to 005
				
				boolean exists = pokedexApp.getPokemonManager().getPokemons().stream().anyMatch(pokemon -> pokemon.getId() == id);
				if (exists) {
					errorLabel.setText("Ya existe un pokemon con ese ID");
					return;
				}
				
				// Checks if the new pokemon ID is valid
				try {
					imageURL = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + idString + ".png";
					new URL(imageURL);
				} catch (IOException e) {
					errorLabel.setText("No se ha encontrado el pokemon con ese ID");
					return;
				}
				
				float height = Float.parseFloat(heightString);
				float weight = Float.parseFloat(weightString);
				
				Pokemon pokemon = new Pokemon(id, name, description, specie, ability, imageURL, height, weight, Gender.valueOf(gender.toString().toUpperCase()));
				pokedexApp.getPokemonManager().getPokemons().add(pokemon);
				
				frame.dispose();
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
