package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.ui.UserInterfaceFlow;

/**
 * This is the client-program from where everything is run.
 */
public class Main {
  private static UserInterfaceFlow ui;

  private static void setUi(UserInterfaceFlow ui) {
    Main.ui = ui;
  }
  /**
   * This is the function that runs when program starts.
   */
  public static void main(String[] args) {
    setUi(new UserInterfaceFlow());
    ui.init();
    ui.start();
  }
}