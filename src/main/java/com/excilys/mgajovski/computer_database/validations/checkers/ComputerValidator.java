package com.excilys.mgajovski.computer_database.validations.checkers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.excilys.mgajovski.computer_database.dto.impl.ComputerDTOImpl;
import com.excilys.mgajovski.computer_database.utils.ErrorTags;
/**
 * @author Gajovski Maxime
 * @date 3 avr. 2017
 */
@Component
@Scope("singleton")
public class ComputerValidator implements Validator {

  public static final Logger LOGGER = LoggerFactory.getLogger(ComputerValidator.class.getName());

  public ComputerValidator() {

  }

  @Override
  public boolean supports(Class<?> clazz) {
    return ComputerDTOImpl.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    ComputerDTOImpl computerDTO = (ComputerDTOImpl) target;
    ComputerChecker.dtoIsValidWithIdInit(computerDTO, false).forEach((k, v) -> {
      if (v != ErrorTags.SUCCESS) {
        errors.rejectValue(k.toString(), v.toString());
      }
    });
  }
}
