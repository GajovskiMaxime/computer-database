package com.excilys.mgajovski.computer_database.utils;

/**
 * @author	Gajovski Maxime
 * @date	4 avr. 2017
 */
public enum SpringPaths {

  ID("id"),
  NAME("name"),
  INTRODUCED("introduced"),  
  DISCONTINUED("discontinued"),
  COMPANY_ID("company.id");
  
  private final String property;

  SpringPaths(String property) {
    this.property = property;
  }

  public String toString() {
    return property;
  }
}
