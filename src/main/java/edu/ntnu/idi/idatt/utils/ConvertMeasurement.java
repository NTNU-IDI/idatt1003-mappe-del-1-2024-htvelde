package edu.ntnu.idi.idatt.utils;

/**
 * <h1>ConvertMeasurement</h1>
 * Converts the most common measurements.
 * That includes:
 *  dL  -> L
 *  L   -> dL
 *  g   -> kg
 *  kg  -> g
 */
public class ConvertMeasurement {

  /**
   * <h1>deciLiterToLiter</h1>
   * This method converts from deciliter to liter.
   * 
   * @param dl the amount to convert.
   * @return the amount in liters.
   */
  public static double deciliterToLiter(double dl) {
    return dl / 10;
  }

  /**
   * <h1>literToDeciliter</h1>
   * This method converts from liter to deciliter.
   *
   * @param L the amount to convert.
   * @return the amount in deciliters.
   */
  public static double literToDeciliter(double L) {
    return L * 10;
  }

  /**
   * <h1>gramToKilogram</h1>
   * This method converts from gram to kilogram.
   *
   * @param g the amount to convert.
   * @return the amount in kilogram.
   */
  public static double gramToKilogram(double g) {
    return g / 1000;
  }

  /**
   * <h1>KilogramToGram</h1>
   * This method converts from kg to gram.
   *
   * @param kg the amount to convert.
   * @return the amount in liters.
   */
  public static double KilogramToGram(double kg) {
    return kg * 1000;
  }
}
