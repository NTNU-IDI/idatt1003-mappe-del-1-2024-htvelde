package edu.ntnu.idi.idatt.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <h5>Class</h5>
 * <h3>Date</h3>
 * This class converts between String and LocalDate.
 */
public class Date {
  /**
   * <h5>Method</h5>
   * <h3>stringToDate()</h3>
   * This method converts a String to a LocalDate object.
   *
   * @param date String to convert to LocalDate.
   * @return LocalDate from String.
   */
  public static LocalDate stringToDate(String date) {
    return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
  }

  /**
   * <h5>Method</h5>
   * <h3>dateToString()</h3>
   * This method converts a LocalDate to a string representing the date on the format dd.MM.yyyy.
   *
   * @param date LocalDate to convert to String.
   * @return Date String on format dd.MM.yyyy.
   */
  public static String dateToString(LocalDate date) {
    return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
  }
}
