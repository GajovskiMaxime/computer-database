package com.excilys.mgajovski.computer_database.validations.primitives;

import com.excilys.mgajovski.computer_database.exceptions.mapping.NameException;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
public final class StringValidation {

    public static final String VALID_NAME_ERR = "null or empty name : ";

    /**
     * This method looks if the string name is valid or not.
     * @param name : the name to test.
     * @return true if the string name is correctly fulfilled, else otherwise.
     * @throws NameException : if the name is null or empty.
     */
    public static boolean hasValidName(String name) throws NameException {
        boolean validName = name != null && !name.isEmpty();
        if (!validName) {
            throw new NameException(VALID_NAME_ERR + name);
        }
        return validName;
    }

}
