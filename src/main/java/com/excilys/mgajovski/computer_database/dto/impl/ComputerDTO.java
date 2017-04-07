package com.excilys.mgajovski.computer_database.dto.impl;

import com.excilys.mgajovski.computer_database.dto.DTO;
import com.excilys.mgajovski.computer_database.entities.Computer;

/**
 * @author Gajovski Maxime
 * @date 6 avr. 2017
 */
public interface ComputerDTO extends DTO<Computer>{

  CompanyDTOImpl getCompany();

  void setCompany(CompanyDTOImpl company);

  void companyIsNull();

  long getId();

  void setId(long computerId);

  String getName();

  void setName(String computerName);

  String getIntroduced();

  void setIntroduced(String introduced);

  String getDiscontinued();

  void setDiscontinued(String discontinued);

}
