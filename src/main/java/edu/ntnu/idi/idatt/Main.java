package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.ui.UserInterfaceFlow;

/**
 * This is the client-program from where everything is run.
 */
public class Main {

  /**
   * This is the function that runs when program starts.
   */
  public static void main(String[] args) {
    UserInterfaceFlow ui = new UserInterfaceFlow();
    ui.init();
    ui.start();
  }
}