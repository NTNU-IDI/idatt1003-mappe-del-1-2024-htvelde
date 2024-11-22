package edu.ntnu.idi.idatt.UI;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {
  Scanner scanner = new Scanner(System.in);

  /**
   * This class asks for the user input.
   * It returns a byte instead of int as it should be sufficient
   * with 128 options for the user (negatives are not accepted).
   *
   * @param max Defines the upper limit of choices.
   * @return Returns the byte as the action of the user.
   * @throws IllegalArgumentException Raises exception if invalid input.
   */
  public byte inputNumber(int max) throws IllegalArgumentException {
    byte number = 0;



    return number;
  }

  /**
   * Checks if user-input is in av valid range. <br>
   * <b>
   *   Note that there are some values that will not return false.
   *   These errors happens only to the second digit in "dd",
   *   when the month is shorter than 31 days.
   * </b>
   *
   * @param input Takes String as input
   * @return boolean that indicates if the given date is valid
   */
  public boolean dateInput(String input) {
    String regexCheckForDate = "([012][1-9]|3[01])\\.";
    String regexCheckForMonth = "(0[1-9]|1[0-2])\\.";
    String regexCheckForYear = "[0-9]{4}";

    return input.matches(regexCheckForDate + regexCheckForMonth + regexCheckForYear);
  }
}
