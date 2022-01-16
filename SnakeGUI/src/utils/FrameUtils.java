package utils;

public class FrameUtils {

	public static int getMultiplierOfTwentyFrom(int n) {
		int max = n;
		int min = n - 20;

		// Returns random number between a max and min
		return (int) Math.round((Math.random() * (max - min) + min) / 20) * 20;
	}

}
