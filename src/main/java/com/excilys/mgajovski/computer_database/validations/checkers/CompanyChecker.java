package com.excilys.mgajovski.computer_database.validations.checkers;

import com.excilys.mgajovski.computer_database.dto.impl.CompanyDTOImpl;
import com.excilys.mgajovski.computer_database.exceptions.IdException;
import com.excilys.mgajovski.computer_database.exceptions.NameException;
import com.excilys.mgajovski.computer_database.validations.primitives.LongValidation;
import com.excilys.mgajovski.computer_database.validations.primitives.StringValidation;

/**
 * @author	Gajovski Maxime
 * @date	3 mars 2017
 */
public final class CompanyChecker {

  
  public static boolean DTOIsValid(CompanyDTOImpl companyDTO) throws IdException, NameException{
    
      return LongValidation.idIsNotInitialized(companyDTO.getCompanyId()) &&  
              StringValidation.hasValidName(companyDTO.getCompanyName());
  }
}
