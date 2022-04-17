package ga.mmbh.cfgs.netflixdb.graphic.panels.favourite;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class FavouriteShowElementPanel extends JPanel {
	
	private JLabel titleLabel;
	
	public FavouriteShowElementPanel() {
		titleLabel = new JLabel("");
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBounds(10, 0, 300, 30);
		this.add(titleLabel);
	}
	
}