package edu.ntnu.idi.idatt.ui;

import edu.ntnu.idi.idatt.classes.foodstorage.FoodStorage;
import edu.ntnu.idi.idatt.classes.recipe.CookBook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTextSourceTest {

  @Test
  void searchGroceryString() {
    FoodStorage foodStorage = new FoodStorage(true);
    String out = UserInterfaceTextSource.searchGroceryString(foodStorage);
    assertInstanceOf(String.class, out);
  }

  @Test
  void searchRecipeString() {
    CookBook cookBook = new CookBook();
    String out = UserInterfaceTextSource.searchRecipeString(cookBook);
    assertInstanceOf(String.class, out);
  }

  @Test
  void allGroceriesStringHeader() {
    String out = UserInterfaceTextSource.allGroceriesStringHeader();
    assertInstanceOf(String.class, out);
  }
}