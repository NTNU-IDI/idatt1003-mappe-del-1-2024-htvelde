package edu.ntnu.idi.idatt.classes.recipe;

public class Ingredients {
  private String name;
  private double quantity;
  private String unit;
  private boolean allergies;

  public Ingredients(String name, double quantity, String unit, boolean allergies) {
    this.name = name;
    this.quantity = quantity;
    this.unit = unit;
    this.allergies = allergies;
  }

  public Ingredients(String name, double quantity, String unit) {
    this.name = name;
    this.quantity = quantity;
    this.unit = unit;
    this.allergies = false;
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
}
