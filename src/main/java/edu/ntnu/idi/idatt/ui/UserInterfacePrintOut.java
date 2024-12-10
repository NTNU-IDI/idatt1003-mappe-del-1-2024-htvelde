package edu.ntnu.idi.idatt.ui;

import edu.ntnu.idi.idatt.classes.foodstorage.Groceries;
import java.util.ArrayList;

/**
 * <h5>Class</h5>
 * <h3>UserInterfacePrintOut</h3>
 * A static collection of different print features.
 */
public class UserInterfacePrintOut {

  /**
   * <h5>Method</h5>
   * <h3>print()</h3>
   * The very same function as System.out.println, but just extracted here.
   */
  public static void print(String string) {
    System.out.println(string);
  }

  /**
   * <h5>Method</h5>
   * <h3>printMenu()</h3>
   * Prints a given menu String, followed by prompt.
   */
  public static void printMenu(String menu) {
    System.out.println(menu);
    prompt();
  }


  /**
   * <h5>Method</h5>
   * <h3>printUserInputError()</h3>
   * Print error from the user input.
   */
  public static void printUserInputError(String message) {
    System.out.println(message);
  }

  /**
   * <h5>Method</h5>
   * <h3>prompt()</h3>
   * Prints a prompt-string, indicating that the user should enter something.
   */
  public static void prompt() {
    System.out.print(">>> ");
  }

  /**
   * <h5>Method</h5>
   * <h3>printTotalValueOfGroceries()</h3>
   * Prints the value of all groceries in a given list with some nice text.
   */
  public static void printTotalValueOfGroceries(ArrayList<Groceries> groceries) {
    double totalValueOfGroceries = groceries.stream()
        .mapToDouble(Groceries::totalPrice).sum();
    System.out.println("Total value is " + totalValueOfGroceries + " kr.");
  }

  /**
   * <h5>Method</h5>
   * <h3>printSuggestedRecipe()</h3>
   * Print suggested recipes.
   */
  public static void printSuggestedRecipe(String recipeInfo, double portions) {
    System.out.println(recipeInfo
        + "\nThis recipe with the given ingredients can make " + portions + " portions.");
    newLine();
  }

  /**
   * <h5>Method</h5>
   * <h3>printArrayList()</h3>
   * Prints info about Groceries in an ArrayList.
   *
   * @param arrayList Array to print.
   */
  public static void printArrayList(ArrayList<Groceries> arrayList) {
    arrayList.forEach(m -> print(m.info()));
  }

  /**
   * <h5>Method</h5>
   * <h3>printArrayList()</h3>
   * Prints info about Groceries in an ArrayList.
   *
   * @param arrayList Array to print.
   * @param color Color to each item.
   */
  public static void printArrayList(ArrayList<Groceries> arrayList, String color) {
    arrayList.forEach(m -> print(color + m.info() + "\u001B[0m"));
  }

  /**
   * <h5>Method</h5>
   * <h3>newLine()</h3>
   * Prints a new, empty line.
   */
  public static void newLine() {
    System.out.println();
  }
}
