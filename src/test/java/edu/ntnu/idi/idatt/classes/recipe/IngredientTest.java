package edu.ntnu.idi.idatt.classes.recipe;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {
  Ingredient ingredient;

  @BeforeEach
  void setUp() {
    ingredient = new Ingredient("Demo", 10, "kg", false);
  }

  @AfterEach
  void tearDown() {
    ingredient = null;
  }

  @Test
  void testGetName() {
    String name = ingredient.getName();
    assertEquals("Demo", name);
  }

  @Test
  void testGetQuantity() {
    double quantity = ingredient.getQuantity();
    assertEquals(10, quantity);
  }

  @Test
  void testGetUnit() {
    String unit = ingredient.getUnit();
    assertEquals("kg", unit);
  }

  @Test
  void testInfo() {
    String info = ingredient.info();
    assertInstanceOf(String.class, info);
  }
}