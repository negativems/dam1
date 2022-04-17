package ga.mmbh.cfgs.netflixdb.views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ga.mmbh.cfgs.netflixdb.NetflixApp;
import ga.mmbh.cfgs.netflixdb.graphic.frames.CustomFrame;
import ga.mmbh.cfgs.netflixdb.handlers.SearchData;
import ga.mmbh.cfgs.netflixdb.models.Show;
import ga.mmbh.cfgs.netflixdb.models.User;
import ga.mmbh.cfgs.netflixdb.utils.AppUtils;

public class SearchView {
	
	private final NetflixApp netflixApp = NetflixApp.getInstance();
	private CustomFrame frame;
	
	private JTextField titleField;
	private JButton searchButton;
	private JLabel yearLabel, directorLabel;
	private JComboBox yearComboBox, directorComboBox;

	/**
	 * @wbp.parser.constructor
	 */
	public SearchView() {
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
		
		yearLabel = new JLabel("Año");
		yearLabel.setBounds(50, 140, 50, 25);
		yearLabel.setForeground(Color.WHITE);
		frame.getContentPane().add(yearLabel);
		
		directorLabel = new JLabel("Director");
		directorLabel.setBounds(50, 181, 50, 25);
		directorLabel.setForeground(Color.WHITE);
		frame.getContentPane().add(directorLabel);
		
		yearComboBox = new JComboBox<String>();
		yearComboBox.setBounds(130, 140, 90, 25);
		frame.getContentPane().add(yearComboBox);
		
		directorComboBox = new JComboBox<String>();
		directorComboBox.setBounds(130, 180, 92, 26);
		frame.getContentPane().add(directorComboBox);
		
		searchButton = new JButton("Buscar");
		searchButton.setBackground(AppUtils.ACCENT_COLOR);
		searchButton.setBounds(0, 300, 400, 50);
		frame.getContentPane().add(searchButton);
		
		frame.setVisible(true);
	}
	
	private void addComboBoxData() {
		Set<String> years = new TreeSet<String>();
		Set<String> directors = new TreeSet<String>();
		
		List<Show> shows = NetflixApp.getInstance().getShowManager().getShows();
		for (Show show : shows) {
			if (show.getReleaseYear() != null && !show.getReleaseYear().isEmpty()) {
				years.add(show.getReleaseYear() == null ? "" : show.getReleaseYear());
			}

			if (show.getDirector() != null && !show.getDirector().isEmpty()) {
				directors.add(show.getDirector() == null ? "" : show.getDirector());
			}
		}
		
		years.stream().forEach(year -> yearComboBox.addItem(year));
		directors.stream().forEach(director -> directorComboBox.addItem(director));
	}
	
	public void loadListeners() {
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new HomeView(new SearchData(titleField.getText(), yearComboBox.getName(), directorComboBox.getName()));
			}
		});
	}
}
