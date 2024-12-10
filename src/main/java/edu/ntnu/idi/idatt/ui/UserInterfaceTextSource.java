package edu.ntnu.idi.idatt.ui;

import edu.ntnu.idi.idatt.classes.foodstorage.FoodStorage;
import edu.ntnu.idi.idatt.classes.recipe.CookBook;

/**
 * <h5>Class</h5>
 * <h3>UserInterFaceTextSource</h3>
 * Most Strings are gathered here,
 * the exceptions are few but info-methods.
 */
public class UserInterfaceTextSource {
  public static final String RESET_COLOR = "\u001B[0m";
  public static final String RED = "\u001B[31m";
  public static final String BLUE = "\u001B[34m";
  public static final String PURPLE = "\u001B[35m";
  public static final String CYAN = "\u001B[36m";

  public static final String welcome = """
      Hello!
      Welcome to your new favorite food-application!
      What do you want to do today?
      """;

  public static final String noSuggestions;
  public static final String expired;
  public static final String requestGroceryType;
  public static final String requestUnit;
  public static final String requestQuantity;
  public static final String requestDate;
  public static final String requestPrice;
  public static final String requestRecipeName;
  public static final String requestRecipeDescription;
  public static final String requestRecipePortionSize;
  public static final String requestPortionRemoval;
  public static final String procedure;
  public static final String empty;

  static {
    noSuggestions = "There are no recipes you can make with what you have.";
    expired = "All products that have expired";
    procedure = "Describe the procedure";
    empty = "It's empty, here is nothing to show.";
    requestGroceryType = "Enter the "
        + CYAN + "NAME" + RESET_COLOR + " of the product";
    requestUnit = "Enter the "
        + CYAN + "UNIT" + RESET_COLOR + " of measurement of the product";
    requestQuantity = "Enter the "
        + CYAN + "QUANTITY" + RESET_COLOR + " of the product";
    requestDate = "Enter the "
        + CYAN + "EXPIRATION DATE" + RESET_COLOR + " of the product";
    requestPrice = "Enter the "
        + CYAN + "PRICE" + RESET_COLOR + " of the product";
    requestRecipeName = "Enter the "
        + CYAN + "NAME" + RESET_COLOR + " of the recipe";
    requestRecipeDescription = "Enter the "
        + CYAN + "DESCRIPTION" + RESET_COLOR + " of your recipe";
    requestRecipePortionSize = "Enter the number of "
        + CYAN + "PORTIONS" + RESET_COLOR + " this recipe makes";
    requestPortionRemoval = "Enter the number of "
        + CYAN + "PORTIONS" + RESET_COLOR + " you made";
  }


  public static final String mainMenuText = """
      [1] Access the food storage
      [2] Access recipes
      [0] Exit\r
      """;

  public static final String groceriesMenuText = """
      [1] Add grocery
      [2] Remove grocery
      [3] View all stored groceries
      [4] Search grocery
      [5] Search expiration date for groceries
      [6] View expired groceries
      [7] Get total value of food storage
      [0] Exit to main menu\r
      """;

  public static final String recipesMenuText = """
      [1] Add recipe to cook book
      [2] Remove recipe from cook book
      [3] View all recipes
      [4] Search for a recipe
      [5] View suggested recipes
      [6] Use a recipe
      [0] Exit to main menu\r
      """;


  /**
   * <h5>Method</h5>
   * <h3>SearchGroceryString()</h3>
   * Text giving search options depending on what is in the storage.
   *
   * @param foodStorage FoodStorage that is searched.
   * @return String with names of options.
   */
  public static String searchGroceryString(FoodStorage foodStorage) {
    String searchText = "Enter the name of a specific grocery";
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(searchText);
    stringBuilder.append("\n");
    for (int i = 0; i < foodStorage.getStorage().size(); i++) {
      stringBuilder.append(foodStorage.getStorage().get(i).getGroceryName());
      stringBuilder.append("\n");
    }
    stringBuilder.append("Enter [exit] to exit");

    return stringBuilder.toString();
  }

  /**
   * <h5>Method</h5>
   * <h3>searchRecipeString()</h3>
   * Creates a String with search options from the Cook Book.
   *
   * @param cookBook Gives search alternatives.
   * @return Text with search alternatives.
   */
  public static String searchRecipeString(CookBook cookBook) {
    String searchText = "Enter the name of a specific recipe";
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(searchText);
    stringBuilder.append("\n");
    for (int i = 0; i < cookBook.getRecipes().size(); i++) {
      stringBuilder.append(cookBook.getRecipes().get(i).getName());
      stringBuilder.append("\n");
    }
    stringBuilder.append("Enter [exit] to exit");

    return stringBuilder.toString();
  }

  /**
   * <h5>Method</h5>
   * <h3>allGroceriesStringHeader()</h3>
   * Gives the header for when all the groceries are listed.
   *
   * @return String that shows name of each column.
   */
  public static String allGroceriesStringHeader() {
    String name = String.format("%-32s", "Item name");
    String unit = String.format("%-3s", "Unit");
    String price = String.format("%-7s", "Price");
    String quantity = String.format("%-5s", "Qty.");
    String expiration = "Expiration date";

    String header = name + " | "  + quantity + unit + " | " + price + " kr | " + expiration + "\n";

    // In-the-moment trick to not surpass the col:100-rule
    String firstPart = "-".repeat(33) + "+";
    String secondPart = "-".repeat(11) + "+";
    String thirdPart = "-".repeat(12) + "+";
    String lastPart = "-".repeat(17);
    return header + firstPart + secondPart + thirdPart + lastPart;
  }
}
