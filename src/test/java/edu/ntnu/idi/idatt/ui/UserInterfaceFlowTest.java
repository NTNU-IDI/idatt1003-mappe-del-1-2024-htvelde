package edu.ntnu.idi.idatt.ui;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceFlowTest {
  UserInterfaceFlow flow;

  @Test
  void init() {
  }

  @Test
  void start() {
  }

  @Test
  void testMainMenuWhenInputIsZero() {
    flow = new UserInterfaceFlow();
    ByteArrayInputStream inputStream = new ByteArrayInputStream("0".getBytes());
    UserInput input = new UserInput(new Scanner(inputStream).useDelimiter("\n"));
    flow.setInput(input);

    boolean output = flow.mainMenu();
    assertFalse(output);
  }

  @Test
  void testMainMenuWhenInputIsOne() {
    flow = new UserInterfaceFlow();
    ByteArrayInputStream inputStream = new ByteArrayInputStream("1\n0".getBytes());
    UserInput input = new UserInput(new Scanner(inputStream).useDelimiter("\n"));
    flow.setInput(input);

    boolean output = flow.mainMenu();
    assertTrue(output);
  }

  @Test
  void testMainMenuWhenInputIsTwo() {
    flow = new UserInterfaceFlow();
    ByteArrayInputStream inputStream = new ByteArrayInputStream("2\n0".getBytes());
    UserInput input = new UserInput(new Scanner(inputStream).useDelimiter("\n"));
    flow.setInput(input);

    boolean output = flow.mainMenu();
    assertTrue(output);
  }

  @Test
  void testMainMenuWhenInputIsOtherByte() {
    flow = new UserInterfaceFlow();
    ByteArrayInputStream inputStream = new ByteArrayInputStream("10\n0".getBytes());
    UserInput input = new UserInput(new Scanner(inputStream).useDelimiter("\n"));
    flow.setInput(input);

    boolean output = flow.mainMenu();
    assertFalse(output);
  }

  @Test
  void testMainMenuWhenInputIsOtherInput() {
    flow = new UserInterfaceFlow();
    ByteArrayInputStream inputStream = new ByteArrayInputStream("some string\n0".getBytes());
    UserInput input = new UserInput(new Scanner(inputStream).useDelimiter("\n"));
    flow.setInput(input);

    boolean output = flow.mainMenu();
    assertFalse(output);
  }
 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @Test
  void testGroceriesMenuWhenInputIsZero() {
    flow = new UserInterfaceFlow();
    ByteArrayInputStream inputStream = new ByteArrayInputStream("0".getBytes());
    UserInput input = new UserInput(new Scanner(inputStream).useDelimiter("\n"));
    flow.setInput(input);

    boolean output = flow.mainMenu();
    assertFalse(output);
  }

  @Test
  void testGroceriesMenuWhenInputIsOne() {
    flow = new UserInterfaceFlow();
    ByteArrayInputStream inputStream = new ByteArrayInputStream("1\n0".getBytes());
    UserInput input = new UserInput(new Scanner(inputStream).useDelimiter("\n"));
    flow.setInput(input);

    boolean output = flow.mainMenu();
    assertTrue(output);
  }

  @Test
  void testGroceriesMenuWhenInputIsTwo() {
    flow = new UserInterfaceFlow();
    ByteArrayInputStream inputStream = new ByteArrayInputStream("2\n0".getBytes());
    UserInput input = new UserInput(new Scanner(inputStream).useDelimiter("\n"));
    flow.setInput(input);

    boolean output = flow.mainMenu();
    assertTrue(output);
  }

  @Test
  void testGroceriesMenuWhenInputIsOtherByte() {
    flow = new UserInterfaceFlow();
    ByteArrayInputStream inputStream = new ByteArrayInputStream("20\n0".getBytes());
    UserInput input = new UserInput(new Scanner(inputStream).useDelimiter("\n"));
    flow.setInput(input);

    boolean output = flow.mainMenu();
    assertFalse(output);
  }

  @Test
  void testGroceriesMenuWhenInputIsOtherInput() {
    flow = new UserInterfaceFlow();
    ByteArrayInputStream inputStream = new ByteArrayInputStream("some string\n0".getBytes());
    UserInput input = new UserInput(new Scanner(inputStream).useDelimiter("\n"));
    flow.setInput(input);

    boolean output = flow.mainMenu();
    assertFalse(output);
  }
}