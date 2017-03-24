package com.excilys.mgajovski.computer_database.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
