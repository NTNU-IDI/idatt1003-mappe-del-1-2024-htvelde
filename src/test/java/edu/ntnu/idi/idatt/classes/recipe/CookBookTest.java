package edu.ntnu.idi.idatt.classes.recipe;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CookBookTest {
  CookBook cookBook;

  @BeforeEach
  void setUp() {
    // Two items are added immediately, by creation.
    cookBook = new CookBook();
  }

  @AfterEach
  void tearDown() {
    cookBook = null;
  }

  @Test
  void testGetRecipes() {
    int size = cookBook.getRecipes().size();
    assertEquals(2, size);
  }

  @Test
  void testAddRecipe() {
    int sizeBefore = cookBook.getRecipes().size();
    ArrayList<Ingredient> ingredients = new ArrayList<>();
    Recipe newRecipe = new Recipe("New", "New Recipe", ingredients, "Shaken and not stirred.", 1);
    cookBook.addRecipe(newRecipe);
    int sizeAfter = cookBook.getRecipes().size();
    assertEquals(sizeBefore + 1, sizeAfter);
  }

  @Test
  void testRemoveRecipe() {
    int sizeBefore = cookBook.getRecipes().size();
    Recipe recipe = cookBook.getRecipes().getFirst();
    cookBook.removeRecipe(recipe);
    int sizeAfter = cookBook.getRecipes().size();
    assertEquals(sizeBefore - 1, sizeAfter);
  }

  @Test
  void testSearchRecipesIndex() {
    int index = cookBook.searchRecipesIndex("simple");
    assertEquals(1, index);
  }

  @Test
  void testSearchRecipes() {
    ArrayList<Ingredient> ingredients = new ArrayList<>();
    Recipe newRecipe = new Recipe("New", "New Recipe", ingredients, "Shaken and not stirred.", 1);
    cookBook.addRecipe(newRecipe);
    Recipe foundRecipe = cookBook.searchRecipes("New");
    assertEquals(newRecipe, foundRecipe);
  }
}