package ga.mmbh.cfgs.pokedexdb.views;

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

import ga.mmbh.cfgs.pokedexdb.PokedexApp;
import ga.mmbh.cfgs.pokedexdb.models.Gender;
import ga.mmbh.cfgs.pokedexdb.models.Pokemon;
import ga.mmbh.cfgs.pokedexdb.utils.AppUtils;
import ga.mmbh.cfgs.pokedexdb.utils.ImageUtils;
import ga.mmbh.cfgs.pokedexdb.utils.RoundedJPanel;

public class PokemonEditorView {

	private final PokedexApp pokedexApp;

	private Pokemon pokemon;

	private JFrame frame;
	private JPanel topPanel;
	private JButton backButton;
	private JButton deletePokemonButton, savePokemonButton;
	private JLabel returnLabel, nameLabel, imageLabel, errorLabel, heightLabel, weightLabel, genderLabel, specieLabel, abilityLabel, URLIdLabel;
	private JTextField heightField, specieField, abilityField, weightField, URLField;
	private JSpinner genderSpinner;

	/**
	 * Constructor
	 */
	public PokemonEditorView(PokedexApp pokedexApp, Pokemon pokemon) {
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
		backButton.setIcon(new ImageIcon(PokemonEditorView.class.getResource("/return.png")));
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

		// Pokemon's name
		nameLabel = new JLabel(pokemon.getName());
		nameLabel.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 24));
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setBounds(0, 300, 484, 39);
		frame.getContentPane().add(nameLabel);

		// Pokemon image
		imageLabel = new JLabel("");
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imageLabel.setBounds(79, 78, 316, 190);

		try {
			URL url = new URL("https://assets.pokemon.com/assets/cms2/img/pokedex/full/"
					+ ImageUtils.getPokemonById(pokemon.getId()));
			BufferedImage bufferedImage = ImageIO.read(url);
			Image image = new ImageIcon(bufferedImage).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
			imageLabel.setIcon(new ImageIcon(image));
		} catch (IOException editPokemonButton) {
			editPokemonButton.printStackTrace();
		}

		topPanel.add(imageLabel);

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
		heightField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		heightField.setBounds(113, 372, 66, 25);
		heightField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
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
		weightField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		weightField.setBounds(113, 409, 66, 25);
		weightField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		frame.getContentPane().add(weightField);

		// Gender
		genderLabel = new JLabel("Género");
		genderLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		genderLabel.setForeground(Color.WHITE);
		genderLabel.setBounds(52, 445, 59, 25);
		frame.getContentPane().add(genderLabel);
		
		genderSpinner = new JSpinner();
		genderSpinner.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		genderSpinner.setForeground(Color.WHITE);
		genderSpinner.setBackground(AppUtils.BACKGROUND_COLOR);
		genderSpinner.setModel(new SpinnerListModel(new String[] { "macho", "hembra" }));
		genderSpinner.setBounds(113, 445, 66, 25);
		frame.getContentPane().add(genderSpinner);

		// Specie
		specieLabel = new JLabel("Especie");
		specieLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		specieLabel.setForeground(Color.WHITE);
		specieLabel.setBounds(282, 382, 73, 25);
		frame.getContentPane().add(specieLabel);

		specieField = new JTextField("Planta");
		specieField.setForeground(Color.WHITE);
		specieField.setBackground(AppUtils.BACKGROUND_COLOR);
		specieField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		specieField.setBounds(365, 372, 73, 46);
		specieField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		frame.getContentPane().add(specieField);

		// Ability
		abilityLabel = new JLabel("Habilidad");
		abilityLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		abilityLabel.setForeground(Color.WHITE);
		abilityLabel.setBounds(287, 437, 73, 25);
		frame.getContentPane().add(abilityLabel);

		abilityField = new JTextField("");
		abilityField.setForeground(Color.WHITE);
		abilityField.setBackground(AppUtils.BACKGROUND_COLOR);
		abilityField.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		abilityField.setBounds(365, 429, 73, 41);
		abilityField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		frame.getContentPane().add(abilityField);

		// Error label
		errorLabel = new JLabel("");
		errorLabel.setVerticalAlignment(SwingConstants.TOP);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setForeground(Color.WHITE);
		errorLabel.setBounds(0, 493, 484, 26);
		frame.getContentPane().add(errorLabel);

		// Delete Button
		deletePokemonButton = new JButton("Borrar");
		deletePokemonButton.setForeground(Color.WHITE);
		deletePokemonButton.setBackground(Color.RED);
		deletePokemonButton.setBounds(358, 530, 80, 23);
		frame.getContentPane().add(deletePokemonButton);

		// Save Button
		savePokemonButton = new JButton("Guardar");
		savePokemonButton.setForeground(Color.WHITE);
		savePokemonButton.setBackground(AppUtils.GREEN_COLOR);
		savePokemonButton.setBounds(268, 530, 80, 23);
		frame.getContentPane().add(savePokemonButton);
		
		// URL
		URLIdLabel = new JLabel("ID");
		URLIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		URLIdLabel.setForeground(Color.WHITE);
		URLIdLabel.setBounds(52, 530, 46, 23);
		frame.getContentPane().add(URLIdLabel);
		
		String currentURL = pokemon.getImageURL();
		URLField = new JTextField("");
		URLField.setBounds(80, 530, 178, 23);
		frame.getContentPane().add(URLField);
	}

	/**
	 * Update all the view fields
	 */
	private void updateView() {
		nameLabel.setText(pokemon.getName());
		heightField.setText(pokemon.getHeight() + "");
		weightField.setText(pokemon.getWeight() + "");
		genderSpinner.setValue(pokemon.getGender().name().toLowerCase());
		specieField.setText(pokemon.getSpecie());
		abilityField.setText(pokemon.getAbility());

		try {
			new URL("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + URLField.getText() + ".png");
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
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				int index = pokedexApp.getPokemonManager().getPokemons().indexOf(pokemon);

				pokemon.setName(nameLabel.getText());
				pokemon.setImageURL("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + URLField.getText() + ".png");
				pokemon.setHeight(Float.parseFloat(heightField.getText()));
				pokemon.setWeight(Float.parseFloat(weightField.getText()));
				pokemon.setGender(Gender.valueOf(genderSpinner.getValue().toString().toUpperCase()));
				pokemon.setSpecie(specieField.getText());
				pokemon.setAbility(abilityField.getText());

				pokedexApp.getPokemonManager().getPokemons().set(index, pokemon);
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
