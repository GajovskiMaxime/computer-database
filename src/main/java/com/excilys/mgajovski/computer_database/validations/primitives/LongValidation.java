package com.excilys.mgajovski.computer_database.validations.primitives;

import com.excilys.mgajovski.computer_database.exceptions.mapping.IdException;

/**
 * @author Gajovski Maxime
 * @date 27 févr. 2017
 */
public final class LongValidation {

    private static final String ID_STRING_NULL= "Id seems to be null.";
    private static final String ID_INITIALIZED = "Id seems already initialized.";
    private static final String ID_NOT_INITIALIZED = "Id isn't initialized.";
    private static final String ID_STRING_IS_NOT_A_LONG = "Id sring is not a long.";

    /**
     * This method looks if an id is already initialized in database.
     * @param id : the long id to test.
     * @return true if the id is not initialized, else otherwise.
     * @throws IdException if the id is already initialized.
     */
    public static boolean idIsNotInitialized(long id) throws IdException {
        if (id != 0) {
            throw new IdException(ID_INITIALIZED);
        }
        return true;
    }
    
    public static boolean idIsInitialized(long id) throws IdException{
        if(id <= 0){
            throw new IdException(ID_NOT_INITIALIZED);
        }
        return true;
    }
    
    public static long parseIdStringToLong(String id) throws IdException{
        if(id == null){
            throw new IdException(ID_STRING_NULL);
        }
        try{
            return Long.parseLong(id);
        } catch(NumberFormatException e) {
            throw new IdException(ID_STRING_IS_NOT_A_LONG);
        }
    }
    
    public static boolean isAnInitializedId(String id) throws IdException{
        long longId = parseIdStringToLong(id);
        return idIsInitialized(longId);
    }
}
