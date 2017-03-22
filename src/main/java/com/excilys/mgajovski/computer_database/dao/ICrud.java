package com.excilys.mgajovski.computer_database.dao;

import java.sql.Connection;
import java.util.List;

import com.excilys.mgajovski.computer_database.exceptions.DAOException;
import com.excilys.mgajovski.computer_database.exceptions.PageException;
import com.excilys.mgajovski.computer_database.pager.FilteredPage;
import com.excilys.mgajovski.computer_database.pager.Page;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
public interface ICrud<T> {

  
  T find(Connection connection, long id) throws DAOException;
  List<T> findAll(Connection connection) throws DAOException;
  List<T> findByPage(Connection connection, Page<T> k) throws PageException, DAOException;
  List<T> findByPage(Connection connection, FilteredPage<T> k) throws PageException, DAOException;
  T create(Connection connection, T obj) throws DAOException;
  List<T> findByFilter(Connection connection, String filter) throws DAOException;
  T update(Connection connection, T obj) throws DAOException;
  void delete(Connection connection, T obj) throws DAOException;
  void delete(Connection connection, long id) throws DAOException;
  public int sizeOfFilteredQuery(Connection connection, String sequence) throws DAOException;

}
