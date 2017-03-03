package com.excilys.mgajovski.computer_database.exceptions;

/**
 * @author	Gajovski Maxime
 * @date	3 mars 2017
 */
public class PageException extends Exception {

  public static final String PAGE_NULL = "object page is null.";
  public static final String LAST_PAGE_REACHED = "last page reached.";
  public static final String NEGATIVE_CURRENT_PAGE = "current page seems to be negative : ";
  public static final String NEGATIVE_NUMBERS_OF_ELEMENTS = "number of elements seems to be negative : ";
  
  /**
   * 
   */
  private static final long serialVersionUID = 7223649270978050060L;

  public PageException(){
      super();
  }

  public PageException (String message, Throwable error) {
      super(message , error);
  }

  public PageException (String message) {
      super(message);
  }

  public PageException (Throwable error) {
      super(error);
  }
  
}
