package edu.ntnu.idi.idatt.classes.foodStorage;

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
  private final double quantity;
  private final LocalDate expiryDate;
  private final double price;

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
  public Grocery(String groceryType, double quantity, LocalDate expirationDate, double price) {
    this.groceryType = groceryType;
    this.quantity = quantity;
    this.expiryDate = expirationDate;
    this.price = price;
  }

  // TODO: create a nice toString(), and rename to something else
  @Override
  public String toString() {
    return this.groceryType;
  }

  public double getQuantity() {
    return quantity;
  }

  public ChronoLocalDate getExpiryDate() {
    return expiryDate;
  }

  public double getPrice() {
    return price;
  }
}
