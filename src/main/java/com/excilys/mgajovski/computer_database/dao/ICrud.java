package com.excilys.mgajovski.computer_database.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.excilys.mgajovski.computer_database.dto.page.FilteredPageDTO;
import com.excilys.mgajovski.computer_database.dto.page.PageDTO;
import com.excilys.mgajovski.computer_database.entities.Computer;
import com.excilys.mgajovski.computer_database.exceptions.PageException;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
public interface ICrud<T> {

  public Connection databaseConnection = MySQLConnection.INSTANCE.getDatabaseConnection();

  /**
   * Find a specific entity. Can be used in order to find an entity of a specific table into a
   * database.
   * 
   * @param id
   *          : the id of the entity.
   * @return an Optional<T> who can contain an entity.
   */
  Optional<T> find(long id);

  /**
   * Find all entities. Can be used in order to find all entities of a specific table into a
   * database.
   * 
   * @return an Optional<List<T>> who can contain a list of entities.
   */
  Optional<List<T>> findAll();

  /**
   * Find all entities names. Can be used in order to find all entities names of a specific table
   * into a database.
   * 
   * @return an Optional<List<String>> who can contain a list of entities names.
   */
  Optional<List<String>> findAllNames();

  /**
   * Find all entities names by page method. Can be used in order to find row entities names of a
   * specific table into a database.
   * 
   * @param page
   *          : index of page = page * row.
   * @param row
   *          : the number of row per page.
   * @return an Optional<List<String>> who can contain a list of entities names.
   */
  Optional<List<String>> findNamesByPage(int page, int row);

  
  Optional<List<T>> findByPage(PageDTO<T> k) throws PageException;
  Optional<List<T>> findByPage(FilteredPageDTO<T> k) throws PageException;

  
  /**
   * Create a specific entity.
   * 
   * @param obj
   *          : the optional object needed to be inserted into database.
   * @return the optional object with the generated id by the database.
   */
  Optional<T> create(Optional<T> obj);


  Optional<List<T>> findByFilter(String sequence);

  /**
   * 
   * @param obj
   * @return
   */
  Optional<T> update(Optional<T> obj);

  /**
   * 
   * @param obj
   * @throws SQLException
   */
  boolean delete(Optional<T> obj) throws SQLException;

  /**
   * 
   * @param id
   * @throws SQLException
   */
  boolean delete(long id);

  public int size(String sequence);
}
