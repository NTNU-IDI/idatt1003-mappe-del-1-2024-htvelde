package edu.ntnu.idi.idatt.classes.recipe;

/**
 * <h1>Ingredient (Class)</h1>
 * This class is stores all necessary values of an ingredient.
 * You get the name, quantity, unit and if people may have an allergic reaction.
 */
public class Ingredient {
  private String name;
  private double quantity;
  private String unit;
  private boolean allergies;

  /**
   * <h1>Ingredient</h1>
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
    this.allergies = allergies;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getQuantity() {
    return quantity;
  }

  public void setQuantity(double quantity) {
    this.quantity = quantity;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public boolean isAllergic() {
    return allergies;
  }

  public void setAllergic(boolean allergicBool) {
    this.allergies = allergicBool;
  }

  public String info() {
    return quantity + " " + unit + "\t" + name;
  }

  public String info(double portions) {
    return quantity*portions + " " + unit + "\t" + name;
  }
}
