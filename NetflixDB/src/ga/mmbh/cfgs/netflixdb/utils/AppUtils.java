package ga.mmbh.cfgs.netflixdb.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AppUtils {

	public static final Color BACKGROUND_COLOR = new Color(10, 10, 10);
	public static final Color DARK_BACKGROUND = new Color(20, 20, 20);
	public static final Color TEXT_COLOR = new Color(255, 255, 255);
	public static final Color ACCENT_COLOR = new Color(255, 100, 100);
	public static final Color YELLOW = new Color(255, 221, 103);
	public static final Color ORANGE = new Color(255, 145, 102);
	public static final Color ERROR_COLOR = new Color(255, 0, 0);
	public static final Color GREEN_COLOR = new Color(92, 255, 138);
	public static final Color TRANSPARENT_COLOR = new Color(0, 0, 0, 0);
	
	public static final String HEADER_CSV = "show_id,type,title,director,cast,country,date_added,release_year,rating,duration,listed_in,description";

	public static Font getFont(String path, float size) {
		try {
			Font myFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/" + path));
			return myFont.deriveFont(Font.PLAIN, size);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		return null;
	}

	public static Font getTitleFont() {
		return getFont("NetflixSansBlack.ttf", 26);
	}

	public static Font getRegularFont() {
		return getFont("NetflixSansRegular.ttf", 12);
	}

	public static Font getBoldFont() {
		return getFont("NetflixSansBlack.ttf", 12);
	}

	public static String getFormattedPokemonId(int id) {
		if (id < 10)
			return "00" + id;
		else if (id < 100)
			return "0" + id;
		else
			return "" + id;
	}

	public static void changeFont(Component component, Font font) {
		component.setFont(font);
		if (component instanceof Container) {
			for (Component child : ((Container) component).getComponents()) {
				changeFont(child, font);
			}
		}
	}

	private static String convertToHex(final byte[] messageDigest) {
		BigInteger bigint = new BigInteger(1, messageDigest);
		String hexText = bigint.toString(16);
		while (hexText.length() < 32) {
			hexText = "0".concat(hexText);
		}
		return hexText;
	}
}
