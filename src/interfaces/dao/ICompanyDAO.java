package interfaces.dao;

import java.sql.SQLException;
import java.util.List;

import interfaces.entities.ICompany;

/**
 * @author	Gajovski Maxime
 * @date	21 févr. 2017
 */
public interface ICompanyDAO extends ICrud<ICompany>{
	
	List<String> 	findAllNames() 				throws SQLException;
	List<String> 	findNamesByPage(int page) 	throws SQLException;
	List<ICompany> 	findByPage(int page) 		throws SQLException;

}
