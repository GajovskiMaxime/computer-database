package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public class MySQLConnection{
    
	private static final Logger logger = Logger.getLogger(MySQLConnection.class.getName());


    private static Connection databaseConnection;
    
    
	public static Connection getInstance() {
		
		if(databaseConnection == null){
	        try {
	        	databaseConnection= (Connection) DriverManager.getConnection(DatabaseCsts.DB_LINK);
	            if (databaseConnection != null) {
	               logger.info("Connected to the database");
	            }
	        } catch (SQLException ex) {
	        	logger.warning("An error occurred. Maybe user/password is invalid");
	            ex.printStackTrace();
	        }
		}
        return databaseConnection;
    }

}