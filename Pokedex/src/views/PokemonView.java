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
	
	private JPanel topPanel, pokemonTypePanel;
	private JButton returnButton, previousPokemonButton, nextPokemonButton;
	private JLabel returnTextLabel, pokemonNameLabel, pokemonImageLabel, errorLabel;

	/**
	 * Inicia la vista Pokedex
	 */
	public PokemonView() {
		initialize();
		setListeners();
		frame.setVisible(true);
	}

	/**
	 * Muestra el contenido de la vista
	 */
	private void initialize() {
		pokemon = PokedexApp.getPokemons().get(0);
		
		frame = new JFrame();
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
		returnButton = new JButton("");
		returnButton.setIcon(new ImageIcon(PokemonView.class.getResource("/assets/img/return.png")));
		returnButton.setBounds(10, 39, 32, 28);
		returnButton.setBackground(AppUtils.TRANSPARENT_COLOR);
		returnButton.setOpaque(false);
		returnButton.setBorder(null);
		topPanel.add(returnButton);
		
		// Back button text
		returnTextLabel = new JLabel("Pokedex");
		returnTextLabel.setForeground(Color.WHITE);
		returnTextLabel.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 18));
		returnTextLabel.setBounds(69, 33, 115, 34);
		topPanel.add(returnTextLabel);
		
		// Pokemon's name
		pokemonNameLabel = new JLabel(pokemon.getName());
		pokemonNameLabel.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 24));
		pokemonNameLabel.setForeground(Color.WHITE);
		pokemonNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pokemonNameLabel.setBounds(10, 300, 464, 39);
		frame.getContentPane().add(pokemonNameLabel);
		
		// Panel behind pokemon
		pokemonTypePanel = new RoundedJPanel(20, 20);
		pokemonTypePanel.setBounds(171, 350, 154, 26);
		pokemonTypePanel.setBackground(Color.WHITE);
		pokemonTypePanel.setBorder(null);
		pokemonTypePanel.setOpaque(false);
		frame.getContentPane().add(pokemonTypePanel);
		
		// Pokemon image
		pokemonImageLabel = new JLabel("");
		pokemonImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pokemonImageLabel.setBounds(79, 78, 316, 190);
		
		try {
			URL url = new URL("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + ImageUtils.getPokemonById(pokemon.getId()));
			BufferedImage bufferedImage = ImageIO.read(url);
			Image image = new ImageIcon(bufferedImage).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
			pokemonImageLabel.setIcon(new ImageIcon(image));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		topPanel.add(pokemonImageLabel);
		
		// Previous pokemon button
		previousPokemonButton = new JButton("<");
		previousPokemonButton.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 24));
		previousPokemonButton.setBackground(AppUtils.TRANSPARENT_COLOR);
		previousPokemonButton.setForeground(Color.WHITE);
		previousPokemonButton.setBounds(10, 78, 59, 190);
		previousPokemonButton.setBorder(null);
		previousPokemonButton.setOpaque(false);
		topPanel.add(previousPokemonButton);
		
		// Next pokemon button
		nextPokemonButton = new JButton(">");
		nextPokemonButton.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 24));
		nextPokemonButton.setBackground(AppUtils.TRANSPARENT_COLOR);
		nextPokemonButton.setForeground(Color.WHITE);
		nextPokemonButton.setBounds(415, 78, 59, 190);
		nextPokemonButton.setBorder(null);
		nextPokemonButton.setOpaque(false);
		topPanel.add(nextPokemonButton);
		
		// Error label
		errorLabel = new JLabel("");
		errorLabel.setForeground(Color.WHITE);
		errorLabel.setBounds(10, 531, 484, 26);
		frame.getContentPane().add(errorLabel);
	}
	
	public void setListeners() {
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new LoginView();
			}
		});
		
		nextPokemonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (PokedexApp.getPokemons().size() <= pokemon.getId()) {
					errorLabel.setText("No hay más pokemons");
					return;
				}
				errorLabel.setText("test");
				int nextPokemonId = pokemon.getId() + 1;
				pokemon = PokedexApp.getPokemons().get(nextPokemonId);
			}
		});
		
		previousPokemonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (PokedexApp.getPokemons().size() <= pokemon.getId()) {
					errorLabel.setText("No hay más pokemons");
					return;
				}
				errorLabel.setText("test");
				int nextPokemonId = pokemon.getId() + 1;
				pokemon = PokedexApp.getPokemons().get(nextPokemonId);
			}
		});
	}
}
