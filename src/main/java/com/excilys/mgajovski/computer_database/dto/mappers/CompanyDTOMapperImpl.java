package com.excilys.mgajovski.computer_database.dto.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.excilys.mgajovski.computer_database.dto.DTO;
import com.excilys.mgajovski.computer_database.dto.DTOMapper;
import com.excilys.mgajovski.computer_database.dto.impl.CompanyDTO;
import com.excilys.mgajovski.computer_database.dto.impl.CompanyDTOImpl;
import com.excilys.mgajovski.computer_database.entities.Company;

/**
 * @author Gajovski Maxime
 * @date 3 mars 2017
 */
@Component
public class CompanyDTOMapperImpl implements CompanyDTOMapper {

  
  private static final String COMPANY_EMPTY_OR_NEGATIVE_ID = "Company id not set or negative value.";
  private static final String COMPANY_NULL = "Company seems to be null.";

  public CompanyDTO transformToDTO(Company company) {

    if (company == null) {
      throw new IllegalArgumentException(COMPANY_NULL);
    }
    if (company.getId() < 0) {
      throw new IllegalArgumentException(COMPANY_EMPTY_OR_NEGATIVE_ID);
    }
    CompanyDTOImpl companyDTO = new CompanyDTOImpl();
    companyDTO.setId(company.getId());
    companyDTO.setName(company.getName());
    return companyDTO;
  }

  public Company transformFromDTO(CompanyDTO companyDTO) {

    if (companyDTO == null) {
      throw new IllegalArgumentException(COMPANY_NULL);
    }

    Company company = Company.builder().id(companyDTO.getId()).name(companyDTO.getName()).build();
    return company;
  }

  @Override
  public List<CompanyDTO> transformListToDTOList(List<Company> k) {
    List<CompanyDTO> companyDtos = new ArrayList<>();
    for (Company company : k) {
      companyDtos.add(transformToDTO(company));
    }
    return companyDtos;
  }

  @Override
  public List<Company> transformListFromDTOList(List<CompanyDTO> i) {
    List<Company> companies = new ArrayList<>();
    for (CompanyDTO companyDto : i) {
      companies.add(transformFromDTO(companyDto));
    }
    return companies;
  }

}