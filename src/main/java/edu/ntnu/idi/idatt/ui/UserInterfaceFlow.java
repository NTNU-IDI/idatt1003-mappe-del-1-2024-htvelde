package edu.ntnu.idi.idatt.ui;

import static edu.ntnu.idi.idatt.ui.UserInterfacePrintOut.*;
import static edu.ntnu.idi.idatt.ui.UserInterfaceTextSource.*;
import static edu.ntnu.idi.idatt.ui.UserInterfaceTextSource.requestPrice;
import static edu.ntnu.idi.idatt.ui.ValidateInput.*;

import edu.ntnu.idi.idatt.classes.foodstorage.FoodStorage;
import edu.ntnu.idi.idatt.classes.foodstorage.Groceries;
import edu.ntnu.idi.idatt.classes.foodstorage.Grocery;
import edu.ntnu.idi.idatt.classes.recipe.CookBook;
import edu.ntnu.idi.idatt.classes.recipe.Ingredient;
import edu.ntnu.idi.idatt.classes.recipe.Recipe;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * <h5>Class</h5>
 * <h3>UserInterfaceFlow</h3>
 * This class is the brain of the project.
 * The class controls the flow of the program,
 * and by extension, most methods appear here.
 */
public class UserInterfaceFlow {
  FoodStorage foodStorage;
  CookBook cookBook;
  UserInput input;

  /**
   * <h5>Method</h5>
   * <h3>init()</h3>
   * Initialize the UI with a FoodStorage to handle all user input.
   */
  public void init() {
    // Initialize the FoodStorage-class, and the other UI-classes
    this.foodStorage = new FoodStorage();
    this.cookBook = new CookBook();
    this.input = new UserInput();
    print(welcome());
  }

  /**
   * <h5>Method</h5>
   * <h3>start()</h3>
   * Initiates the menu that repeats and controls until the user is satisfied.
   */
  public void start() {
    boolean running = true;
    while (running) {
      running = mainMenu();
    }
  }

  /**
   * <h5>Method</h5>
   * <h3>Main Menu()</h3>
   * The mainMenu dictates the flow of the program with user input.
   * All inputs are required to be bytes.
   * It is meant to repeat until user wants to exit, which is indicated with a boolean.
   *
   * @return returns a boolean to indicate whether the function should repeat or not.
   */
  public boolean mainMenu() {
    final byte max = 3;
    byte userInput = byteInput(max, mainMenuText());
    boolean repeat = true;

    switch (userInput) {
      case 1 -> groceriesMenu();
      case 2 -> recipesMenu();
      default -> repeat = false;
    }

    return repeat;
  }

  /**
   * <h5>Method</h5>
   * <h3>groceriesMenu()</h3>
   * Creates a menu for the actions for groceries.
   * Each input are bytes.
   */
  public void groceriesMenu() {
    final byte max = 6;

    boolean repeat = true;
    while (repeat) {
      byte userInput = byteInput(max, groceriesMenuText());

      switch (userInput) {
        case 1 -> addGrocery();
        case 2 -> removeGrocery();
        case 3 -> searchGrocery();
        case 4 -> expiredGroceries();
        case 5 -> showAllGroceries();
        default -> repeat = false;
      }
    }
  }

  /**
   * <h5>Method</h5>
   * <h3>searchGrocery()</h3>
   * Prints all the groceries of a user-specified type.
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
   * <h5>Method</h5>
   * <h3>recipesMenu()</h3>
   * Prints name of different recipes that you know.
   */
  public void recipesMenu() {
    final byte max = 6;

    boolean repeat = true;
    while (repeat) {
      byte userInput = byteInput(max, recipesMenuText());

      switch (userInput) {
        case 1 -> addRecipe();
        case 2 -> removeRecipe();
        case 3 -> viewRecipes();
        case 4 -> searchRecipe();
        case 5 -> suggestedRecipe();
        default -> repeat = false;
      }
    }
  }

