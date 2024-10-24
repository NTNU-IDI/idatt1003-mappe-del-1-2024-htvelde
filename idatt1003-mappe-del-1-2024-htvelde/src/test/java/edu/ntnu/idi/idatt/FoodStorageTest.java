package edu.ntnu.idi.idatt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodStorageTest {

  @Test
  void twoPlusTwoShouldEqualFour() {
    FoodStorage foodStorage = new FoodStorage();
    assertEquals(4, foodStorage.add(2, 2));
  }
}