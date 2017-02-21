package interfaces.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import database.MySQLConnection;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public interface ICrud<T> {

	public Connection databaseConnection = MySQLConnection.getInstance();
	
	T 				find(int id) 		throws SQLException;
	List<T> 		findAll() 			throws SQLException;
	T 				create(T obj) 		throws SQLException;
	T 				update(T obj)		throws SQLException;
	void 			delete(T obj) 		throws SQLException;
	void 			delete(Integer id) 	throws SQLException;
}

