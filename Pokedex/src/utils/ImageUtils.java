package utils;

public class ImageUtils {

	public static String getPokemonById(int id) {
		return ((id < 10) ? "00" + id : (id < 100) ? "0" + id : id + "") + ".png";
	}
	
}
