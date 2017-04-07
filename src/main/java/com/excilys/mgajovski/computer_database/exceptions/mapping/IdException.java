package com.excilys.mgajovski.computer_database.exceptions.mapping;

/**
 * @author Gajovski Maxime
 * @date 3 mars 2017
 * This exception is thrown if an id field is not correctly fulfilled.
 * @see DTOMapperException
 */
public class IdException extends DTOMapperException {

  private static final long serialVersionUID = 6578575176782354909L;


  /**
   * Empty constructor for IdException.
   */
  public IdException() {
      super();
  }

  
  /**
   * Constructor with error and message field.
   * @param message : the error message.
   * @param error : the error.
   */
  public IdException(String message, Throwable error) {
      super(message, error);
  }

  /**
   * Constructor with error message field.
   * @param message : the error message.
   */
  public IdException(String message) {
      super(message);
  }

  /**
   * Constructor with error field.
   * @param error : the error.
   */
  public IdException(Throwable error) {
      super(error);
  }

}
