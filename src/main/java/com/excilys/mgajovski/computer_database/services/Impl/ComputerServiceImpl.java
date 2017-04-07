package com.excilys.mgajovski.computer_database.services.Impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.mgajovski.computer_database.dao.DatabaseManager;
import com.excilys.mgajovski.computer_database.dao.interfaces.ComputerDAO;
import com.excilys.mgajovski.computer_database.entities.Computer;
import com.excilys.mgajovski.computer_database.exceptions.DAOException;
import com.excilys.mgajovski.computer_database.exceptions.PageException;
import com.excilys.mgajovski.computer_database.exceptions.ServiceException;
import com.excilys.mgajovski.computer_database.pager.FilteredPage;
import com.excilys.mgajovski.computer_database.pager.Page;
import com.excilys.mgajovski.computer_database.services.ComputerService;

/**
 * @author Gajovski Maxime.
 * @date 20 mars 2017
 */

@Service
public class ComputerServiceImpl implements ComputerService {
  

  @Autowired
  private ComputerDAO computerDAO;
  
  @Autowired
  private DatabaseManager databaseManager;
  
  public static final Logger LOGGER = LoggerFactory.getLogger(ComputerServiceImpl.class.getName());
  
  @Override
  public Computer find(long id) throws ServiceException {
    Connection connection = null;
    Computer computer = null;
    try {
      connection = databaseManager.getConnection();
      computer = computerDAO.find(connection, id);
    } catch (DAOException | SQLException exception) {
      throw new ServiceException(exception);
    } finally {
      try {
        databaseManager.closeConnection(connection);
      } catch (SQLException exception) {
        throw new ServiceException(exception);
      }
    }
    return computer;
  }

  @Override
  public List<Computer> findAll() throws ServiceException {

    Connection connection = null;
    List<Computer> computers = null;
    try {
      connection = databaseManager.getConnection();
      computers = computerDAO.findAll(connection);
    } catch (DAOException | SQLException exception) {
      throw new ServiceException(exception);
    } finally {
      try {
        databaseManager.closeConnection(connection);
      } catch (SQLException exception) {
        throw new ServiceException(exception);
      }
    }
    return computers;
  }

  @Override
  public List<Computer> findByPage(Page<Computer> k) throws ServiceException {
    Connection connection = null;
    List<Computer> computers = null;
    try {
      connection = databaseManager.getConnection();
      computers = computerDAO.findByPage(connection, k);
    } catch (DAOException | SQLException | PageException exception) {
      throw new ServiceException(exception);
    } finally {
      try {
        databaseManager.closeConnection(connection);
      } catch (SQLException exception) {
        throw new ServiceException(exception);
      }
    }
    return computers;
  }

  @Override
  public Map<String, Object> findByPage(FilteredPage<Computer> k) throws ServiceException {
    Connection connection = null;
    
    List<Computer> computers = null;
    int rowsReturnedFromFilterQuery;
    Map<String, Object> map  = new HashMap<>();
    try {
      connection = databaseManager.getConnection();
      computers = computerDAO.findByPage(connection, k);
      rowsReturnedFromFilterQuery = computerDAO.sizeOfFilteredQuery(connection, k.getFilter());
      map.put("list", computers);
      map.put("size", rowsReturnedFromFilterQuery);
      
    } catch (DAOException | SQLException | PageException exception) {
      throw new ServiceException(exception);
    } finally {
      try {
        databaseManager.closeConnection(connection);
      } catch (SQLException exception) {
        throw new ServiceException(exception);
      }
    }
    return map;
  }

  @Override
  public Computer create(Computer obj) throws ServiceException {
    Connection connection = null;
    Computer computer = null;

    try {
      connection = databaseManager.getConnection();
      computer = computerDAO.create(connection, obj);
      databaseManager.commit(connection);

    } catch (DAOException | SQLException exception) {
      throw new ServiceException(exception);
    } finally {
      try {
        databaseManager.closeConnection(connection);
      } catch (SQLException exception) {
        throw new ServiceException(exception);
      }
    }
    return computer;
  }

  @Override
  public List<Computer> findByFilter(String filter) throws ServiceException {
    Connection connection = null;
    List<Computer> computers = null;
    try {
      connection = databaseManager.getConnection();
      computers = computerDAO.findByFilter(connection, filter);
    } catch (DAOException | SQLException exception) {
      throw new ServiceException(exception);
    } finally {
      try {
        databaseManager.closeConnection(connection);
      } catch (SQLException exception) {
        throw new ServiceException(exception);
      }
    }
    return computers;
  }

  @Override
  public Computer update(Computer obj) throws ServiceException {

    Connection connection = null;
    Computer computer = null;

    try {
      connection = databaseManager.getConnection();
      computer = computerDAO.update(connection, obj);
      databaseManager.commit(connection);

    } catch (DAOException | SQLException exception) {
      throw new ServiceException(exception);
    } finally {
      try {
        databaseManager.closeConnection(connection);
      } catch (SQLException exception) {
        throw new ServiceException(exception);
      }
    }
    return computer;
  }

  @Override
  public void delete(Computer obj) throws ServiceException {
    this.delete(obj.getId());
  }

  @Override
  public void delete(long id) throws ServiceException {
    Connection connection = null;
    try {
      connection = databaseManager.getConnection();
      computerDAO.delete(connection, id);
      databaseManager.commit(connection);
    } catch (DAOException | SQLException exception) {
      throw new ServiceException(exception);
    } finally {
      try {
        databaseManager.closeConnection(connection);
      } catch (SQLException exception) {
        throw new ServiceException(exception);
      }
    }
  }
  
  @Override
  public void delete(List<Long> ids) throws ServiceException {
    Connection connection = null;
    try {
      connection = databaseManager.getConnection();
      for(Long id : ids){
        computerDAO.delete(connection, id);
      }
      databaseManager.commit(connection);
    } catch (DAOException | SQLException exception) {
      throw new ServiceException(exception);
    } finally {
      try {
        databaseManager.closeConnection(connection);
      } catch (SQLException exception) {
        throw new ServiceException(exception);
      }
    }
  }

  
  @Override
  public int sizeOfFilteredQuery(String sequence) throws ServiceException {
    
    Connection connection = null;
    int size = -1;
    try{
      databaseManager.getConnection();
      size = computerDAO.sizeOfFilteredQuery(connection, sequence);
    } catch(DAOException | SQLException exception){
      throw new ServiceException(exception);
    }finally {
      try {
        databaseManager.closeConnection(connection);
      } catch (SQLException exception) {
        throw new ServiceException(exception);
      }
    }
    return size;
  }

}
