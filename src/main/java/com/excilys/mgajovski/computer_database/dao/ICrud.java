package com.excilys.mgajovski.computer_database.dao;


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
	
	Optional<T> 			find(Long id) 						throws SQLException;
	Optional<List<T>> 		findAll() 							throws SQLException;
	Optional<List<String>> 	findAllNames() 						throws SQLException;
	Optional<List<String>> 	findNamesByPage(int page, int row) 	throws SQLException;
	Optional<List<T>> 		findByPage(int page, int row) 		throws SQLException;
	Optional<T>				create(T obj) 						throws SQLException;
	Optional<T>				update(T obj)						throws SQLException;
	void 					delete(T obj) 						throws SQLException;
	void 					delete(Long id) 					throws SQLException;
	
}

