package edu.ntnu.idi.idatt;

import java.util.Calendar;

/**
 * This class' task is to be the lowest part of the system.
 * It is here that each grocery is defined.
 */
public class Grocery {
  /*
  The role of this class is to be a blueprint
  of each item stored in the fridge.
  This class is responsible to have public information
  on the status of each grocery, that includes:
  what kind of grocery, the unit of measurement and expiry date.
  */
  private final String groceryType;
  private final String unit;
  private final Calendar expiryDate;

  public Grocery(String groceryType, String unit, Calendar expiryDate, double price) {
    this.groceryType = groceryType;
    this.unit = unit;
    this.expiryDate = expiryDate;
  }

  @Override
  public String toString() {
    String strOut = this.groceryType + " ";
    return this.groceryType;
  }

  // All GETTERS and SETTERS provided with help of AI.
  public String getGroceryType() {
    return groceryType;
  }

  public String getUnit() {
    return unit;
  }

  public String getExpiryDate() {
    return expiryDate.toString();
  }
}
