package com.excilys.mgajovski.computer_database.exceptions;

/**
 * @author Gajovski Maxime
 * @date 24 févr. 2017
 */
public class DAOException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public DAOException(){
        super();
    }

    public DAOException(String pMessage, Throwable pCause, boolean pEnableSuppression, boolean pWritableStackTrace) {
        super(pMessage, pCause, pEnableSuppression, pWritableStackTrace);
    }

    public DAOException(String pMessage, Throwable pCause) {
        super(pMessage, pCause);
    }

    public DAOException(String pMessage) {
        super(pMessage);
    }

    public DAOException(Throwable pCause) {
        super(pCause);
    }
    
}