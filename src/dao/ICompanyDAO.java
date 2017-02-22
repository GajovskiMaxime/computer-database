package dao;

import java.sql.SQLException;
import java.util.List;

import entities.Company;

/**
 * @author	Gajovski Maxime
 * @date	21 févr. 2017
 */
public interface ICompanyDAO extends ICrud<Company>{
	
	List<String> 	findAllNames() 				throws SQLException;
	List<String> 	findNamesByPage(int page) 	throws SQLException;
	List<Company> 	findByPage(int page) 		throws SQLException;

}
