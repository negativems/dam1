package ga.mmbh.cfgs.utils;

public class ImageUtils {

	public static String getFormattedId(int id) {
		return ((id < 10) ? "00" + id : (id < 100) ? "0" + id : id + "");
	}
	
}
