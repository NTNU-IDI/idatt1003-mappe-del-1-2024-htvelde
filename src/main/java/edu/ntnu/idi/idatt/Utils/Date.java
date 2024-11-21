package edu.ntnu.idi.idatt.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {
  public LocalDate stringToDate(String date) {
    LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    return parsedDate;
  }

  // Oldest date
  // Newest date
}
