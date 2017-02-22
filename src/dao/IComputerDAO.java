package dao;

import java.sql.SQLException;
import java.util.List;

import entities.Computer;

/**
 * @author	Gajovski Maxime
 * @date	21 févr. 2017
 */
public interface IComputerDAO extends ICrud<Computer>{

	List<String> findAllNames() 			throws SQLException;
	List<String> findNamesByPage(int page) 	throws SQLException;
	List<Computer> findByPage(int page) 	throws SQLException;
}
