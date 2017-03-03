package com.excilys.mgajovski.computer_database.validations.primitives;

import com.excilys.mgajovski.computer_database.exceptions.NameException;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public final class StringValidation {
    
    public static final String VALID_NAME_ERR = "null or empty name : ";
    
    public static boolean hasValidName(String string) throws NameException{
      boolean validName = string!= null && !string.isEmpty(); 
      if(!validName){
        throw new NameException(VALID_NAME_ERR + string);
      }
      return validName;
  }
  
}
