package edu.ntnu.idi.idatt.classes.recipe;

/**
 * <h5>Class</h5>
 * <h3>Ingredient</h3>
 * This class is stores all necessary values of an ingredient.
 * You get the name, quantity, unit and if people may have an allergic reaction.
 */
public class Ingredient {
  private final String name;
  private final double quantity;
  private final String unit;

  /**
   * <h5>Constructor</h5>
   * <h3>Ingredient</h3>
   * An Ingredient contains all necessary info of an Ingredient to the chef.
   * That includes; name, quantity, unit and whether it may cause allergic reactions.
   *
   * @param name Depicting a name (singular).
   * @param quantity How much is needed.
   * @param unit Which measurement is used.
   * @param allergies A boolean if it may cause allergies.
   */
  public Ingredient(String name, double quantity, String unit, boolean allergies) {
    this.name = name;
    this.quantity = quantity;
    this.unit = unit;
  }

  /**
   * <h5>Method</h5>
   * <h3>getName()</h3>
   * Gets the name of the ingredient.
   *
   * @return Name of this ingredient.
   */
  public String getName() {
    return name;
  }

  /**
   * <h5>Method</h5>
   * <h3>getQuantity()</h3>
   * Returns the quantity of the ingredient.
   *
   * @return Returns the quantity of this ingredient.
   */
  public double getQuantity() {
    return quantity;
  }

  /**
   * <h5>Method</h5>
   * <h3>getUnit()</h3>
   * Returns the unit of measurement.
   *
   * @return Returns unit as a String.
   */
  public String getUnit() {
    return unit;
  }

  /**
   * <h5>Method</h5>
   * <h3>info()</h3>
   * Creates a string of basic info about an ingredient in a recipe.
   *
   * @return Info about ingredient.
   */
  public String info() {
    return quantity + " " + unit + "\t" + name;
  }

  /**
   * <h5>Method</h5>
   * <h3>info(double portions)</h3>
   * Returns a string that shows how much ingredients you need,
   * based on portions.
   *
   * @param portions How many portions you can make.
   * @return String with info about quantity of an ingredient.
   */
  public String info(double portions) {
    return quantity * portions + " " + unit + "\t" + name;
  }
}
