package ga.mmbh.cfgs.netflixdb.graphic.frames;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;

import ga.mmbh.cfgs.netflixdb.utils.AppUtils;

public class CustomFrame extends JFrame {
	
	public CustomFrame() {
		this.setBackground(AppUtils.BACKGROUND_COLOR);
		this.setResizable(false);
		this.setLayout(null);
		this.setBounds(0, 0, 500, 600);
		this.getContentPane().setLayout(null);
	}
	
	public void changeFont(Component component) {
	    component.setFont(AppUtils.getNetflixFont());
	    if (component instanceof Container) {
	        for (Component child : ((Container) component).getComponents()) {
	            changeFont(child);
	        }
	    }
	}
	
}
