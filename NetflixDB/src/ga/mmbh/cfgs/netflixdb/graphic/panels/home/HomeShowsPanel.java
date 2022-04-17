package ga.mmbh.cfgs.netflixdb.graphic.panels.home;

import java.util.List;

import javax.swing.JPanel;

import ga.mmbh.cfgs.netflixdb.NetflixApp;
import ga.mmbh.cfgs.netflixdb.models.Show;
import ga.mmbh.cfgs.netflixdb.models.User;
import ga.mmbh.cfgs.netflixdb.utils.AppUtils;

public class HomeShowsPanel extends JPanel {
	
	public final static int MAX_SHOWS_PER_PAGE = 9;
	
	public HomeShowsPanel(int page) {
		this.setBackground(AppUtils.TRANSPARENT_COLOR);
		this.setBounds(100, 200, 700, 700);
		this.setLayout(null);
		showPage(page);
	}
	
	public void showPage(int page) {
		List<Show> shows = NetflixApp.getInstance().getShowManager().getShows();
		int i = 0;
		int startingIndex = (page * MAX_SHOWS_PER_PAGE) - MAX_SHOWS_PER_PAGE;
		for(int row = 0; row < 3; row++) {
			for(int column = 0; column < 3; column++) {
				if (startingIndex + i >= shows.size()) break;
				Show show = shows.get(((page * MAX_SHOWS_PER_PAGE) + i) - MAX_SHOWS_PER_PAGE);
				User user = NetflixApp.getInstance().getUserManager().getSession();
				JPanel elementPanel = new HomeShowElementPanel(user, show);
				elementPanel.setBounds(240 * column, 200 * row, 220, 180);
				this.add(elementPanel);
				i++;
			}
		}
	}
}
