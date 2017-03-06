package com.excilys.mgajovski.computer_database.exceptions.mapping;

/**
 * @author Gajovski Maxime
 * @date 3 mars 2017
 * This exception is thrown if a date is not correctly fulfilled.
 * @see DTOMapperException
 */
public class DateException extends DTOMapperException {

    private static final long serialVersionUID = 6578575176782354909L;

    /**
     * Empty constructor for DateException.
     */
    public DateException() {
        super();
    }

    /**
     * Constructor with error and message field.
     * @param message : the error message.
     * @param error : the error.
     */
    public DateException(String message, Throwable error) {
        super(message, error);
    }

    /**
     * Constructor with error message field.
     * @param message : the error message.
     */
    public DateException(String message) {
        super(message);
    }

    /**
     * Constructor with error field.
     * @param error : the error.
     */
    public DateException(Throwable error) {
        super(error);
    }
}
