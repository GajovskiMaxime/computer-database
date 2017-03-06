package com.excilys.mgajovski.computer_database.dto.mappers;

import com.excilys.mgajovski.computer_database.dto.impl.CompanyDTOImpl;
import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.exceptions.mapping.DTOMapperException;

import com.excilys.mgajovski.computer_database.exceptions.mapping.IdException;
import com.excilys.mgajovski.computer_database.exceptions.mapping.NameException;
import com.excilys.mgajovski.computer_database.validations.checkers.CompanyChecker;

/**
 * @author Gajovski Maxime
 * @date 3 mars 2017
 */
public class CompanyMapper {

   /**
    * This method transform a DTO object into the associated entity.
    * @param companyDTO : the object to transform.
    * @return a company object if all companyDTO fields are correctly fulfilled.
    * @throws DTOMapperException if one DTO field is not correctly fulfilled.
    */
    public static Company transformDTO(CompanyDTOImpl companyDTO) throws DTOMapperException {

        try {
            CompanyChecker.dtoIsValid(companyDTO);
        } catch (IdException | NameException e) {
            throw new DTOMapperException(e.getMessage(), e);
        }
        Company company = Company.builder()
                .id(companyDTO.getCompanyId())
                .name(companyDTO.getCompanyName())
                .build();

        return company;
    }
}