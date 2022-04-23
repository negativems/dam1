package ga.mmbh.cfgs.netflixdb.views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ga.mmbh.cfgs.netflixdb.NetflixApp;
import ga.mmbh.cfgs.netflixdb.graphic.frames.CustomFrame;
import ga.mmbh.cfgs.netflixdb.graphic.panels.TitlePanel;
import ga.mmbh.cfgs.netflixdb.graphic.panels.home.HomeShowsPanel;
import ga.mmbh.cfgs.netflixdb.handlers.SearchData;
import ga.mmbh.cfgs.netflixdb.models.Show;
import ga.mmbh.cfgs.netflixdb.utils.AppUtils;

public class HomeView {

	private final HomeView instance;
	
	private final NetflixApp netflixApp = NetflixApp.getInstance();
	private List<Show> shows;

	private CustomFrame frame;
	private TitlePanel titlePanel;
	private HomeShowsPanel showsPanel;
	private JLabel errorLabel;
	private JButton previousShowButton, nextShowButton, searchButton, favouritesButton, exportButton;

	private int page = 1;

	/**
	 * @wbp.parser.constructor
	 */
	public HomeView() {
		this.instance = this;
		this.shows = netflixApp.getShowManager().getShows();
		initialize();
		loadListeners();
	}

	public HomeView(SearchData searchData) {
		this.instance = this;
		
		filter(searchData);
		initialize();
		loadListeners();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new CustomFrame();
		
		// Header
		titlePanel = new TitlePanel("NETFLIX", AppUtils.BACKGROUND_COLOR, Color.RED, 900, 150);
		frame.getContentPane().add(titlePanel);
		
		// Header buttons
		searchButton = new JButton("Buscador");
		searchButton.setBounds(100, 160, 120, 30);
		searchButton.setBackground(AppUtils.ACCENT_COLOR);
		frame.getContentPane().add(searchButton);
		
		favouritesButton = new JButton("Favoritos");
		favouritesButton.setBackground(AppUtils.YELLOW);
		favouritesButton.setBounds(398, 160, 120, 30);
		frame.getContentPane().add(favouritesButton);
		
		exportButton = new JButton("Exportar");
		exportButton.setBackground(AppUtils.ORANGE);
		exportButton.setBounds(680, 160, 120, 30);
		frame.getContentPane().add(exportButton);
		
		// Content
		showsPanel = new HomeShowsPanel(shows, page);
		showsPanel.setBounds(100, 200, 700, 588);
		frame.getContentPane().add(showsPanel);
		
		// Bottom buttons
		previousShowButton = new JButton("Anterior");
		previousShowButton.setBounds(100, 800, 100, 25);
		previousShowButton.setBackground(Color.WHITE);
		previousShowButton.setEnabled(page != 1);
		frame.getContentPane().add(previousShowButton);
		
		nextShowButton = new JButton("Siguiente");
		nextShowButton.setBounds(700, 800, 100, 25);
		nextShowButton.setBackground(Color.WHITE);
		frame.getContentPane().add(nextShowButton);
		
		errorLabel = new JLabel();
		errorLabel.setBounds(0, 800, 900, 25);
		errorLabel.setForeground(Color.ORANGE);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(errorLabel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.repaint();
	}
	
	public void filter(SearchData searchData) {
		this.shows = netflixApp.getShowManager().getShows().stream().filter(show -> {
			if (show.getTitle().contains(searchData.getTitle()) || searchData.getTitle().isEmpty()) {
				boolean isSameCountry = searchData.getCountry().isEmpty() || show.getCountry().equals(searchData.getCountry());
				boolean isSameDirector = searchData.getDirector().isEmpty() || show.getDirector().equals(searchData.getDirector());
				boolean isSameYear = searchData.getYear().isEmpty() || show.getReleaseYear().equals(searchData.getYear());
				
				return isSameCountry && isSameDirector && isSameYear;
			}
			
			return false;
		}).toList();
		
		refreshPage();
	}
	
	public void refreshPage() {
		nextShowButton.setEnabled(shows.size() >= page * HomeShowsPanel.MAX_SHOWS_PER_PAGE);
		previousShowButton.setEnabled(page != 1);

		showsPanel.removeAll();
		frame.repaint();
		
		showsPanel = new HomeShowsPanel(shows, page);
		frame.getContentPane().add(showsPanel);
	}
	
	public void loadListeners() {
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SearchView(instance);
			}
		});
		
		favouritesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (netflixApp.getUserManager().getSession().getFavouritesShows().size() == 0) {
					errorLabel.setText("No tienes shows en favoritos");
					return;
				}
				
				new FavouriteView();
			}
		});
		
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ExportView(shows);
			}
		});
		
		nextShowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				page++;
				refreshPage();
			}
		});
		
		previousShowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				page--;
				refreshPage();
			}
		});
	}
}
