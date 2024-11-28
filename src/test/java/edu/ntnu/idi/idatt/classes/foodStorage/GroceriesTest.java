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