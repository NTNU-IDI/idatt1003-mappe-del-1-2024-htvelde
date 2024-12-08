package edu.ntnu.idi.idatt.classes.foodstorage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class FoodStorageTest {
  FoodStorage foodStorage;

  @BeforeEach
  void setUp() {
    // Creates a storage where Milk already exists
    foodStorage = new FoodStorage();
  }

  @Test
  void getStorageReturnsArrayList() {
    assertInstanceOf(ArrayList.class, foodStorage.getStorage());
  }

  @Test
  void searchGroceries() {

  }

  @Test
  void getStorage() {
  }

  @Test
  void defaultSizeIsOne() {
    assertEquals(foodStorage.getStorage().size(), 1);
  }

  @Test
  void addingJuiceExpandsStorageToTwo() {
    LocalDate today = LocalDate.now();
    foodStorage.addToGroceries("Juice", "L", 1, today, 25.5);
    assertEquals(foodStorage.getStorage().size(), 2);
  }

  @Test
  void searchOfMilkGivesZero() {
    foodStorage.addToGroceries("Milk", "L", 1, LocalDate.now(), 21.5);
    assertEquals(foodStorage.searchGroceries("Milk"), 0);
  }

  @Test
  void searchOfJuiceGivesNeg1() {
    assertEquals(foodStorage.searchGroceries("Juice"), -1);
  }

  @Test
  void getUnit() {
    assertEquals(foodStorage.getUnit(0), "L");
  }

  @Test
  void testGetUnit() {
    assertEquals(foodStorage.getUnit("Milk"), "L");
  }
}