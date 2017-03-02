package com.excilys.mgajovski.computer_database.managers;

import javax.annotation.ManagedBean;

import com.excilys.mgajovski.computer_database.validations.StringValidation;

/**
 * @author	Gajovski Maxime
 * @date	1 mars 2017
 */
@ManagedBean
public class ComputerDetailManager {
  
  private String name;
//  private DateLocal;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
//  public boolean hasValidName(){
//    return StringValidation.hasValidName(name);  
//  }
    
}
