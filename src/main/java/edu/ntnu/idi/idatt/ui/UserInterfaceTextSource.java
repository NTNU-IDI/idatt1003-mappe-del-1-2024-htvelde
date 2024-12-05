package edu.ntnu.idi.idatt.ui;

import edu.ntnu.idi.idatt.classes.foodStorage.FoodStorage;

public class UserInterfaceTextSource {
  private static final String RESET_COLOR = "\u001B[0m";
  // private static final String BLACK = "\u001B[30m";
  private static final String RED = "\u001B[31m";
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
      [5] Exit to main menu\r
      """;
  }

  /**
   * Creates a menu for what you want to do with the recipes.
   * You can choose between read recipe, use recipe or change recipes.
   */
  public static String recipesMenuString() {
    return "Options for recipes";
  }

  public static String searchOptionsString(FoodStorage foodStorage) {
    String searchText = "You may enter the name of a specific grocery";
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

  public static String allGroceriesString(FoodStorage foodStorage) {

    // In-the-moment trick to not surpass the col:100-rule
    String firstPart = "-".repeat(33) + "+";
    String secondPart = "-".repeat(11) + "+";
    String thirdPart = "-".repeat(12) + "+";
    String lastPart = "-".repeat(17);
    return firstPart + secondPart + thirdPart + lastPart;

    // TODO: Java-streams to print ALL GROCERIES
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

  public static String removeGroceryInformation() {
    String name = CYAN + "NAME" + RESET_COLOR;
    String quantity = CYAN + "QUANTITY" + RESET_COLOR;
    return "Enter the " + name + " and then " + quantity + " of the product you wish to remove";
  }
}
