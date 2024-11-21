package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.Classes.FoodStorage.FoodStorage;
import edu.ntnu.idi.idatt.UI.UI;

/**
 * This is the client-program from where everything is run.
 */
public class Main {

  /**
   * This is the origin of the program from where it branches.
   */
  public static void main(String[] args) {
    UI ui = new UI();
    ui.init();
    ui.start();
  }
}