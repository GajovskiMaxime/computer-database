package com.excilys.mgajovski.computer_database.dto.mappers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.excilys.mgajovski.computer_database.dto.DTOMapper;
import com.excilys.mgajovski.computer_database.dto.impl.CompanyDTO;
import com.excilys.mgajovski.computer_database.dto.impl.CompanyDTOImpl;
import com.excilys.mgajovski.computer_database.dto.impl.ComputerDTO;
import com.excilys.mgajovski.computer_database.dto.impl.ComputerDTOImpl;
import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.entities.Computer;

/**
 * @author Gajovski Maxime
 * @date 3 mars 2017
 */
@Component
public class ComputerDTOMapperImpl implements ComputerDTOMapper {

  private static final String COMPUTER_EMPTY_OR_NEGATIVE_ID = "Computer id not set or negative value.";
  private static final String COMPUTER_NULL = "Computer seems to be null.";

  @Autowired
  CompanyDTOImpl companyDTO;
   
  @Autowired
  ComputerDTOImpl computerDTO;
  
  @Autowired
  CompanyDTOMapperImpl companyMapper;
  
  public ComputerDTO transformToDTO(Computer computer) {

    if (computer == null) { 
      throw new IllegalArgumentException(COMPUTER_NULL);
    }
    if (computer.getId() < 0) {
      throw new IllegalArgumentException(COMPUTER_EMPTY_OR_NEGATIVE_ID);
    }
    companyMapper = new CompanyDTOMapperImpl();
    ComputerDTOImpl computerDTO = new ComputerDTOImpl();

    String introduced = computer.getIntroduced() == null ? null
        : computer.getIntroduced().toString();

    String discontinued = computer.getDiscontinued() == null ? null
        : computer.getDiscontinued().toString();

    computerDTO.setId(computer.getId());
    computerDTO.setName(computer.getName());
    computerDTO.setDiscontinued(discontinued);
    computerDTO.setIntroduced(introduced);

    if (computer.getCompany() == null) {
      computerDTO.companyIsNull();
    } else {
      companyDTO = (CompanyDTOImpl) companyMapper.transformToDTO(computer.getCompany());
      computerDTO.setCompany(companyDTO);
    }

    return computerDTO;
  }

  public Computer transformFromDTO(ComputerDTO computerDTO) {

    LocalDate introduced = computerDTO.getDiscontinued().isEmpty() ? null
        : LocalDate.parse(computerDTO.getIntroduced());

    LocalDate discontinued = computerDTO.getDiscontinued().isEmpty() ? null
        : LocalDate.parse(computerDTO.getDiscontinued());

    Company company = null;
    if (computerDTO.getCompany() != null) {
      company = Company.builder().id(computerDTO.getCompany().getId())
          .name(computerDTO.getCompany().getName()).build();
    }

    Computer computer = Computer.builder().id(computerDTO.getId()).name(computerDTO.getName())
        .discontinued(discontinued).introduced(introduced).company(company).build();
    return computer;
  }

  @Override
  public List<ComputerDTO> transformListToDTOList(List<Computer> k) {
    List<ComputerDTO> computerDtos = new ArrayList<>();
    for (Computer computer : k) {
      computerDtos.add(transformToDTO(computer));
    }
    return computerDtos;
  }

  @Override
  public List<Computer> transformListFromDTOList(List<ComputerDTO> i) {
    List<Computer> computers = new ArrayList<>();
    for (ComputerDTO computerDto : i) {
      computers.add(transformFromDTO(computerDto));
    }
    return computers;
  }

}
