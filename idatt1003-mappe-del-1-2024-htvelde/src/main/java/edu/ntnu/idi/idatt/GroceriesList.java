package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Here is the implementation of each kind of groceries.
 * That includes keeping track on the oldest items in inventory.
 */
public class GroceriesList {
  private ArrayList<Grocery> groceries;
  private final String groceryName;
  private final String groceryUnit;
  private final double groceryPrice;
  private Calendar groceryOldestDate;
  private double groceryQuantity;

  public GroceriesList(String groceryName,
                       String groceryUnit,
                       double groceryPrice,
                       Calendar groceryOldestDate,
                       double quantity) {
    this.groceryName = groceryName;
    this.groceryUnit = groceryUnit;
    this.groceryPrice = groceryPrice;
  }

  @Override
  public String toString() {
    return groceryName + " " + groceryUnit + " " + groceryPrice;
  }

  // Getters and setters provided with help of AI
  public ArrayList<Grocery> getGroceries() {
    return groceries;
  }

  public void setGroceries(ArrayList<Grocery> groceries) {
    this.groceries = groceries;
  }

  // Methods
  public void addGrocery() {}

  public String getGroceryName() {
    return groceryName;
  }
}