  /**
   * <h5>Method</h5>
   * <h3>addRecipe()</h3>
   * Recipes aren't made by themselves, someone must be making them.
   * But you are in luck, some delicious desert pancakes are included!
   */
  private void addRecipe() {
    String name = stringInput(2, requestRecipeName());

    ArrayList<Ingredient> ingredients = ingredientsInput();

    String description = stringInput(2, requestRecipeDescription());
    int portions = intInput(1_000, requestRecipePortionSize());

    Recipe recipe = new Recipe(name, description, ingredients, portions);
    cookBook.addRecipes(recipe);
  }

  /**
   * <h5>Method</h5>
   * <h3>removeRecipe()</h3>
   * Removes a recipe by asking for the name of recipe to remove.
   */
  private void removeRecipe() {
    viewRecipes();
    String recipeName = stringInput(2, requestGroceryType());

    int result = cookBook.searchRecipes(recipeName);
    if (result == -1) {
      return;
    }
    cookBook.removeRecipe(cookBook.getRecipes().get(result));
    newLine();
  }

  /**
   *
   * <h5>Method</h5>
   * <h3>viewRecipes()</h3>
   * Gives a list of all the stored recipes' names.
   * You must search for a recipe to show what it contains.
   */
  private void viewRecipes() {
    if (cookBook.getRecipes().isEmpty()) {
      print(empty());
      return;
    }

    for (Recipe recipe : cookBook.getRecipes()) {
      print(recipe.getName());
    }
  }

  /**
   *
   * <h5>Method</h5>
   * <h3>searchRecipes()</h3>
   * First a list of all recipes show up,
   * then you enter in one of the names to get the complete recipe,
   * with name, ingredients, description.
   */
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

