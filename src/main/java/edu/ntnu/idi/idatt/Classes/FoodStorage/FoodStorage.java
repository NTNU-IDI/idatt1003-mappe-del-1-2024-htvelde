package edu.ntnu.idi.idatt.Classes.FoodStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

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
                              double quantity,
                              LocalDate date,
                              double price) {
    int index = searchGroceries(groceryType);
    if (index == -1) {
      storage.add(new Groceries(groceryType, unit, quantity, date, price));
    } else {
      storage.get(index).addGrocery(groceryType, quantity, date, price);
    }
  }

  /**
   * Locates a grocery based on name only.
   *
   * @param groceryName - The name of the items you can search for.
   * @return : Returns the first (and only) index of the sought after grocery.
   */
  public int searchGroceries(String groceryName) {
    Optional<Groceries> searchResult = storage.stream()
        .filter(grocery -> grocery.getGroceryName().equals(groceryName))
        .findFirst();

    if (searchResult.isPresent()) {
      System.err.println("Search result: " + searchResult.get());
      return storage.indexOf(searchResult.get());
    }
    return -1;
  }
}
