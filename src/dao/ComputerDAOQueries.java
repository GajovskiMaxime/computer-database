package dao;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public class ComputerDAOQueries {


	public static final int 	COMPUTERS_PER_PAGE = 10;
	public static final String 	COMPUTER_TABLE = "computer";
	
	public static final String 	CREATE_COMPUTER
		= "INSERT INTO "+ COMPUTER_TABLE + "(name, introduced, discontinued, company_id) VALUES(?, ?, ?, ?)";

	public final static String 	SELECT_ALL_COMPUTERS
		= "SELECT * FROM " + COMPUTER_TABLE;
	
	public final static String 	SELECT_ALL_COMPUTERS_NAMES
		= "SELECT name FROM "+ COMPUTER_TABLE;

	public final static String 	SELECT_COMPUTER_WITH_ID
		= "SELECT * FROM "+ COMPUTER_TABLE +" WHERE id= ";
	
	public final static String 	DELETE_COMPUTER_WITH_ID
		= "DELETE FROM " + COMPUTER_TABLE + " WHERE id= ";
	
	public final static String 	SELECT_NAMES_BY_PAGE
				= "SELECT name FROM " + COMPUTER_TABLE + " LIMIT " + COMPUTERS_PER_PAGE + " OFFSET ";
	
	public final static String 	SELECT_ALL_BY_PAGE
				= "SELECT * FROM " + COMPUTER_TABLE + " LIMIT " + COMPUTERS_PER_PAGE + " OFFSET ";
}
