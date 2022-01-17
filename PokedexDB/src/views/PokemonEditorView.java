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

public class PokemonEditorView {

	private Pokemon pokemon;

	private JFrame frame;
	private JPanel topPanel;
	private JButton backButton;
	private JLabel returnTextLabel, pokemonNameLabel, pokemonImageLabel, errorLabel, heightLabel, weightLabel, genderLabel,
					specieLabel, abilityLabel;
	private JButton deletePokemonButton, savePokemonButton;
	private JTextField heightValueField, specieValueField, abilityValueField, weightValueField;
	private JSpinner genderValueSpinner;

	/**
	 * Inicia la vista Pokedex
	 */
	public PokemonEditorView(Pokemon pokemon) {
		this.pokemon = pokemon;
		initialize();
		setListeners();
		frame.setVisible(true);
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
		backButton.setIcon(new ImageIcon(PokemonEditorView.class.getResource("/assets/img/return.png")));
		backButton.setBounds(10, 39, 32, 28);
		backButton.setBackground(AppUtils.TRANSPARENT_COLOR);
		backButton.setOpaque(false);
		backButton.setBorder(null);
		topPanel.add(backButton);
		
		// Back button text
		returnTextLabel = new JLabel("Pokedex > Editor");
		returnTextLabel.setForeground(Color.WHITE);
		returnTextLabel.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 18));
		returnTextLabel.setBounds(69, 33, 190, 34);
		topPanel.add(returnTextLabel);
		
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
		
		// Stats
		// Height
		heightLabel = new JLabel("Altura");
		heightLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		heightLabel.setForeground(Color.WHITE);
		heightLabel.setBounds(52, 384, 46, 14);
		frame.getContentPane().add(heightLabel);
		
		heightValueField = new JTextField(pokemon.getHeight() + "");
		heightValueField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		heightValueField.setBounds(113, 384, 66, 14);
		frame.getContentPane().add(heightValueField);
		
		// Weight
		weightLabel = new JLabel("Peso");
		weightLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		weightLabel.setForeground(Color.WHITE);
		weightLabel.setBounds(52, 409, 38, 14);
		frame.getContentPane().add(weightLabel);
		
		weightValueField = new JTextField(pokemon.getWeight() + "");
		weightValueField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		weightValueField.setBounds(113, 409, 66, 14);
		frame.getContentPane().add(weightValueField);
		
		// Gender
		genderLabel = new JLabel("Género");
		genderLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		genderLabel.setForeground(Color.WHITE);
		genderLabel.setBounds(52, 434, 59, 14);
		frame.getContentPane().add(genderLabel);
		
		genderValueSpinner = new JSpinner();
		genderValueSpinner.setForeground(Color.WHITE);
		genderValueSpinner.setBackground(AppUtils.BACKGROUND_COLOR);
		genderValueSpinner.setModel(new SpinnerListModel(new String[] {"macho", "hembra"}));
		genderValueSpinner.setBounds(113, 434, 66, 14);
		frame.getContentPane().add(genderValueSpinner);
		
		// Specie
		specieLabel = new JLabel(pokemon.getTypes().size() > 1 ? "Especies" : "Especie");
		specieLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		specieLabel.setForeground(Color.WHITE);
		specieLabel.setBounds(282, 384, 73, 14);
		frame.getContentPane().add(specieLabel);
		
		specieValueField = new JTextField(pokemon.getSpecie());
		specieValueField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		specieValueField.setBounds(365, 384, 73, 14);
		frame.getContentPane().add(specieValueField);
		
		// Type
		abilityLabel = new JLabel("Habilidad");
		abilityLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		abilityLabel.setForeground(Color.WHITE);
		abilityLabel.setBounds(282, 409, 73, 14);
		frame.getContentPane().add(abilityLabel);
		
		abilityValueField = new JTextField(pokemon.getAbility());
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
		
		savePokemonButton = new JButton("Guardar");
		savePokemonButton.setForeground(Color.WHITE);
		savePokemonButton.setBackground(AppUtils.GREEN_COLOR);
		savePokemonButton.setBounds(268, 530, 80, 23);
		frame.getContentPane().add(savePokemonButton);
		
		deletePokemonButton = new JButton("Borrar");
		deletePokemonButton.setForeground(Color.WHITE);
		deletePokemonButton.setBackground(Color.RED);
		deletePokemonButton.setBounds(358, 530, 80, 23);
		frame.getContentPane().add(deletePokemonButton);
	}
	
	private void updateView() {
		pokemonNameLabel.setText(pokemon.getName());
		heightValueField.setText(pokemon.getHeight() + "");
		weightValueField.setText(pokemon.getWeight() + "");
		genderValueSpinner.setValue(pokemon.getGender().name().toLowerCase());
		specieValueField.setText(pokemon.getSpecie());
		abilityValueField.setText(pokemon.getAbility());
		
		try {
			URL url = new URL("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + ImageUtils.getPokemonById(pokemon.getId()));
			BufferedImage bufferedImage = ImageIO.read(url);
			Image image = new ImageIcon(bufferedImage).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
			pokemonImageLabel.setIcon(new ImageIcon(image));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setListeners() {
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new PokemonView(pokemon.getId());
			}
		});
		
		savePokemonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				int index = PokedexApp.getPokemons().indexOf(pokemon);

				pokemon.setName(pokemonNameLabel.getText());
				pokemon.setHeight(Float.parseFloat(heightValueField.getText()));
				pokemon.setWeight(Float.parseFloat(weightValueField.getText()));
				pokemon.setGender(Gender.valueOf(genderValueSpinner.getValue().toString().toUpperCase()));
				pokemon.setSpecie(specieValueField.getText());
				pokemon.setAbility(abilityValueField.getText());
				
				PokedexApp.getPokemons().set(index, pokemon);
				new PokemonView(pokemon.getId());
			}
		});
		
		deletePokemonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PokedexApp.getPokemons().remove(pokemon.getId() - 1);
				if (pokemon.getId() - 1 == PokedexApp.getPokemons().size()) {
					pokemon = PokedexApp.getPokemons().get(pokemon.getId() - 2);
				} else {
					pokemon = PokedexApp.getPokemons().get(pokemon.getId() - 1);
				}
				
				updateView();
			}
		});
	}

}
