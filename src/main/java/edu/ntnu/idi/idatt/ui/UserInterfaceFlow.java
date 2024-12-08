package edu.ntnu.idi.idatt.ui;

import edu.ntnu.idi.idatt.classes.foodStorage.FoodStorage;
import edu.ntnu.idi.idatt.classes.foodStorage.Grocery;
import edu.ntnu.idi.idatt.classes.recipe.CookBook;
import edu.ntnu.idi.idatt.classes.recipe.Ingredient;
import edu.ntnu.idi.idatt.classes.recipe.Recipe;

import java.time.LocalDate;
import java.util.ArrayList;
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
  CookBook cookBook;
  UserInput input;

  /**
   * Initialize the UI with a FoodStorage to handle all user input.
   */
  public void init() {
    // Initialize the FoodStorage-class, and the other UI-classes
    this.foodStorage = new FoodStorage();
    this.cookBook = new CookBook();
    this.input = new UserInput();
    print(welcome());
  }

  public void start() {
    boolean running = true;
    while (running) {
      running = mainMenu();
    }
  }


  /**
   * The mainMenu dictates the flow of the program with user input.
   * It is meant to repeat until user wants to exit, which is indicated with a boolean.
   *
   * @return returns a boolean to indicate whether the function should repeat or not.
   */
  public boolean mainMenu() {
    byte max = 6;
    byte userInput = byteInput(max, mainMenuText());
    boolean repeat = true;

    switch (userInput) {
      case 1 -> groceriesMenu();
      case 2 -> addGrocery();
      case 3 -> removeGrocery();
      case 4 -> searchGrocery();
      case 5 -> recipesMenu();
      default -> repeat = false;
    }

    return repeat;
  }

  /**
   * Creates a menu for the actions for groceries.
   */
  public void groceriesMenu() {
    byte max = 6;
    byte userInput = byteInput(max, groceriesMenuText());

    switch (userInput) {
      case 1 -> addGrocery();
      case 2 -> removeGrocery();
      case 3 -> searchGrocery();
      case 4 -> expiredGroceries();
      case 5 -> showAllGroceries();
      default -> {
        return;
      }
    }
  }

  /**
   * Prints all the groceries of a specified type.
   */
  public void searchGrocery() {
    // Builds a string-block from the groceries in the food storage
    String searchOptions = searchGroceryString(foodStorage);

    print(searchOptions);
    String searchString = input.inputString(2);

    if (searchString.equalsIgnoreCase("exit")) {
      return;
    }

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
    byte max = 6;
    byte userInput = byteInput(max, recipesMenuText());

    switch (userInput) {
      case 1 -> addRecipe();
      case 2 -> removeRecipe();
      case 3 -> viewRecipes();
      case 4 -> searchRecipe();
      case 5 -> suggestedRecipe();
      default -> {
        return;
      }
    }
  }

  private void addRecipe() {
    String name = stringInput(2, requestRecipeName());

    ArrayList<Ingredient> ingredients = ingredientsInput();

    String description = stringInput(2, requestRecipeDescription());
    int portions = intInput(1_000, requestRecipePortionSize());

    Recipe recipe = new Recipe(name, description, ingredients, portions);
    cookBook.addRecipes(recipe);
  }

  private void removeRecipe() {
    String name = stringInput(2, requestRecipeName());
    for (Recipe recipe : cookBook.getRecipes()) {
      if (name.equalsIgnoreCase(recipe.getName())) {
        cookBook.removeRecipe(recipe);
      }
    }
  }

  private void viewRecipes() {
    for (Recipe recipe : cookBook.getRecipes()) {
      print(recipe.getName());
    }
  }

  private void searchRecipe() {
    String searchOptions = searchRecipeString(cookBook);
    print(searchOptions);

    String searchString = input.inputString(2);

    if (searchString.equalsIgnoreCase("exit")) {
      return;
    }

    int searchResult = cookBook.searchRecipes(searchString.toLowerCase());
    if (searchResult == -1) {
      searchRecipe();
    }

    System.err.println("Search" + searchResult);
    print(cookBook.getRecipes().get(searchResult).info());
  }

  private void suggestedRecipe() {
  }

  private void addGrocery() {
    ArrayList<String> restrictions = new ArrayList<String>();
    restrictions.add("exit");

    String typeName = stringInput(2, requestGroceryType(), restrictions);
    String unit =
        (foodStorage.searchGroceries(typeName) == -1)
        ? stringInput(1, requestUnit())
        : foodStorage.getUnit(typeName);
    double quantity = doubleInput(0, 1_000_000, requestQuantity());
    double price = doubleInput(0, 1_000_000, requestPrice());
    LocalDate date = dateInput(requestDate());

    foodStorage.addToGroceries(typeName, unit, quantity, date, price);
  }

  private void removeGrocery() {
    print(removeGroceryInformation());
    String typeName = stringInput(2, requestGroceryType());
    double quantity = doubleInput(0, 1_000_000, requestQuantity());

    foodStorage.getStorage().get(foodStorage.searchGroceries(typeName)).removeGrocery(quantity);
    System.out.println("Removed groceries");
    newLine();
  }

  private void showAllGroceries() {
    print(allGroceriesString());
    printArrayList(foodStorage.getStorage());
    newLine();
  }

  //TODO: Fix that thing
  private void expiredGroceries() {
    print(RED);
    print(expired());
    print(allGroceriesString());
    printArrayList(foodStorage.getExpired());
    print(RESET_COLOR);
    newLine();
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
        userInput = input.inputByte();
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

  private int intInput(int max, String menu) {
    int userInput = 0;

    boolean askAgain = true;
    while (askAgain) {
      printMenu(menu);
      try {
        userInput = input.inputInt();
        if (!isValidInt(1, max, userInput)) {
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
        userInput = input.inputDouble();
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

  private String stringInput(int minLength, String menu, ArrayList<String> restrictions) {
    String stringOut = "";
    boolean askAgain = true;
    while (askAgain) {
      printMenu(menu);
      try {
        stringOut = input.inputString(minLength);
        if (!isRestricted(stringOut, restrictions)) {
          // I have used an exception to print what the user did wrong.
          // If I had more time I would make a standard solution.
          throw new IllegalArgumentException("Restricted input.");
        }
        askAgain = false;
      } catch (IllegalArgumentException e) {
        printUserInputError(e.getMessage());
      }
    }
    return stringOut;
  }

  private boolean booleanInput(String request) {
    String stringInput;
    boolean output = false;

    ArrayList<String> positive = new ArrayList<String>();
    positive.add("true");
    positive.add("1");
    positive.add("yes");
    positive.add("y");

    boolean askAgain = true;
    while (askAgain) {
      print(request);
      try {
        stringInput = input.inputString(1);
        // Required for the java-stream
        String finalStringInput = stringInput;
        output = positive.stream().anyMatch(m -> m.equalsIgnoreCase(finalStringInput));
        askAgain = false;
        System.err.println(output);
      } catch (IllegalArgumentException e) {
        printUserInputError(e.getMessage());
      }
    }
    return output;
  }

  private ArrayList<Ingredient> ingredientsInput() {
    ArrayList<Ingredient> ingredients = new ArrayList<>();
    boolean askAgain = true;
    while (askAgain) {
      String name = stringInput(2, "Ingredient name");
      double quantity = doubleInput(0, 1_000_000, requestQuantity());
      String unit = stringInput(1, "Ingredient unit");
      boolean allergies = booleanInput("Allergic");
      askAgain = booleanInput("More ingredients?");

      Ingredient ingredient = new Ingredient(name, quantity, unit, allergies);
      ingredients.add(ingredient);
    }
    return ingredients;
  }
  
  private boolean isRestricted(String input, ArrayList<String> restrictions) {
    return restrictions.stream()
        .noneMatch(str -> str.equalsIgnoreCase(input));
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
