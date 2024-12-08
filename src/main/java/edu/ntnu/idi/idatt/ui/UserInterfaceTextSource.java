package edu.ntnu.idi.idatt.ui;

import edu.ntnu.idi.idatt.classes.foodstorage.FoodStorage;
import edu.ntnu.idi.idatt.classes.recipe.CookBook;

public class UserInterfaceTextSource {
  static final String RESET_COLOR = "\u001B[0m";
  // private static final String BLACK = "\u001B[30m";
  static final String RED = "\u001B[31m";
  private static final String GREEN = "\u001B[32m";
  // private static final String YELLOW = "\u001B[33m";
  // private static final String BLUE = "\u001B[34m";
  // private static final String PURPLE = "\u001B[35m";
  private static final String CYAN = "\u001B[36m";
  // private static final String WHITE = "\u001B[37m";

  public static String prompt() {
    return ">>> ";
  }

  public static String welcome() {
    return """
    Hello!
    Welcome to your new favorite food-application!
    What do you want to do today?
    """;
  }

  /**
   * Prints a main menu with the available actions.
   * The user is also taken here after exiting a deeper layer.
   */
  public static String mainMenuText() {
    return """
      [1] Access the storage
      [2] Add grocery to the storage
      [3] Remove grocery from the storage
      [4] Search for one specific grocery
      [5] View recipes
      [6] Exit\r
      """;
  }

  public static String groceriesMenuText() {
    return """
      [1] Add grocery to food storage
      [2] Remove grocery from food storage
      [3] Search for a grocery from food storage
      [4] View expired groceries from food storage
      [5] View all stored groceries
      [6] Exit to main menu\r
      """;
  }

  /**
   * Creates a menu for what you want to do with the recipes.
   * You can choose between read recipe, use recipe or change recipes.
   */
  public static String recipesMenuText() {
    return """
      [1] Add recipe to cook book
      [2] Remove recipe from cook book
      [3] View all recipes
      [4] Search for a recipe
      [5] View suggested recipes
      [6] Exit to main menu\r
      """;
  }

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

  public static String allGroceriesString() {
    String name = String.format("%-32s", "Item name");
    String unit = String.format("%-3s", "Unit");
    String price = String.format("%-7s", "Price");
    String quantity = String.format("%-5s", "Qty.");;


    String expiration = "Expiration date";

    String header = name + " | "  + quantity + unit + " | " + price + " kr | " + expiration + "\n";

    // In-the-moment trick to not surpass the col:100-rule
    String firstPart = "-".repeat(33) + "+";
    String secondPart = "-".repeat(11) + "+";
    String thirdPart = "-".repeat(12) + "+";
    String lastPart = "-".repeat(17);
    return header + firstPart + secondPart + thirdPart + lastPart;
  }

  public static String expired() {
    return "All products that have expired";
  }

  public static String requestGroceryType() {
    String name = CYAN + "NAME" + RESET_COLOR;
    return "Enter the " + name + " of the product";
  }

  public static String requestUnit() {
    String unit = CYAN + "UNIT" + RESET_COLOR;
    return "Enter the " + unit + " of measurement of the product";
  }

  public static String requestQuantity() {
    String quantity = CYAN + "QUANTITY" + RESET_COLOR;
    return "Enter the " + quantity + " of the product";
  }

  public static String requestDate() {
    String expirationDate = CYAN + "EXPIRATION DATE" + RESET_COLOR;
    return "Enter the " + expirationDate + " of the product";
  }

  public static String requestPrice() {
    String price = CYAN + "PRICE" + RESET_COLOR;
    return "Enter the " + price + " of the product";
  }

  public static String requestSearchName() {
    String name = CYAN + "NAME" + RESET_COLOR;
    return "Enter the " + name + " of the product you search for";
  }

  public static String requestRecipeName() {
    String name = CYAN + "NAME" + RESET_COLOR;
    return "Enter the " + name + " of the recipe";
  }

  public static String requestRecipeDescription() {
    String descriptiom = CYAN + "DESCRIPTION" + RESET_COLOR;
    return "Enter the " + descriptiom + " of your recipe";
  }

  public static String requestRecipePortionSize() {
    String portions = CYAN + "PORTIONS" + RESET_COLOR;
    return "Enter the number of " + portions + " this recipe makes";
  }

  public static String removeGroceryInformation() {
    String name = CYAN + "NAME" + RESET_COLOR;
    String quantity = CYAN + "QUANTITY" + RESET_COLOR;
    return "Enter the " + name + " and then " + quantity + " of the product you wish to remove";
  }
}
