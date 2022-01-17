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
import javax.swing.SwingConstants;

import mainApp.PokedexApp;
import models.Pokemon;
import utils.AppUtils;
import utils.ImageUtils;
import utils.RoundedJPanel;

public class PokemonView {

	private JFrame frame;
	
	private Pokemon pokemon;
	
	private JPanel  topPanel;
	private JButton backButton, previousPokemonButton, nextPokemonButton, editPokemonButton;
	private JLabel  returnTextLabel, pokemonNameLabel, pokemonImageLabel, errorLabel,
				    pokemonIdLabel, heightLabel, weightLabel, genderLabel, heightValueLabel,
				    specieLabel, specieValueLabel, abilityLabel, abilityValueLabel, genderValueLabel, weightValueLabel;

	/**
	 * Inicia la vista Pokedex
	 */
	public PokemonView(int id) {
		for (Pokemon pokemon : PokedexApp.getPokemons()) {
			if (pokemon.getId() == id) this.pokemon = pokemon;
		}
		
		initialize();
		setListeners();
		frame.setVisible(true);
	}
	
	public PokemonView() {
		this(1);
	}

	/**
	 * Muestra el contenido de la vista
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
		backButton.setIcon(new ImageIcon(PokemonView.class.getResource("/assets/img/return.png")));
		backButton.setBounds(10, 39, 32, 28);
		backButton.setBackground(AppUtils.TRANSPARENT_COLOR);
		backButton.setOpaque(false);
		backButton.setBorder(null);
		topPanel.add(backButton);
		
		// Back button text
		returnTextLabel = new JLabel("Pokedex");
		returnTextLabel.setForeground(Color.WHITE);
		returnTextLabel.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 18));
		returnTextLabel.setBounds(69, 33, 115, 34);
		topPanel.add(returnTextLabel);
		
		// Pokemon ID label
		pokemonIdLabel = new JLabel("# 0");
		pokemonIdLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		pokemonIdLabel.setForeground(Color.WHITE);
		pokemonIdLabel.setBounds(416, 46, 46, 14);
		topPanel.add(pokemonIdLabel);
		
		// Pokemon's name
		pokemonNameLabel = new JLabel(pokemon.getName());
		pokemonNameLabel.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 24));
		pokemonNameLabel.setForeground(Color.WHITE);
		pokemonNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pokemonNameLabel.setBounds(10, 300, 464, 39);
		frame.getContentPane().add(pokemonNameLabel);
		
		// Pokemon image
		pokemonImageLabel = new JLabel("");
		pokemonImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pokemonImageLabel.setBounds(79, 78, 316, 190);
		
		try {
			URL url = new URL("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + ImageUtils.getPokemonById(pokemon.getId()));
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
		heightLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		heightLabel.setForeground(Color.WHITE);
		heightLabel.setBounds(52, 384, 46, 14);
		frame.getContentPane().add(heightLabel);
		
		heightValueLabel = new JLabel(pokemon.getHeight() + "");
		heightValueLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		heightValueLabel.setForeground(Color.WHITE);
		heightValueLabel.setBounds(113, 384, 66, 14);
		frame.getContentPane().add(heightValueLabel);
		
		// Weight
		weightLabel = new JLabel("Peso");
		weightLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		weightLabel.setForeground(Color.WHITE);
		weightLabel.setBounds(52, 409, 38, 14);
		frame.getContentPane().add(weightLabel);
		
		weightValueLabel = new JLabel(pokemon.getWeight() + "");
		weightValueLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		weightValueLabel.setForeground(Color.WHITE);
		weightValueLabel.setBounds(113, 409, 66, 14);
		frame.getContentPane().add(weightValueLabel);
		
		// Gender
		genderLabel = new JLabel("Género");
		genderLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		genderLabel.setForeground(Color.WHITE);
		genderLabel.setBounds(52, 434, 59, 14);
		frame.getContentPane().add(genderLabel);
		
		genderValueLabel = new JLabel(pokemon.getGender().name().toLowerCase());
		genderValueLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		genderValueLabel.setForeground(Color.WHITE);
		genderValueLabel.setBounds(113, 434, 66, 14);
		frame.getContentPane().add(genderValueLabel);
		
		
		// Specie
		specieLabel = new JLabel(pokemon.getTypes().size() > 1 ? "Especies" : "Especie");
		specieLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		specieLabel.setForeground(Color.WHITE);
		specieLabel.setBounds(282, 384, 73, 14);
		frame.getContentPane().add(specieLabel);
		
		specieValueLabel = new JLabel(pokemon.getSpecie());
		specieValueLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		specieValueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		specieValueLabel.setForeground(Color.WHITE);
		specieValueLabel.setBounds(365, 384, 73, 14);
		frame.getContentPane().add(specieValueLabel);
		
		// Ability
		abilityLabel = new JLabel("Habilidad");
		abilityLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		abilityLabel.setForeground(Color.WHITE);
		abilityLabel.setBounds(282, 409, 73, 14);
		frame.getContentPane().add(abilityLabel);
		
		abilityValueLabel = new JLabel(pokemon.getAbility());
		abilityValueLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		abilityValueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		abilityValueLabel.setForeground(Color.WHITE);
		abilityValueLabel.setBounds(365, 409, 73, 14);
		frame.getContentPane().add(abilityValueLabel);
		
		// Error label
		errorLabel = new JLabel("");
		errorLabel.setVerticalAlignment(SwingConstants.TOP);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setForeground(Color.WHITE);
		errorLabel.setBounds(0, 493, 484, 26);
		frame.getContentPane().add(errorLabel);
		
		// Edit pokemon button
		editPokemonButton = new JButton("Editar");
		editPokemonButton.setForeground(Color.WHITE);
		editPokemonButton.setBounds(349, 530, 89, 23);
		editPokemonButton.setBackground(AppUtils.ACCENT_COLOR);
		frame.getContentPane().add(editPokemonButton);
	}
	
	public void setListeners() {
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new LoginView();
			}
		});
		
		nextPokemonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 0;
				while (PokedexApp.getPokemons().get(i) != pokemon) i++;
				if (i >= PokedexApp.getPokemons().size() - 1) {
					errorLabel.setText("No hay más pokemons");
					return;
				}
				pokemon = PokedexApp.getPokemons().get(i + 1);
				updateView();
			}
		});
		
		previousPokemonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 0;
				while (PokedexApp.getPokemons().get(i) != pokemon) i++;
				if (i == 0) {
					errorLabel.setText("No hay más pokemons");
					return;
				}
				pokemon = PokedexApp.getPokemons().get(i - 1);
				updateView();
			}
		});
		
		editPokemonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new PokemonEditorView(pokemon);
			}
		});
	}
	
	private void updateView() {
		pokemonIdLabel.setText("# " + pokemon.getId());
		pokemonNameLabel.setText(pokemon.getName());
		heightValueLabel.setText(pokemon.getHeight() + "");
		weightValueLabel.setText(pokemon.getWeight() + "");
		genderValueLabel.setText(pokemon.getGender().name().toLowerCase());
		specieValueLabel.setText(pokemon.getSpecie());
		abilityValueLabel.setText(pokemon.getAbility());
		
		try {
			URL url = new URL("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + ImageUtils.getPokemonById(pokemon.getId()));
			BufferedImage bufferedImage = ImageIO.read(url);
			Image image = new ImageIcon(bufferedImage).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
			pokemonImageLabel.setIcon(new ImageIcon(image));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
