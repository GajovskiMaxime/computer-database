package com.excilys.mgajovski.computer_database.dto.impl;


/**
 * @author Gajovski Maxime
 * @date 3 mars 2017
 * DTO class for company entity.
 */
public class CompanyDTOImpl {

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
