package edu.ntnu.idi.idatt.classes.foodstorage;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

/**
 * This class is to be the lowest part of the system.
 * It is here that each grocery is defined.
 */
public class Grocery {

  /**
  The role of this class is to be a blueprint
  of each item stored in the fridge.
  This class is responsible to have public information
  on the status of each grocery, that includes:
  what kind of grocery, the unit of measurement and expiry date.
  */
  private final String groceryType;
  private final String unit;
  private final LocalDate expiryDate;
  private double price;
  private double quantity;

  /**
   * Constructor for the Grocery-class.
   * This is the lowest form of groceries,
   * where each grocery is defined, or more precisely, a batch of groceries.
   * This exists in order to differentiate the different groceries,
   * so that the relevant expiration date is uphold.
   *
   * @param groceryType - Name of the grocery
   * @param quantity - How much there is in this batch
   * @param expirationDate - When the food turns bad
   * @param price - How much this batch costed
   */
  public Grocery(String groceryType,
                 String unit,
                 double quantity,
                 LocalDate expirationDate,
                 double price) {
    this.groceryType = groceryType;
    this.unit = unit;
    this.quantity = quantity;
    this.expiryDate = expirationDate;
    this.price = price;
  }

  /**
   * <h5>Method</h5>
   * <h3>updatePrice()</h3>
   * Updates price after a certain amount is removed from the storage.
   *
   * @return String of info meant to be printed.
   */
  public String info() {
    String name;
    if (groceryType.length() > 32) {
      name = groceryType.substring(0, 29) + "...";
    } else {
      name = String.format("%-32s", groceryType);
    }

    String unitString;
    if (unit.length() > 3) {
      unitString = unit.substring(0, 3);
    } else {
      unitString = String.format("%-3s", unit);
    }

    String priceString;
    String groceryPriceStr = String.format("%.2f", price);
    if (groceryPriceStr.length() > 7) {
      priceString = groceryPriceStr.substring(0, 7);
    } else {
      priceString = String.format("%-7s", groceryPriceStr);
    }

    String quantityString;
    String groceryQuantityStr = String.format("%.2f", quantity);
    if (groceryQuantityStr.length() > 5) {
      quantityString = groceryQuantityStr.substring(0, 2) + "...";
    } else {
      quantityString = String.format("%-5s", groceryQuantityStr);
    }

    String expiration = expiryDate.toString();

    return name + " | "  + quantityString + " " + unitString + " | " + priceString + " kr | " + expiration;
  }
/*
    return this.groceryType + " "
        + this.quantity + " "
        + this.unit + " "
        + this.expiryDate + " "
        + this.price + " kr";

  }

  /**
   * <h5>Method</h5>
   * <h3>getQuantity()</h3>
   * Returns the quantity of the grocery.
   *
   * @return Returns quantity.
   */
  public double getQuantity() {
    return quantity;
  }

  /**
   * <h5>Method</h5>
   * <h3>getExpiryDate()</h3>
   * Returns a date in the ChronoLocalDate format.
   * Used to compare dates.
   *
   * @return Returns the date as ChronoLocalDate.
   */
  public ChronoLocalDate getExpiryDate() {
    return expiryDate;
  }

  /**
   * <h5>Method</h5>
   * <h3>hasExpired()</h3>
   * Checks if a grocery has expired.
   *
   * @return Returns if the food has expired.
   */
  public boolean hasExpired() {
    return expiryDate.isBefore(LocalDate.now());
  }

  /**
   * <h5>Method</h5>
   * <h3>hasExpired(LocalDate date)</h3>
   * Checks if a grocery has expired by date.
   *
   * @param date Date to check upto if expired.
   * @return Boolean indicating the date is expired.
   */
  public boolean hasExpired(LocalDate date) {
    return expiryDate.isBefore(date);
  }

  /**
   * <h5>Method</h5>
   * <h3>getPrice()</h3>
   * Returns price as double.
   *
   * @return Returns price.
   */
  public double getPrice() {
    return price;
  }

  /**
   * <h5>Method</h5>
   * <h3>removeQuantity()</h3>
   * Updates price before a certain amount is removed from the storage.
   *
   * @param quantity Quantity to remove.
   */
  public void removeQuantity(double quantity) {
    updatePrice(quantity);
    this.quantity -= quantity;
  }

  /**
   * <h5>Method</h5>
   * <h3>updatePrice()</h3>
   * Updates price when a certain amount is removed from the storage.
   *
   * @param quantityRemoved Quantity that was removed.
   */
  private void updatePrice(double quantityRemoved) {
    if (quantityRemoved > 0) {
      double percentageRemoved = quantityRemoved / quantity;
      System.err.println(percentageRemoved);
      this.price = this.price * (1 - percentageRemoved);
    }
  }
}
