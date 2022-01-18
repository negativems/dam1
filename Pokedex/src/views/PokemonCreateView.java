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
import utils.RoundedJPanel;

public class PokemonCreateView {

	private final PokedexApp pokedexApp;

	private final int pokemonId;
	
	private JFrame frame;
	private JPanel topPanel;
	private JButton backButton;
	private JLabel returnTextLabel, pokemonImageLabel, errorLabel, heightLabel, weightLabel,
			genderLabel, specieLabel, abilityLabel;
	private JButton cancelButton, savePokemonButton;
	private JTextField pokemonNameField, heightValueField, specieValueField, abilityValueField, weightValueField;
	private JSpinner genderValueSpinner;

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
		pokemonNameField = new JTextField("Nombre Pokemon");
		pokemonNameField.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 24));
		pokemonNameField.setForeground(Color.WHITE);
		pokemonNameField.setHorizontalAlignment(SwingConstants.CENTER);
		pokemonNameField.setBounds(0, 300, 484, 39);
		frame.getContentPane().add(pokemonNameField);

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

		// Stats
		// Height
		heightLabel = new JLabel("Altura");
		heightLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		heightLabel.setForeground(Color.WHITE);
		heightLabel.setBounds(52, 384, 46, 14);
		frame.getContentPane().add(heightLabel);

		heightValueField = new JTextField("1.0");
		heightValueField.setForeground(Color.WHITE);
		heightValueField.setBackground(AppUtils.BACKGROUND_COLOR);
		heightValueField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		heightValueField.setBounds(113, 384, 66, 14);
		frame.getContentPane().add(heightValueField);

		// Weight
		weightLabel = new JLabel("Peso");
		weightLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		weightLabel.setForeground(Color.WHITE);
		weightLabel.setBounds(52, 409, 38, 14);
		frame.getContentPane().add(weightLabel);

		weightValueField = new JTextField("1.0");
		weightValueField.setForeground(Color.WHITE);
		weightValueField.setBackground(AppUtils.BACKGROUND_COLOR);
		weightValueField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		weightValueField.setBounds(113, 409, 66, 14);
		frame.getContentPane().add(weightValueField);

		// Gender
		genderLabel = new JLabel("Género");
		genderLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		genderLabel.setForeground(Color.WHITE);
		genderLabel.setBounds(52, 434, 59, 14);
		frame.getContentPane().add(genderLabel);

		// Specie
		specieLabel = new JLabel("Especie");
		specieLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		specieLabel.setForeground(Color.WHITE);
		specieLabel.setBounds(282, 384, 73, 14);
		frame.getContentPane().add(specieLabel);

		specieValueField = new JTextField("Planta");
		specieValueField.setForeground(Color.WHITE);
		specieValueField.setBackground(AppUtils.BACKGROUND_COLOR);
		specieValueField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		specieValueField.setBounds(365, 384, 73, 14);
		frame.getContentPane().add(specieValueField);

		// Type
		abilityLabel = new JLabel("Habilidad");
		abilityLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		abilityLabel.setForeground(Color.WHITE);
		abilityLabel.setBounds(282, 409, 73, 14);
		frame.getContentPane().add(abilityLabel);

		abilityValueField = new JTextField("Habilidad");
		abilityValueField.setForeground(Color.WHITE);
		abilityValueField.setBackground(AppUtils.BACKGROUND_COLOR);
		abilityValueField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		abilityValueField.setBounds(365, 409, 73, 14);
		frame.getContentPane().add(abilityValueField);

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
		genderValueSpinner.setBounds(113, 434, 66, 14);
		frame.getContentPane().add(genderValueSpinner);
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
				int index = pokedexApp.getPokemonManager().getPokemons().size();

				Pokemon pokemon = new Pokemon(pokemonId, pokemonName);
				pokemon.setName(pokemonNameLabel.getText());
				pokemon.setHeight(Float.parseFloat(heightValueField.getText()));
				pokemon.setWeight(Float.parseFloat(weightValueField.getText()));
				pokemon.setGender(Gender.valueOf(genderValueSpinner.getValue().toString().toUpperCase()));
				pokemon.setSpecie(specieValueField.getText());
				pokemon.setAbility(abilityValueField.getText());

				pokedexApp.getPokemonManager().getPokemons().set(index, pokemon);
				new PokemonView(pokedexApp, pokemon.getId());
			}
		});

		cancelButton.addActionListener(new ActionListener() {
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
