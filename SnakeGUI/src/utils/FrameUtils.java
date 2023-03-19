package utils;

public class FrameUtils {

   /**
    * 
    * Get the first multiple of 20 between a given number and 20
    * numbers below it.
    * Example: getFirstMultiplierOfTwentyFrom(95) returns 80
    * 
    * @param n the number from which to find the first multiple of 20
    * @return the first multiple of 20 between n and 20 numbers below it
    */
   public static int getFirstMultiplierOfTwentyFrom(int n) {
      int max = n;
      int min = n - 20;

      // Returns random number between a max and min multiplier of 20
      return (int) Math.round((Math.random() * (max - min) + min) / 20) * 20;
   }

}
