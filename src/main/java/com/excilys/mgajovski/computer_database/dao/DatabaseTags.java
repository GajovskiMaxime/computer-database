package com.excilys.mgajovski.computer_database.dao;


/**
 * @author Gajovski Maxime
 * @date 23 mars 2017
 */
public enum DatabaseTags {

  FILE_NAME("db.properties"),
  SERVER_NAME("database.serverName"),
  PORT("database.port"),
  USER_NAME("database.userName"),
  PASSWORD("database.password"),
  DRIVER_CLASS_NAME("database.driverClassName"),
  DATABASE_NAME("database.name");


  private final String property;

  DatabaseTags(String property) {
    this.property = property;
  }

  public String toString() {
    return property;
  }
  
 
  
}
