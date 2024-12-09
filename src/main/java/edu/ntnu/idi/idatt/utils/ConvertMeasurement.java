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
   * <h5>Method</h5>
   * <h3>milliliterToLiter()</h3>
   * Takes some milliliters and converts to liter.
   *
   * @param milliliter Double that indicates volume.
   * @return Liter based on milliliter.
   */
  public static double milliliterToLiter(double milliliter) {
    return milliliter / 1000;
  }

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
  public static double kilogramToGram(double kg) {
    return kg * 1000;
  }

  /**
   * <h5>Method</h5>
   * <h3>teaspoonToKg</h3>
   * This converts US-tablespoons to kg (because of SI-units).
   * Based on weight of water.
   *
   * @param tsp Number of tsp to convert.
   * @return Weight of tsp in kg.
   */
  public static double teaspoonToKg(double tsp) {
    return tsp*5.7/1000;
  }

  /**
   * <h5>Method</h5>
   * <h3>tablespoonToKg</h3>
   * This converts teaspoons to kg (because of SI-units).
   * Based on weight of water.
   *
   * @param tbsp Number of tbsp to convert.
   * @return Weight of tbsp in kg.
   */
  public static double tablespoonToKg(double tbsp) {
    return tbsp*15/1000;
  }

  /**
   * <h5>Method</h5>
   * <h3>teaspoonToLiter</h3>
   * This converts US-tablespoons to liter (because of SI-units).
   * Based on weight of water.
   *
   * @param tsp Number of tsp to convert.
   * @return Weight of tsp in liter.
   */
  public static double teaspoonToLiter(double tsp) {
    return tsp*4.93/1000;
  }

  /**
   * <h5>Method</h5>
   * <h3>tablespoonToLiter</h3>
   * This converts teaspoons to liter (because of SI-units).
   * Based on weight of water.
   *
   * @param tbsp Number of tbsp to convert.
   * @return Weight of tbsp in liter.
   */
  public static double tablespoonToLiter(double tbsp) {
    return tbsp*14.8/1000;
  }
}
