package com.excilys.mgajovski.computer_database.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
//TODO Faire en deux étapes.
public enum MySQLConnection {
    INSTANCE;

    private final Logger LOGGER = Logger.getLogger(MySQLConnection.class.getName());
    private static final String DATABASE_CONNECTED = "You're now connected to the database\n";
    private static final String DATABASE_ERR = "An error occurred. Maybe user/password is invalid\n";

    private Connection databaseConnection;

    /**
     * MySQLConnection method open the properties and open a connection into the database.
     */
    MySQLConnection() {

        if (databaseConnection != null) {
            return;
        }
        Properties databaseProps = new Properties();
        FileInputStream in;

        try {
            in = new FileInputStream("/home/maxime/workspace/computer-database/src/main/java/com/excilys/mgajovski/computer_database/db.properties");
            try {
                databaseProps.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String url = databaseProps.getProperty("jdbc.url");
        String username = databaseProps.getProperty("jdbc.username");
        String password = databaseProps.getProperty("jdbc.password");

        try {
          try {
            Class.forName("com.mysql.jdbc.Driver");
          } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
            databaseConnection = (Connection) DriverManager.getConnection(url, username, password);
            if (databaseConnection != null) {
                LOGGER.info(DATABASE_CONNECTED);
            }
        } catch (SQLException ex) {
            LOGGER.warning(DATABASE_ERR);
            ex.printStackTrace();
        }
    }

    public Connection getDatabaseConnection() {
        return databaseConnection;
    }

}