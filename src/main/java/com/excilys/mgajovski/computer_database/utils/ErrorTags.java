package com.excilys.mgajovski.computer_database.utils;

/**
 * @author	Gajovski Maxime
 * @date	4 avr. 2017
 */
public enum ErrorTags {

  SUCCESS("errMessage.success"),
  
  NAME_IS_EMPTY("errMessage.nameIsEmpty"),
  NAME_IS_NULL("errMessage.nameIsNull"),
  NAME_MISMATCH_REGEX("errMessage.nameMismatchRegex"),
  
  DATE_IS_NULL("errMessage.dateIsNull"),
  DATE_IS_EMPTY("errMessage.dateIsEmpty"), 
  DATE_MISMATCH_REGEX("errMessage.dateMismatchRegex"),
  DATE_MUST_BE_BEFORE("errMessage.dateMustBeBefore"),
  
  ID_NOT_SELECTED("errMessage.idNotSelected"),
  ID_IS_NULL("errMessage.idIsNull"),
  ID_IS_EMPTY("errMessage.idIsEmpty"),
  ID_IS_INIT("errMessage.idIsInitialized"), 
  ID_IS_NOT_INIT("errMessage.idIsNotInitialized"), 
  ID_IS_NaN("errMessage.idIsNaN"); 
 
  private final String property;

  ErrorTags(String property) {
    this.property = property;
  }

  public String toString() {
    return property;
  }
  
}
