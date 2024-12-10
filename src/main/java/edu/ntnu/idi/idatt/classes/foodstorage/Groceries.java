package edu.ntnu.idi.idatt.classes.foodstorage;

import static edu.ntnu.idi.idatt.utils.ConvertMeasurement.convertToStandardUnits;
import static edu.ntnu.idi.idatt.utils.ConvertMeasurement.unitToStandardUnit;
import static edu.ntnu.idi.idatt.utils.Date.dateToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;


/**
 * <h5>Class</h5>
 * <h3>Groceries</h3>
 * Here is the implementation of each kind of groceries.
 * That includes keeping track on the oldest items in inventory.
 */
public class Groceries {
  private final ArrayList<Grocery> groceries;
  private final String groceryName;
  private final String groceryUnit;
  private LocalDate groceryOldestDate;

  /**
   * <h5>Constructor</h5>
   * <h3>Groceries</h3>
   * Constructor for the Grocery-class.
   *
   * @param groceryName Name on grocery
   * @param groceryUnit Unit of measurement
   * @param quantity How much it is
   * @param expirationDate When the food will turn bad
   * @param groceryPrice How much the specific grocery cost
   */
  public Groceries(String groceryName,
                   String groceryUnit,
                   double quantity,
                   LocalDate expirationDate,
                   double groceryPrice) {
    groceries = new ArrayList<>();
    this.groceryName = groceryName.substring(0, 1).toUpperCase() + groceryName.substring(1);
    this.groceryOldestDate = expirationDate;
    this.groceryUnit = unitToStandardUnit(groceryUnit);
    this.addGrocery(groceryName, groceryUnit, quantity, expirationDate, groceryPrice);
  }

  /**
   * <h5>Method</h5>
   * <h3>info()</h3>
   * Returns a nice informative string instead of toString.
   *
   * @return Nice string to be printed.
   */
  public String info() {
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

    String expiration;
    if (hasExpired()) {
      expiration = "\u001B[31m";
      expiration += dateToString(groceryOldestDate);
      expiration += "\u001B[0m";
    } else {
      expiration = "\u001B[32m";
      expiration += dateToString(groceryOldestDate);
      expiration += "\u001B[0m";
    }

    return name + " | "  + quantity + " " + unit + " | " + price + " kr | " + expiration;
  }

  /**
   * <h5>Method</h5>
   * <h3>getGroceries()</h3>
   * Gives access to what is stored in the groceries list.
   *
   * @return ArrayList of groceries.
   */
  public ArrayList<Grocery> getGroceries() {
    return groceries;
  }

  /**
   * <h5>Method</h5>
   * <h3>groceryType()</h3>
   * Adds a grocery to the list of groceries.
   *
   * @param groceryType Name of the grocery type
   * @param quantity How much it is
   * @param expiryDate When it will turn bad
   * @param price How much it costed
   */
  public void addGrocery(
      String groceryType,
      String unit,
      double quantity,
      LocalDate expiryDate,
      double price) {
    if (!groceryType.equalsIgnoreCase(groceryName)) {
      throw new IllegalArgumentException("New type of grocery should not be added to Groceries.");
    }
    quantity = convertToStandardUnits(quantity, unit);
    unit = unitToStandardUnit(unit);
    groceries.add(new Grocery(groceryType, unit, quantity, expiryDate, price));
    this.oldestDate();
  }

  /**
   * <h5>Method</h5>
   * <h3>removeGrocery()</h3>
   * Removes a given quantity from grocery based on the parameter.
   *
   * @param quantity How much should be removed.
   */
  public void removeGrocery(double quantity) {
    if (quantity >= totalQuantity()) {
      groceries.removeAll(groceries);
      return;
    }

    double quantityRemaining = quantity;
    for (Grocery g : groceries) {
      if (quantityRemaining >= g.getQuantity()) {

        quantityRemaining -= g.getQuantity();
        groceries.remove(g);
      } else {
        g.removeQuantity(quantityRemaining);
        quantityRemaining = 0;
      }
    }
  }

  /**
   * <h5>Method</h5>
   * <h3>sortGrocery()</h3>
   * This method mutates the groceries-field to be sorted.
   */
  public void sortGrocery() {
    groceries.sort(Comparator.comparing(Grocery::getExpiryDate));
  }

