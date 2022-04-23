package ga.mmbh.cfgs.netflixdb.graphic.panels.favourite;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ga.mmbh.cfgs.netflixdb.NetflixApp;
import ga.mmbh.cfgs.netflixdb.models.Show;

public class FavouriteShowsPanel extends JPanel {
	
	private final List<Show> favourites;
	public final static int MAX_SHOWS_PER_PAGE = 5;
	
	public FavouriteShowsPanel(int page) {
		this.favourites = NetflixApp.getInstance().getUserManager().getSession().getFavouritesShows();
		this.setBackground(null);
		this.setLayout(null);
		showPage(page);
	}
	
	public void showPage(int page) {
		int firstPageIndex = (page * MAX_SHOWS_PER_PAGE) - MAX_SHOWS_PER_PAGE;
		int lastPageIndex = (page * MAX_SHOWS_PER_PAGE);
		lastPageIndex = lastPageIndex >= favourites.size() ? favourites.size() : lastPageIndex;
		
		Iterator<Show> favouritesIterator = favourites.subList(firstPageIndex, lastPageIndex).iterator();
		
		int i = 0;
		while (favouritesIterator.hasNext()) {
			Show show = favouritesIterator.next();
			JPanel favouriteElement = new JPanel();
			favouriteElement.setBackground(Color.WHITE);
			favouriteElement.setBounds(0, (40 * (i)) + 20, 400, 30);
			favouriteElement.setLayout(null);
			
			JLabel titleLabel = new JLabel(show.getTitle());
			titleLabel.setBounds(20, 0, 360, 30);
			titleLabel.setForeground(Color.BLACK);
			favouriteElement.add(titleLabel);
			
			this.add(favouriteElement);
			i++;
		}
	}
}
