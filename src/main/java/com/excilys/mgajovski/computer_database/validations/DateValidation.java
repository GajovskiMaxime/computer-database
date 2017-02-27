package com.excilys.mgajovski.computer_database.validations;

import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
public class DateValidation {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(DateValidation.class);

  public static boolean formatIsValid(Optional<String> optDate) {
      if (optDate != null && optDate.isPresent()) {
          String date = optDate.get();
          if (hasGoodDateRegex(date)) {
              return true;
          }
          LOGGER.warn(ValidationUtils.INVALID_DATE_FORMAT);
      }
      return false;
  }

  private static boolean hasGoodDateRegex(String date){
    return date.matches(ValidationUtils.DATE_FORMAT_REGEX);
  }
  
  public static boolean dateIsValid(Optional<LocalDate> optionalDateBefore, Optional<LocalDate> optionalDateAfter) {
      if (optionalDateBefore != null && optionalDateAfter != null && 
          optionalDateBefore.isPresent() && optionalDateAfter.isPresent()) {
          LocalDate dateBefore = optionalDateBefore.get();
          LocalDate dateAfter = optionalDateAfter.get();

          if (dateBefore.isBefore(dateAfter)) {
              return true;
          }
          LOGGER.info(ValidationUtils.BEFORE_DATE_ERR);
      }
      return false;
  }
}