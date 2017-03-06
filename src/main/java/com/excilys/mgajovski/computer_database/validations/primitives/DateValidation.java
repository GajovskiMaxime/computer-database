package com.excilys.mgajovski.computer_database.validations.primitives;

import java.time.LocalDate;

import com.excilys.mgajovski.computer_database.exceptions.mapping.DateException;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
public final class DateValidation {

  public static final String INVALID_DATE_FORMAT = "date format is not valid : ";
  public static final String DATE_NULL = "date null.";
  public static final String DATE_FORMAT_REGEX = "\\d{4}-\\d{2}-\\d{2}";
  public static final String BEFORE_DATE_ERR = "introduced date can't be after discontinued date.";

  /**
   * This method looks if the string date is not null and have a good format.
   * @param date : the string to parse.
   * @return true if date is valid, false otherwise.
   * @throws DateException if date is null or not in a good format.
   */
  public static boolean formatIsValid(String date) throws DateException {
      if (date != null) {
          if (hasGoodDateRegex(date) || date.isEmpty()) {
              return true;
          }
          throw new DateException(INVALID_DATE_FORMAT + date);
      }
      throw new DateException(DATE_NULL);
  }

  /**
   * This method looks if the parameter date matches a pre-register date regex.
   * @param date : the string date.
   * @return true if the string date matches with the regex : "\\d{4}-\\d{2}-\\d{2}".
   */
  private static boolean hasGoodDateRegex(String date) {
    return date.matches(DATE_FORMAT_REGEX);
  }

  /**
   * This method looks if two dates are in a good time order.
   * @param beforeDate : the first date.
   * @param afterDate : the second date.
   * @return true if the first date is before the second.
   * @throws DateException if dates are null or if dates are not in a good time order.
   * @see datesAreInGoodTimeOrder(String, String)
   */
  public static boolean datesAreInGoodTimeOrder(LocalDate beforeDate, LocalDate afterDate) throws DateException {
      if (beforeDate == null || afterDate == null) {
          throw new DateException(DATE_NULL);
      }
      if (!beforeDate.isBefore(afterDate)) {
          throw new DateException(BEFORE_DATE_ERR);
      }
      return true;
  }

  /**
   * This method looks if two dates are in a good time order.
   * @param beforeDate : the first date as string.
   * @param afterDate : the second date as string.
   * @return true if the first date is before the second.
   * @throws DateException if dates are null or if dates are not in a good time order.
   * @see datesAreInGoodTimeOrder(LocalDate, LocalDate)
   */
  public static boolean datesAreInGoodTimeOrder(String beforeDate, String afterDate) throws DateException {
      if (beforeDate == null || afterDate == null) {
          throw new DateException(DATE_NULL);
      }
      if (beforeDate.isEmpty() || afterDate.isEmpty()) {
          return true;
      }
      return datesAreInGoodTimeOrder(LocalDate.parse(beforeDate), LocalDate.parse(afterDate));
  }

}