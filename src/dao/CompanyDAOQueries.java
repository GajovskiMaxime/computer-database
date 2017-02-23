package dao;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public final class CompanyDAOQueries {
	
	public static final String 	COMPANY_TABLE 		= "company";
	
	public static final String CREATE_COMPANY
				= "INSERT INTO " + COMPANY_TABLE + "(name) VALUES(?)";
	
	public final static String SELECT_ALL_COMPANIES	
				= "SELECT * FROM "+ COMPANY_TABLE;
	
	public final static String SELECT_ALL_COMPANIES_NAMES	
				= "SELECT name FROM " + COMPANY_TABLE;

	public final static String SELECT_COMPANY_WITH_ID
				= "SELECT * FROM " + COMPANY_TABLE + " WHERE id=?";

	public final static String DELETE_COMPANY_WITH_ID
				= "DELETE FROM " +COMPANY_TABLE + " WHERE id=?";
	
	public final static String SELECT_NAMES_BY_PAGE
				= "SELECT name FROM "+ COMPANY_TABLE + " LIMIT ? OFFSET ?";
	
	public final static String SELECT_ALL_BY_PAGE
				= "SELECT * FROM " + COMPANY_TABLE + " LIMIT ? OFFSET ?";
	
	public final static String UPDATE_WITH_ID
				= "UPDATE " + COMPANY_TABLE  + "SET name =? WHERE id=?";
	
	public final static String LAST_ROW_INDEX
				= "SELECT id FROM " + COMPANY_TABLE + " ORDER BY id DESC LIMIT 1";
}
