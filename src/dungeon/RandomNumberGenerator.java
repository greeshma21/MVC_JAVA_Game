package dungeon;

import java.util.Random;

/**
 * A class that represents random number generator within a given range.
 */
public class RandomNumberGenerator {

  /**
   * A method that generates random number within specified range and for testing purposes.
   *
   * @param min  minimum range for random number generation.
   * @param max  maximum range for random number generation.
   * @param seed seed value for testing purpose.
   * @return
   */
  public static int getRandomNumberInRange(int min, int max, int seed) {
    Random random;
    if (seed != 0) {
      random = new Random(seed);
    } else {
      random = new Random();
    }
    return random.nextInt((max - min) + 1) + min;
  }
}
