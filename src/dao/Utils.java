package dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import entities.Company;

/**
 * @author	Gajovski Maxime
 * @date	22 févr. 2017
 */
public class Utils {
	
	public static final String ENTITY_NOT_FOUND = "Entity not found on the database.\n";

	  public static LocalDateTime getLocalDateTimeFromDB(String date){
	    if (date != null) {
	      return LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	    } else {
	      return null;
	    }
	  }
	  
	  public static String entityWithIdNotFound(int id){
		  return "Entity with id : '"+ id +"' not found on the database.\n";
	  }
	  
	
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
