package com.excilys.mgajovski.computer_database;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.util.FileHelper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.mgajovski.computer_database.dao.DatabaseManager;
import com.excilys.mgajovski.computer_database.dao.DatabaseProperties;
import com.excilys.mgajovski.computer_database.dao.DatabaseTags;
import com.excilys.mgajovski.computer_database.dao.impl.CompanyDAOImpl;
import com.excilys.mgajovski.computer_database.dao.interfaces.CompanyDAO;
import com.excilys.mgajovski.computer_database.dao.mappers.CompanyMapper;
import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.factories.CompanyFactory;



/**
 * @author	Gajovski Maxime
 * @date	23 mars 2017
 */
@Component
public class DatabaseTest {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseTest.class.getName());

  
  @Autowired
  DatabaseManager databaseManager;

  @Before
  public void loadDatabaseProperties() throws ClassNotFoundException {
    ClassLoader classLoader = getClass().getClassLoader();
    File schema = new File(classLoader.getResource("sql/1-SCHEMA.sql").getFile());
    File privileges = new File(classLoader.getResource("sql/2-PRIVILEGES.sql").getFile());
    File entries = new File(classLoader.getResource("sql/3-ENTRIES.sql").getFile());

    DatabaseProperties databaseProperties = new DatabaseProperties(DatabaseTest.class);  

    try {
      Connection connection = databaseManager.getConnection();
      ScriptRunner runner = new ScriptRunner(connection);
      runner.runScript(new FileReader(schema));
      runner.runScript(new FileReader(privileges));
      runner.runScript(new FileReader(entries));
      databaseManager.commit(connection);
      runner.closeConnection();
      
    } catch (SQLException e) {
      LOGGER.error(e.getMessage(), e);
    } catch (FileNotFoundException e) {
      LOGGER.error(e.getMessage(), e);
    }
  }
//  
//  @Before
//  public void importDataSet() {
//    companies = CompanyFactory.create(COMPANY_DB_SIZE);
//    IDataSet dataSet = null;
//    try {
//      dataSet = CompanyMapper.companiesToDataSet(companies);
//      cleanlyInsert(dataSet);
//    } catch (DataSetException e) {
//      LOGGER.error(e.getMessage(), e);
//    }
//  }
//  
//  private void cleanlyInsert(IDataSet dataSet) {
//    
//    IDatabaseTester databaseTester = null;
//    try {
//      databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, USER, PASSWORD, "cdb");
//      databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
//      databaseTester.setDataSet(dataSet);
//      databaseTester.onSetup();
//      
//    } catch (Exception exception) {
//      LOGGER.error(exception.getMessage(), exception);
//    }
//  }
//  
//
//
  
  @Test
  public void findsAndReadsExistingPersonByFirstName() throws Exception {
    
  }
  

  
  
}
