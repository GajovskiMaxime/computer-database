package interfaces.dao;

import java.sql.SQLException;
import java.util.List;

import interfaces.entities.IComputer;

/**
 * @author	Gajovski Maxime
 * @date	21 févr. 2017
 */
public interface IComputerDAO extends ICrud<IComputer>{

	List<String> findAllNames() 			throws SQLException;
	List<String> findNamesByPage(int page) 	throws SQLException;
	List<IComputer> findByPage(int page) 	throws SQLException;
}
