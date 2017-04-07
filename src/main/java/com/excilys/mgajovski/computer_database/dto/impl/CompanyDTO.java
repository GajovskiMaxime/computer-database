package com.excilys.mgajovski.computer_database.dto.impl;

import com.excilys.mgajovski.computer_database.dto.DTO;
import com.excilys.mgajovski.computer_database.entities.Company;

/**
 * @author Gajovski Maxime
 * @date 6 avr. 2017
 */
public interface CompanyDTO extends DTO<Company> {

  long getId();

  void setId(long companyId);

  String getName();

  void setName(String companyName);
}
