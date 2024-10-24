package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is THE STORAGE.
 * This is mainly a list of all owned items,
 * and works as the interface between users and the groceries.
 */
public class FoodStorage {
  ArrayList<GroceriesList> storage;

  public FoodStorage() {
  }

  public void menu() {
    // TODO: make a nice menu
    Scanner scanner = new Scanner(System.in);
    mainMenu: while (scanner.hasNextInt()) {
      System.out.println("1. Add Groceries");
      System.out.println("2. Delete Groceries");
      System.out.println("3. Quit application");
      int choice = scanner.nextInt();
      switch (choice) {
        case 1 -> System.out.println("Case 1");
        case 2 -> System.out.println("Case 2");
        case 3 -> {
          break mainMenu;
        }
        default -> System.out.println("Invalid choice");
      }
    }
  }

  private void prettyPrint() {
    int longestName;
    int longestId;
    int longestUnit;
    int longestPrice;
    int longestExpiryDate;

    for (GroceriesList groceryItem : storage) {
      groceryItem.toString();
    }
  }

  private void checkForNewGroceryType(String strintInput) {
    for (GroceriesList groceryItem : storage) {
      if (groceryItem.getGroceryName().equals(strintInput)) {}
    }
  }
}
