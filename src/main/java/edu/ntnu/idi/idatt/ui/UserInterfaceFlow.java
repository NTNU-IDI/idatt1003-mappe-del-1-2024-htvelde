package edu.ntnu.idi.idatt.ui;

import static edu.ntnu.idi.idatt.ui.UserInterfacePrintOut.*;
import static edu.ntnu.idi.idatt.ui.UserInterfaceTextSource.BLUE;
import static edu.ntnu.idi.idatt.ui.UserInterfaceTextSource.CYAN;
import static edu.ntnu.idi.idatt.ui.UserInterfaceTextSource.PURPLE;
import static edu.ntnu.idi.idatt.ui.UserInterfaceTextSource.RED;
import static edu.ntnu.idi.idatt.ui.UserInterfaceTextSource.RESET_COLOR;
import static edu.ntnu.idi.idatt.ui.UserInterfaceTextSource.allGroceriesStringHeader;
import static edu.ntnu.idi.idatt.ui.UserInterfaceTextSource.empty;
import static edu.ntnu.idi.idatt.ui.UserInterfaceTextSource.expired;
import static edu.ntnu.idi.idatt.ui.UserInterfaceTextSource.groceriesMenuText;
import static edu.ntnu.idi.idatt.ui.UserInterfaceTextSource.mainMenuText;
import static edu.ntnu.idi.idatt.ui.UserInterfaceTextSource.noSuggestions;
import static edu.ntnu.idi.idatt.ui.UserInterfaceTextSource.procedure;
import static edu.ntnu.idi.idatt.ui.UserInterfaceTextSource.recipesMenuText;
import static edu.ntnu.idi.idatt.ui.UserInterfaceTextSource.requestGroceryType;
import static edu.ntnu.idi.idatt.ui.UserInterfaceTextSource.requestPortionRemoval;
import static edu.ntnu.idi.idatt.ui.UserInterfaceTextSource.requestPrice;
import static edu.ntnu.idi.idatt.ui.UserInterfaceTextSource.requestQuantity;
import static edu.ntnu.idi.idatt.ui.UserInterfaceTextSource.requestRecipeDescription;
import static edu.ntnu.idi.idatt.ui.UserInterfaceTextSource.requestRecipeName;
import static edu.ntnu.idi.idatt.ui.UserInterfaceTextSource.requestUnit;
import static edu.ntnu.idi.idatt.ui.UserInterfaceTextSource.searchGroceryString;
import static edu.ntnu.idi.idatt.ui.UserInterfaceTextSource.searchRecipeString;
import static edu.ntnu.idi.idatt.ui.UserInterfaceTextSource.welcome;
import static edu.ntnu.idi.idatt.utils.ValidateInput.isValidByte;
import static edu.ntnu.idi.idatt.utils.ValidateInput.isValidDouble;
import static edu.ntnu.idi.idatt.utils.ValidateInput.isValidInt;
import static edu.ntnu.idi.idatt.utils.ConvertMeasurement.convertToStandardUnits;

