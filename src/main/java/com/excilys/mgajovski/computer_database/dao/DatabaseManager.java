package com.excilys.mgajovski.computer_database.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
//TODO Faire en deux étapes.
public enum DatabaseManager {
    INSTANCE;

    private final static Logger LOGGER = LoggerFactory.getLogger(DatabaseManager.class.getName());
    

        
    public static final String CONFIG_FILENAME = "db.properties";
    public static final String KEY_CLASS_NAME = "dataSourceClassName";
    public static final String KEY_SERVER_NAME = "dataSource.serverName";
    public static final String KEY_DATABASE_NAME = "dataSource.databaseName";
    public static final String KEY_PORT_NUMBER = "dataSource.port";
    public static final String KEY_USER = "dataSource.user";
    public static final String KEY_PASSWORD = "dataSource.password";
/*    public static final String KEY_CREATE_SCRIPTS = "createScripts";
    public static final String KEY_INSERT_SCRIPTS = "insertScripts";*/
    public static final String KEY_MIN_SIZE_POOL = "dataSource.minimumPoolSize";
    public static final String KEY_MAX_SIZE_POOL = "dataSource.max-active";

    private static HikariDataSource hikariDataSource;
    
    private static final String CLASS_NAME;
    private static final String SERVER_NAME;
    private static final String DATABASE_NAME;
    private static final String PORT_NUMBER;
    private static final String PASSWORD;
    private static final String USER;
    /*private static final String CREATE_SCRIPTS; 
    private static final String INSERT_SCRIPTS;*/
/*    private static final String MIN_SIZE_POOL;*/
    private static final String MAX_SIZE_POOL;

    
/*    private static final String DATABASE_CONNECTED = "You're now connected to the database\n";
    private static final String DATABASE_ERR = "An error occurred. Maybe user/password is invalid\n";
*/
    /*
    private Connection databaseConnection;
    */
    
     static {
        
        // Chargement des préférences de connexions
        Properties prefs = new Properties();

        // Chargement de l'objet Preferences
        if (new File(CONFIG_FILENAME).exists()) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Chargement config à partir du fichier "+CONFIG_FILENAME);
            }
            try (FileInputStream file = new FileInputStream(new File(CONFIG_FILENAME))) {
                prefs.load(file);
                file.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            InputStream inputStream = DatabaseManager.class.getClassLoader().getResourceAsStream(CONFIG_FILENAME);
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

        CLASS_NAME = prefs.getProperty(KEY_CLASS_NAME, "");
        SERVER_NAME = prefs.getProperty(KEY_SERVER_NAME, "");
        DATABASE_NAME = prefs.getProperty(KEY_DATABASE_NAME, "");
        PORT_NUMBER = prefs.getProperty(KEY_PORT_NUMBER, "");
        USER = prefs.getProperty(KEY_USER, "");
        PASSWORD = prefs.getProperty(KEY_PASSWORD, "");
/*        CREATE_SCRIPTS = prefs.getProperty(KEY_CREATE_SCRIPTS, "");
        INSERT_SCRIPTS = prefs.getProperty(KEY_INSERT_SCRIPTS, "");*/
     /*   MIN_SIZE_POOL = prefs.getProperty(KEY_MIN_SIZE_POOL, "");*/
        MAX_SIZE_POOL = prefs.getProperty(KEY_MAX_SIZE_POOL, "");
        
        HikariConfig config = new HikariConfig(prefs);
        hikariDataSource = new HikariDataSource(config);
        hikariDataSource.setMaximumPoolSize(50);
        try {
            /*Class.forName("com.mysql.jdbc.Driver").newInstance();*/
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
      /*  
        if (!CREATE_SCRIPTS.isEmpty() || INSERT_SCRIPTS.isEmpty()) {
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                
                connection.setAutoCommit(false);
                
                List<String> list = new ArrayList<>();
                if (!CREATE_SCRIPTS.isEmpty()) {
                    list.addAll(Arrays.asList(CREATE_SCRIPTS.split(";")));
                }
                if (!INSERT_SCRIPTS.isEmpty()) {
                    list.addAll(Arrays.asList(INSERT_SCRIPTS.split(";")));
                }
                
                System.out.println(list);
                
                for (String fileLink : list) {
                    StringBuilder builder = new StringBuilder();
                    //noinspection ConstantConditions
                    File file = new File(DatabaseManager.class.getClassLoader().getResource(fileLink).getFile());
                    
                    try (Scanner scanner = new Scanner(file)) {
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            if (!line.trim().isEmpty()) {
                                builder.append(line);
                            
                                if (line.trim().endsWith(";")) {
                                    Statement statement = connection.createStatement();
                                    statement.executeUpdate(builder.toString());
                                    builder = new StringBuilder();
                                }
                            }
                        }
                        
                        Statement statement = connection.createStatement();
                        statement.executeUpdate(builder.toString());*/
                   /* } catch (IOException e) {
                        try {
                            throw new Exception("Error with the file "+fileLink, e);
                        } catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }*/
                //}
               /* 
                connection.setAutoCommit(true);
                
            } catch (SQLException e1) {
                LOGGER.error(e1.getMessage(), e1);
                try {
                    throw new Exception(e1);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }*/

        // Mise en plase du pool de connexion
     
    }
    

    
/*    *//**
     * MySQLConnection method open the properties and open a connection into the database.
     *//*
    DatabaseManager() {

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
    }*/
    
    public synchronized Connection getConnection() throws SQLException {

        return hikariDataSource.getConnection();
    }
}