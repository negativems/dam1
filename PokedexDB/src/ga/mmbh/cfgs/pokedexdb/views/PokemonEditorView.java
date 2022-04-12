package ga.mmbh.cfgs.pokedexdb.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
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

public class PokemonEditorView {

	private final PokedexDB pokedexApp;

	private Pokemon pokemon;

	private JFrame frame;
	private JPanel topPanel;
	private JButton backButton;
	private JButton deletePokemonButton, savePokemonButton;
	private JLabel returnLabel, imageLabel, errorLabel, heightLabel, weightLabel, genderLabel, specieLabel, abilityLabel, pokemonIdLabel;
	private JTextField nameField, heightField, specieField, abilityField, weightField, pokemonIdField;
	private JSpinner genderSpinner;
	private JTextArea descriptionArea;

	/**
	 * Constructor
	 */
	public PokemonEditorView(PokedexDB pokedexApp, Pokemon pokemon) {
		this.pokedexApp = pokedexApp;
		this.pokemon = pokemon;
		initialize();
		createListeners();
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
		topPanel.setBounds(0, -15, 500, 300);
		topPanel.setBackground(AppUtils.ACCENT_COLOR);
		topPanel.setBorder(null);
		topPanel.setOpaque(false);
		topPanel.setLayout(null);
		frame.getContentPane().add(topPanel);

		// Back button
		backButton = new JButton("");
		backButton.setIcon(new ImageIcon(new File("resources/return.png").getAbsolutePath()));
		backButton.setBounds(10, 39, 32, 28);
		backButton.setBackground(AppUtils.TRANSPARENT_COLOR);
		backButton.setOpaque(false);
		backButton.setBorder(null);
		topPanel.add(backButton);

		// Back button text
		returnLabel = new JLabel("Pokedex > Editor");
		returnLabel.setForeground(Color.WHITE);
		returnLabel.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 18));
		returnLabel.setBounds(69, 33, 190, 34);
		topPanel.add(returnLabel);

		// Pokemon image
		imageLabel = new JLabel("");
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imageLabel.setBounds(90, 79, 316, 190);

		try {
			URL url = new URL("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + ImageUtils.getFormattedId(pokemon.getId()) + ".png");
			BufferedImage bufferedImage = ImageIO.read(url);
			Image image = new ImageIcon(bufferedImage).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
			imageLabel.setIcon(new ImageIcon(image));
		} catch (IOException editPokemonButton) {
			editPokemonButton.printStackTrace();
		}

		topPanel.add(imageLabel);
		
		// Name
		nameField = new JTextField(pokemon.getName());
		nameField.setBounds(10, 247, 484, 39);
		nameField.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 24));
		nameField.setBackground(AppUtils.ACCENT_COLOR);
		nameField.setForeground(Color.WHITE);
		nameField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		topPanel.add(nameField);
		
		// Description
		descriptionArea = new JTextArea(pokemon.getDescription());
		descriptionArea.setBounds(50, 300, 400, 50);
		descriptionArea.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 14));
		descriptionArea.setForeground(Color.WHITE);
		descriptionArea.setBackground(AppUtils.BACKGROUND_COLOR);
		descriptionArea.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		descriptionArea.setLineWrap(true);
		frame.getContentPane().add(descriptionArea);

		// Id
		pokemonIdLabel = new JLabel("ID");
		pokemonIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pokemonIdLabel.setForeground(Color.WHITE);
		pokemonIdLabel.setBounds(50, 380, 80, 25);
		frame.getContentPane().add(pokemonIdLabel);
		
		pokemonIdField = new JTextField(ImageUtils.getFormattedId(pokemon.getId()));
		pokemonIdField.setForeground(Color.WHITE);
		pokemonIdField.setBackground(AppUtils.BACKGROUND_COLOR);
		pokemonIdField.setBounds(130, 380, 80, 25);
		pokemonIdField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		frame.getContentPane().add(pokemonIdField);
		
		// Height
		heightLabel = new JLabel("Altura");
		heightLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		heightLabel.setForeground(Color.WHITE);
		heightLabel.setBounds(50, 430, 50, 25);
		frame.getContentPane().add(heightLabel);

		heightField = new JTextField(pokemon.getHeight() + "");
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
		weightLabel.setBounds(50, 480, 50, 25);
		frame.getContentPane().add(weightLabel);

		weightField = new JTextField(pokemon.getWeight() + "");
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
		genderSpinner.setBounds(370, 480, 80, 25);
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
		specieField.setBounds(370, 380, 80, 25);
		specieField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		frame.getContentPane().add(specieField);

		// Ability
		abilityLabel = new JLabel("Habilidad");
		abilityLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		abilityLabel.setForeground(Color.WHITE);
		abilityLabel.setBounds(280, 430, 80, 25);
		frame.getContentPane().add(abilityLabel);

		abilityField = new JTextField(pokemon.getAbility());
		abilityField.setHorizontalAlignment(SwingConstants.LEFT);
		abilityField.setForeground(Color.WHITE);
		abilityField.setBackground(AppUtils.BACKGROUND_COLOR);
		abilityField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		abilityField.setBounds(370, 430, 80, 25);
		abilityField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		frame.getContentPane().add(abilityField);

		// Error label
		errorLabel = new JLabel("");
		errorLabel.setVerticalAlignment(SwingConstants.TOP);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setForeground(Color.WHITE);
		errorLabel.setBounds(10, 550, 250, 25);
		frame.getContentPane().add(errorLabel);

		// Delete Button
		deletePokemonButton = new JButton("Borrar");
		deletePokemonButton.setForeground(Color.WHITE);
		deletePokemonButton.setBackground(Color.RED);
		deletePokemonButton.setBounds(360, 550, 90, 25);
		frame.getContentPane().add(deletePokemonButton);

		// Save Button
		savePokemonButton = new JButton("Guardar");
		savePokemonButton.setForeground(Color.WHITE);
		savePokemonButton.setBackground(AppUtils.GREEN_COLOR);
		savePokemonButton.setBounds(260, 550, 90, 25);
		frame.getContentPane().add(savePokemonButton);
	}

	/**
	 * Update all the view fields
	 */
	private void updateView() {
		nameField.setText(pokemon.getName());
		heightField.setText(pokemon.getHeight() + "");
		weightField.setText(pokemon.getWeight() + "");
		genderSpinner.setValue(pokemon.getGender().name().toLowerCase());
		specieField.setText(pokemon.getSpecie());
		abilityField.setText(pokemon.getAbility());

		try {
			new URL("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + pokemonIdField.getText() + ".png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates the buttons listener
	 */
	public void createListeners() {
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new PokemonView(pokedexApp, pokemon.getId());
			}
		});

		savePokemonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int index = pokedexApp.getPokemonManager().getPokemons().indexOf(pokemon);
				
				String idString = pokemonIdField.getText();
				String name = nameField.getText();
				String description = descriptionArea.getText();
				String specie = specieField.getText();
				String ability = abilityField.getText();
				String height = heightField.getText();
				String weight = weightField.getText();
				Object gender = genderSpinner.getValue();
				
				if (!JavaUtils.isFloat(height, weight) || !JavaUtils.isString(gender) || !JavaUtils.isInteger(idString)) {
					errorLabel.setText("Comprueba que todos los campos sean correctos.");
					return;
				}
				
				int id = Integer.parseInt(idString);
				idString = ImageUtils.getFormattedId(id); // Converts to formatted id, e.j: 5 to 005
				
				// Checks if the new pokemon ID is valid
				try {
					String URLPath = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + idString + ".png";
					new URL(URLPath);
					pokemon.setImageURL(URLPath);
				} catch (IOException e) {
					errorLabel.setText("No se ha encontrado el pokemon con ese ID");
					return;
				}
				
				pokemon.setId(id);
				pokemon.setName(name);
				pokemon.setDescription(description);
				pokemon.setSpecie(specie);
				pokemon.setAbility(ability);
				pokemon.setHeight(Float.parseFloat(heightField.getText()));
				pokemon.setWeight(Float.parseFloat(weightField.getText()));
				pokemon.setGender(Gender.valueOf(gender.toString().toUpperCase()));
				pokemon.setSpecie(specieField.getText());
				pokemon.setAbility(abilityField.getText());
				
				pokedexApp.getPokemonManager().getPokemons().set(index, pokemon);
				
				frame.dispose();
				new PokemonView(pokedexApp, pokemon.getId());
			}
		});

		deletePokemonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pokedexApp.getPokemonManager().getPokemons().remove(pokemon.getId() - 1);
				if (pokemon.getId() - 1 == pokedexApp.getPokemonManager().getPokemons().size()) {
					pokemon = pokedexApp.getPokemonManager().getPokemons().get(pokemon.getId() - 2);
				} else {
					pokemon = pokedexApp.getPokemonManager().getPokemons().get(pokemon.getId() - 1);
				}

				updateView();
			}
		});
	}
}
