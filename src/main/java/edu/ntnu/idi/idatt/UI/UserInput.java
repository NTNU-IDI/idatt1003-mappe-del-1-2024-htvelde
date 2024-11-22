package edu.ntnu.idi.idatt.UI;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {
  private final Scanner scanner = new Scanner(System.in);

  /**
   * This class asks for the user input.
   * It returns a byte instead of int as it should be sufficient
   * with 128 options for the user (negatives are not accepted).
   *
   * @param max Defines the upper limit of choices.
   * @return Returns the byte as the action of the user.
   * @throws IllegalArgumentException Raises exception if invalid input.
   */
  public byte inputByte(byte max) throws IllegalArgumentException, InputMismatchException {
    byte number = 0;

    try {
      number = scanner.nextByte();
    }
    catch (InputMismatchException e) {
      scanner.next();
      throw new InputMismatchException();
    }

    return number;
  }

  public double inputDouble(double max) throws IllegalArgumentException, InputMismatchException {
    double number = 0;

    try {
      number = scanner.nextDouble();
    }
    catch (InputMismatchException e) {
      scanner.next();
      throw new InputMismatchException();
    }

    return number;
  }

  public String inputString() throws IllegalArgumentException, InputMismatchException {
    String string = "";

    try {
      string = scanner.nextLine();
      if (string.length() < 2) {
        System.out.println("Length" + string.length());
        throw new IllegalArgumentException("Too short input. It must be at least 2 characters.");
      }
    }
    catch (IllegalArgumentException e) {
      scanner.next();
      throw new IllegalArgumentException("Please enter a string with more than 2 characters.");
    }
    catch (InputMismatchException e) {
      scanner.next();
      throw new InputMismatchException("Please enter a valid string.");
    }
    return string;
  }

  public String inputDate() throws IllegalArgumentException, InputMismatchException {
    String string = "";

    try {
      string = scanner.nextLine();
      // Regex I made on my own after an introduction to logical Regex.
      if (!string.matches("([012][1-9]|3[01])\\.(0[1-9]|1[0-2])\\.[0-9]{4}")) {
        System.out.println("Does not conform the dd.MM.yyyy format.");
        throw new IllegalArgumentException("Does not conform the dd.MM.yyyy format.");
      }
    }
    catch (IllegalArgumentException e) {
      scanner.next();
      throw new IllegalArgumentException("Please enter a date on the format dd.MM.yyyy.");
    }
    catch (InputMismatchException e) {
      scanner.next();
      throw new InputMismatchException("Please enter a valid string.");
    }
    return string;
  }
}