  /**
   * <h5>Method</h5>
   * <h3>getGroceryName()</h3>
   * Gives only the name of the grocery.
   *
   * @return Name of grocery.
   */
  public String getGroceryName() {
    return groceryName;
  }

  /**
   * <h5>Method</h5>
   * <h3>totalQuantity()</h3>
   * Used to get the total amount of groceries in the storage.
   *
   * @return Returns total amount of the grocery
   */
  public double totalQuantity() {
    double tempQuantity = 0;
    if (groceries.isEmpty()) {
      return 0;
    }
    for (Grocery g : groceries) {
      tempQuantity += g.getQuantity();
    }
    return tempQuantity;
  }

  /**
   * <h5>Method</h5>
   * <h3>totalPrice()</h3>
   * Used to get the total cost of groceries in the storage.
   *
   * @return Returns total cost
   */
  public double totalPrice() {
    double tempPrice = 0;
    if (groceries.isEmpty()) {
      return 0;
    }
    for (Grocery g : groceries) {
      tempPrice += g.getPrice();
    }
    return tempPrice;
  }

  /**
   * <h5>Method</h5>
   * <h3>oldestDate()</h3>
   * Searches for the first item that will expire, and mutates field-values thereafter.
   */
  public void oldestDate() {
    sortGrocery();
    // Checks for the lowest date-value in the ArrayList
    LocalDate tempDate = LocalDate.MAX;
    for (Grocery g : groceries) {
      if (tempDate.isAfter(g.getExpiryDate())) {
        tempDate = (LocalDate) g.getExpiryDate();
      }
    }
    this.groceryOldestDate = tempDate;
  }

  /**
   * <h5>Method</h5>
   * <h3>getExpirationDate()</h3>
   * Get the date of the grocery that expires the soonest.
   *
   * @return LocalDate with the oldest grocery.
   */
  public LocalDate getExpirationDate() {
    return this.groceryOldestDate;
  }

  /**
   * <h5>Method</h5>
   * <h3>hasExpired()</h3>
   * Returns a boolean value to indicate if a grocery has expired.
   *
   * @return Returns true if expired, else returns false.
   */
  public boolean hasExpired() {
    return groceryOldestDate.isBefore(LocalDate.now());
  }

  /**
   * <h5>Method</h5>
   * <h3>hasExpired(LocalDate searchDate)</h3>
   * Returns a boolean value to indicate if a grocery has
   * expired after a specified date.
   *
   * @param date Date to search expiration of groceries.
   * @return Returns true if expired, else returns false.
   */
  public boolean hasExpired(LocalDate date) {
    return groceryOldestDate.isBefore(date);
  }

  /**
   * <h5>Method</h5>
   * <h3>getExpiredGroceries()</h3>
   * Finds all the specific groceries that have expired, then returns them.
   *
   * @return All expired groceries of the implicit given type.
   */
  public ArrayList<Grocery> getExpiredGroceries() {
    ArrayList<Grocery> expiredGroceries = new ArrayList<>();
    for (Grocery g : groceries) {
      if (g.hasExpired()) {
        expiredGroceries.add(g);
      }
    }
    return expiredGroceries;
  }

  /**
   * <h5>Method</h5>
   * <h3>getExpiredValue()</h3>
   * Finds the value of all the expired groceries.<br>
   * <i>In junction with the similar method in UserInterfaceFlow,
   * they make out my favourite java-stream-functions</i>
   *
   * @return A double that is the accumulative sum of all expired groceries.
   */
  public double getExpiredValue() {
    return getExpiredGroceries().stream()
        .filter(Grocery::hasExpired)
        .mapToDouble(Grocery::getPrice)
        .sum();
  }

  /**
   * <h5>Method</h5>
   * <h3>getGroceryUnit()</h3>
   * Returns the unit of measurement of the given grocery.
   *
   * @return String indicating unit of measurement.
   */
  public String getGroceryUnit() {
    return groceryUnit;
  }

  /**
   * <h5>Method</h5>
   * <h3>isEmpty()</h3>
   * Checks if the grocery is empty.
   *
   * @return Returns true if empty, else returns false.
   */
  public boolean isEmpty() {
    sortGrocery();
    for (Grocery g : groceries) {
      if (g.getQuantity() > 0) {
        return false;
      }
    }
    return true;
  }
}
