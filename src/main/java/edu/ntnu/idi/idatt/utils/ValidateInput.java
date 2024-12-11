package edu.ntnu.idi.idatt.utils;

/**
 * <h5>Class</h5>
 * <h3>ValidateInput</h3>
 * Class to ensure the user inputs correct numbers according what is needed.
 * All methods are static.
 */
public class ValidateInput {

  /**
   * <h5>Method</h5>
   * <h3>isValidByte()</h3>
   * Checks if a given byte is within the specified boundaries.
   *
   * @param max The maximum value of the byte.
   * @param input The byte to check.
   * @return Boolean indicating if the byte is within the boundary.
   */
  public static boolean isValidByte(byte max, byte input) {
    return input >= 0 && input <= max;
  }

  /**
   * <h5>Method</h5>
   * <h3>isValidInt()</h3>
   * Checks if a given integer is within the specified boundaries.
   *
   * @param min The minimum value of the integer.
   * @param max The maximum value of the integer.
   * @param input The integer to check.
   * @return Boolean indicating if the integer is within or without the boundary.
   */
  public static boolean isValidInt(int min, int max, int input) {
    return input >= min && input <= max;
  }

  /**
   * <h5>Method</h5>
   * <h3>isValidDouble()</h3>
   * Checks if a given number is within the specified boundaries.
   *
   * @param min The minimum value of the number.
   * @param max The maximum value of the number.
   * @param input The number to check.
   * @return Boolean indicating if the number is within or without the boundary.
   */
  public static boolean isValidDouble(double min, double max, double input) {
    return input >= min && input <= max;
  }
}
