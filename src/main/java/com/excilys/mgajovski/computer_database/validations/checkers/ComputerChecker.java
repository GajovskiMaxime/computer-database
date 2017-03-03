package com.excilys.mgajovski.computer_database.validations.checkers;

import com.excilys.mgajovski.computer_database.dto.impl.ComputerDTOImpl;
import com.excilys.mgajovski.computer_database.exceptions.DateException;
import com.excilys.mgajovski.computer_database.exceptions.IdException;
import com.excilys.mgajovski.computer_database.exceptions.NameException;
import com.excilys.mgajovski.computer_database.validations.primitives.DateValidation;
import com.excilys.mgajovski.computer_database.validations.primitives.LongValidation;
import com.excilys.mgajovski.computer_database.validations.primitives.StringValidation;

/**
 * @author	Gajovski Maxime
 * @date	3 mars 2017
 */
public final class ComputerChecker {
  
  public static boolean DTOIsValid(ComputerDTOImpl computerDTO) throws IdException, NameException, DateException{
    
      return  LongValidation.idIsNotInitialized(computerDTO.getComputerId()) &&  
              StringValidation.hasValidName(computerDTO.getComputerName()) &&
              DateValidation.formatIsValid(computerDTO.getIntroduced()) &&
              DateValidation.formatIsValid(computerDTO.getDiscontinued());
  }
}
