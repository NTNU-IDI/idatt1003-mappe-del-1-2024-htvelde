package edu.ntnu.idi.idatt.classes.foodstorage;

import static edu.ntnu.idi.idatt.utils.ConvertMeasurement.translateToStandardUnits;
import static edu.ntnu.idi.idatt.utils.ConvertMeasurement.unitToStandardUnit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;


/**
 * <h5>Class</h5>
 * <h3>FoodStorage</h3>
 * This is THE STORAGE.
 * This is mainly a list of all owned items,
 * and works as the interface between users and the groceries.
 */
public class FoodStorage {
  ArrayList<Groceries> storage = new ArrayList<>();

  /**
   * <h5>Constructor</h5>
   * <h3>FoodStorage</h3>
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
    ArrayList<Groceries> expired = new ArrayList<>();
    for (Groceries g : storage) {
      if (g.hasExpired()) {
        Groceries expiredGroceries = new Groceries(g.getGroceryName(),
          g.getGroceryUnit(),
          g.totalQuantity(),
          g.getExpirationDate(),
          g.getExpiredValue());
        expired.add(expiredGroceries);
      }
    }
    return expired;
  }

  /**
   * <h5>Method</h5>
   * <h3>addToGroceries()</h3>
   * If the grocery is new, it adds a new Groceries-object in the groceries-ArrayList.
   * If the grocery type already exists, it adds on to an existing Groceries object.
   *
   * @param groceryType Name of the grocery
   * @param unit Unit the grocery is measured in
   * @param quantity How much there is
   * @param date When the grocery turns bad
   * @param price How much it costs
   */
  public void addToGroceries(String groceryType,
                              String unit,
                              double quantity,
                              LocalDate date,
                              double price) {
    quantity = translateToStandardUnits(quantity, unit);
    unit = unitToStandardUnit(unit);
    int index = searchGroceries(groceryType);
    if (index == -1) {
      storage.add(new Groceries(groceryType, unit, quantity, date, price));
    } else {
      storage.get(index).addGrocery(groceryType, unit, quantity, date, price);
    }
  }

  /**
   * <h5>Method</h5>
   * <h3>searchGroceries()</h3>
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

  /**
   * <h5>Method</h5>
   * <h3>getUnit(int index)</h3>
   * Returns the unit of the grocery on a given index.
   * Number indicating position in ArrayList.
   * Should only to be used directly after a search,
   * before any mutation occur, that can have impact.<br>
   * <b>Use the overloaded method with String if unsure.</br>
   *
   * @param groceryIndex Index of a grocery in ArrayList.
   * @return Unit String.
   */
  public String getUnit(int groceryIndex) {
    return storage.get(groceryIndex).getGroceryUnit();
  }

  /**
   * <h5>Method</h5>
   * <h3>getUnit(String groceryName)</h3>
   * Return the unit of a grocery by name.
   *
   * @param groceryName String of grocery name.
   * @return Unit String.
   */
  public String getUnit(String groceryName) {
    return storage.get(searchGroceries(groceryName)).getGroceryUnit();
  }

  /**
   * <h5>Method</h5>
   * <h3>update()</h3>
   * Runs methods that update values in the class/object.<br>
   * Methods include:<br>
   * - removeEmptyGroceries()<br>
   * - updateDate()<br>
   * - sortStorage()<br>
   *
   * @see FoodStorage#removeEmptyGroceries()
   * @see FoodStorage#updateDate()
   * @see FoodStorage#sortStorage()
   */
  private void update() {
    removeEmptyGroceries();
    updateDate();
    sortStorage();
  }

  /**
   * <h5>Method</h5>
   * <h3>sortStorage()</h3>
   * Mutates object to sort every grocery by name.
   */
  private void sortStorage() {
    storage.sort(Comparator.comparing(Groceries::getGroceryName));
  }

  /**
   * <h5>Method</h5>
   * <h3>removeEmptyGroceries()</h3>
   * Runs through all items stored, and removes the ones without items.
   */
  private void removeEmptyGroceries() {
    storage.removeIf(Groceries::isEmpty);
  }

  /**
   * <h5>Method</h5>
   * <h3>updateDate()</h3>
   * Updates the expiration date of a stored grocery, to the soonest expiration.
   */
  private void updateDate() {
    for (Groceries g : storage) {
      g.oldestDate();
    }
  }
}
