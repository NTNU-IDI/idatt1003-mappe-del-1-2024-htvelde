package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.Classes.FoodStorage.FoodStorage;
import edu.ntnu.idi.idatt.UserInterface.UserInterface;

/**
 * This is the client-program from where everything is run.
 */
public class Main {

  /**
   * This is the origin of the program from where it branches.
   */
  public static void main(String[] args) {
    UserInterface userInterface = new UserInterface(new FoodStorage());
    userInterface.welcome();
    try {
      userInterface.printMenu();
    } catch (IllegalStateException e) {
      System.err.println(e.getMessage());
    }
  }
}