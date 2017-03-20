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
    
    private static final String COMPANY_EMPTY_OR_NEGATIVE_ID = "Company id not set or negative value.";
    private static final String COMPANY_NULL = "Company seems to be null.";
    
    public static CompanyDTOImpl transformToDTO(Company company) {
        
        if(company == null){
            throw new IllegalArgumentException(COMPANY_NULL);
        }
        if(company.getId() < 0){
            throw new IllegalArgumentException(COMPANY_EMPTY_OR_NEGATIVE_ID);
        }
        
        CompanyDTOImpl companyDTO = new CompanyDTOImpl();
        companyDTO.setId(company.getId());
        companyDTO.setName(company.getName());
        
        return companyDTO;
    }
    
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
                .id(companyDTO.getId())
                .name(companyDTO.getName())
                .build();

        return company;
    }
}