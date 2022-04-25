package ga.mmbh.cfgs.netflixjunit.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ga.mmbh.cfgs.netflixjunit.NetflixApp;
import ga.mmbh.cfgs.netflixjunit.models.Movie;
import ga.mmbh.cfgs.netflixjunit.utils.AppUtils;
import ga.mmbh.cfgs.netflixjunit.utils.RoundedJPanel;

public class MainView {

	private final NetflixApp netflixApp;

	private Movie movie;

	private JFrame frame;
	private JPanel topPanel;
	private JButton backButton, previousMovieButton, nextMovieButton, createButton, deleteButton;
	private JLabel backButtonLabel, movieNameLabel, movieImageLabel, errorLabel, movieIdLabel, durationLabel,
			minAgeLabel, genreLabel, durationValueLabel, directorLabel, directorValueLabel, genreValueLabel,
			minAgeValueLabel;

	/**
	 * Constructor, initialize view
	 */
	public MainView(NetflixApp netflixApp, int id) {
		this.netflixApp = netflixApp;

		List<Movie> movies = netflixApp.getMovieManager().getMovies();
		for (Movie movie : movies) {
			if (movie.getId() == id) {
				this.movie = movie;
			}
		}

		initialize();
		createListeners();
		frame.setVisible(true);
	}

	/**
	 * @wbp.parser.constructor
	 */
	public MainView(NetflixApp netflixApp) {
		this(netflixApp, 0);
	}

