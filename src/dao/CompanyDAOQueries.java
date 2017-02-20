package dao;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public final class CompanyDAOQueries {
	
	public static final String CREATE_COMPANY
				= "INSERT INTO company(name) VALUES(?)";
	
	public final static String SELECT_ALL_COMPANIES	
				= "SELECT * FROM company";

	public final static String SELECT_COMPANY_WITH_ID
				= "SELECT * FROM company WHERE id= ";

	public final static String DELETE_COMPANY_WITH_ID
				= "DELETE FROM company WHERE id= ";
}
