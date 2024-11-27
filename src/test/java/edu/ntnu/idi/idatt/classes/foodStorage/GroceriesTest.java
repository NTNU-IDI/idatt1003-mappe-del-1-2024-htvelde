package edu.ntnu.idi.idatt.classes.foodStorage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GroceriesTest {
  Groceries groceries;

  @BeforeEach
  void setUp() {
    groceries = new Groceries("Milk", "L", 10, LocalDate.now(), 21.5);
  }

  @Test
  void addingExistingGroceryShouldKeepSizeEqual() {
    int before = groceries.getGroceries().size();
    groceries.addGrocery("Milk", "L", 1, LocalDate.now(), 21.5);
    int after = groceries.getGroceries().size();
    assertEquals(0, after - before);
  }

  @Test
  void addingNewGroceryIncrementsSize() {
    int before = groceries.getGroceries().size();
    groceries.addGrocery("Juice", "L", 1, LocalDate.now(), 26.5);
    int after = groceries.getGroceries().size();
    assertEquals(1, after - before);
  }

  @Test
  void getGroceryName() {
    assertEquals("Milk", groceries.getGroceryName());
  }

  @Test
  void totalQuantityShouldBeTenLiters() {
    assertEquals(10, groceries.totalQuantity());
  }

  @Test
  void totalPrice() {
    assertEquals(21.5, groceries.totalPrice());
  }

  @Test
  void getGroceryUnit() {
    assertEquals("L", groceries.getGroceryUnit());
  }
}