package dao;

import java.sql.Connection;
import java.sql.SQLException;

import database.MySQLConnection;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public interface ICrud<T> {

	public Connection databaseConnection = MySQLConnection.getInstance();
	
	public abstract T 		find(int id) 		throws SQLException;
	public abstract T 		create(T obj) 		throws SQLException;
	public abstract T 		update(T obj)		throws SQLException;
	public abstract void 	delete(T obj) 		throws SQLException;
	public abstract void 	delete(Integer id) 	throws SQLException;
}

