package com.excilys.mgajovski.computer_database.exceptions;

/**
 * @author Gajovski Maxime
 * @date 3 mars 2017
 */
public class PageException extends Exception {

  public static final String PAGE_NULL = "object page is null.";
  public static final String EMPTY_SET = "empty set.";
  public static final String NEGATIVE_CURRENT_PAGE = "current page seems to be negative : ";
  public static final String NEGATIVE_NUMBERS_OF_ELEMENTS = "number of elements seems to be negative : ";

  private static final long serialVersionUID = 7223649270978050060L;

  /**
   * Empty constructor for PageException.
   */
  public PageException() {
      super();
  }

  /**
   * Constructor with message and error field.
   * @param message : the error message.
   * @param error : the error.
   */
  public PageException(String message, Throwable error) {
      super(message, error);
  }

  /**
   * Constructor with message field.
   * @param message : the error message.
   */
  public PageException(String message) {
      super(message);
  }

  /**
   * Constructor with error field.
   * @param error : the error.
   */
  public PageException(Throwable error) {
      super(error);
  }

}
