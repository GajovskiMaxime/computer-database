package com.excilys.mgajovski.computer_database.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author	Gajovski Maxime
 * @date	23 mars 2017
 */
public class DatabaseProperties {
  

  private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseTags.class.getName());
  private Properties databaseProperties;
  
  public DatabaseProperties(Class clazz){
    
    databaseProperties = new Properties();
    try {
      ClassLoader classLoader = clazz.getClassLoader();
      File databasePropertiesFile = new File(
          classLoader.getResource(DatabaseTags.FILE_NAME.toString()).getFile());
      databaseProperties.load(new FileInputStream(databasePropertiesFile));
    } catch (FileNotFoundException fileNotFoundException) {
      LOGGER.error(fileNotFoundException.getMessage(), fileNotFoundException);
    } catch (IOException ioException) {
      LOGGER.error(ioException.getMessage(), ioException);
    }
  }
  
  public String getPassword(){
    return databaseProperties.getProperty(DatabaseTags.PASSWORD.toString());
  }
  
  public String getPort(){
    return databaseProperties.getProperty(DatabaseTags.PORT.toString());
  }
  
  public String getServerName(){
    return databaseProperties.getProperty(DatabaseTags.SERVER_NAME.toString());
  }
  
  public String getUserName(){
    return databaseProperties.getProperty(DatabaseTags.USER_NAME.toString());
  }
  
  public String getDriverClassName(){
    return databaseProperties.getProperty(DatabaseTags.DRIVER_CLASS_NAME.toString());
  }
  
  public String getUrl(){

    return "jdbc:mysql://" + 
        databaseProperties.getProperty(DatabaseTags.SERVER_NAME.toString()) + ":" +
        databaseProperties.getProperty(DatabaseTags.PORT.toString()) + "/" + 
        databaseProperties.getProperty(DatabaseTags.DATABASE_NAME.toString());
  }
}
