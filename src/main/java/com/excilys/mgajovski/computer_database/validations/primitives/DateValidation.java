package com.excilys.mgajovski.computer_database.validations.primitives;

import java.time.LocalDate;
import com.excilys.mgajovski.computer_database.exceptions.DateException;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
public final class DateValidation {
  
  
  public static final String INVALID_DATE_FORMAT = "date format is not valid : ";
  public static final String DATE_NULL = "date null.";
  public static final String DATE_FORMAT_REGEX = "\\d{4}-\\d{2}-\\d{2}";
  public static final String BEFORE_DATE_ERR = "introduced date can't be after discontinued date.";

  public static boolean formatIsValid(String date) throws DateException{
      if (date != null) {
          if (hasGoodDateRegex(date)) {
              return true;
          }
          throw new DateException(INVALID_DATE_FORMAT + date);
      }
      throw new DateException(DATE_NULL);
  }

  private static boolean hasGoodDateRegex(String date){
    return date.matches(DATE_FORMAT_REGEX);
  }
  
  public static boolean dateIsValid(LocalDate beforeDate, LocalDate afterDate) throws DateException {
      if (beforeDate != null && afterDate!= null) {
          if (beforeDate.isBefore(afterDate)) {
              return true;
          }
          throw new DateException(BEFORE_DATE_ERR);
      }
      throw new DateException(DATE_NULL);
  }
  
}