package edu.ntnu.idi.idatt;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This is THE STORAGE.
 * This is mainly a list of all owned items,
 * and works as the interface between users and the groceries.
 */
public class FoodStorage {
  ArrayList<Groceries> storage = new ArrayList<>();

  /**
   * Initialize the food storage with some milk.
   */
  public FoodStorage() {
    addToGroceries("Milk", "L", 1, LocalDate.now(), 21.5);
  }

  public ArrayList<Groceries> getStorage() {
    return storage;
  }

  /**
   * If the grocery is new, it adds a new Groceries-object in the groceries-ArrayList.
   *
   * @param groceryType - Name of the grocery
   * @param unit - Unit the grocery is measured in
   * @param quantity - How much there is
   * @param date - When the grocery turns bad
   * @param price - How much it costs
   */
  public void addToGroceries(String groceryType,
                              String unit,
                              int quantity,
                              LocalDate date,
                              double price) {
    if (!storage.isEmpty()) {
      int index = searchGroceries(groceryType);
      storage.get(index).addGrocery(groceryType, quantity, date, price);
      return;
    }
    storage.add(new Groceries(groceryType, unit, quantity, date, price));
  }

  /**
   * Locates a grocery based on name only.
   *
   * @param groceryName - The name you are searching for
   * @return : Returns the first (and only) index of the sought after grocery.
   */
  public int searchGroceries(String groceryName) {
    if (storage.isEmpty()) {
      return -1;
    } else {
      for (int i = 0; i < storage.size(); i++) {
        if (storage.get(i).getGroceryName().equalsIgnoreCase(groceryName)) {
          return i;
        }
      }
    }
    return -1;
  }

  //TODO: REMOVE AFTER TEST!
  /**
   * Simple test.
   */
  public void test() {
    addToGroceries("Milk", "L", 1, LocalDate.now(), 21.5);
  }
}
