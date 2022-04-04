package ga.mmbh.cfgs.netflixdb.panels;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import ga.mmbh.cfgs.netflixdb.models.Show;
import ga.mmbh.cfgs.netflixdb.utils.AppUtils;

public class ShowElementPanel extends JPanel {

	public ShowElementPanel(JPanel parent, Show show) {
		this.setBackground(null);
		this.setBorder(BorderFactory.createLineBorder(AppUtils.ACCENT_COLOR));
		parent.add(this);
		this.setLayout(null);
		
		JButton nextButton = new JButton("Next");
		nextButton.setBounds(261, 377, 89, 23);
		this.add(nextButton);
		
		JButton previousButton = new JButton("Previous");
		previousButton.setBounds(0, 377, 89, 23);
		this.add(previousButton);
	}
	
}
