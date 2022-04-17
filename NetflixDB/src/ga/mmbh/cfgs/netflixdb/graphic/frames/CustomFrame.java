package ga.mmbh.cfgs.netflixdb.graphic.frames;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JFrame;

import ga.mmbh.cfgs.netflixdb.utils.AppUtils;

public class CustomFrame extends JFrame {
	
	public CustomFrame() {
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		this.setBounds(0, 0, 900, 900);
		this.getContentPane().setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(AppUtils.DARK_BACKGROUND);
		
		changeFont(this);
	}
	
	public void changeFont(Component component) {
	    component.setFont(AppUtils.getRegularFont());
	    if (component instanceof Container) {
	        for (Component child : ((Container) component).getComponents()) {
	            changeFont(child);
	        }
	    }
	}
}
