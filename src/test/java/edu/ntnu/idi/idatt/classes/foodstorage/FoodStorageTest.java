package edu.ntnu.idi.idatt.classes.foodstorage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class FoodStorageTest {
  FoodStorage foodStorage;

  @BeforeEach
  void setUp() {
    foodStorage = new FoodStorage();
  }

  @AfterEach
  void tearDown() {
    foodStorage = null;
  }

  @Test
  void testGetStorageGivesArrayList() {
    assertInstanceOf(ArrayList.class, foodStorage.getStorage());
  }

  @Test
  void defaultSizeIsZero() {
    int size = foodStorage.getStorage().size();
    assertEquals(0, size);
  }

  @Test
  void testPreFill() {
    foodStorage.preFill();
    int size = foodStorage.getStorage().size();
    assertNotEquals(0, size);
  }

  @Test
  void testAddToGroceriesExpandsStorageSize() {
    LocalDate today = LocalDate.now();
    int sizeBefore = foodStorage.getStorage().size();
    foodStorage.addToGroceries("Juice", "L", 1, today, 25.5);
    int size = foodStorage.getStorage().size();
    assertEquals(sizeBefore + 1, size);
  }

  @Test
  void testSearchGroceriesOfExistingGroceries() {
    foodStorage.addToGroceries("Milk", "L", 1, LocalDate.now(), 21.5);
    int searchResult = foodStorage.searchGroceries("Milk");
    assertNotEquals(-1, searchResult);
  }

  @Test
  void testSearchGroceriesOfNonExistingGroceries() {
    int searchResult = foodStorage.searchGroceries("Milk");
    assertEquals(-1, searchResult);
  }

  @Test
  void testGetUnit() {
    foodStorage.addToGroceries("Milk", "liter", 1, LocalDate.now(), 21.5);
    String unit = foodStorage.getUnit(0);
    assertEquals("liter", unit);
  }

  @Test
  void negativeTestGetUnit() {
    foodStorage.addToGroceries("Milk", "liter", 1, LocalDate.now(), 21.5);
    String unit = foodStorage.getUnit(0);
    assertNotEquals("kilogram", unit);
  }

  @Test
  void getExpired() {
    foodStorage.preFill();
    LocalDate today = LocalDate.now();
    int sizeOfExpired = foodStorage.getExpired().size();
    // Manually counted expired groceries.
    assertEquals(2, sizeOfExpired);
  }
}