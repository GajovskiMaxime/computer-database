package com.excilys.mgajovski.computer_database.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.excilys.mgajovski.computer_database.dao.columns.ComputerColumn;
import com.excilys.mgajovski.computer_database.dto.page.FilteredPageDTO;
import com.excilys.mgajovski.computer_database.dto.page.PageDTO;
import com.excilys.mgajovski.computer_database.entities.Computer;
import com.excilys.mgajovski.computer_database.exceptions.DAOException;
import com.excilys.mgajovski.computer_database.exceptions.PageException;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
public interface ICrud<T> {
  /**
   * Find a specific entity. Can be used in order to find an entity of a specific table into a
   * database.
   * 
   * @param id
   *          : the id of the entity.
   * @return an Optional<T> who can contain an entity.
 * @throws DAOException 
   */
  Optional<T> find(long id) throws DAOException;

  /**
   * Find all entities. Can be used in order to find all entities of a specific table into a
   * database.
   * 
   * @return an Optional<List<T>> who can contain a list of entities.
 * @throws DAOException 
   */
  Optional<List<T>> findAll() throws DAOException;


  Optional<List<T>> findByPage(PageDTO<T> k) throws PageException, DAOException;
  Optional<List<T>> findByPage(FilteredPageDTO<T> k) throws PageException, DAOException;

  
  /**
   * Create a specific entity.
   * 
   * @param obj
   *          : the optional object needed to be inserted into database.
   * @return the optional object with the generated id by the database.
 * @throws DAOException 
   */
  Optional<T> create(Optional<T> obj) throws DAOException;


  Optional<List<T>> findByFilter(String sequence) throws DAOException;

  /**
   * 
   * @param obj
   * @return
 * @throws DAOException 
   */
  Optional<T> update(Optional<T> obj) throws DAOException;

  /**
   * 
   * @param obj
   * @throws SQLException
 * @throws DAOException 
   */
  boolean delete(Optional<T> obj) throws SQLException, DAOException;

  /**
   * 
   * @param id
 * @throws DAOException 
   * @throws SQLException
   */
  boolean delete(long id) throws DAOException;

  public int size(String sequence) throws DAOException;

}
