package ga.mmbh.cfgs.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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

import ga.mmbh.cfgs.NetflixApp;
import ga.mmbh.cfgs.models.Movie;
import ga.mmbh.cfgs.utils.AppUtils;
import ga.mmbh.cfgs.utils.RoundedJPanel;

public class MainView {

   private final NetflixApp netflixApp;

   private Movie movie;

   private JFrame frame;
   private JPanel topPanel;
   private JButton backButton, previousMovieButton, nextMovieButton, createButton, saveButton;
   private JLabel backButtonLabel, movieNameLabel, movieImageLabel, errorLabel, movieIdLabel, durationLabel,
         minAgeLabel, genreLabel, durationValueLabel, specieLabel, directorValueLabel,
         genreValueLabel, minAgeValueLabel;

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
      this(netflixApp, 1);
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
      topPanel.setBounds(0, -22, 484, 300);
      topPanel.setBackground(AppUtils.ACCENT_COLOR);
      topPanel.setBorder(null);
      topPanel.setOpaque(false);
      frame.getContentPane().add(topPanel);
      topPanel.setLayout(null);

      // Back button
      backButton = new JButton("");
      backButton.setIcon(new ImageIcon(MainView.class.getResource("/return.png")));
      backButton.setBounds(10, 39, 32, 28);
      backButton.setBackground(AppUtils.TRANSPARENT_COLOR);
      backButton.setOpaque(false);
      backButton.setBorder(null);
      topPanel.add(backButton);

