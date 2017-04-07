package com.excilys.mgajovski.computer_database.validations.primitives;

import java.time.LocalDate;

import com.excilys.mgajovski.computer_database.exceptions.mapping.DateException;
import com.excilys.mgajovski.computer_database.utils.ErrorTags;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
public final class DateValidation {

  public static final String DATE_FORMAT_REGEX = "\\d{4}-\\d{2}-\\d{2}";
  public static final String INVALID_DATE_FORMAT = "Date must respect the regex : "
      + DATE_FORMAT_REGEX;
  public static final String DATE_NULL = "Date seems to be null.";
  public static final String BEFORE_DATE_ERR = "Introduced date can't be after Discontinued date.";

  public static ErrorTags formatIsValid(String date) {
    if (date == null) {
      return ErrorTags.DATE_IS_NULL;
    }
    if(!date.trim().isEmpty() && !hasGoodDateRegex(date)) {
      return ErrorTags.DATE_MISMATCH_REGEX;
    }
    return ErrorTags.SUCCESS;
  }

  private static boolean hasGoodDateRegex(String date) {
    return date.matches(DATE_FORMAT_REGEX);
  }

  public static ErrorTags datesAreInGoodTimeOrder(LocalDate beforeDate, LocalDate afterDate) {
    if (beforeDate == null || afterDate == null) {
      return ErrorTags.DATE_IS_NULL;
    }
    if (!beforeDate.isBefore(afterDate)) {
      return ErrorTags.DATE_MUST_BE_BEFORE;
    }
    return ErrorTags.SUCCESS;
  }

  public static ErrorTags datesAreInGoodTimeOrder(String beforeDate, String afterDate) {
    if (beforeDate == null || afterDate == null) {
      return ErrorTags.DATE_IS_NULL;
    }
    if (!beforeDate.isEmpty() && !afterDate.isEmpty()) {
      return datesAreInGoodTimeOrder(LocalDate.parse(beforeDate), LocalDate.parse(afterDate));
    }
    return ErrorTags.SUCCESS;
  }

}