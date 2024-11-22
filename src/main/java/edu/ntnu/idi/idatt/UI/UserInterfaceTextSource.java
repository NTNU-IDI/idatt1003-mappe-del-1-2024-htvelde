package edu.ntnu.idi.idatt.UI;

import edu.ntnu.idi.idatt.Classes.FoodStorage.Groceries;
import edu.ntnu.idi.idatt.Classes.FoodStorage.FoodStorage;

public class UserInterfaceTextSource {
  private static final String RESET_COLOR = "\u001B[0m";
  private static final String BLACK = "\u001B[30m";
  private static final String RED = "\u001B[31m";
  private static final String GREEN = "\u001B[32m";
  private static final String YELLOW = "\u001B[33m";
  private static final String BLUE = "\u001B[34m";
  private static final String PURPLE = "\u001B[35m";
  private static final String CYAN = "\u001B[36m";
  private static final String WHITE = "\u001B[37m";

  public static void clearConsole() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static void prompt() {
    System.out.print(">>> ");
  }

  public static void welcome() {
    System.out.println("""
    Hello!
    Welcome to your new favorite food-application!
    What do you want to do today?
    """);
  }

  /**
   * Prints a main menu with the available actions.
   * The user is also taken here after exiting a deeper layer.
   */
  public static void printMainMenu() {
    clearConsole();

    String menuString = """
      [1] Access the storage
      [2] Add grocery to the storage
      [3] Remove grocery from the storage
      [4] Search for one specific grocery
      [5] View recipes
      [6] Use or change recipes
      [7] Exit
      """;

    System.out.println(menuString);
    prompt();
  }

  public static void printGroceriesMenu() {
    clearConsole();

    String groceriesMenu = """
      [1] Add grocery to food storage
      [2] Remove grocery from food storage
      [3] Search for a grocery from food storage
      [4] View expired groceries from food storage
      [5] Exit to main menu
      """;

    System.out.println(groceriesMenu);
    prompt();
  }

  /**
   * Creates a menu for what you want to do with the recipes.
   * You can choose between read recipe, use recipe or change recipes.
   */
  public static void printRecipesMenu() {
    System.out.println("Options for recipes");
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

  public static void printAllGroceries(FoodStorage foodStorage) {

    // In-the-moment trick to not surpass the col:100-rule
    String[] h = {"Food Name", "Quantity", "Price", "Expiration Date"};
    System.out.printf("%-32s | %-9s | %-1s | %s\n", h[0], h[1], h[3], h[3]);
    String firstPart = "-".repeat(33) + "+";
    String secondPart = "-".repeat(11) + "+";
    String thirdPart = "-".repeat(12) + "+";
    String lastPart = "-".repeat(17);
    System.out.println(firstPart + secondPart + thirdPart + lastPart);

    // TODO: Java-streams to print ALL GROCERIES
  }

  public static void requestGroceryType() {
    String name = CYAN + "NAME" + RESET_COLOR;
    System.out.println("Enter the " + name + " of the product");
    prompt();
  }

  public static void requestUnit() {
    String unit = CYAN + "UNIT" + RESET_COLOR;
    System.out.println("Enter the " + unit + " of measurement of the product");
    prompt();
  }

  public static void requestQuantity() {
    String quantity = CYAN + "QUANTITY" + RESET_COLOR;
    System.out.println("Enter the " + quantity + " of the product");
    prompt();
  }

  public static void requestDate() {
    String expirationDate = CYAN + "EXPIRATION DATE" + RESET_COLOR;
    System.out.println("Enter the " + expirationDate + " of the product");
    prompt();
  }

  public static void requestPrice() {
    String price = CYAN + "PRICE" + RESET_COLOR;
    System.out.println("Enter the " + price + " of the product");
    prompt();
  }
}
