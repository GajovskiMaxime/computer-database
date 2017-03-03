package com.excilys.mgajovski.computer_database.dto.mappers;

import java.util.Optional;

import com.excilys.mgajovski.computer_database.dto.impl.CompanyDTOImpl;
import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.exceptions.IdException;
import com.excilys.mgajovski.computer_database.exceptions.NameException;
import com.excilys.mgajovski.computer_database.validations.checkers.CompanyChecker;

/**
 * @author	Gajovski Maxime
 * @date	3 mars 2017
 */
public class CompanyMapper {
  
  public static Optional<Company> transformDTO(CompanyDTOImpl companyDTO) throws IdException, NameException {
    
    if(!CompanyChecker.DTOIsValid(companyDTO)){
        return Optional.empty();
      }
      
      Company company = Company.builder()
          .id(companyDTO.getCompanyId())
          .name(companyDTO.getCompanyName())
          .build();
      
      return Optional.of(company);
  }
}