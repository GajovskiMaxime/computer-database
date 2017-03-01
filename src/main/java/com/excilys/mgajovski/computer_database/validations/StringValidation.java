package com.excilys.mgajovski.computer_database.validations;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public final class StringValidation {

  public static boolean hasValidName(String string) {
    return string!= null && !string.isEmpty();
  }
  
}
