package edu.ntnu.idi.idatt.classes.foodstorage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GroceriesTest {
  Groceries groceries;

  @BeforeEach
  void setUp() {
    // Will be using the fact that the expiration day is today.
    LocalDate today = LocalDate.now();
    groceries = new Groceries("Milk", "L", 10, today, 21.5);
  }

  @AfterEach
  void tearDown() {
    groceries = null;
  }

  @Test
  void addGroceryIncrements() {
    int count_before = groceries.getGroceries().size();
    groceries.addGrocery("Milk", "L", 10, LocalDate.now(), 20.5);
    int count_after = groceries.getGroceries().size();
    assertEquals(count_before+1, count_after);
  }

  @Test
  void addingNonExistingGroceriesThrowsException() {
    assertThrows(IllegalArgumentException.class, () ->
        groceries.addGrocery("Juice", "L", 1, LocalDate.now(), 21.5));
  }

  @Test
  void getGroceryName() {
    String name = groceries.getGroceryName();
    assertEquals("Milk", name);
  }

  @Test
  void totalQuantityShouldBeTenLiters() {
    double quantity = groceries.totalQuantity();
    assertEquals(10, quantity);
  }

  @Test
  void totalQuantityIncreasesAfterAddingGrocery() {
    double quantityBefore = groceries.totalQuantity();
    double increment = 2;
    groceries.addGrocery("Milk", "L", increment, LocalDate.now(), 20.5);
    double quantity = groceries.totalQuantity();
    assertEquals(quantityBefore + increment, quantity);
  }

  @Test
  void totalQuantityIncreasesAfterAddingGroceryWithOtherUnit() {
    double quantityBefore = groceries.totalQuantity();
    double increment = 5;
    groceries.addGrocery("Milk", "dl", increment, LocalDate.now(), 20.5);
    double quantity = groceries.totalQuantity();
    assertEquals(quantityBefore + increment/10, quantity);
  }

  @Test
  void testTotalPrice() {
    double price = groceries.totalPrice();
    assertEquals(21.5, price);
  }

  @Test
  void totalPriceIncreaseAfterAddingGrocery() {
    double priceBefore = groceries.totalPrice();
    double increment = 20;
    groceries.addGrocery("Milk", "L", 10, LocalDate.now(), increment);
    double price = groceries.totalPrice();
    assertEquals(priceBefore + increment, price);
  }

  @Test
  void getGroceryUnit() {
    String unit = groceries.getGroceryUnit();
    assertEquals("L", unit);
  }

  @Test
  void testUnitAfterAddingGroceryWithOtherUnit() {
    String unit = groceries.getGroceryUnit();
    assertEquals("L", unit);
  }

  @Test
  void testInfo() {
    String info = groceries.info();
    assertInstanceOf(String.class, info);
  }

  @Test
  void getGroceries() {
    ArrayList<Grocery> localGroceries = groceries.getGroceries();
    assertInstanceOf(ArrayList.class, localGroceries);
  }

  @Test
  void testRemoveSomeGrocery() {
    double quantity = groceries.totalQuantity();
    double remove = 0.4;
    groceries.removeGrocery(remove);
    assertEquals(quantity - remove, groceries.totalQuantity());
  }

  @Test
  void testRemoveExactlyAllGroceries() {
    double quantityBefore = groceries.totalQuantity();
    groceries.removeGrocery(quantityBefore);
    double quantity = groceries.totalQuantity();
    assertEquals(0, groceries.totalQuantity());
  }

  @Test
  void testRemoveTooMuchGroceriesRemovesAll() {
    double remove = groceries.totalQuantity() + 1;
    groceries.removeGrocery(remove);
    double quantity = groceries.totalQuantity();
    assertEquals(0, groceries.totalQuantity());
  }

  @Test
  void sortGroceryKeepsSize() {
    groceries.addGrocery("Milk", "L", 10, LocalDate.now(), 21.5);
    int sizeBefore = groceries.getGroceries().size();
    groceries.sortGrocery();
    int size = groceries.getGroceries().size();
    assertEquals(sizeBefore, size);
  }

  @Test
  void testOldestDate() {
    LocalDate localDate = LocalDate.now();
    groceries.oldestDate();
    LocalDate date = groceries.getExpirationDate();
    assertEquals(localDate, date);
  }

  @Test
  void testExpirationAfterOlderDate() {
    LocalDate dateBeforeChange = groceries.getExpirationDate();
    groceries.addGrocery("Milk", "L", 10, LocalDate.now().minusDays(1), 20.5);
    LocalDate date = groceries.getExpirationDate();
    boolean newDateIsAfter = date.isBefore(dateBeforeChange);
    assertTrue(newDateIsAfter);
  }

  @Test
  void testNewExpirationAfterNewerDate() {
    LocalDate dateBefore = groceries.getExpirationDate();
    groceries.addGrocery("Milk", "L", 10, LocalDate.now().minusDays(1), 20.5);
    LocalDate date = groceries.getExpirationDate();
    boolean newDateIsBefore = date.isBefore(dateBefore);
    assertTrue(newDateIsBefore);
  }

  @Test
  void testHasNotExpiredToday() {
    boolean expired = groceries.hasExpired();
    assertFalse(expired);
  }

  @Test
  void testHasAfterSpecifiedGivenDay() {
    boolean expired = groceries.hasExpired(LocalDate.now().plusDays(1));
    assertTrue(expired);
  }

  @Test
  void testGetExpiredGroceriesWhenNoneHasExpired() {
    ArrayList<Grocery> expiredGroceries = groceries.getExpiredGroceries();
    assertTrue(expiredGroceries.isEmpty());
  }

  @Test
  void testGetExpiredGroceriesWhenSomeHasExpired() {
    groceries.addGrocery("Milk", "L", 10, LocalDate.now().minusDays(1), 20.5);
    ArrayList<Grocery> expiredGroceries = groceries.getExpiredGroceries();
    assertFalse(expiredGroceries.isEmpty());
  }

  @Test
  void testGetExpiredValueWhenNoneHasExpired() {
    double expired = groceries.getExpiredValue();
    assertEquals(0, expired);
  }

  @Test
  void testGetExpiredValueWhenSomeHasExpired() {
    double expireValue = 10;
    groceries.addGrocery("Milk", "L", 10, LocalDate.now().minusDays(1), expireValue);
    double expired = groceries.getExpiredValue();
    assertEquals(expireValue, expired);
  }

  @Test
  void testIsEmptyWhenNotEmpty() {
    assertFalse(groceries.isEmpty());
  }

  @Test
  void testIsEmptyWhenEmpty() {
    double toRemove = groceries.totalQuantity();
    groceries.removeGrocery(toRemove);
    assertTrue(groceries.isEmpty());
  }
}