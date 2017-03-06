package com.excilys.mgajovski.computer_database.validations.checkers;

import com.excilys.mgajovski.computer_database.dto.impl.ComputerDTOImpl;
import com.excilys.mgajovski.computer_database.exceptions.mapping.DateException;
import com.excilys.mgajovski.computer_database.exceptions.mapping.IdException;
import com.excilys.mgajovski.computer_database.exceptions.mapping.NameException;
import com.excilys.mgajovski.computer_database.validations.primitives.DateValidation;
import com.excilys.mgajovski.computer_database.validations.primitives.LongValidation;
import com.excilys.mgajovski.computer_database.validations.primitives.StringValidation;

/**
 * @author Gajovski Maxime
 * @date 3 mars 2017
 */

public final class ComputerChecker {

    /**
     * This method looks all computerDTO fields and return if they're all correctly fulfilled.
     * @param computerDTO : the corresponding DTO for computer entity.
     * @return true if all fields are correctly fulfilled, false otherwise.
     * @throws IdException : if the computer id field is not correctly fulfilled.
     * @throws NameException : if the computer name field is not correctly fulfilled.
     * @throws DateException : if the computer introduced & discontinued fields are not correctly fulfilled.
     */
    public static boolean dtoIsValid(ComputerDTOImpl computerDTO) throws IdException, NameException, DateException {

        return LongValidation.idIsNotInitialized(computerDTO.getComputerId())
                && StringValidation.hasValidName(computerDTO.getComputerName())
                && DateValidation.formatIsValid(computerDTO.getIntroduced())
                && DateValidation.formatIsValid(computerDTO.getDiscontinued())
                && DateValidation.datesAreInGoodTimeOrder(computerDTO.getIntroduced(), computerDTO.getDiscontinued());
    }
}
