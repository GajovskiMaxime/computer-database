package com.excilys.mgajovski.computer_database.exceptions.mapping;

import com.excilys.mgajovski.computer_database.utils.ErrorTags;

/**
 * @author	Gajovski Maxime
 * @date	3 avr. 2017
 */
public class CompanyException extends DTOMapperException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * Empty constructor for DateException.
   */
  public CompanyException() {
      super();
  }

  public CompanyException(ErrorTags errTag, Throwable error) {
    super(errTag.toString(), error);
  }
  
  public CompanyException(ErrorTags errTag) {
    super(errTag.toString());
  }
  
  /**
   * Constructor with error and message field.
   * @param message : the error message.
   * @param error : the error.
   */
  public CompanyException(String message, Throwable error) {
      super(message, error);
  }

  /**
   * Constructor with error message field.
   * @param message : the error message.
   */
  public CompanyException(String message) {
      super(message);
  }

  /**
   * Constructor with error field.
   * @param error : the error.
   */
  public CompanyException(Throwable error) {
      super(error);
  }
}
