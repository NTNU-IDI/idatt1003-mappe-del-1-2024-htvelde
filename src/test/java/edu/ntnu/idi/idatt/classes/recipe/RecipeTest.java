package edu.ntnu.idi.idatt.classes.recipe;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class RecipeTest {
  Recipe recipe;

  @BeforeEach
  void setUp() {
    String name = "Name";
    String description = "Description";
    ArrayList<Ingredient> ingredients = new ArrayList<>();
    String procedure = "Procedure";
    int portions = 1;
    recipe = new Recipe(name, description, ingredients, procedure, portions);
  }

  @AfterEach
  void tearDown() {
    recipe = null;
  }

  @Test
  void testInfo() {
    String info = recipe.info();
    assertInstanceOf(String.class, info);
  }

  @Test
  void testGetName() {
    String actualName = "Name";
    String name = recipe.getName();
    assertEquals(name, actualName);
  }

  @Test
  void testGetIngredients() {
    ArrayList<Ingredient> expectedIngredients = new ArrayList<>();
    ArrayList<Ingredient> actualIngredients = recipe.getIngredients();
    assertEquals(expectedIngredients, actualIngredients);
  }
}