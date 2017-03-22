package com.excilys.mgajovski.computer_database.exceptions;

/**
 * @author	Gajovski Maxime
 * @date	22 mars 2017
 */
public class ServiceException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * Empty constructor for DAOException.
   */
  public ServiceException() {
      super();
  }

  /**
   * Constructor with message and error field.
   * @param message : the error message.
   * @param error : the error.
   */
  public ServiceException(String message, Throwable error) {
      super(message, error);
  }

  /**
   * Constructor with message field.
   * @param message : the error message.
   */
  public ServiceException(String message) {
      super(message);
  }

  /**
   * Constructor with error field.
   * @param error : the error.
   */
  public ServiceException(Throwable error) {
      super(error);
  }
}
