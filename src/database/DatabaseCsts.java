package database;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public final class DatabaseCsts {
	
	public static final String HOST		= "192.168.56.100";
	public static final String PORT		= "3306";
	public static final String SGBD		= "mysql";
	public static final String DB_NAME	= "computer-database-db";

	public static final String USER 	= "root";
	public static final String USER_PWD = "root";
	
	public static final String FIRST_ARG= "zeroDateTimeBehavior=convertToNull";

	public static final String DB_LINK = 
			"jdbc:" + SGBD + 
			"://" + HOST + ":" + PORT + "/" + DB_NAME + "?"  +
			"user=" + USER + "&password=" + USER_PWD 
			+ "&" + FIRST_ARG ;

}
