package com.excilys.mgajovski.computer_database.exceptions;

/**
 * @author	Gajovski Maxime
 * @date	3 mars 2017
 */
public class DateException extends Exception{
  
  /**
   * 
   */
  private static final long serialVersionUID = 6578575176782354909L;

  public DateException(){
      super();
  }

  public DateException(String message, Throwable error) {
      super(message , error);
  }

  public DateException (String message) {
      super(message);
  }

  public DateException (Throwable error) {
      super(error);
  }
}
