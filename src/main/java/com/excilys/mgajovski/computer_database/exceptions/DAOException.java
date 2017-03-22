package com.excilys.mgajovski.computer_database.exceptions;

/**
 * @author Gajovski Maxime
 * @date 24 févr. 2017
 */
public class DAOException extends ServiceException {

    public static final String ENTITY_NULL = "entity must not be null";
    public static final String ENTITY_NULL_OR_ALREADY_EXIST = "null or already persisted object";
    public static final String ENTITY_NULL_OR_NOT_IN_DB = "null or not in database.";
    public static final String EMPTY_TABLE = "table seems to be empty.";
    public static final String ENTITY_NOT_FOUND = "entity not found on the database.";
    public static final String NO_MATCH_FOR_FILTER = "entity not found for desired filter.";
    public static final String NEGATIVE_OR_NULL_ID = "wrong given id (negative or zero).";
    public  static final String PREP_STATEMENT_FAILED = "preparedStatement failed.";

    private static final long serialVersionUID = 1L;

    /**
     * Empty constructor for DAOException.
     */
    public DAOException() {
        super();
    }

    /**
     * Constructor with message and error field.
     * @param message : the error message.
     * @param error : the error.
     */
    public DAOException(String message, Throwable error) {
        super(message, error);
    }

    /**
     * Constructor with message field.
     * @param message : the error message.
     */
    public DAOException(String message) {
        super(message);
    }

    /**
     * Constructor with error field.
     * @param error : the error.
     */
    public DAOException(Throwable error) {
        super(error);
    }

}