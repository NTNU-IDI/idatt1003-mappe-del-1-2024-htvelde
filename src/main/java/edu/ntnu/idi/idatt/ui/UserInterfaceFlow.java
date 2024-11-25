package edu.ntnu.idi.idatt.ui;

import edu.ntnu.idi.idatt.classes.foodStorage.FoodStorage;
import edu.ntnu.idi.idatt.classes.foodStorage.Grocery;

import java.time.LocalDate;
import java.util.InputMismatchException;

import static edu.ntnu.idi.idatt.ui.UserInterfaceTextSource.requestPrice;

import static edu.ntnu.idi.idatt.ui.UserInterfacePrintOut.*;
import static edu.ntnu.idi.idatt.ui.UserInterfaceTextSource.*;
import static edu.ntnu.idi.idatt.ui.ValidateInput.*;

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
      // running = false;
    }
  }

  public void mainMenu() {
    byte max = 6;
    byte userInput = byteInput(max, mainMenuText());

    switch (userInput) {
      case 1 -> groceriesMenu();
      case 2 -> addGrocery();
      case 3 -> removeGrocery();
      case 4 -> searchGrocery();
      case 5 -> recipesMenu();
      default -> {
        break;
      }
    }
  }

  /**
   * Creates a menu for the actions for groceries.
   */
  public void groceriesMenu() {
    byte max = 5;
    byte userInput = byteInput(max, groceriesMenuText());

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

    print(searchOptions);
    String searchString = input.inputString(2);

    int searchResult = foodStorage.searchGroceries(searchString.toLowerCase());
    if (searchResult == -1) {
      searchGrocery();
    }

    for (Grocery grocery : foodStorage.getStorage().get(searchResult).getGroceries()) {
      print(grocery.info());
    }

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
    String typeName = stringInput(2, requestGroceryType());
    String unit =
        (foodStorage.searchGroceries(typeName) == -1)
        ? stringInput(1, requestUnit())
        : foodStorage.getUnit(typeName);
    double quantity = doubleInput(0, 10_000, requestQuantity());
    LocalDate date = dateInput(requestDate());
    double price = doubleInput(0, 10_000, requestPrice());

    foodStorage.addToGroceries(typeName, unit, quantity, date, price);
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
        userInput = input.inputByte(max);
        if (!isValidByte(max, userInput)) {
          throw new IllegalArgumentException();
        }
        askAgain = false;
      } catch (IllegalArgumentException e) {
        printUserInputError("Number not in valid range [1-" + max + "]");
      } catch (InputMismatchException e) {
        printUserInputError("Not a valid number");
      }
    }
    return userInput;
  }

  private double doubleInput(double min, double max, String menu) {
    double userInput = 0;

    boolean askAgain = true;
    while (askAgain) {
      printMenu(menu);
      try {
        userInput = input.inputDouble(max);
        if (!isValidDouble(min, max, userInput)) {
          throw new IllegalArgumentException();
        }
        askAgain = false;
      } catch (IllegalArgumentException e) {
        printUserInputError("Number not in valid range [" + min + "-" + max + "]");
      } catch (InputMismatchException e) {
        printUserInputError("Not a valid number");
      }
    }
    return userInput;
  }

  private String stringInput(int minLength, String menu) {
    String stringOut = "";
    boolean askAgain = true;
    while (askAgain) {
      printMenu(menu);
      try {
        stringOut = input.inputString(minLength);
        askAgain = false;
      } catch (IllegalArgumentException e) {
        printUserInputError(e.getMessage());
      }
    }
    return stringOut;
  }

  private LocalDate dateInput(String menu) {
    LocalDate userInput = LocalDate.now();

    boolean askAgain = true;
    while (askAgain) {
      printMenu(menu);
      try {
        userInput = input.inputDate();
        askAgain = false;
      } catch (IllegalArgumentException e) {
        // printUserInputError("Date is not written in correct format. Correct way: dd.MM.yyyy.");
        printUserInputError(e.getMessage());
      } catch (InputMismatchException e) {
        printUserInputError("Not parsed at all. Correct way: dd.MM.yyyy");
      }
    }
    return userInput;
  }
}
