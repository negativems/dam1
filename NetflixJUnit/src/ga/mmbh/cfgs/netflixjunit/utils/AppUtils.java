package ga.mmbh.cfgs.netflixjunit.utils;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

public class AppUtils {

	public static final Color BACKGROUND_COLOR = new Color(39, 45, 54);
	public static final Color TEXT_COLOR = new Color(255, 255, 255);
	public static final Color ACCENT_COLOR = new Color(229, 9, 20);
	public static final Color ERROR_COLOR = new Color(255, 0, 0);
	public static final Color GREEN_COLOR = new Color(92, 255, 138);
	public static final Color TRANSPARENT_COLOR = new Color(0, 0, 0, 0);

	/**
	 * Gets Netflix font from resource folder
	 * @param fontType Light, Medium or Bold
	 * @param size Font size
	 * @return Font
	 */
	public static Font getNetflixFont(String fontType, int size) {
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File("resources/Netflix Sans " + fontType + ".otf")).deriveFont(size);
			return font;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
