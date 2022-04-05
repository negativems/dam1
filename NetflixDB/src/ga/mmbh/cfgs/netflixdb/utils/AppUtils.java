package ga.mmbh.cfgs.netflixdb.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class AppUtils {
	
	public static final Color BACKGROUND_COLOR = new Color(0, 199, 239);
	public static final Color DARK_BACKGROUND = new Color(20,20,20);
	public static final Color TEXT_COLOR = new Color(255, 255, 255);
	public static final Color ACCENT_COLOR = new Color(76, 187, 252);
	public static final Color ERROR_COLOR = new Color(255, 0, 0);
	public static final Color GREEN_COLOR = new Color(92, 255, 138);
	public static final Color TRANSPARENT_COLOR = new Color(0, 0, 0, 0);
	
	public static Font getNetflixFont() {
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File("resources/NetflixSansThin.ttf")).deriveFont(50);
			return font;
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
			return new Font("Rubik", Font.BOLD, 22);
		}
	}
	
	public static String getFormattedPokemonId(int id) {
		if (id < 10) return "00" + id;
		else if (id < 100) return "0" + id;
		else return "" + id;
	}
	
	public static void changeFont(Component component, Font font) {
	    component.setFont(font);
	    if (component instanceof Container) {
	        for (Component child : ((Container) component).getComponents()) {
	            changeFont(child, font);
	        }
	    }
	}
}
