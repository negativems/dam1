package ga.mmbh.cfgs.netflixdb.panels;

import java.io.IOException;

import javax.swing.JFrame;

import ga.mmbh.cfgs.netflixdb.utils.AppUtils;
import ga.mmbh.cfgs.netflixdb.utils.JPanelBackground;

public class CustomFrame extends JFrame {
	
	public CustomFrame(String backgroundImageURL) {
		this.setBackground(AppUtils.BACKGROUND_COLOR);
		this.setResizable(false);
		this.setBounds(0, 0, 500, 600);
		this.getContentPane().setLayout(null);
		
		try {
			JPanelBackground panel = new JPanelBackground(backgroundImageURL);
			panel.setBounds(0, 0, 500, 600);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public CustomFrame() {
		this("resources/home_background.jfif");
	}
	
}
