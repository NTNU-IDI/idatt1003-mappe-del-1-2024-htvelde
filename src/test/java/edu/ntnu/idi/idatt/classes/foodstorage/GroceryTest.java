package edu.ntnu.idi.idatt.classes.foodstorage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GroceryTest {
  Grocery grocery;
  LocalDate standardDate = LocalDate.of(2020, 1, 1);

  @BeforeEach
  void setUp() {
    grocery = new Grocery(
        "Normal", "kg", 10.5, standardDate, 21.4
    );
  }

  @AfterEach
  void tearDown() {
    grocery = null;
  }

  @Test
   void info() {
    assertEquals("Normal 10.5 kg 2020-01-01 21.4 kr", grocery.info());
  }

  @Test
  void getQuantity() {
    assertEquals(10.5, grocery.getQuantity());
  }

  @Test
  void getExpiryDate() {
    assertEquals(standardDate, grocery.getExpiryDate());
  }

  @Test
  void getPrice() {
    assertEquals(21.4, grocery.getPrice());
  }
}