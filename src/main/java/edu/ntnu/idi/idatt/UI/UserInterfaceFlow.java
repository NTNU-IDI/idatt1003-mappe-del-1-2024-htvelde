package edu.ntnu.idi.idatt.UI;

import edu.ntnu.idi.idatt.Classes.FoodStorage.FoodStorage;
import edu.ntnu.idi.idatt.Classes.FoodStorage.Grocery;

import java.util.InputMismatchException;

import static edu.ntnu.idi.idatt.UI.UserInterfacePrintOut.*;
import static edu.ntnu.idi.idatt.UI.ValidateInput.*;
import static edu.ntnu.idi.idatt.UI.UserInterfaceTextSource.*;

/**
 * All commands and instructions are handled within this class.
 */
public class UserInterfaceFlow {
  FoodStorage foodStorage;
  UserInput input;

  /**
   * Initialize the UI with a FoodStorage to handle all user input.
   */
  public void init() {
    // Initialize the FoodStorage-class, and the other UI-classes
    this.foodStorage = new FoodStorage();
    this.input = new UserInput();
    print(welcome());
  }

  public void start() {
    boolean running = true;
    while (running) {
      mainMenu();
      running = false;
    }
  }

  public void mainMenu() {
    byte max = 6;
    byte userInput = byteInput(max, printMainMenu());

    switch (userInput) {
      case 1 -> groceriesMenu();
      case 2 -> addGrocery();
      case 3 -> removeGrocery();
      case 4 -> searchGrocery();
      case 5 -> recipesMenu();
    }
  }

  /**
   * Creates a menu for the actions for groceries.
   */
  public void groceriesMenu() {
    byte max = 5;
    byte userInput = byteInput(max, printGroceriesMenu());

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
  public void recipesMenu() {
    byte max = 5;
    byte userInput = byteInput(max, recipesMenuString());

    switch (userInput) {
      case 1 -> addRecipe();
      case 2 -> removeRecipe();
      case 3 -> searchRecipe();
      case 4 -> suggestedRecipe();
      default -> mainMenu();
    }
  }

  private void addRecipe() {
  }

  private void removeRecipe() {
  }

  private void searchRecipe() {
  }

  private void suggestedRecipe() {
  }

  private void addGrocery() {
    printRequest(requestGroceryType());
    String typeName = input.inputString();
    printRequest(requestUnit());
    String unit = input.inputString();
    printRequest(requestQuantity());
    double quantity = input.inputDouble(10_000);
    printRequest(requestDate());
    String date = input.inputDate();
    printRequest(requestPrice());
    double price = input.inputDouble(10_000);
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

  private byte byteInput(byte max, String menu) {
    byte userInput = 0;

    boolean askAgain = true;
    while (askAgain) {
      printMenu(menu);
      try {
        userInput = input.inputNumber(max);
        if (!isValidByte(max, userInput)) {
          throw new IllegalArgumentException();
        }
        askAgain = false;
      }
      catch (IllegalArgumentException e) {
        printUserInputError("Number not in valid range [1-" + max + "]");
      }
      catch (InputMismatchException e) {
        printUserInputError("Not a valid number");
      }
    }
    return userInput;
  }
}
