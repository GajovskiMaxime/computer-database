package com.excilys.mgajovski.computer_database.exceptions.mapping;

import com.excilys.mgajovski.computer_database.utils.ErrorTags;

/**
 * @author Gajovski Maxime
 * @date 3 mars 2017
 * This exception is thrown if a name field is not correctly fulfilled.
 * @see DTOMapperException
 */
public class NameException extends DTOMapperException {

  private static final long serialVersionUID = 7223649270978050060L;

  /**
   * Empty constructor for NameException.
   */

  public NameException() {
      super();
  }


  public NameException(ErrorTags errTag, Throwable error) {
    super(errTag.toString(), error);
  }
  
  public NameException(ErrorTags errTag) {
    super(errTag.toString());
  }
  
  /**
   * Constructor with error and message field.
   * @param message : the error.
   * @param error : the error message.
   */

  public NameException(String message, Throwable error) {
      super(message, error);
  }

  /**
   * Constructor with error message field.
   * @param message : the error message.
   */
  public NameException(String message) {
      super(message);
  }

  /**
   * Constructor with error field.
   * @param error : the error.
   */
  public NameException(Throwable error) {
      super(error);
  }
}
