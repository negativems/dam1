package ga.mmbh.cfgs.netflixdb.graphic.frames;

import java.io.IOException;

import javax.swing.JFrame;

import ga.mmbh.cfgs.netflixdb.graphic.panels.BackgroundPanel;
import ga.mmbh.cfgs.netflixdb.utils.AppUtils;

public class HomeFrame extends JFrame {
	
	public HomeFrame(String backgroundImageURL) {
		this.setBackground(AppUtils.BACKGROUND_COLOR);
		this.setFont(AppUtils.getNetflixFont());
		this.setResizable(false);
		this.setLayout(null);
		this.setBounds(0, 0, 500, 600);
		this.getContentPane().setLayout(null);
		
		try {
			BackgroundPanel panel = new BackgroundPanel(backgroundImageURL);
			panel.setBounds(0, 0, 500, 600);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public HomeFrame() {
		this("resources/home_background.jfif");
	}
	
}