	/**
	 * Setup and show view
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
		topPanel.setBounds(0, -22, 500, 300);
		topPanel.setBackground(AppUtils.ACCENT_COLOR);
		topPanel.setBorder(null);
		topPanel.setOpaque(false);
		frame.getContentPane().add(topPanel);
		topPanel.setLayout(null);

		// Back button
		backButton = new JButton("");
		backButton.setIcon(new ImageIcon(new File("resources/return.png").getAbsolutePath()));
		backButton.setBounds(10, 39, 32, 28);
		backButton.setBackground(AppUtils.TRANSPARENT_COLOR);
		backButton.setOpaque(false);
		backButton.setBorder(null);
		topPanel.add(backButton);

		backButtonLabel = new JLabel("Cerrar sesión");
		backButtonLabel.setForeground(Color.WHITE);
		backButtonLabel.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 18));
		backButtonLabel.setBounds(69, 33, 150, 34);
		topPanel.add(backButtonLabel);

		// Movie ID label
		movieIdLabel = new JLabel("# " + movie.getId());
		movieIdLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		movieIdLabel.setForeground(Color.WHITE);
		movieIdLabel.setBounds(416, 46, 46, 14);
		topPanel.add(movieIdLabel);

		// Next and previous buttons
		previousMovieButton = new JButton("<");
		previousMovieButton.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 24));
		previousMovieButton.setBackground(AppUtils.TRANSPARENT_COLOR);
		previousMovieButton.setForeground(Color.WHITE);
		previousMovieButton.setBounds(10, 78, 59, 190);
		previousMovieButton.setBorder(null);
		previousMovieButton.setContentAreaFilled(false);
		previousMovieButton.setOpaque(false);
		topPanel.add(previousMovieButton);

		nextMovieButton = new JButton(">");
		nextMovieButton.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 24));
		nextMovieButton.setBackground(AppUtils.TRANSPARENT_COLOR);
		nextMovieButton.setForeground(Color.WHITE);
		nextMovieButton.setBounds(415, 78, 59, 190);
		nextMovieButton.setBorder(null);
		nextMovieButton.setContentAreaFilled(false);
		nextMovieButton.setOpaque(false);
		topPanel.add(nextMovieButton);

		// Movie's name
		movieNameLabel = new JLabel(movie.getName());
		movieNameLabel.setBounds(0, 240, 500, 39);
		movieNameLabel.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 24));
		movieNameLabel.setForeground(Color.WHITE);
		movieNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		topPanel.add(movieNameLabel);

		// Movie's image
		movieImageLabel = new JLabel("");
		movieImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		movieImageLabel.setBounds(0, 78, 500, 153);
		try {
			URL url = new URL(movie.getImageURL());
			BufferedImage bufferedImage = ImageIO.read(url);
			Image image = new ImageIcon(bufferedImage).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
			movieImageLabel.setIcon(new ImageIcon(image));
		} catch (IOException editMovieButton) {
			editMovieButton.printStackTrace();
		}
		topPanel.add(movieImageLabel);

		// Duration
		durationLabel = new JLabel("Duración");
		durationLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		durationLabel.setForeground(Color.WHITE);
		durationLabel.setBounds(100, 334, 150, 25);
		frame.getContentPane().add(durationLabel);

		durationValueLabel = new JLabel(movie.getDuration() + "");
		durationValueLabel.setHorizontalAlignment(SwingConstants.LEFT);
		durationValueLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		durationValueLabel.setForeground(Color.WHITE);
		durationValueLabel.setBounds(250, 334, 150, 25);
		frame.getContentPane().add(durationValueLabel);

		// Min age label
		minAgeLabel = new JLabel("Edad recomendada");
		minAgeLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		minAgeLabel.setForeground(Color.WHITE);
		minAgeLabel.setBounds(100, 374, 150, 25);
		frame.getContentPane().add(minAgeLabel);

		minAgeValueLabel = new JLabel(movie.getMinAge() + "");
		minAgeValueLabel.setHorizontalAlignment(SwingConstants.LEFT);
		minAgeValueLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		minAgeValueLabel.setForeground(Color.WHITE);
		minAgeValueLabel.setBounds(250, 374, 150, 25);
		frame.getContentPane().add(minAgeValueLabel);

		// Genre
		genreLabel = new JLabel("Género");
		genreLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		genreLabel.setForeground(Color.WHITE);
		genreLabel.setBounds(100, 454, 150, 25);
		frame.getContentPane().add(genreLabel);

		genreValueLabel = new JLabel(movie.getGenre().name().toLowerCase());
		genreValueLabel.setHorizontalAlignment(SwingConstants.LEFT);
		genreValueLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		genreValueLabel.setForeground(Color.WHITE);
		genreValueLabel.setBounds(250, 454, 150, 25);
		frame.getContentPane().add(genreValueLabel);

		// Director
		directorLabel = new JLabel("Director");
		directorLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		directorLabel.setForeground(Color.WHITE);
		directorLabel.setBounds(100, 414, 150, 25);
		frame.getContentPane().add(directorLabel);

		directorValueLabel = new JLabel(movie.getDirector());
		directorValueLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		directorValueLabel.setHorizontalAlignment(SwingConstants.LEFT);
		directorValueLabel.setForeground(Color.WHITE);
		directorValueLabel.setBounds(250, 414, 150, 25);
		frame.getContentPane().add(directorValueLabel);

		// Error label
		errorLabel = new JLabel("");
		errorLabel.setVerticalAlignment(SwingConstants.TOP);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setForeground(Color.WHITE);
		errorLabel.setBounds(0, 493, 500, 26);
		frame.getContentPane().add(errorLabel);

		// Create movie button
		createButton = new JButton("Crear");
		createButton.setBackground(AppUtils.GREEN_COLOR);
		createButton.setForeground(AppUtils.BACKGROUND_COLOR);
		createButton.setBounds(350, 530, 100, 23);
		frame.getContentPane().add(createButton);

		deleteButton = new JButton("Eliminar");
		deleteButton.setForeground(AppUtils.BACKGROUND_COLOR);
		deleteButton.setBackground(AppUtils.GREEN_COLOR);
		deleteButton.setBounds(240, 530, 100, 23);
		frame.getContentPane().add(deleteButton);
	}

	public void createListeners() {
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new LoginView(netflixApp);
			}
		});

		nextMovieButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 0;
				while (netflixApp.getMovieManager().getMovies().get(i) != movie) {
					i++;
				}
				if (i >= netflixApp.getMovieManager().getMovies().size() - 1) {
					errorLabel.setText("No hay más películas");
					return;
				}

				movie = netflixApp.getMovieManager().getMovies().get(i + 1);
				updateView();
			}
		});

		previousMovieButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 0;
				while (netflixApp.getMovieManager().getMovies().get(i) != movie) {
					i++;
				}
				if (i == 0) {
					errorLabel.setText("No hay más películas");
					return;
				}
				movie = netflixApp.getMovieManager().getMovies().get(i - 1);
				updateView();
			}
		});

		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new MovieCreateView(netflixApp);
			}
		});

		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (netflixApp.getMovieManager().getMovies().size() == 1) {
					errorLabel.setText("No puedes eliminar la única película que existe");
					return;
				}

				int id = movie.getId();
				netflixApp.getMovieManager().getMovies().remove(movie.getId());
				movie = netflixApp.getMovieManager().getMovies().get(movie.getId() == 0 ? 0 : id - 1);
				updateView();
			}
		});
	}

	private void updateView() {
		movieIdLabel.setText("# " + movie.getId());
		movieNameLabel.setText(movie.getName());
		durationValueLabel.setText(movie.getMinAge() + "");
		minAgeValueLabel.setText(movie.getDirector() + "");
		genreValueLabel.setText(movie.getImageURL());
		directorValueLabel.setText(movie.getDuration() + "");
		genreValueLabel.setText(movie.getGenre().name().toLowerCase().replaceAll("_", " "));

		try {
			URL url = new URL(movie.getImageURL());
			BufferedImage bufferedImage = ImageIO.read(url);
			Image image = new ImageIcon(bufferedImage).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
			movieImageLabel.setIcon(new ImageIcon(image));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
