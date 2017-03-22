package com.excilys.mgajovski.computer_database.exceptions;

/**
 * @author	Gajovski Maxime
 * @date	21 mars 2017
 */
public class ConnectionException extends Exception {


  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * Empty constructor for ConnectionException.
   */
  public ConnectionException() {
      super();
  }

  /**
   * Constructor with message and error field.
   * @param message : the error message.
   * @param error : the error.
   */
  public ConnectionException(String message, Throwable error) {
      super(message, error);
  }

  /**
   * Constructor with message field.
   * @param message : the error message.
   */
  public ConnectionException(String message) {
      super(message);
  }

  /**
   * Constructor with error field.
   * @param error : the error.
   */
  public ConnectionException(Throwable error) {
      super(error);
  }

}
