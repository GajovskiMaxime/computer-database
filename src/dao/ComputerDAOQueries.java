package dao;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public class ComputerDAOQueries {

	public static final String 	COMPUTER_TABLE = "computer";
	
	public static final String 	CREATE_COMPUTER
		= "INSERT INTO "+ COMPUTER_TABLE + "(name, introduced, discontinued, company_id) VALUES(?, ?, ?, ?)";

	public final static String 	SELECT_ALL_COMPUTERS
		= "SELECT * FROM " + COMPUTER_TABLE;
	
	public final static String 	SELECT_ALL_COMPUTERS_NAMES
		= "SELECT name FROM "+ COMPUTER_TABLE;

	public final static String 	SELECT_COMPUTER_WITH_ID
		= "SELECT * FROM "+ COMPUTER_TABLE +" WHERE id=?";
	
	public final static String 	DELETE_COMPUTER_WITH_ID
		= "DELETE FROM " + COMPUTER_TABLE + " WHERE id=?";
	
	public final static String 	SELECT_NAMES_BY_PAGE
				= "SELECT name FROM " + COMPUTER_TABLE + " LIMIT ? OFFSET ?";
	
	public final static String 	SELECT_ALL_BY_PAGE
				= "SELECT * FROM " + COMPUTER_TABLE + " LIMIT ? OFFSET ?";
	
	public final static String UPDATE_COMPUTER
				= "UPDATE " + COMPUTER_TABLE + " SET "
						+ "name = ?, introduced = ?, discontinued = ?, company_id = ? "
						+ " WHERE id= ";

	public final static String LAST_ROW_INDEX
				= "SELECT id FROM " + COMPUTER_TABLE + " ORDER BY id DESC LIMIT 1";
	
	public final static String RIGHT_JOIN = "SELECT * FROM computer RIGHT JOIN company ON computer.company_id = company.id";
	public final static String RIGHT_JOIN_WITH_ID = 
			"SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id,  comp.name as company_name  FROM computer c "
			+ "RIGHT JOIN company comp  ON c.company_id = comp.id WHERE c.id = ?";


}
