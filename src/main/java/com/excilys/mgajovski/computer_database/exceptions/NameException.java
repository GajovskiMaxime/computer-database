package com.excilys.mgajovski.computer_database.exceptions;

/**
 * @author	Gajovski Maxime
 * @date	3 mars 2017
 */
public class NameException extends Exception{
  
  /**
   * 
   */
  private static final long serialVersionUID = 7223649270978050060L;

  public NameException(){
      super();
  }

  public NameException (String message, Throwable error) {
      super(message , error);
  }

  public NameException (String message) {
      super(message);
  }

  public NameException (Throwable error) {
      super(error);
  }
}
