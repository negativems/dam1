package ga.mmbh.cfgs.netflixdb.graphic.panels;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ga.mmbh.cfgs.netflixdb.NetflixApp;
import ga.mmbh.cfgs.netflixdb.utils.AppUtils;

public class TitlePanel extends JPanel {

	private JLabel titleField, welcomeLabel;
	
	public TitlePanel(String title, Color backgroundColor, Color foregroundColor, int width, int height) {
		this.setBackground(backgroundColor);
		this.setBounds(0, 0, width, height);
		this.setLayout(null);
		
		titleField = new JLabel(title, SwingConstants.CENTER);
		titleField.setOpaque(true);
		titleField.setBackground(null);
		titleField.setForeground(foregroundColor);
		titleField.setFont(AppUtils.getTitleFont());
		titleField.setBounds(0, 0, width, height);
		this.add(titleField);
	}
	
	public TitlePanel(String title, Color backgroundColor, Color foregroundColor, int width) {
		this(title, AppUtils.TRANSPARENT_COLOR, Color.WHITE, 900, 100);
	}
	
	public TitlePanel(String title) {
		this(title, AppUtils.TRANSPARENT_COLOR, Color.WHITE, 900);
	}
}