      // Back button text
      backButtonLabel = new JLabel("Cerrar sesión");
      backButtonLabel.setForeground(Color.WHITE);
      backButtonLabel.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 18));
      backButtonLabel.setBounds(69, 33, 115, 34);
      topPanel.add(backButtonLabel);

      // Movie ID label
      movieIdLabel = new JLabel("# " + movie.getId());
      movieIdLabel.setHorizontalAlignment(SwingConstants.RIGHT);
      movieIdLabel.setForeground(Color.WHITE);
      movieIdLabel.setBounds(416, 46, 46, 14);
      topPanel.add(movieIdLabel);

      // Movie's name
      movieNameLabel = new JLabel(movie.getName());
      movieNameLabel.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 24));
      movieNameLabel.setForeground(Color.WHITE);
      movieNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
      movieNameLabel.setBounds(10, 300, 464, 39);
      frame.getContentPane().add(movieNameLabel);

      // Netflix image
      movieImageLabel = new JLabel("");
      movieImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
      movieImageLabel.setBounds(79, 78, 316, 190);

      try {
    	  URL url = new URL("https://pics.filmaffinity.com/Scary_Movie-943532513-large.jpg");
          BufferedImage bufferedImage = ImageIO.read(url);
          Image image = new ImageIcon(bufferedImage).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
          movieImageLabel.setIcon(new ImageIcon(image));
      } catch (IOException editPokemonButton) {
    	  editPokemonButton.printStackTrace();
      }

      topPanel.add(movieImageLabel);

      // Previous movie button
      previousMovieButton = new JButton("<");
      previousMovieButton.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 24));
      previousMovieButton.setBackground(AppUtils.TRANSPARENT_COLOR);
      previousMovieButton.setForeground(Color.WHITE);
      previousMovieButton.setBounds(10, 78, 59, 190);
      previousMovieButton.setBorder(null);
      previousMovieButton.setContentAreaFilled(false);
      previousMovieButton.setOpaque(false);
      topPanel.add(previousMovieButton);

      // Next movie button
      nextMovieButton = new JButton(">");
      nextMovieButton.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 24));
      nextMovieButton.setBackground(AppUtils.TRANSPARENT_COLOR);
      nextMovieButton.setForeground(Color.WHITE);
      nextMovieButton.setBounds(415, 78, 59, 190);
      nextMovieButton.setBorder(null);
      nextMovieButton.setContentAreaFilled(false);
      nextMovieButton.setOpaque(false);
      topPanel.add(nextMovieButton);

      // Stats
      // Height
      durationLabel = new JLabel("Duración");
      durationLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
      durationLabel.setForeground(Color.WHITE);
      durationLabel.setBounds(52, 384, 120, 14);
      frame.getContentPane().add(durationLabel);

      durationValueLabel = new JLabel(movie.getMinAge() + "");
      durationValueLabel.setHorizontalAlignment(SwingConstants.LEFT);
      durationValueLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
      durationValueLabel.setForeground(Color.WHITE);
      durationValueLabel.setBounds(182, 384, 158, 14);
      frame.getContentPane().add(durationValueLabel);

      // Edad recomendada
      minAgeLabel = new JLabel("Edad recomendada");
      minAgeLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
      minAgeLabel.setForeground(Color.WHITE);
      minAgeLabel.setBounds(52, 409, 120, 14);
      frame.getContentPane().add(minAgeLabel);

      minAgeValueLabel = new JLabel(movie.getMinAge() + "");
      minAgeValueLabel.setHorizontalAlignment(SwingConstants.LEFT);
      minAgeValueLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
      minAgeValueLabel.setForeground(Color.WHITE);
      minAgeValueLabel.setBounds(182, 409, 158, 14);
      frame.getContentPane().add(minAgeValueLabel);

      // Genre
      genreLabel = new JLabel("Género");
      genreLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
      genreLabel.setForeground(Color.WHITE);
      genreLabel.setBounds(52, 434, 120, 14);
      frame.getContentPane().add(genreLabel);

      genreValueLabel = new JLabel(movie.getGenre().name().toLowerCase());
      genreValueLabel.setHorizontalAlignment(SwingConstants.LEFT);
      genreValueLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
      genreValueLabel.setForeground(Color.WHITE);
      genreValueLabel.setBounds(182, 434, 158, 14);
      frame.getContentPane().add(genreValueLabel);

      // Specie
      specieLabel = new JLabel("Director");
      specieLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
      specieLabel.setForeground(Color.WHITE);
      specieLabel.setBounds(52, 459, 120, 14);
      frame.getContentPane().add(specieLabel);

      directorValueLabel = new JLabel(movie.getDirector());
      directorValueLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
      directorValueLabel.setHorizontalAlignment(SwingConstants.LEFT);
      directorValueLabel.setForeground(Color.WHITE);
      directorValueLabel.setBounds(182, 459, 158, 14);
      frame.getContentPane().add(directorValueLabel);

      // Error label
      errorLabel = new JLabel("");
      errorLabel.setVerticalAlignment(SwingConstants.TOP);
      errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
      errorLabel.setForeground(Color.WHITE);
      errorLabel.setBounds(0, 493, 484, 26);
      frame.getContentPane().add(errorLabel);

      // Saves movie button
      saveButton = new JButton("Guardar");
      saveButton.setForeground(Color.WHITE);
      saveButton.setBounds(251, 530, 89, 23);
      saveButton.setBackground(AppUtils.GREEN_COLOR);
      frame.getContentPane().add(saveButton);

      // Create movie button
      createButton = new JButton("Crear");
      createButton.setForeground(Color.WHITE);
      createButton.setBounds(350, 530, 89, 23);
      createButton.setBackground(AppUtils.ACCENT_COLOR);
      frame.getContentPane().add(createButton);
      
      JLabel imageLabel = new JLabel("");
      imageLabel.setBounds(52, 11, 390, 278);
      frame.getContentPane().add(imageLabel);
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

      saveButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            errorLabel.setForeground(AppUtils.GREEN_COLOR);
            errorLabel.setText("Pokemons guardados correctamente");
            errorLabel.setForeground(Color.WHITE);
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

      /*try {
         URL url = new URL(
               "https://assets.movie.com/assets/cms2/img/pokedex/full/" + ImageUtils.getPokemonById(movie.getId()));
         BufferedImage bufferedImage = ImageIO.read(url);
         Image image = new ImageIcon(bufferedImage).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
         movieImageLabel.setIcon(new ImageIcon(image));
      } catch (IOException e) {
         e.printStackTrace();
      }*/
   }
}
