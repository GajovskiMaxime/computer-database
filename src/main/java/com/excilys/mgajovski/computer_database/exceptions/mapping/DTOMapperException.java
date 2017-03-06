package com.excilys.mgajovski.computer_database.exceptions.mapping;

/**
 * @author Gajovski Maxime
 * @date 6 mars 2017
 * This exception is thrown if a DTO field is not correctly fulfilled.
 * @see IdException
 * @see NameException
 * @see DateException
 */
public class DTOMapperException extends Exception {

    private static final long serialVersionUID = 6578575176782354909L;

    /**
     * Empty constructor for DTOMapperException.
     */
    public DTOMapperException() {
        super();
    }

    /**
     * Constructor with error and message field.
     * @param message : the error message.
     * @param error : the error.
     */
    public DTOMapperException(String message, Throwable error) {
        super(message, error);
    }

    /**
     * Constructor with error message field.
     * @param message : the error message.
     */
    public DTOMapperException(String message) {
        super(message);
    }

    /**
     * Constructor with error field.
     * @param error : the error.
     */
    public DTOMapperException(Throwable error) {
        super(error);
    }
}
