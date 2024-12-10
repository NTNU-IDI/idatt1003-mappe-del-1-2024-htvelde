package edu.ntnu.idi.idatt.ui;

public class ValidateInput {

  public static boolean isValidByte(byte max, byte input) {
    return input >= 0 && input <= max;
  }

  public static boolean isValidInt(int min, int max, int input) {
    return input >= min && input <= max;
  }

  public static boolean isValidDouble(double min, double max, double input) {
    return input >= min && input <= max;
  }
}