import edu.ntnu.idi.idatt.classes.foodstorage.FoodStorage;
import edu.ntnu.idi.idatt.classes.foodstorage.Groceries;
import edu.ntnu.idi.idatt.classes.foodstorage.Grocery;
import edu.ntnu.idi.idatt.classes.recipe.CookBook;
import edu.ntnu.idi.idatt.classes.recipe.Ingredient;
import edu.ntnu.idi.idatt.classes.recipe.Recipe;
import edu.ntnu.idi.idatt.classes.recipe.SuggestedRecipes;
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
  private FoodStorage foodStorage;
  private CookBook cookBook;
  private UserInput input;

  /**
   * <h3>setCookBook()</h3>
   * Sets the input-field to an instance of FoodStorage class.
   *
   * @param foodStorage FoodStorage that stores groceries.
   */
  private void setFoodStorage(FoodStorage foodStorage) {
    this.foodStorage = foodStorage;
  }

  /**
   * <h3>setCookBook()</h3>
   * Sets the input-field to an instance of CookBook class.
   *
   * @param cookBook CookBook that stores recipes.
   */
  private void setCookBook(CookBook cookBook) {
    this.cookBook = cookBook;
  }

  /**
   * <h3>setInput()</h3>
   * Sets the input-field to an instance of UserInput class.
   *
   * @param input UserInput takes input from user.
   */
  protected void setInput(UserInput input) {
    this.input = input;
  }

  /**
   * <h3>init()</h3>
   * Initialize the UI with a FoodStorage to handle all user input.
   */
  public void init() {
    // Initialize the FoodStorage-class, and the other UI-classes
    setFoodStorage(new FoodStorage(true));
    setCookBook(new CookBook());
    setInput(new UserInput());
    print(welcome);
  }

  /**
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
   * <h3>MainMenu()</h3>
   * The mainMenu dictates the flow of the program with user input.
   * All inputs are required to be bytes.
   * It is meant to repeat until user wants to exit, which is indicated with a boolean.
   *
   * @return returns a boolean to indicate whether the function should repeat or not.
   */
  public boolean mainMenu() {
    final byte max = 2;
    byte userInput = byteInput(max, mainMenuText);
    boolean repeat = true;

    switch (userInput) {
      case 1 -> groceriesMenu();
      case 2 -> recipesMenu();
      default -> repeat = false;
    }

    return repeat;
  }

  /**
   * <h3>groceriesMenu()</h3>
   * Creates a menu for the actions for groceries.
   * Each input are bytes.<br>
   * The method differs from main menu,
   * by having the loop locally.
   */
  public void groceriesMenu() {
    final byte max = 7;

    boolean repeat = true;
    while (repeat) {
      byte userInput = byteInput(max, groceriesMenuText);

      switch (userInput) {
        case 1 -> addGrocery();
        case 2 -> userRemoveGrocery();
        case 3 -> showAllGroceries();
        case 4 -> searchGrocery();
        case 5 -> searchExpirationDate();
        case 6 -> expiredGroceries();
        case 7 -> showTotalValueOfGroceries();
        default -> repeat = false;
      }
    }
  }

  /**
   * <h3>recipesMenu()</h3>
   * Prints name of different recipes that you know.<br>
   * The method differs from main menu,
   * by having the loop locally.
   */
  public void recipesMenu() {
    final byte max = 6;

    boolean repeat = true;
    while (repeat) {
      byte userInput = byteInput(max, recipesMenuText);

      switch (userInput) {
        case 1 -> addRecipe();
        case 2 -> removeRecipe();
        case 3 -> viewRecipes();
        case 4 -> searchRecipe();
        case 5 -> suggestedRecipe();
        case 6 -> useRecipe();
        default -> repeat = false;
      }
    }
  }

  /**
   * <h3>searchGrocery()</h3>
   * Searches and prints all groceries the user can search for.
   */
  public void searchGrocery() {
    newLine();

    if (foodStorage.getStorage().isEmpty()) {
      print(empty);
      return;
    }

    // Builds a string-block from the groceries in the food storage
    String searchOptions = searchGroceryString(foodStorage);

    print(BLUE);
    print(searchOptions);
    print(RESET_COLOR);
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

    newLine();
  }

  /**
   * <h3>addRecipe()</h3>
   * Recipes aren't made by themselves, someone must make them.
   * But you are in luck, some delicious <i>desert pancakes</i> are included!
   */
  private void addRecipe() {
    String name = stringInput(2, requestRecipeName);
    String description = stringInput(2, requestRecipeDescription);
    ArrayList<Ingredient> ingredients = ingredientsInput();
    int portions = intInput();
    String procedureString = stringInput(2, procedure);

    Recipe recipe = new Recipe(name, description, ingredients, procedureString, portions);
    cookBook.addRecipe(recipe);
  }

  /**
   * <h3>removeRecipe()</h3>
   * Removes a recipe by asking for the name of recipe to remove.
   */
  private void removeRecipe() {
    viewRecipes();
    String recipeName = stringInput(2, requestGroceryType);

    int result = cookBook.searchRecipesIndex(recipeName);
    if (result == -1) {
      return;
    }
    cookBook.removeRecipe(cookBook.getRecipes().get(result));
    newLine();
  }

  /**
   * <h3>viewRecipes()</h3>
   * Prints a list of all searchable recipes' names.
   */
  private void viewRecipes() {
    if (cookBook.getRecipes().isEmpty()) {
      print(empty);
      return;
    }

    cookBook.getRecipes().forEach(recipe -> print(recipe.getName()));
  }

  /**
   * <h3>searchRecipes()</h3>
   * Searches for a recipe.
   * User enters a string that is the name of a recipe.
   * If found, the recipe is printed.
   */
  private void searchRecipe() {
    newLine();

    if (cookBook.getRecipes().isEmpty()) {
      print(empty);
      return;
    }

    String searchOptions = searchRecipeString(cookBook);
    print(searchOptions);

    String searchString = input.inputString(2);

    if (searchString.equalsIgnoreCase("exit")) {
      return;
    }

    int searchResult = cookBook.searchRecipesIndex(searchString);
    if (searchResult == -1) {
      searchRecipe();
    }

    print(cookBook.getRecipes().get(searchResult).info());
  }

  /**
   * <h3>suggestedRecipe()</h3>
   * Shows the names of all recipes that you can make. The method is not case sensitive.<br>
   *
   * <b><i>Mark that all ingredients must be spelled like its counterpart!</i></b>
   */
  private void suggestedRecipe() {
    if (cookBook.getRecipes().isEmpty()
        || foodStorage.getStorage().isEmpty()
        || cookBook.getRecipes().size() >= foodStorage.getStorage().size()) {
      print(noSuggestions);
      return;
    }

    // Eliminate candidates that have not met the sublist condition.
    // Disclaimer: I don't like to nest 3 loops,
    // but I couldn't figure out a better way to omit them.
    ArrayList<SuggestedRecipes> sublistOfRecipes = new ArrayList<>();
    ArrayList<Groceries> apparentGroceries;
    for (Recipe recipe : cookBook.getRecipes()) {
      apparentGroceries = new ArrayList<>();
      for (Groceries grocery : foodStorage.getStorage()) {
        for (Ingredient ingredient : recipe.getIngredients()) {
          if (ingredient.getName().equalsIgnoreCase(grocery.getGroceryName())) {
            apparentGroceries.add(grocery);
          }
        }
      }

      if (apparentGroceries.size() >= recipe.getIngredients().size()) {
        SuggestedRecipes suggestion = new SuggestedRecipes(recipe, apparentGroceries);
        if (suggestion.getPortions() >= 1) {
          sublistOfRecipes.add(suggestion);
        }
      }
    }

    // Determine how many portions you could make.
    print(BLUE);
    for (SuggestedRecipes suggestedRecipe : sublistOfRecipes) {
      printSuggestedRecipe(suggestedRecipe.recipeInfo(), suggestedRecipe.getPortions());
    }
    print(RESET_COLOR);
  }

  private void useRecipe() {
    String recipeName = stringInput(2, requestRecipeName);
    double portions = doubleInput(requestPortionRemoval);

    for (Ingredient ingredient : cookBook.searchRecipes(recipeName).getIngredients()) {
      double portionToRemove = convertToStandardUnits(portions, ingredient.getUnit());
      removeGrocery(ingredient.getName(), portionToRemove);
    }
  }

  /**
   *
   * <h3>addGrocery()</h3>
   * User inputs details on a grocery to add it to the storage.
   * A suggestion is to keep all names singular, to avoid duplicates.
   */
  private void addGrocery() {
    ArrayList<String> restrictions = new ArrayList<>();
    restrictions.add("exit");

    newLine();
    String typeName = stringInput(requestGroceryType, restrictions);
    double quantity = doubleInput(requestQuantity);
    String unit = stringInput(requestUnit, restrictions);
    double price = doubleInput(requestPrice);
    LocalDate date = dateInput();

    foodStorage.addToGroceries(typeName, unit, quantity, date, price);

    newLine();
  }

  /**
   * <h3>userRemoveGrocery()</h3>
   * Removes a grocery from foodStorage based on name and quantity.
   * The user specifies which grocery to remove.
   * Grocery deletes when more is removed than possible.
   */
  private void userRemoveGrocery() {
    newLine();

    String typeName = stringInput(2, requestGroceryType);
    double quantity = doubleInput(requestQuantity);
    String unit = stringInput(1, requestUnit);
    quantity = convertToStandardUnits(quantity, unit);

    removeGrocery(typeName, quantity);
  }

  /**
   * <h3>removeGrocery()</h3>
   * Removes a grocery from foodStorage based on name and quantity.
   * Grocery deletes when more is removed than possible.
   */
  private void removeGrocery(String groceryName, double quantity) {
    int result = foodStorage.searchGroceries(groceryName);
    if (result == -1) {
      return;
    }

    foodStorage.getStorage().get(result).removeGrocery(quantity);
  }

  /**
   * <h3>showAllGroceries()</h3>
   * Prints a nice overview of what is in the foodStorage.
   */
  private void showAllGroceries() {
    newLine();

    if (foodStorage.getStorage().isEmpty()) {
      print(empty);
      return;
    }
    print(allGroceriesStringHeader());
    printArrayList(foodStorage.getStorage(), BLUE);
    newLine();
  }

  /**
   * <h3>expiredGroceries()</h3>
   * Prints all groceries that have expired.
   * To ease development, all groceries of an expired type
   * are shown, despite not necessarily being expired.
   */
  private void expiredGroceries() {
    newLine();

    print(RED);
    print(expired);
    print(allGroceriesStringHeader());
    printArrayList(foodStorage.getExpired(), RED);
    showTotalValueOfGroceries(foodStorage.getExpired());
    print(RESET_COLOR);
    newLine();
  }

  /**
   * <h3>expiredGroceries()</h3>
   * Prints all groceries that have expired.
   * To ease development, all groceries of an expired type
   * are shown, despite not necessarily being expired.
   */
  private void expiredGroceries(LocalDate searchDate) {
    newLine();

    print(PURPLE);
    print(expired);
    print(allGroceriesStringHeader());
    printArrayList(foodStorage.getExpired(searchDate), PURPLE);
    showTotalValueOfGroceries(foodStorage.getExpired(searchDate));
    print(RESET_COLOR);
    newLine();
  }

  private void searchExpirationDate() {
    LocalDate searchDate = dateInput();
    expiredGroceries(searchDate);
  }

  /**
   * <h3>showTotalValueOfGroceries(ArrayList groceries)</h3>
   * Show total value of all groceries in an ArrayList.
   *
   * @param groceries ArrayList with all groceries.
   */
  private void showTotalValueOfGroceries(ArrayList<Groceries> groceries) {
    newLine();
    printTotalValueOfGroceries(groceries);
    newLine();
  }

  /**
   * <h3>showTotalValueOfGroceries()</h3>
   * Show total value of all groceries stored in the food storage.
   */
  private void showTotalValueOfGroceries() {
    newLine();
    print(CYAN);
    printTotalValueOfGroceries(foodStorage.getStorage());
    print(RESET_COLOR);
    newLine();
  }

  /**
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
        printUserInputError("Number not in valid range [0-" + max + "]");
      } catch (InputMismatchException e) {
        printUserInputError("Not a valid number");
      }
    }
    return userInput;
  }

  /**
   * <h3>intInput()</h3>
   * This is a function that asks for the user to input an int,
   * then makes sure it is a valid int (a value between <b>0 and max</b>.
   * If it is not, it repeats itself until the user enters a valid int.
   *
   * @return Returns a valid int.
   */
  private int intInput() {
    int userInput = 0;

    boolean askAgain = true;
    while (askAgain) {
      printMenu(UserInterfaceTextSource.requestRecipePortionSize);
      try {
        userInput = input.inputInt();
        if (!isValidInt(1, 1_000_000_000, userInput)) {
          throw new IllegalArgumentException();
        }
        askAgain = false;
      } catch (IllegalArgumentException e) {
        printUserInputError("Number not in valid range [1-" + 1000 + "]");
      } catch (InputMismatchException e) {
        printUserInputError("Not a valid number");
      }
    }
    return userInput;
  }

  /**
   * <h3>doubleInput(String menu)</h3>
   * Method asks for the user to input a double,
   * then makes sure it is a valid double (a value between <b>0 and 1_000_000_000</b>.
   * If it is not, it repeats itself until the user enters a valid double.<br>
   * <b><i>Notice that the decimal point is "," and not ".".</i></b>
   *
   * @param menu A string that is informative of what input is expected.
   * @return Returns a double that commonly depicts a quantity or price.
   */
  private double doubleInput(String menu) {
    double userInput = 0;

    boolean askAgain = true;
    while (askAgain) {
      printMenu(menu);
      try {
        userInput = input.inputDouble();
        if (!isValidDouble(0, 1_000_000_000, userInput)) {
          throw new IllegalArgumentException();
        }
        askAgain = false;
      } catch (IllegalArgumentException e) {
        printUserInputError("Number not in valid range ["
            + (double) 0 + "-" + (double) 1_000_000_000
            + "]");
      } catch (InputMismatchException e) {
        printUserInputError("Not a valid number");
      }
    }
    return userInput;
  }

  /**
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
   * <h3>stringInput() w. (restrictions)</h3>
   * This is a function that asks for the user to input a double,
   * then makes sure it is a valid double (a value between <b>min and max</b>.
   * If it is not, it repeats itself until the user enters a valid double.<br>
   * <b><i>You can enter an ArrayList&lt String &gt with unavailable input.</i></b>
   *
   * @param menu         A string that is informative of what input is expected.
   * @param restrictions An ArrayList of strings to make unavailable.
   * @return Returns a string that usually indicates a name.
   */
  private String stringInput(String menu, ArrayList<String> restrictions) {
    String stringOut = "";
    boolean askAgain = true;
    while (askAgain) {
      printMenu(menu);
      try {
        stringOut = input.inputString(1);
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
   * <h3>booleanInput()</h3>
   * Requests the user to input something that is either positive or negative.
   * If the user is not positive,
   * the program assumes the answer is negative (Silence means consent).
   *
   * @param request Informative string the user should answer to.
   * @return boolean value based on user input.
   */
  private boolean booleanInput(String request) {
    ArrayList<String> positive = new ArrayList<>();
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
      } catch (IllegalArgumentException e) {
        printUserInputError(e.getMessage());
      }
    }
    return output;
  }

  /**
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
      double quantity = doubleInput(requestQuantity);
      String unit = stringInput(1, "Ingredient unit");
      boolean allergies = booleanInput("Allergic");
      askAgain = booleanInput("More ingredients?");

      Ingredient ingredient = new Ingredient(name, quantity, unit, allergies);
      ingredients.add(ingredient);
    }
    return ingredients;
  }

  /**
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
   * <h3>dateInput()</h3>
   * Asks the user for a valid date.
   * Repeats until satisfied.
   *
   * @return LocalDate of a given date.
   */
  private LocalDate dateInput() {
    LocalDate userInput = LocalDate.now();

    boolean askAgain = true;
    while (askAgain) {
      printMenu(UserInterfaceTextSource.requestDate);
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
