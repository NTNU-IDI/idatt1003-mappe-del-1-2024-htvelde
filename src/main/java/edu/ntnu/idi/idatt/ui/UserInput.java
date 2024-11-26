package edu.ntnu.idi.idatt.ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static edu.ntnu.idi.idatt.utils.Date.stringToDate;

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

  public String inputString(int minLength) throws IllegalArgumentException, InputMismatchException {
    String string;

    try {
      string = scanner.next();
      if (string.length() < minLength) {
        throw new IllegalArgumentException(
            "Too short input. It must be at least " + minLength + " characters."
        );
      }
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(
          "Please enter a string with more than " + minLength + " characters."
      );
    } catch (InputMismatchException e) {
      scanner.next();
      throw new InputMismatchException("Please enter a valid string.");
    }
    return string;
  }

  public LocalDate inputDate() throws IllegalArgumentException, InputMismatchException {
    String string = "";
    LocalDate localDate = null;

    try {
      string = scanner.next();
      // Regex I made on my own after an introduction to logical Regex.
      if (!string.matches("(0[1-9]|[12][0-9]|3[01])\\.(0" +
          "[1-9]|1[0-2])\\.[0-9]{4}")) {
        throw new IllegalArgumentException("Does not conform the dd.MM.yyyy format.");
      }
      localDate = stringToDate(string);
    }
    catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Please enter a date on the format dd.MM.yyyy.");
    }
    catch (InputMismatchException e) {
      scanner.next();
      throw new InputMismatchException("Please enter a valid string.");
    }
    catch (DateTimeParseException e) {
      System.err.println("Something has gone very wrong in parsing of date!");
    }
    return localDate;
  }
}
