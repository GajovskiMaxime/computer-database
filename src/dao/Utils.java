package dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;


/**
 * @author	Gajovski Maxime
 * @date	22 févr. 2017
 */
public class Utils {
	
	public static final Logger LOGGER 					= Logger.getLogger(Utils.class.getName());
	public static final String ENTITY_NOT_FOUND 		= "Entity not found on the database.\n";
	public static final String EMPTY_TABLE 				= "Table seems to be empty.\n";
	public static final String REACH_LAST_PAGE 			= "It seems that you already reach the last page.\n";
	public static final String PREPARED_STATEMENT_ERR 	= "Prepared Statement err. Mistakes into queries ?!\n";
	public static final String CREATE_STATEMENT_ERR 	= "Impossible to create statement!\n";
	public static final String RESULT_SET_TO_LIST_ERR 	= "Mapping : ResultSet to List err!\n";
	public static final String BAD_QUERY 				= "BAD QUERY!\n";
	

	public static List<HashMap<String,Object>> convertResultSetToList(ResultSet rs) throws SQLException {
	    
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		try{
			ResultSetMetaData md = rs.getMetaData();
		    int columns = md.getColumnCount();
		    while (rs.next()) {
		        HashMap<String,Object> row = new HashMap<String, Object>(columns);
		        for(int i=1; i<=columns; ++i) {
		        	row.put(md.getColumnLabel(i),rs.getObject(i));
		        }
		        list.add(row);
		    }
		}catch(SQLException e){
			LOGGER.warning(RESULT_SET_TO_LIST_ERR);
			throw e;
		}
		return list;
	}

	public static List<String> getNamesFromResultSet(List<HashMap<String,Object>> rows) {

		List<String> list = new ArrayList<>();
		
		for(int i = 0; i < rows.size(); i++){
			HashMap<String,Object> row = rows.get(i);
			list .add((String)row.get("name"));
		}
		return list;
	}
//
//	  public static LocalDateTime getLocalDateTimeFromDB(String date){
//	    if (date != null) {
//	      return LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
//	    } else {
//	      return null;
//	    }
//	  }
//	  
//	  public static String entityWithIdNotFound(int id){
//		  return "Entity with id : '"+ id +"' not found on the database.\n";
//	  }
//	  
	
//	public static Optional<Company> createCompanyFromResultSet(ResultSet result) {
//		
//		Optional<Company> company;
//		ResultSetMetaData rsmd;
//		try {
//			rsmd = result.getMetaData();
//	
//			List<String> databaseFields = new ArrayList<>();
//			for (int i = 1 ; i <= rsmd.getColumnCount() ; i++) {
//				databaseFields.add(rsmd.getColumnName(i));
//			}
//			
//			company = Company.builder()
//	    			.id(result.getInt(databaseFields.get(1)))
//	    			.name(result.getString(databaseFields.get(2)))
//	    			.build();
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
		
//		return company;
//	}
}
