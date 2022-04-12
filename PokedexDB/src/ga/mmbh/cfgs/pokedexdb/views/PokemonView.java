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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import ga.mmbh.cfgs.pokedexdb.PokedexDB;
import ga.mmbh.cfgs.pokedexdb.models.Pokemon;
import ga.mmbh.cfgs.pokedexdb.utils.AppUtils;
import ga.mmbh.cfgs.pokedexdb.utils.ImageUtils;
import ga.mmbh.cfgs.pokedexdb.utils.RoundedJPanel;

public class PokemonView {

	private final PokedexDB pokedexApp;
	
	private Pokemon pokemon;
		
	private JFrame frame;
	private JPanel topPanel;
	private JButton backButton, previousPokemonButton, nextPokemonButton, editButton, createButton, saveButton;
	private JTextArea descriptionArea;
	private JLabel returnTextLabel, nameLabel, pokemonImageLabel, errorLabel, pokemonIdLabel, heightLabel, weightLabel, genderLabel,
				   heightValueLabel, specieLabel, specieValueLabel, abilityLabel, abilityValueLabel, genderValueLabel, weightValueLabel;

	/**
	 * Constructor, initialize view
	 */
	public PokemonView(PokedexDB pokedexApp, Pokemon pokemon) {
		this.pokedexApp = pokedexApp;
		this.pokemon = pokemon;
		
		initialize();
		createListeners();
		frame.setVisible(true);
	}
	
	public PokemonView(PokedexDB pokedexApp, int id) {	
		this(pokedexApp, pokedexApp.getPokemonManager().getPokemons().stream().filter(pokemon -> pokemon.getId() == id).findFirst().get());
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public PokemonView(PokedexDB pokedexApp) {
		this(pokedexApp, pokedexApp.getPokemonManager().getPokemons().get(0));
	}
	
	/**
	 * Setup and show view
	 */
	private void initialize() {	
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		frame.getContentPane().setBackground(AppUtils.BACKGROUND_COLOR);
		frame.setBounds(100, 100, 500, 600);
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
		backButton.setBounds(30, 30, 30, 30);
		backButton.setBackground(AppUtils.TRANSPARENT_COLOR);
		backButton.setBorder(null);
		topPanel.add(backButton);
		
		// Back button text
		returnTextLabel = new JLabel("Pokedex");
		returnTextLabel.setForeground(Color.WHITE);
		returnTextLabel.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 18));
		returnTextLabel.setBounds(69, 30, 115, 30);
		topPanel.add(returnTextLabel);
		
		// Pokemon ID label
		pokemonIdLabel = new JLabel("# " + pokemon.getId());
		pokemonIdLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		pokemonIdLabel.setForeground(Color.WHITE);
		pokemonIdLabel.setBounds(416, 46, 46, 14);
		topPanel.add(pokemonIdLabel);
		
