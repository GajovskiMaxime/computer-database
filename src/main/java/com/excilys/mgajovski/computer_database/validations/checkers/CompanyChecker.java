package com.excilys.mgajovski.computer_database.validations.checkers;

import com.excilys.mgajovski.computer_database.dto.impl.CompanyDTOImpl;
import com.excilys.mgajovski.computer_database.exceptions.mapping.IdException;
import com.excilys.mgajovski.computer_database.exceptions.mapping.NameException;
import com.excilys.mgajovski.computer_database.validations.primitives.LongValidation;
import com.excilys.mgajovski.computer_database.validations.primitives.StringValidation;

/**
 * @author Gajovski Maxime
 * @date 3 mars 2017
 */
public final class CompanyChecker {

    /**
     * This method looks all companyDTO fields and return if they're all correctly fulfilled.
     * @param companyDTO : the corresponding DTO for company entity.
     * @return true if all fields are correctly fulfilled, false otherwise.
     * @throws IdException : if the company id field is not correctly fulfilled.
     * @throws NameException : if the company name field is not correctly fulfilled.
     */
    public static boolean dtoIsValid(CompanyDTOImpl companyDTO) throws IdException, NameException {

        return LongValidation.idIsNotInitialized(companyDTO.getCompanyId())
                && StringValidation.hasValidName(companyDTO.getCompanyName());
    }
}
