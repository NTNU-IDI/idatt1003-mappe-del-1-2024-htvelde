package edu.ntnu.idi.idatt.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

import static edu.ntnu.idi.idatt.utils.Date.dateToString;
import static edu.ntnu.idi.idatt.utils.Date.stringToDate;
import static org.junit.jupiter.api.Assertions.*;
class UserInputTest {
  UserInput userInput;

  @BeforeEach
  void setUp() {
    userInput = new UserInput();
  }

  @AfterEach
  void tearDown() {
    userInput = null;
  }

  @Test
  void testInputByteWhenByte() {
    ByteArrayInputStream inputStream = new ByteArrayInputStream("1".getBytes());
    Scanner scanner = new Scanner(inputStream).useDelimiter("\n");
    userInput = new UserInput(scanner);
    byte byteValue = userInput.inputByte();
    assertEquals((byte) 1, byteValue);
  }

  @Test
  void testInputByteWhenNotByte() {
    ByteArrayInputStream inputStream = new ByteArrayInputStream("a".getBytes());
    userInput = new UserInput(new Scanner(inputStream).useDelimiter("\n"));
    assertThrows(InputMismatchException.class, () -> userInput.inputByte());
  }

  @Test
  void testInputIntWhenInteger() {
    ByteArrayInputStream inputStream = new ByteArrayInputStream("1".getBytes());
    Scanner scanner = new Scanner(inputStream).useDelimiter("\n");
    userInput = new UserInput(scanner);
    int intValue = userInput.inputInt();
    assertEquals( 1, intValue);
  }

  @Test
  void testInputIntWhenNotInteger() {
    ByteArrayInputStream inputStream = new ByteArrayInputStream("a".getBytes());
    Scanner scanner = new Scanner(inputStream).useDelimiter("\n");
    userInput = new UserInput(scanner);
    assertThrows(InputMismatchException.class, () -> userInput.inputInt());
  }

  @Test
  void testInputDoubleWhenDouble() {
    ByteArrayInputStream inputStream = new ByteArrayInputStream("1".getBytes());
    Scanner scanner = new Scanner(inputStream).useDelimiter("\n");
    userInput = new UserInput(scanner);
    int doubleValue = userInput.inputInt();
    assertEquals( 1, doubleValue);
  }

  @Test
  void testInputDoubleWhenNotDouble() {
    ByteArrayInputStream inputStream = new ByteArrayInputStream("a".getBytes());
    Scanner scanner = new Scanner(inputStream).useDelimiter("\n");
    userInput = new UserInput(scanner);
    assertThrows(InputMismatchException.class, () -> userInput.inputDouble());
  }

  @Test
  void testInputString() {
    ByteArrayInputStream inputStream = new ByteArrayInputStream("some text".getBytes());
    Scanner scanner = new Scanner(inputStream).useDelimiter("\n");
    userInput = new UserInput(scanner);
    String out = userInput.inputString(1);
    assertEquals("some text", out);
  }

  @Test
  void testInputDate() {
    String actualDate = dateToString(LocalDate.now());
    ByteArrayInputStream inputStream = new ByteArrayInputStream(actualDate.getBytes());
    Scanner scanner = new Scanner(inputStream).useDelimiter("\n");
    userInput = new UserInput(scanner);
    String date = dateToString(userInput.inputDate());
    assertEquals(actualDate, date);
  }

  @Test
  void testInputDateWhenNotDate() {
    ByteArrayInputStream inputStream = new ByteArrayInputStream("some text".getBytes());
    Scanner scanner = new Scanner(inputStream).useDelimiter("\n");
    userInput = new UserInput(scanner);
    assertThrows(IllegalArgumentException.class, () -> userInput.inputDate());
  }

  @Test
  void testInputDateWhenNull() {
    ByteArrayInputStream inputStream = new ByteArrayInputStream("".getBytes());
    Scanner scanner = new Scanner(inputStream).useDelimiter("\n");
    userInput = new UserInput(scanner);
    assertThrows(NoSuchElementException.class, () -> userInput.inputDate());
  }

  @Test
  void testDateWhenRegexGiveError() {
    ByteArrayInputStream inputStream = new ByteArrayInputStream("40.33.9999".getBytes());
    Scanner scanner = new Scanner(inputStream).useDelimiter("\n");
    userInput = new UserInput(scanner);
    assertThrows(IllegalArgumentException.class, () -> userInput.inputDate());
  }
}