package com.excilys.mgajovski.computer_database.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.excilys.mgajovski.computer_database.exceptions.DAOException;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
public interface ICrud<T> {

    public Connection databaseConnection = MySQLConnection.INSTANCE.getDatabaseConnection();

    /**
     * Find method.
     * Can be used in order to find an entity of a specific table into a database.
     * @param id : the id of the entity.
     * @return an Optional<T> who can contain an entity.
     * @throws SQLException :
     */
    Optional<T> find(long id);

    /**
     * Find All method.
     * Can be used in order to find all entities of a specific table into a database.
     * @return an Optional<List<T>> who can contain a list of entities.
     * @throws SQLException :
     */
    Optional<List<T>> findAll() throws SQLException;

    /**
     * 
     * @return
     * @throws SQLException
     */
    Optional<List<String>> findAllNames() throws SQLException;

    /**
     * 
     * @param page
     * @param row
     * @return
     * @throws SQLException
     */
    Optional<List<String>> findNamesByPage(int page, int row) throws SQLException;

    /**
     * 
     * @param page
     * @param row
     * @return
     * @throws SQLException
     */
    Optional<List<T>> findByPage(int page, int row) throws SQLException;

    /**
     * 
     * @param obj
     * @return
     * @throws SQLException
     */
    Optional<T> create(Optional<T> obj) throws DAOException;
/**
 * 
 * @param obj
 * @return
 * @throws SQLException
 */
    Optional<T> update(Optional<T> obj) throws SQLException;
/**
 * 
 * @param obj
 * @throws SQLException
 */
    boolean delete(T obj) throws SQLException;
/**
 * 
 * @param id
 * @throws SQLException
 */
    boolean delete(long id) ;

}
