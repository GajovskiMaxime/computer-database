package com.excilys.mgajovski.computer_database.validations.primitives;

import com.excilys.mgajovski.computer_database.exceptions.mapping.NameException;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
public final class StringValidation {

    public static final String VALID_NAME_ERR = "Name seems to be null or empty !";
    public static final String NAME_REGEX = "[a-zA-Z0-9-_ ./]*";
    public static final String NAME_REGEX_ERR = "Name mismatch regex : " + NAME_REGEX ;
    
    /**
     * This method looks if the string name is valid or not.
     * @param name : the name to test.
     * @return true if the string name is correctly fulfilled, else otherwise.
     * @throws NameException : if the name is null, empty or mismatch regex.
     */
    public static boolean hasValidName(String name) throws NameException {
        
        if (name == null || name.isEmpty()) {
            throw new NameException(VALID_NAME_ERR + name);
        }
        if(!name.matches(NAME_REGEX)){
            throw new NameException(NAME_REGEX_ERR);
        }
        return true;
    }    
}
