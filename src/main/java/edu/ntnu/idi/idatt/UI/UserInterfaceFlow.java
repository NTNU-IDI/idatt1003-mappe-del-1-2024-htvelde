package edu.ntnu.idi.idatt.UI;

import edu.ntnu.idi.idatt.Classes.FoodStorage.FoodStorage;
import edu.ntnu.idi.idatt.Classes.FoodStorage.Groceries;
import edu.ntnu.idi.idatt.Classes.FoodStorage.Grocery;

import static edu.ntnu.idi.idatt.UI.UserInterfaceTextSource.*;
import static edu.ntnu.idi.idatt.UI.UserInterfaceTextSource.welcome;

/**
 * All commands and instructions are handled within this class.
 */
public class UserInterfaceFlow {
  FoodStorage foodStorage;
  UserInput input;
  byte userInput;

  /**
   * Initialize the UI with a FoodStorage to handle all user input.
   */
  public void init() {
    // Initialize the FoodStorage-class, and the other UI-classes
    this.foodStorage = new FoodStorage();
    this.input = new UserInput();
    welcome();
  }

  public void start() {
    mainMenu();
  }

  public void mainMenu() {
    byte max = 7;

    printMainMenu();
    try {
      userInput = input.inputNumber(max);
    } catch (IllegalArgumentException e) {
      userInput = max;
      throw new IllegalArgumentException(e);
    }

    switch (userInput) {
      case 1 -> groceriesMenu();
      case 2 -> addGrocery();
      case 3 -> removeGrocery();
      case 4 -> searchGrocery();
      case 5 -> printRecipes();
      case 6 -> printRecipesMenu();
    }
  }

  /**
   * Creates a menu for the actions for groceries.
   */
  public void groceriesMenu() {
    byte max = 5;
    printGroceriesMenu();
    try {
      userInput = input.inputNumber(max);
    } catch (IllegalArgumentException e) {
      userInput = max;
      throw new IllegalArgumentException(e);
    }

    switch (userInput) {
      case 1 -> addGrocery();
      case 2 -> removeGrocery();
      case 3 -> searchGrocery();
      case 4 -> expiredGroceries();
      default -> mainMenu();
    }
  }

  /**
   * Prints all the groceries of a specified type.
   */
  public void searchGrocery() {

    // Builds a string-block from the groceries in the food storage
    String searchOptions = searchOptionsString(foodStorage);

    System.out.println(searchOptions);
    // TODO: Check for quit or similar
    String searchString = "FIX";
    int searchResult = foodStorage.searchGroceries(searchString.toLowerCase());
    if (searchResult == -1) {
      searchGrocery();
    }

    for (Grocery grocery : foodStorage.getStorage().get(searchResult).getGroceries()) {
      System.out.println(grocery.toString());
    }

    System.out.println(searchOptions);
  }

  /**
   * Prints name of different recipes that you know.
   */
  public void printRecipes() {
    printRecipesMenu();
  }

  private void addGrocery() {
    // Groceries(groceryType, unit, quantity, date, price)
    requestGroceryType();
    requestUnit();
    requestQuantity();
    requestDate();
    requestPrice();
  }

  private void removeGrocery() {
    System.out.println("Removed groceries");
  }

  private void expiredGroceries() {
    System.out.println("Expired groceries");
    System.out.println(valueOfExpiredGroceries());
  }

  private double valueOfExpiredGroceries() {
    return 0.0;
  }
}
