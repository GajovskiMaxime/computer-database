package com.excilys.mgajovski.computer_database.dto.impl;

import org.springframework.stereotype.Component;

/**
 * @author Gajovski Maxime
 * @date 3 mars 2017
 * DTO class for company entity.
 */
@Component
public class CompanyDTOImpl implements CompanyDTO{

  private long id;
  private String name;

  public long getId() {
    return id;
  }
  public void setId(long companyId) {
    this.id = companyId;
  }
  public String getName() {
    return name;
  }
  public void setName(String companyName) {
    this.name = companyName;
  }

}
