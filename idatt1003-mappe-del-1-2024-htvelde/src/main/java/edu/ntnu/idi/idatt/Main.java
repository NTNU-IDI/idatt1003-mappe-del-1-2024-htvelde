package edu.ntnu.idi.idatt;

/**
 * This is the client-program from where everything is run.
 */
public class Main {

  /**
   * This is the origin of the program from where it branches.
   */
  public static void main(String[] args) {
    CustomUserInterface userInterface = new CustomUserInterface(new FoodStorage());
    userInterface.welcome();
    try {
      userInterface.printMenu();
    } catch (IllegalStateException e) {
      System.err.println(e.getMessage());
    }
  }
}