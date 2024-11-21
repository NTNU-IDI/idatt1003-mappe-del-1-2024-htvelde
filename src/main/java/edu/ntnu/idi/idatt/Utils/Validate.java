package edu.ntnu.idi.idatt.Utils;

public class Validate {

  /**
   * Checks if user-input is in av valid range. <br>
   * <b>
   *   Note that there are some values that will not return false.
   *   These errors happens only to the second digit in "dd" and "MM"
   *   when either the date or month is quit early because of the next month or year.
   * </b>
   *
   * @param input Takes String as input
   * @return boolean that indicates if the given date is valid
   */
  public boolean isValidDate(String input) {
    String regexForDate = "[0-3][0-9].";
    String regexForMonth = "[01][0-9].";
    String regexForYear = "[12][0-9]{3}\n";

    return input.matches(regexForDate + regexForMonth + regexForYear);
  }
}
