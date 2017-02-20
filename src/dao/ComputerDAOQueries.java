package dao;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public class ComputerDAOQueries {

	public static final String CREATE_COMPUTER
		= "INSERT INTO computer(name) VALUES(?)";

	public final static String SELECT_ALL_COMPUTERS
		= "SELECT * FROM computer";
	
	public final static String SELECT_COMPUTER_WITH_ID
		= "SELECT * FROM computer WHERE id= ";
	
	public final static String DELETE_COMPUTER_WITH_ID
		= "DELETE FROM computer WHERE id= ";
}
