package com.excilys.mgajovski.computer_database.exceptions;

/**
 * @author Gajovski Maxime
 * @date 6 mars 2017
 */
public class SQLMappingException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Empty constructor for SQLMappingException.
     */
    public SQLMappingException() {
        super();
    }

    /**
     * Constructor with message and error field.
     * @param message : the error message.
     * @param error : the error.
     */
    public SQLMappingException(String message, Throwable error) {
        super(message, error);
    }

    /**
     * Constructor with message field.
     * @param message : the error message.
     */
    public SQLMappingException(String message) {
        super(message);
    }

    /**
     * Constructor with error field.
     * @param error : the error.
     */
    public SQLMappingException(Throwable error) {
        super(error);
    }
}
