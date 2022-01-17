package utils;

public class FrameUtils {

	public static int getFirstMultiplierOfTwentyFrom(int n) {
		int max = n;
		int min = n - 20;

		// Returns random number between a max and min multiplier of 20
		return (int) Math.round((Math.random() * (max - min) + min) / 20) * 20;
	}

}
