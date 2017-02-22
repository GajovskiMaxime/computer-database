package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public interface ICrud<T> {

	public Connection databaseConnection = MySQLConnection.INSTANCE.getDatabaseConnection();
	
	Optional<T> 		find(int id) 		throws SQLException;
	Optional<List<T>> 	findAll() 			throws SQLException;
	void 				create(T obj) 		throws SQLException;
	T 					update(T obj)		throws SQLException;
	void 				delete(T obj) 		throws SQLException;
	void 				delete(Integer id) 	throws SQLException;
}

