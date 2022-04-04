package ga.mmbh.cfgs.netflixdb.panels;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ga.mmbh.cfgs.netflixdb.models.Show;
import ga.mmbh.cfgs.netflixdb.utils.AppUtils;
import ga.mmbh.cfgs.netflixdb.NetflixApp;

public class ShowsPanel extends JPanel {

	public ShowsPanel(JFrame frame, int page) {
		this.setBounds(75, 120, 350, 400);
		this.setBackground(AppUtils.TRANSPARENT_COLOR);
		this.setLayout(null);
		
		int index = page * 7;
		for(int i = 0; i < 7; i++) {
			Show show = NetflixApp.getInstance().getShowManager().getShows().get(index);
			JPanel elementPanel = new ShowElementPanel(this, show);
			elementPanel.setBounds(0, 0 + (50 * i), 350, 40);
		}
		
	}
	
}
