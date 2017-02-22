package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;


/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public enum MySQLConnection{
    INSTANCE;
	
	private final Logger LOGGER = Logger.getLogger(MySQLConnection.class.getName());
	
	private static final String DATABASE_CONNECTED = "You're now connected to the database\n";
	private static final String DATABASE_ERR = "An error occurred. Maybe user/password is invalid\n";
	
    private Connection databaseConnection;
    
	private MySQLConnection() {
		
		if(databaseConnection != null) return;
		
		Properties databaseProps = new Properties();
		FileInputStream in;
		
		try{
			in = new FileInputStream("/home/excilys/workspace/Computer-Database/src/db.properties");
			try {
				databaseProps.load(in);
			}catch (IOException e) {
				e.printStackTrace();
			}finally{
				in.close();
			}
		}catch(IOException e){
			e.printStackTrace();
		}

		String url 		= databaseProps.getProperty("jdbc.url");
		String username = databaseProps.getProperty("jdbc.username");
		String password = databaseProps.getProperty("jdbc.password");

        try {
        	databaseConnection= (Connection) DriverManager.getConnection(url, username, password);
            if (databaseConnection != null) {
            	LOGGER.info(DATABASE_CONNECTED);
            }
        } catch (SQLException ex) {
        	LOGGER.warning(DATABASE_ERR);
            ex.printStackTrace();
        }
    }
	
	public Connection getDatabaseConnection(){
		return databaseConnection;
	}

}