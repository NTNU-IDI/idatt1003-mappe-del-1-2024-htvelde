package edu.ntnu.idi.idatt.UI;

public class UserInterfacePrintOut {
  public static void print(String string) {
    System.out.println(string);
  }

  public static void printMenu(String menu) {
    clearConsole();
    System.out.println(menu);
    prompt();
  }

  public static void clearConsole() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static void printUserInputError(String message) {
    System.out.println(message);
  }

  public static void prompt() {
    System.out.print(">>> ");
  }

  public static void printRequest(String request) {
    System.out.println(request);
  }

  public static void printGroceriesHeader() {
    String[] h = {"Food Name", "Quantity", "Price", "Expiration Date"};
    System.out.printf("%-32s | %-9s | %-1s | %s\n", h[0], h[1], h[3], h[3]);
  }

  public static void printGroceriesMain(String groceriesString) {
    System.out.println(groceriesString);
  }
}
