package edu.ntnu.idi.idatt.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {
  public static LocalDate stringToDate(String date) {
    LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    return parsedDate;
  }

  public static String dateToString(LocalDate date) {
    // Written by copilot (autocomplete)
    return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
  }

  // Oldest date
  // Newest date
}
