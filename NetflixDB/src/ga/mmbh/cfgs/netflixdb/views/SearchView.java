package ga.mmbh.cfgs.netflixdb.views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ga.mmbh.cfgs.netflixdb.NetflixApp;
import ga.mmbh.cfgs.netflixdb.graphic.frames.CustomFrame;
import ga.mmbh.cfgs.netflixdb.handlers.EscKeyPress;
import ga.mmbh.cfgs.netflixdb.handlers.SearchData;
import ga.mmbh.cfgs.netflixdb.models.Show;
import ga.mmbh.cfgs.netflixdb.utils.AppUtils;

public class SearchView {
	
	private final HomeView homeView;
	private CustomFrame frame;
	
	private JTextField titleField;
	private JButton searchButton;
	private JLabel countryLabel, directorLabel, yearLabel;
	private JComboBox<String> countryComboBox, directorComboBox, yearComboBox;

	public SearchView(HomeView homeView) {
		this.homeView = homeView;
		
		initialize();
		loadListeners();
		addComboBoxData();
	}
	
	private void initialize() {
		frame = new CustomFrame();
		frame.setBounds(250, 250, 400, 400);
		
		titleField = new JTextField("Introduce el nombre de un show...");
		titleField.setBackground(Color.WHITE);
		titleField.setBounds(50, 50, 300, 50);
		titleField.setBorder(BorderFactory.createLineBorder(AppUtils.ACCENT_COLOR));
		frame.getContentPane().add(titleField);
		
		countryLabel = new JLabel("País");
		countryLabel.setBounds(50, 140, 50, 25);
		countryLabel.setForeground(Color.WHITE);
		frame.getContentPane().add(countryLabel);
		
		countryComboBox = new JComboBox<String>();
		countryComboBox.setBounds(130, 140, 92, 26);
		frame.getContentPane().add(countryComboBox);
		
		directorLabel = new JLabel("Director");
		directorLabel.setBounds(50, 180, 50, 25);
		directorLabel.setForeground(Color.WHITE);
		frame.getContentPane().add(directorLabel);
		
		directorComboBox = new JComboBox<String>();
		directorComboBox.setBounds(130, 180, 92, 26);
		frame.getContentPane().add(directorComboBox);
		
		yearLabel = new JLabel("Año");
		yearLabel.setBounds(50, 220, 50, 25);
		yearLabel.setForeground(Color.WHITE);
		frame.getContentPane().add(yearLabel);
		
		yearComboBox = new JComboBox<String>();
		yearComboBox.setBounds(130, 220, 90, 25);
		frame.getContentPane().add(yearComboBox);
		
		searchButton = new JButton("Buscar");
		searchButton.setFocusable(false);
		searchButton.setBackground(AppUtils.ACCENT_COLOR);
		searchButton.setBounds(0, 300, 400, 50);
		frame.getContentPane().add(searchButton);
		
		titleField.addKeyListener(new EscKeyPress(frame));
		frame.addKeyListener(new EscKeyPress(frame));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private void addComboBoxData() {
		Set<String> countries = new TreeSet<String>();
		Set<String> directors = new TreeSet<String>();
		Set<String> years = new TreeSet<String>();
		
		// Adds empty values
		countries.add("");
		directors.add("");
		years.add("");
		
		List<Show> shows = NetflixApp.getInstance().getShowManager().getShows();
		for (Show show : shows) {
			if (show.getCountry() != null && !show.getCountry().isEmpty()) {
				countries.add(show.getCountry() == null ? "" : show.getCountry());
			}
			
			if (show.getDirector() != null && !show.getDirector().isEmpty()) {
				directors.add(show.getDirector() == null ? "" : show.getDirector());
			}
			
			if (show.getReleaseYear() != null && !show.getReleaseYear().isEmpty()) {
				years.add(show.getReleaseYear() == null ? "" : show.getReleaseYear());
			}
		}
		
		countries.stream().forEach(country -> countryComboBox.addItem(country));
		directors.stream().forEach(director -> directorComboBox.addItem(director));
		years.stream().forEach(year -> yearComboBox.addItem(year));
	}
	
	public void loadListeners() {
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
				String title = titleField.getText().equals("Introduce el nombre de un show...") ? "" : titleField.getText();
				String country = countryComboBox.getSelectedItem().toString();
				String director = directorComboBox.getSelectedItem().toString();
				String year = yearComboBox.getSelectedItem().toString();
				homeView.filter(new SearchData(title, country, director, year));
			}
		});
	}
}
