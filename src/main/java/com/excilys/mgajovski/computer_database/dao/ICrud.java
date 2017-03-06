package com.excilys.mgajovski.computer_database.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.excilys.mgajovski.computer_database.dto.page.FilteredPageDTO;
import com.excilys.mgajovski.computer_database.dto.page.PageDTO;
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
  T find(long id) throws DAOException;

  /**
   * Find all entities. Can be used in order to find all entities of a specific table into a
   * database.
   * 
   * @return an Optional<List<T>> who can contain a list of entities.
 * @throws DAOException 
   */
  List<T> findAll() throws DAOException;


  List<T> findByPage(PageDTO<T> k) throws PageException, DAOException;
  List<T> findByPage(FilteredPageDTO<T> k) throws PageException, DAOException;

  
  /**
   * Create a specific entity.
   * 
   * @param obj
   *          : the optional object needed to be inserted into database.
   * @return the optional object with the generated id by the database.
 * @throws DAOException 
   */
  T create(T obj) throws DAOException;


  List<T> findByFilter(String filter) throws DAOException;

  /**
   * 
   * @param obj
   * @return
 * @throws DAOException 
   */
  T update(T obj) throws DAOException;

  /**
   * 
   * @param obj
   * @throws SQLException
 * @throws DAOException 
   */
  boolean delete(T obj) throws DAOException;

  /**
   * 
   * @param id
 * @throws DAOException 
   * @throws SQLException
   */
  boolean delete(long id) throws DAOException;

  public int sizeOfFilteredQuery(String sequence) throws DAOException;

}
