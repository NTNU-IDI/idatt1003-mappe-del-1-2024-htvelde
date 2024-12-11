package edu.ntnu.idi.idatt.ui;

import edu.ntnu.idi.idatt.utils.ValidateInput;

import static edu.ntnu.idi.idatt.ui.UserInterfacePrintOut.newLine;
import static edu.ntnu.idi.idatt.ui.UserInterfacePrintOut.prompt;
import static edu.ntnu.idi.idatt.utils.Date.stringToDate;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;



/**
 * <h5>Class</h5>
 * <h3>UserInput()</h3>
 * UserInput uses Scanner and functions to get input from the user.<br>
 * It is also the only class to use Scanner in this project,
 * meaning all user input related code must be written here or in the ValidateInput class.
 *
 * @see ValidateInput
 * @see Scanner
 */
public class UserInput {
  private final Scanner scanner;

  public UserInput() {
    scanner = new Scanner(System.in).useDelimiter("\n");
  }

  public UserInput(Scanner scanner) {
    this.scanner = scanner;
  }

  /**
   * <h5>Method</h5>
   * <h3>inputByte()</h3>
   * This method asks for the user to input a byte to be handled as a choice.<br>
   * It returns a byte instead of int as it should be sufficient
   * with 128 options for the user (negatives are not accepted).
   *
   * @return Returns the byte as the action of the user.
   * @throws IllegalArgumentException Raises exception if invalid input.
   */
  public byte inputByte() throws IllegalArgumentException, InputMismatchException {
    byte number;

    try {
      prompt();
      number = scanner.nextByte();
      newLine();
    } catch (InputMismatchException e) {
      scanner.next();
      throw new InputMismatchException();
    }

    return number;
  }

  /**
   * <h5>Method</h5>
   * <h3>inputInt</h3>
   * This method asks for the user to input an int.
   *
   * @return Returns the int.
   * @throws IllegalArgumentException Raises exception if invalid input.
   */
  public int inputInt() throws IllegalArgumentException, InputMismatchException {
    int number;

    try {
      prompt();
      number = scanner.nextInt();
      newLine();
    } catch (InputMismatchException e) {
      scanner.next();
      throw new InputMismatchException();
    }

    return number;
  }

  /**
   * <h5>Method</h5>
   * <h3>inputDouble()</h3>
   * This method asks for the user to input a float.<br>
   * If something besides a float is provided, it throws an exception.
   *
   * @return Returns the float input from the user.
   * @throws InputMismatchException when user input is not a float.
   */
  public double inputDouble() throws InputMismatchException {
    double number;

    try {
      prompt();
      number = scanner.nextDouble();
      newLine();
    } catch (InputMismatchException e) {
      scanner.next();
      throw new InputMismatchException();
    }

    return number;
  }

  /**
   * <h5>Method</h5>
   * <h3>inputString()</h3>
   * This asks for a string input from the user.
   * The method expects a string ended with a linebreak to enter.
   *
   * @param minLength The minimum length of a name to avoid ambiguous naming.
   * @return String returned is the inputted string before the linebreak.
   * @throws IllegalArgumentException Throws an exception if string is not long enough.
   * @throws InputMismatchException Throws exception when user input is not handled.
   */
  public String inputString(int minLength) throws IllegalArgumentException, InputMismatchException {
    String string;

    try {
      prompt();
      string = scanner.next();
      newLine();
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

  /**
   * <h5>Method</h5>
   * <h3>inputDate()</h3>
   * Asks the user for date, which they have to write in dd.mm.yyyy-format.
   * Throws error if the inputted date is not parsed.
   *
   * @return LocalDate of the user input is returned.
   * @throws IllegalArgumentException is thrown if the date is not in correct format.
   * @throws InputMismatchException is a special case of input-error that may be redundant.
   * @throws DateTimeParseException throws when date passes check, but still is not real.
   */
  public LocalDate inputDate() throws IllegalArgumentException,
      InputMismatchException,
      DateTimeParseException,
      NoSuchElementException
  {
    String string;
    LocalDate localDate = null;

    try {
      prompt();
      string = scanner.next();
      newLine();
      // Regex I made on my own after an introduction to logical Regex.
      // It does not know how long any months are
      // An easy solution would be to have four checks dependent on month,
      // then have the correct maximum of days.
      if (!string.matches("(0[1-9]|[12][0-9]|3[01])\\.(0"
          + "[1-9]|1[0-2])\\.[0-9]{4}")) {
        throw new IllegalArgumentException("Does not conform the dd.MM.yyyy format.");
      }
      localDate = stringToDate(string);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Please enter a date on the format dd.MM.yyyy.");
    } catch (NoSuchElementException e) {
      scanner.next();
      throw new InputMismatchException("Please enter a valid string.");
    } catch (DateTimeParseException e) {
      System.err.println("Something has gone very wrong in parsing of date!");
    }
    return localDate;
  }
}