		// Pokemon's name
		nameLabel = new JLabel(pokemon.getName());
		nameLabel.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 24));
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setBounds(0, 250, 500, 30);
		topPanel.add(nameLabel);
		
		// Pokemon's description
		descriptionArea = new JTextArea(pokemon.getDescription());
		descriptionArea.setEditable(false);
		descriptionArea.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 14));
		descriptionArea.setForeground(Color.WHITE);
		descriptionArea.setBackground(AppUtils.BACKGROUND_COLOR);
		descriptionArea.setBounds(50, 300, 400, 50);
		descriptionArea.setLineWrap(true);
		frame.getContentPane().add(descriptionArea);
		
		// Pokemon image
		pokemonImageLabel = new JLabel("");
		pokemonImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pokemonImageLabel.setBounds(100, 70, 300, 190);
		
		try {
			URL url = new URL(pokemon.getImageURL());
			BufferedImage bufferedImage = ImageIO.read(url);
			Image image = new ImageIcon(bufferedImage).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
			pokemonImageLabel.setIcon(new ImageIcon(image));
		} catch (IOException editPokemonButton) {
			editPokemonButton.printStackTrace();
		}
		
		topPanel.add(pokemonImageLabel);
		
		// Previous pokemon button
		previousPokemonButton = new JButton("<");
		previousPokemonButton.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 24));
		previousPokemonButton.setBackground(AppUtils.TRANSPARENT_COLOR);
		previousPokemonButton.setForeground(Color.WHITE);
		previousPokemonButton.setBounds(10, 78, 59, 190);
		previousPokemonButton.setBorder(null);
		previousPokemonButton.setContentAreaFilled(false);
		previousPokemonButton.setOpaque(false);
		topPanel.add(previousPokemonButton);
		
		// Next pokemon button
		nextPokemonButton = new JButton(">");
		nextPokemonButton.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 24));
		nextPokemonButton.setBackground(AppUtils.TRANSPARENT_COLOR);
		nextPokemonButton.setForeground(Color.WHITE);
		nextPokemonButton.setBounds(415, 78, 59, 190);
		nextPokemonButton.setBorder(null);
		nextPokemonButton.setContentAreaFilled(false);
		nextPokemonButton.setOpaque(false);
		topPanel.add(nextPokemonButton);
		
		// Stats
		// Height
		heightLabel = new JLabel("Altura");
		heightLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		heightLabel.setForeground(Color.WHITE);
		heightLabel.setBounds(50, 380, 80, 25);
		frame.getContentPane().add(heightLabel);
		
		heightValueLabel = new JLabel(pokemon.getHeight() + "");
		heightValueLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		heightValueLabel.setForeground(Color.WHITE);
		heightValueLabel.setBounds(130, 380, 100, 25);
		frame.getContentPane().add(heightValueLabel);
		
		// Weight
		weightLabel = new JLabel("Peso");
		weightLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		weightLabel.setForeground(Color.WHITE);
		weightLabel.setBounds(50, 430, 80, 25);
		frame.getContentPane().add(weightLabel);
		
		weightValueLabel = new JLabel(pokemon.getWeight() + "");
		weightValueLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		weightValueLabel.setForeground(Color.WHITE);
		weightValueLabel.setBounds(130, 430, 100, 25);
		frame.getContentPane().add(weightValueLabel);
		
		// Gender
		genderLabel = new JLabel("Género");
		genderLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		genderLabel.setForeground(Color.WHITE);
		genderLabel.setBounds(50, 480, 80, 25);
		frame.getContentPane().add(genderLabel);
		
		genderValueLabel = new JLabel(pokemon.getGender().name().toLowerCase());
		genderValueLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		genderValueLabel.setForeground(Color.WHITE);
		genderValueLabel.setBounds(130, 480, 100, 25);
		frame.getContentPane().add(genderValueLabel);
		
		
		// Specie
		specieLabel = new JLabel(pokemon.getTypes().size() > 1 ? "Especies" : "Especie");
		specieLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		specieLabel.setForeground(Color.WHITE);
		specieLabel.setBounds(290, 380, 80, 25);
		frame.getContentPane().add(specieLabel);
		
		specieValueLabel = new JLabel(pokemon.getSpecie());
		specieValueLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		specieValueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		specieValueLabel.setForeground(Color.WHITE);
		specieValueLabel.setBounds(370, 380, 80, 25);
		frame.getContentPane().add(specieValueLabel);
		
		// Ability
		abilityLabel = new JLabel("Habilidad");
		abilityLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		abilityLabel.setForeground(Color.WHITE);
		abilityLabel.setBounds(290, 430, 80, 25);
		frame.getContentPane().add(abilityLabel);
		
		abilityValueLabel = new JLabel(pokemon.getAbility());
		abilityValueLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		abilityValueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		abilityValueLabel.setForeground(Color.WHITE);
		abilityValueLabel.setBounds(370, 430, 80, 25);
		frame.getContentPane().add(abilityValueLabel);
		
		// Error label
		errorLabel = new JLabel("");
		errorLabel.setVerticalAlignment(SwingConstants.TOP);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setForeground(Color.WHITE);
		errorLabel.setBounds(0, 517, 500, 26);
		frame.getContentPane().add(errorLabel);
		
		// Edit pokemon button
		editButton = new JButton("Editar");
		editButton.setForeground(Color.WHITE);
		editButton.setBounds(260, 550, 90, 25);
		editButton.setBackground(AppUtils.ACCENT_COLOR);
		frame.getContentPane().add(editButton);
		
		// Create pokemon button
		createButton = new JButton("Crear");
		createButton.setForeground(Color.WHITE);
		createButton.setBounds(160, 550, 90, 25);
		createButton.setBackground(AppUtils.ACCENT_COLOR);
		frame.getContentPane().add(createButton);
		
		// Saves pokemon button
		saveButton = new JButton("Guardar");
		saveButton.setForeground(Color.WHITE);
		saveButton.setBounds(360, 550, 90, 25);
		saveButton.setBackground(AppUtils.GREEN_COLOR);
		frame.getContentPane().add(saveButton);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.repaint();
	}
	
	public void createListeners() {
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new LoginView(pokedexApp);
			}
		});
		
		nextPokemonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 0;
				while (pokedexApp.getPokemonManager().getPokemons().get(i) != pokemon) i++;
				if (i >= pokedexApp.getPokemonManager().getPokemons().size() - 1) {
					errorLabel.setText("No hay más pokemons");
					return;
				}
				
				errorLabel.setText("");
				pokemon = pokedexApp.getPokemonManager().getPokemons().get(i + 1);
				updateView();
			}
		});
		
		previousPokemonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 0;
				while (pokedexApp.getPokemonManager().getPokemons().get(i) != pokemon) i++;
				if (i == 0) {
					errorLabel.setText("No hay más pokemons");
					return;
				}
				
				errorLabel.setText("");
				pokemon = pokedexApp.getPokemonManager().getPokemons().get(i - 1);
				updateView();
			}
		});
		
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new PokemonEditorView(pokedexApp, pokemon);
			}
		});
		
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new PokemonCreateView(pokedexApp);
			}
		});
		
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pokedexApp.getPokemonManager().savePokemons();
				errorLabel.setForeground(AppUtils.GREEN_COLOR);
				errorLabel.setText("Pokemons guardados correctamente");
				errorLabel.setForeground(Color.WHITE);
			}
		});
	}
	
	private void updateView() {
		pokemonIdLabel.setText("# " + pokemon.getId());
		nameLabel.setText(pokemon.getName());
		descriptionArea.setText(pokemon.getDescription());
		heightValueLabel.setText(pokemon.getHeight() + "");
		weightValueLabel.setText(pokemon.getWeight() + "");
		genderValueLabel.setText(pokemon.getGender().name().toLowerCase());
		specieValueLabel.setText(pokemon.getSpecie());
		abilityValueLabel.setText(pokemon.getAbility());
		
		try {
			URL url = new URL("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + ImageUtils.getFormattedId(pokemon.getId()) + ".png");
			BufferedImage bufferedImage = ImageIO.read(url);
			Image image = new ImageIcon(bufferedImage).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
			pokemonImageLabel.setIcon(new ImageIcon(image));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
