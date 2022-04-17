package ga.mmbh.cfgs.netflixdb.views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ga.mmbh.cfgs.netflixdb.NetflixApp;
import ga.mmbh.cfgs.netflixdb.graphic.frames.CustomFrame;
import ga.mmbh.cfgs.netflixdb.graphic.panels.TitlePanel;
import ga.mmbh.cfgs.netflixdb.graphic.panels.favourite.FavouriteShowsPanel;
import ga.mmbh.cfgs.netflixdb.handlers.EscKeyPress;
import ga.mmbh.cfgs.netflixdb.models.Show;
import ga.mmbh.cfgs.netflixdb.utils.AppUtils;

public class FavouriteView {

	private final NetflixApp netflixApp = NetflixApp.getInstance();
	private final List<Show> favouriteShows;
	
	private CustomFrame frame;
	private TitlePanel titlePanel;
	private JButton previousPageButton, nextPageButton;
	private FavouriteShowsPanel showsPanel;
	private JLabel escLabel;
	
	private int page = 1;

	/**
	 * @wbp.parser.constructor
	 */
	public FavouriteView() {
		this.favouriteShows = netflixApp.getUserManager().getSession().getFavouritesShows();
		initialize();
		loadListeners();
	}

	private void initialize() {
		frame = new CustomFrame();
		frame.setBounds(250, 250, 400, 400);
		
		escLabel = new JLabel("ESC para salir");
		escLabel.setBounds(10, 10, 100, 10);
		escLabel.setFont(escLabel.getFont().deriveFont(8F));
		frame.add(escLabel);
		
		titlePanel = new TitlePanel("FAVORITOS", AppUtils.YELLOW, Color.WHITE, 400, 100);
		frame.add(titlePanel);
		
		showsPanel = new FavouriteShowsPanel(page);
		showsPanel.setBounds(10, 110, 380, 250);
		frame.add(showsPanel);
		
		previousPageButton = new JButton("Anterior");
		previousPageButton.setBounds(10, 370, 100, 20);
		previousPageButton.setEnabled(page != 1);
		previousPageButton.setFocusable(false);
		frame.add(previousPageButton);
		
		nextPageButton = new JButton("Siguiente");
		nextPageButton.setBounds(290, 370, 100, 20);
		nextPageButton.setEnabled(favouriteShows.size() > page * FavouriteShowsPanel.MAX_SHOWS_PER_PAGE);
		nextPageButton.setFocusable(false);
		frame.add(nextPageButton);
		
		frame.addKeyListener(new EscKeyPress(frame));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private void updatePage() {
		nextPageButton.setEnabled(favouriteShows.size() > page * FavouriteShowsPanel.MAX_SHOWS_PER_PAGE);
		previousPageButton.setEnabled(page == 1 ? false : !previousPageButton.isEnabled());
		
		showsPanel.removeAll();
		frame.repaint();
		
		showsPanel.showPage(page);
	}
	
	private void loadListeners() {
		previousPageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				page--;
				updatePage();
			}
		});
		
		nextPageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				page++;
				updatePage();
			}
		});
	}
}
