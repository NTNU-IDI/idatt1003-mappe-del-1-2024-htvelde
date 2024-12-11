package edu.ntnu.idi.idatt.classes.foodstorage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GroceryTest {
  Grocery grocery;

  @BeforeEach
  void setUp() {
    LocalDate today = LocalDate.now();
    grocery = new Grocery(
        "Normal", "kg", 10.5, today, 21.4
    );
  }

  @AfterEach
  void tearDown() {
    grocery = null;
  }

  @Test
  void testInfo() {
    String info = grocery.info();
    assertInstanceOf(String.class, info);
  }

  @Test
  void testGetQuantity() {
    double quantity = grocery.getQuantity();
    assertEquals(10.5, quantity);
  }

  @Test
  void testGetExpiryDate() {
    LocalDate today = LocalDate.now();
    // Cast from ChronoLocalDate
    LocalDate expirationDate = (LocalDate) grocery.getExpiryDate();
    assertEquals(today, expirationDate);
  }

  @Test
  void testGetPrice() {
    double price = grocery.getPrice();
    assertEquals(21.4, price);
  }

  @Test
  void testHasNotExpiredToday() {
    boolean expired = grocery.hasExpired();
    assertFalse(expired);
  }

  @Test
  void testHasExpiredTomorrow() {
    LocalDate tomorrow = LocalDate.now().plusDays(1);
    boolean expired = grocery.hasExpired(tomorrow);
    assertTrue(expired);
  }

  @Test
  void testHasExpiredYesterday() {
    LocalDate tomorrow = LocalDate.now().minusDays(1);
    boolean expired = grocery.hasExpired(tomorrow);
    assertFalse(expired);
  }

  @Test
  void testRemoveQuantityWhenNotRemovingAll() {
    double quantityBefore = grocery.getQuantity();
    grocery.removeQuantity(quantityBefore - 1);
    double quantityAfter = grocery.getQuantity();
    assertEquals(1, quantityAfter);
  }

  @Test
  void testRemoveQuantityWhenRemovingAll() {
    double quantityBefore = grocery.getQuantity();
    grocery.removeQuantity(quantityBefore);
    double quantityAfter = grocery.getQuantity();
    assertEquals(0, quantityAfter);
  }

  @Test
  void testRemoveMoreThanAll() {
    double quantityBefore = grocery.getQuantity();
    grocery.removeQuantity(quantityBefore + 2);
    double quantityAfter = grocery.getQuantity();
    assertEquals(0, quantityAfter);
  }
}