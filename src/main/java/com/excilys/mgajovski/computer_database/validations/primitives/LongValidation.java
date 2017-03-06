package com.excilys.mgajovski.computer_database.validations.primitives;

import com.excilys.mgajovski.computer_database.exceptions.mapping.IdException;

/**
 * @author Gajovski Maxime
 * @date 27 févr. 2017
 */
public final class LongValidation {

    private static final String ID_INITIALIZED = "id already initialized : ";

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
}
