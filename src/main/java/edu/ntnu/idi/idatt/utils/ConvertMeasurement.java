package edu.ntnu.idi.idatt.utils;

/**
 * <h5>Class</h5>
 * <h3>ConvertMeasurement</h3>
 * Converts the most common measurements.
 * That includes:
 *  dL   -> L
 *  L    -> dL
 *  kg   -> g
 *  g    -> kg
 *  tbsp -> kg
 *  tsp  -> kg
 */
public class ConvertMeasurement {

  // All field values are only meant for regex.
  private static final String gram = "[Gg]|[Gg]ram.";
  private static final String kilogram = "[Kk][Gg]|[Kk]ilogram.";
  private static final String tablespoon = "[Tt][Bb][Ss][Pp]|[Tt]ablespoon.";
  private static final String teaspoon = "[Tt][Ss][Pp]|[Tt]easpoon.";

  private static final String milliliter = "[Mm][Ll]|[Mm]illiliter.";
  private static final String deciliter = "[Dd][Ll]|[Dd]eciliter.";
  private static final String liter = "[Ll]|[Ll]iter.";

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
   * <h5>Method</h5>
   * <h3>deciliterToLiter()</h3>
   * This method converts from deciliter to liter.
   *
   * @param dl the amount to convert.
   * @return the amount in liters.
   */
  public static double deciliterToLiter(double dl) {
    return dl / 10;
  }

  /**
   * <h5>Method</h5>
   * <h3>literToDeciliter()</h3>
   * This method converts from liter to deciliter.
   *
   * @param liter the amount to convert.
   * @return the amount in deciliters.
   */
  public static double literToDeciliter(double liter) {
    return liter * 10;
  }

  /**
   * <h5>Method</h5>
   * <h3>gramToKilogram()</h3>
   * This method converts from gram to kilogram.
   *
   * @param g the amount to convert.
   * @return the amount in kilogram.
   */
  public static double gramToKilogram(double g) {
    return g / 1000;
  }


  /**
   * <h5>Method</h5>
   * <h3>teaspoonToKg()</h3>
   * This converts US-tablespoons to kg (because of SI-units).
   * Based on weight of water.
   *
   * @param tsp Number of tsp to convert.
   * @return Weight of tsp in kg.
   */
  public static double teaspoonToKg(double tsp) {
    return tsp * 5.7 / 1000;
  }

  /**
   * <h5>Method</h5>
   * <h3>tablespoonToKg()</h3>
   * This converts teaspoons to kg (because of SI-units).
   * Based on weight of water.
   *
   * @param tbsp Number of tbsp to convert.
   * @return Weight of tbsp in kg.
   */
  public static double tablespoonToKg(double tbsp) {
    return tbsp * 15 / 1000;
  }

  /**
   * <h5>Method</h5>
   * <h3>unitToStandardUnit()</h3>
   * Converts the name of a unit to SI-unit.
   * Should only be used together with "translateToStandardUnits()".
   * If instance is not parsed to SI-unit, the original String will be returned.
   *
   * @param unit A String indicating unit of measurement.
   * @return A new String in the SI-unit format, or the old if it don't fit.
   */
  public static String unitToStandardUnit(String unit) {
    if (unit.matches(
        ConvertMeasurement.gram + "|"
        + ConvertMeasurement.tablespoon + "|"
        + ConvertMeasurement.teaspoon + "|"
        + ConvertMeasurement.kilogram
    )) {
      return "kg";
    } else if (unit.matches(
        ConvertMeasurement.milliliter + "|"
        + ConvertMeasurement.deciliter + "|"
        + ConvertMeasurement.liter)) {
      return "L";
    } else {
      return unit;
    }
  }

  /**
   * <h5>Method</h5>
   * <h3>translateToStandardUnits()</h3>
   * A method that takes a quantity and an unit to calculate the quantity in SI-units.
   * If there are no fitting SI-units, it returns itself.
   *
   * @param quantity Quantity is a double representing how much to convert.
   * @param unit The measurement unit as a String.
   * @return A new quantity that is SI-correct, except when no fitting SI-units are found.
   */
  public static double convertToStandardUnits(double quantity, String unit) {
    if (unit.matches(ConvertMeasurement.gram)) {
      return gramToKilogram(quantity);
    }
    if (unit.matches(ConvertMeasurement.kilogram)) {
      return quantity;
    }
    if (unit.matches(ConvertMeasurement.deciliter)) {
      return deciliterToLiter(quantity);
    }
    if (unit.matches(ConvertMeasurement.liter)) {
      return quantity;
    }
    if (unit.matches(ConvertMeasurement.milliliter)) {
      return milliliterToLiter(quantity);
    }
    if (unit.matches(ConvertMeasurement.tablespoon)) {
      return tablespoonToKg(quantity);
    }
    if (unit.matches(ConvertMeasurement.teaspoon)) {
      return teaspoonToKg(quantity);
    }
    return quantity;
  }
}