    print(cookBook.getRecipes().get(searchResult).info());
  }

  /**
   * <h5>Method</h5>
   * <h3>suggestedRecipe()</h3>
   * Shows name of all recipes that you can make.
   */
  private void suggestedRecipe() {
  }

  /**
   *
   * <h5>Method</h5>
   * <h3>addGrocery()</h3>
   * User inputs details on a grocery to add it to the storage.
   * A suggestion is to keep all names singular, to avoid duplicates.
   */
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

  /**
   * <h5>Method</h5>
   * <h3>removeGrocery()</h3>
   * Removes a grocery from foodStorage based on name and quantity.
   * Grocery deletes when more is removed than possible.
   */
  private void removeGrocery() {
    String typeName = stringInput(2, requestGroceryType());
    double quantity = doubleInput(0, 1_000_000, requestQuantity());

    int result = foodStorage.searchGroceries(typeName);
    if (result == -1) {
      return;
    }

    foodStorage.getStorage().get(result).removeGrocery(quantity);
    newLine();
  }

  /**
   * <h5>Method</h5>
   * <h3>showAllGroceries()</h3>
   * Prints a nice overview of what is in the foodStorage.
   */
  private void showAllGroceries() {
    if (foodStorage.getStorage().isEmpty()) {
      print(empty());
      return;
    }
    print(allGroceriesString());
    printArrayList(foodStorage.getStorage());
    newLine();
  }

  /**
   * <h5>Method</h5>
   * <h3>expiredGroceries()</h3>
   * Prints all groceries that have expired.
   * To ease development, all groceries of an expired type
   * are shown, despite not necessarily being expired.
   */
  private void expiredGroceries() {
    print(RED);
    print(expired());
    print(allGroceriesString());
    printArrayList(foodStorage.getExpired());
    print("Net loss of " + valueOfExpiredGroceries() + " kr");
    print(RESET_COLOR);
    newLine();
  }

  /**
   * <h5>Method</h5>
   * <h3>valueOfExpiredGroceries()</h3>
   * The effect of this function is just to show how much value
   * that have accumulated of expired groceries.
   *
   * @return total price of only the expired groceries.
   */
  private double valueOfExpiredGroceries() {
    return foodStorage.getExpired().stream()
        .mapToDouble(Groceries::getExpiredValue).sum();
  }

  /**
   * <h5>Method</h5>
   * <h3>byteInput()</h3>
   * This is a function that asks for the user to input a byte,
   * then makes sure it is a valid byte (a value between <b>0 and max</b>.
   * If it is not, it repeats itself until the user enters a valid byte.
   *
   * @param max Upper limit of input.
   * @param menu A string that is informative of what input is expected.
   * @return Returns a byte that is most commonly used in choices.
   */
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

  /**
   * <h5>Method</h5>
   * <h3>intInput()</h3>
   * This is a function that asks for the user to input an int,
   * then makes sure it is a valid int (a value between <b>0 and max</b>.
   * If it is not, it repeats itself until the user enters a valid int.
   *
   * @param max Upper limit of input.
   * @param menu A string that is informative of what input is expected.
   * @return Returns a valid int.
   */
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

  /**
   * <h5>Method</h5>
   * <h3>doubleInput()</h3>
   * This is a function that asks for the user to input a double,
   * then makes sure it is a valid double (a value between <b>min and max</b>.
   * If it is not, it repeats itself until the user enters a valid double.<br>
   * <b><i>Notice that the decimal point is "," and not ".".</i></b>
   *
   * @param min Lower limit of input.
   * @param max Upper limit of input.
   * @param menu A string that is informative of what input is expected.
   * @return Returns a double that commonly depicts a quantity or price.
   */
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

  /**
   * <h5>Method</h5>
   * <h3>stringInput() - part 1</h3>
   * This is a function that asks for the user to a string,
   * then makes sure it is valid.
   * If it is not, it repeats itself until the user enters a valid string.
   *
   * @param minLength Lower limit of string length.
   * @param menu A string that is informative of what input is expected.
   * @return Returns a string that usually indicates a name.
   */
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

  /**
   * <h5>Method</h5>
   * <h3>stringInput() - part 2 (restrictions)</h3>
   * This is a function that asks for the user to input a double,
   * then makes sure it is a valid double (a value between <b>min and max</b>.
   * If it is not, it repeats itself until the user enters a valid double.<br>
   * <b><i>You can enter an ArrayList&lt String &gt with unavailable input.</i></b>
   *
   * @param minLength Lower limit of string length.
   * @param menu A string that is informative of what input is expected.
   * @param restrictions An ArrayList of strings to make unavailable.
   * @return Returns a string that usually indicates a name.
   */
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

  /**
   * <h5>Method</h5>
   * <h3>booleanInput()</h3>
   * Requests the user to input something that is either positive or negative.
   * If the user is not positive,
   * the program assumes the answer is negative (Silence means consent).
   *
   * @param request Informative string the user should answer to.
   * @return boolean value based on user input.
   */
  private boolean booleanInput(String request) {
    ArrayList<String> positive = new ArrayList<String>();
    positive.add("true");
    positive.add("1");
    positive.add("yes");
    positive.add("y");

    String stringInput;
    boolean output = false;
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

  /**
   * <h5>Method</h5>
   * <h3>ingredientsInput()</h3>
   * This method asks for all details of the ingredients (name, quantity, unit, allergies).
   * It will continue until the user are content with the ingredients in a recipe.
   *
   * @return ArrayList of ingredients.
   */
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

  /**
   * <h5>Method</h5>
   * <h3>isRestricted()</h3>
   * Checks if an inputted word is in the restricted array,
   * and if so, returns true.
   *
   * @param input String that is the user input.
   * @param restrictions List of restricted words.
   * @return True if word is restricted.
   */
  private boolean isRestricted(String input, ArrayList<String> restrictions) {
    return restrictions.stream()
        .noneMatch(str -> str.equalsIgnoreCase(input));
  }

  /**
   * <h5>Method</h5>
   * <h3>dateInput()</h3>
   * Asks the user for a valid date.
   * Repeats until satisfied.
   *
   * @param menu Informative string.
   * @return LocalDate of a given date.
   */
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
