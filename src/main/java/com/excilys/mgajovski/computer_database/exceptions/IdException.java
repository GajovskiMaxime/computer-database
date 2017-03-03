package com.excilys.mgajovski.computer_database.exceptions;

/**
 * @author	Gajovski Maxime
 * @date	3 mars 2017
 */
public class IdException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 6578575176782354909L;

  public IdException(){
      super();
  }

  public IdException(String message, Throwable error) {
      super(message , error);
  }

  public IdException (String message) {
      super(message);
  }

  public IdException (Throwable error) {
      super(error);
  }

}
