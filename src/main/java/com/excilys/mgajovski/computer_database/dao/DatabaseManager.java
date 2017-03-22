package com.excilys.mgajovski.computer_database.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */

@Repository
@Scope("singleton")
public class DatabaseManager {

  private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseManager.class.getName());

  public static final String CONNECTION_ROLLBACK_SUCCESS = "connection rollback successfully.";
  public static final String CONNECTION_CLOSED_SUCCESS = "connection closed successfully.";
  public static final String CONNECTION_OPENED_SUCCESS = "connection opened successfully.";
  public static final String CONNECTION_COMMITTED_SUCCESS = "connection committed successfully.";

  public static final String CONFIG_FILENAME = "db.properties";
  public static final String KEY_CLASS_NAME = "dataSourceClassName";
  public static final String KEY_SERVER_NAME = "dataSource.serverName";
  public static final String KEY_DATABASE_NAME = "dataSource.databaseName";
  public static final String KEY_PORT_NUMBER = "dataSource.port";
  public static final String KEY_USER = "dataSource.user";
  public static final String KEY_PASSWORD = "dataSource.password";
  public static final String KEY_MIN_SIZE_POOL = "dataSource.minimumPoolSize";

  private static HikariDataSource hikariDataSource;
  private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
  
  @PostConstruct
  public void initialize(){

    Properties prefs = new Properties();

    if (new File(CONFIG_FILENAME).exists()) {
      if (LOGGER.isInfoEnabled()) {
        LOGGER.info("Chargement config à partir du fichier " + CONFIG_FILENAME);
      }
      try (FileInputStream file = new FileInputStream(new File(CONFIG_FILENAME))) {
        prefs.load(file);
        file.close();
      } catch (IOException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
      }

    } else {
      InputStream inputStream = DatabaseManager.class.getClassLoader()
          .getResourceAsStream(CONFIG_FILENAME);
      if (inputStream != null) {
        try {
          prefs.load(inputStream);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      } else {
        prefs = System.getProperties();
      }
    }

    prefs.getProperty(KEY_CLASS_NAME, "");
    prefs.getProperty(KEY_SERVER_NAME, "");
    prefs.getProperty(KEY_DATABASE_NAME, "");
    prefs.getProperty(KEY_PORT_NUMBER, "");
    prefs.getProperty(KEY_USER, "");
    prefs.getProperty(KEY_PASSWORD, "");

    HikariConfig config = new HikariConfig(prefs);
    hikariDataSource = new HikariDataSource(config);
    hikariDataSource.setMaximumPoolSize(400); 
  }

  /**
   * This method return a connection to the database if it's possible.
   * 
   * @return a connection to the database.
   * @throws SQLException
   *           if it's not possible to get connection.
   */
  public Connection getConnection() throws SQLException {
    Connection connection = null;
    try {
      connection = hikariDataSource.getConnection();
      connection.setAutoCommit(false);
    } catch (SQLException sqlException) {
      throw sqlException;
    }
    LOGGER.info(CONNECTION_OPENED_SUCCESS);
    threadLocal.set(connection);
    return (Connection) threadLocal.get();
  }

  public void closeConnection(Connection connection) throws SQLException {
    try {
      connection.close();
    } catch (SQLException sqlException) {
      throw sqlException;
    }
    LOGGER.info(CONNECTION_CLOSED_SUCCESS);
  }

  public void commit(Connection connection) throws SQLException {
    try {
      connection.commit();
    } catch (SQLException sqlException) {
      throw sqlException;
    }
    LOGGER.info(CONNECTION_COMMITTED_SUCCESS);
  }

  public void rollbackConnection(Connection connection) throws SQLException {
    try {
      connection.rollback();
    } catch (SQLException sqlException) {
      throw sqlException;
    }
    LOGGER.info(CONNECTION_ROLLBACK_SUCCESS);
  }

}