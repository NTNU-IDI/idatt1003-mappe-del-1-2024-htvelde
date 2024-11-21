package edu.ntnu.idi.idatt.UI;

import edu.ntnu.idi.idatt.Classes.FoodStorage.FoodStorage;
import edu.ntnu.idi.idatt.Classes.FoodStorage.Groceries;
import edu.ntnu.idi.idatt.Classes.FoodStorage.Grocery;

import java.util.Scanner;

/**
 * All commands and instructions are handled within this class.
 */
public class UI {
  Scanner scanner;
  FoodStorage foodStorage;
  int userInput;

  /**
   * Initialize the UI with a FoodStorage to handle all user input.
   */
  public void init() {
    new Scanner(System.in);
    this.foodStorage = new FoodStorage();
    welcome();
  }

  public void start() {
    printMenu();
  }

  /**
   * This prints out a nice welcoming message to indicate the program is ready.
   */
  public void welcome() {
    System.out.println("Welcome to your new favorite food-application!");
    System.out.println("What do you want to do today?\n");
  }

  /**
   * The function checks if userInput is between 1 and upperLimit,
   * and if that is the case and assigns the global variable userInput
   * the entered in value.
   *
   * @param upperLimit - Defines the highest valid search value.
   * @return boolean that indicates whether the input is valid or not.
   */
  private boolean handleUserInputMenu(int upperLimit) {
    try {
      userInput = Integer.parseInt(scanner.next());
      if (userInput <= 0 || userInput > upperLimit) {
        return true;
      }
    } catch (IllegalArgumentException e) {
      return true;
    }
    return false;
  }

  /**
   * Prints a main menu with the available actions.
   * The user is also taken here after exiting a deeper layer.
   */
  public void printMenu() {
    String menuString = """
        [1] Look at the storage
        [2] Add grocery to the storage
        [3] Remove grocery from the storage
        [4] Search for one specific grocery
        [5] View recipes
        [6] Use or change recipes
        [7] Exit
        >>>\s""";
    System.out.print(menuString);

    mainLoop: while ((scanner.hasNext())) {
      System.out.println("\nPlease select one of the available actions [1-5]:");

      // Handle the exception by re-running the loop until input is correct.
      if (handleUserInputMenu(7)) {
        System.out.println(menuString);
        continue;
      }

      switch (userInput) {
        case 1 -> printGroceries();
        case 2 -> addGrocery();
        case 3 -> removeGrocery();
        case 4 -> searchGrocery();
        case 5 -> printRecipies();
        case 6 -> printRecipiesMenu();
        case 7 -> {
          break mainLoop;
        }
        default -> throw new IllegalArgumentException("Unexpected value: " + userInput);
      }

      scanner.nextLine();
      System.out.print(menuString);
    }

    scanner.close();
  }

  /**
   * Prints a list of all groceries.
   */
  public void printGroceries() {
    // In-the-moment trick to not surpass the col:100-rule
    String[] h = {"Food Name", "Quantity", "Price", "Expiration Date"};
    System.out.printf("%-32s | %-9s | %-1s | %s\n", h[0], h[1], h[3], h[3]);
    String firstPart = "-".repeat(33) + "+";
    String secondPart = "-".repeat(11) + "+";
    String thirdPart = "-".repeat(12) + "+";
    String lastPart = "-".repeat(17);
    System.out.println(firstPart + secondPart + thirdPart + lastPart);

    for (Groceries groceries : foodStorage.getStorage()) {
      System.out.println(groceries);
    }

    printGroceriesMenu();
  }

  /**
   * Creates a menu for the actions for groceries.
   */
  public void printGroceriesMenu() {
    String groceriesMenu = """
        
        [1] Add grocery to food storage
        [2] Remove grocery from food storage
        [3] Search for a grocery from food storage
        [4] View expired groceries from food storage
        [5] Exit to main menu
        """;

    System.out.println(groceriesMenu);


    groceriesLoop: while (scanner.hasNext()) {
      if (handleUserInputMenu(5)) {
        continue;
      }

      switch (userInput) {
        case 1 -> addGrocery();
        case 2 -> removeGrocery();
        case 3 -> searchGrocery();
        case 4 -> expiredGroceries();
        default -> {
          System.out.println("Returns to main menu.");
          break groceriesLoop;
        }
      }

      scanner.nextLine();
      System.out.println(groceriesMenu);
    }

    foodStorage.test();
    System.out.println("Storage updated!");
  }

  /**
   * Prints all the groceries of a specified type.
   */
  public void searchGrocery() {

    // Builds a string-block from the groceries in the food storage
    String searchOptions = searchOptionsString();

    System.out.println(searchOptions);
    while (scanner.hasNext()) {
      String searchString = scanner.nextLine();
      scanner.next();

      // TODO: change for regex
      if (searchString.equalsIgnoreCase("exit")) {
        break;
      }

      int searchResult = foodStorage.searchGroceries(searchString.toLowerCase());
      if (searchResult == -1) {
        System.out.println(searchOptions);
        continue;
      }

      for (Grocery grocery : foodStorage.getStorage().get(searchResult).getGroceries()) {
        System.out.println(grocery.toString());
      }

      System.out.println(searchOptions);
    }
  }

  private String searchOptionsString() {
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


  /**
   * Prints name of different recipies that you know.
   */
  public void printRecipies() {
    System.out.println("Recipes");
    printRecipiesMenu();
  }

  /**
   * Creates a menu for what you want to do with the recipes.
   * You can choose between read recipe, use recipe or change recipes.
   */
  public void printRecipiesMenu() {
    System.out.println("Options for recipes");
  }

  private void addGrocery() {
    System.out.println("Added groceries");
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
