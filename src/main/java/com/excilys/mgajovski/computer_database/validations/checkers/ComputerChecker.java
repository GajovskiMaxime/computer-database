package com.excilys.mgajovski.computer_database.validations.checkers;

import java.util.HashMap;
import java.util.Map;

import com.excilys.mgajovski.computer_database.dto.impl.ComputerDTOImpl;
import com.excilys.mgajovski.computer_database.utils.ErrorTags;
import com.excilys.mgajovski.computer_database.utils.SpringPaths;
import com.excilys.mgajovski.computer_database.validations.primitives.DateValidation;
import com.excilys.mgajovski.computer_database.validations.primitives.LongValidation;
import com.excilys.mgajovski.computer_database.validations.primitives.StringValidation;

/**
 * @author Gajovski Maxime
 * @date 3 mars 2017
 */

public final class ComputerChecker {

  public static Map<SpringPaths, ErrorTags> dtoIsValidWithIdInit(ComputerDTOImpl computerDTO,
      boolean idIsInit) {

    Map<SpringPaths, ErrorTags> errorMap = new HashMap<>();

//    if (idIsInit) {
//      errorMap.put(SpringPaths.ID, LongValidation.idIsInitialized(computerDTO.getId()));
//    } else {
//      errorMap.put(SpringPaths.ID, LongValidation.idIsNotInitialized(computerDTO.getId()));
//    }

    errorMap.put(SpringPaths.NAME, StringValidation.hasValidName(computerDTO.getName()));
    errorMap.put(SpringPaths.INTRODUCED, DateValidation.formatIsValid(computerDTO.getIntroduced()));
    errorMap.put(SpringPaths.DISCONTINUED,
        DateValidation.formatIsValid(computerDTO.getDiscontinued()));
    if (errorMap.get(SpringPaths.INTRODUCED) == ErrorTags.SUCCESS
        && errorMap.get(SpringPaths.DISCONTINUED) == ErrorTags.SUCCESS) {
      errorMap.put(SpringPaths.DISCONTINUED, DateValidation
          .datesAreInGoodTimeOrder(computerDTO.getIntroduced(), computerDTO.getDiscontinued()));
    }
//    errorMap.put(SpringPaths., value)
    return errorMap;
  }
}
