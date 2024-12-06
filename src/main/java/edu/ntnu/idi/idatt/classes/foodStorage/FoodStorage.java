package edu.ntnu.idi.idatt.classes.foodStorage;

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
    addToGroceries("Milk", "L", 1, LocalDate.now().minusDays(1), 21.5);
    addToGroceries("Flour", "kg", 4, LocalDate.now().plusYears(2), 24.2);
    addToGroceries("Juice", "L", 2.5, LocalDate.now().plusDays(5), 29.5);
    addToGroceries("Sugar", "kg", 0.5, LocalDate.now().plusYears(1), 6.5);
    addToGroceries("Egg", "ea", 12, LocalDate.now().minusDays(5), 41.5);
  }

  public ArrayList<Groceries> getStorage() {
    update();
    return storage;
  }

  public ArrayList<Groceries> getExpired() {
    update();
    ArrayList<Groceries> expired = new ArrayList<Groceries>();
    for (Groceries g : storage) {
      if (g.hasExpired()) {
        expired.add(g);
      }
    }
    return expired;
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
      storage.get(index).addGrocery(groceryType, unit, quantity, date, price);
    }
  }

  /**
   * Locates a grocery based on name only.
   *
   * @param groceryName - The name of the items you can search for.
   * @return : Returns the first (and only) index of the sought after grocery.
   */
  public int searchGroceries(String groceryName) {
    update();
    Optional<Groceries> searchResult = storage.stream()
        .filter(grocery -> grocery.getGroceryName().equalsIgnoreCase(groceryName))
        .findFirst();

    return searchResult.map(groceries -> storage.indexOf(groceries)).orElse(-1);
  }

  public String getUnit(int groceryIndex) {
    return storage.get(groceryIndex).getGroceryUnit();
  }

  public String getUnit(String groceryName) {
    return storage.get(searchGroceries(groceryName)).getGroceryUnit();
  }

  private void update() {
    removeEmptyGroceries();
    updateDate();
  }

  private void removeEmptyGroceries() {
    storage.removeIf(Groceries::isEmpty);
  }

  private void updateDate() {
    for (Groceries g : storage) {
      g.oldestDate();
    }
  }
}
