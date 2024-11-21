package edu.ntnu.idi.idatt.Classes.FoodStorage;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Here is the implementation of each kind of groceries.
 * That includes keeping track on the oldest items in inventory.
 */
public class Groceries {
  private ArrayList<Grocery> groceries;
  private final String groceryName;
  private final String groceryUnit;
  private LocalDate groceryOldestDate;

  /**
   * Constructor for the Grocery-class.
   *
   * @param groceryName - Name on grocery
   * @param groceryUnit - Unit of measurement
   * @param quantity - How much it is
   * @param expirationDate - When the food will turn bad
   * @param groceryPrice - How much the specific grocery cost
   */
  public Groceries(String groceryName,
                   String groceryUnit,
                   double quantity,
                   LocalDate expirationDate,
                   double groceryPrice) {
    groceries = new ArrayList<>();
    this.groceryName = groceryName;
    this.groceryUnit = groceryUnit;
    this.groceryOldestDate = expirationDate;
    this.addGrocery(groceryName, quantity, expirationDate, groceryPrice);
  }

  @Override
  public String toString() {
    // Made partially by AI-prompt in ChatGPT.
    // The take-away from the prompt was how to use substring and how to pad text.
    String name;
    if (groceryName.length() > 32) {
      name = groceryName.substring(0, 29) + "...";
    } else {
      name = String.format("%-32s", groceryName);
    }

    String unit;
    if (groceryUnit.length() > 3) {
      unit = groceryUnit.substring(0, 3);
    } else {
      unit = String.format("%-3s", groceryUnit);
    }

    String price;
    String groceryPriceStr = String.format("%.2f", totalPrice());
    if (groceryPriceStr.length() > 7) {
      price = groceryPriceStr.substring(0, 7);
    } else {
      price = String.format("%-7s", groceryPriceStr);
    }

    String quantity;
    String groceryQuantityStr = String.format("%.2f", totalQuantity());
    if (groceryQuantityStr.length() > 5) {
      quantity = groceryQuantityStr.substring(0, 2) + "...";
    } else {
      quantity = String.format("%-5s", groceryQuantityStr);
    }

    String expiration = groceryOldestDate.toString();

    return name + " | "  + quantity + " " + unit + " | " + price + " kr | " + expiration;
  }

  public ArrayList<Grocery> getGroceries() {
    return groceries;
  }

  // Methods

  /**
   * Adds a grocery to the list of groceries.
   *
   * @param groceryType - Name of the grocery type
   * @param quantity - How much it is
   * @param expiryDate - When it will turn bad
   * @param price - How much it costed
   */
  public void addGrocery(
      String groceryType,
      double quantity,
      LocalDate expiryDate,
      double price) {
    groceries.add(new Grocery(groceryType, quantity, expiryDate, price));
    this.oldestDate();
  }

  public String getGroceryName() {
    return groceryName;
  }

  /**
   * Used to get the total amount of groceries in the storage.
   *
   * @return - Returns total amount of the grocery
   */
  public double totalQuantity() {
    double tempQuantity = 0;
    if (groceries.isEmpty()) {
      System.out.println("There is nothing to show. You have an empty food storage.");
      return 0;
    }
    for (Grocery g : groceries) {
      tempQuantity += g.getQuantity();
    }
    return tempQuantity;
  }

  /**
   * Used to get the total cost of groceries in the storage.
   *
   * @return - Returns total cost
   */
  public double totalPrice() {
    double tempPrice = 0;
    if (groceries.isEmpty()) {
      System.out.println("There is nothing to show from this grocery.");
      return 0;
    }
    for (Grocery g : groceries) {
      tempPrice += g.getPrice();
    }
    return tempPrice;
  }

  /**
   * Searches for the first item that will expire, and mutates field-value thereafter.
   */
  private void oldestDate() {
    // Checks for the lowest date-value in the ArrayList
    LocalDate tempDate = LocalDate.MAX;
    for (Grocery g : groceries) {
      if (tempDate.compareTo(g.getExpiryDate()) > 0) {
        tempDate = (LocalDate) g.getExpiryDate();
      }
    }
  }
}
