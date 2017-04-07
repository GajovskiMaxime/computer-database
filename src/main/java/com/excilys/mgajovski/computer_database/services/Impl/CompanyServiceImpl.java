package com.excilys.mgajovski.computer_database.services.Impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.mgajovski.computer_database.dao.DatabaseManager;
import com.excilys.mgajovski.computer_database.dao.interfaces.CompanyDAO;
import com.excilys.mgajovski.computer_database.dao.interfaces.ComputerDAO;
import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.exceptions.DAOException;
import com.excilys.mgajovski.computer_database.exceptions.PageException;
import com.excilys.mgajovski.computer_database.exceptions.ServiceException;
import com.excilys.mgajovski.computer_database.pager.FilteredPage;
import com.excilys.mgajovski.computer_database.pager.Page;
import com.excilys.mgajovski.computer_database.services.CompanyService;

/**
 * @author Gajovski Maxime
 * @date 20 mars 2017
 */
@Service
public class CompanyServiceImpl implements CompanyService {

  @Autowired
  private CompanyDAO companyDAO;

  @Autowired
  private ComputerDAO computerDAO;
  
  @Autowired
  private DatabaseManager databaseManager;
  
  
  @Override
  public Company find(long id) throws ServiceException {
    
    Company company = null;
    Connection connection = null;
    try {
      connection = databaseManager.getConnection();
      company = companyDAO.find(connection, id);
    } catch (DAOException | SQLException exception) {
      throw new ServiceException(exception);
    } finally {
      try {
        databaseManager.closeConnection(connection);
      } catch (SQLException exception) {
        throw new ServiceException(exception);
      }
    }
    return company;
  }

  @Override
  public List<Company> findAll() throws ServiceException {
    List<Company> companies = null;
    Connection connection = null;

    try {
      connection = databaseManager.getConnection();
      companies = companyDAO.findAll(connection);
    } catch (DAOException | SQLException exception) {
      throw new ServiceException(exception);
    } finally {
      try {
        databaseManager.closeConnection(connection);
      } catch (SQLException exception) {
        throw new ServiceException(exception);
      }
    }
    return companies;
  }

  @Override
  public List<Company> findByPage(Page<Company> k) throws ServiceException {
    List<Company> companies = null;
    Connection connection = null;
    try {
      connection = databaseManager.getConnection();
      companies = companyDAO.findByPage(connection, k);
    } catch (DAOException | SQLException | PageException exception) {
      throw new ServiceException(exception);
    } finally {
      try {
        databaseManager.closeConnection(connection);
      } catch (SQLException exception) {
        throw new ServiceException(exception);
      }
    }
    return companies;
  }

  @Override
  public Map<String, Object> findByPage(FilteredPage<Company> k) throws ServiceException {
    List<Company> companies = null;
    Connection connection = null;
    int rowsReturnedFromFilterQuery = 0;
    Map<String, Object> map  = new HashMap<>();

    try {
      connection = databaseManager.getConnection();
      companies = companyDAO.findByPage(connection, k);
      map.put("list", companies);
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
  public Company create(Company obj) throws ServiceException {

    Company company = null;
    Connection connection = null;
    try {
      connection = databaseManager.getConnection();
      company = companyDAO.create(connection, obj);
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
    return company;
  }

  @Override
  public List<Company> findByFilter(String filter) throws ServiceException {

    List<Company> companies = null;
    Connection connection = null;
    try {
      connection = databaseManager.getConnection();
      companies = companyDAO.findByFilter(connection, filter);
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
    return companies;
  }

  @Override
  public Company update(Company obj) throws ServiceException {
    Connection connection = null;
    Company company = null;
    try {
      connection = databaseManager.getConnection();
      company = companyDAO.update(connection, obj);
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
    return company;
  }

  @Override
  public void delete(Company obj) throws ServiceException {
    this.delete(obj.getId());
  }

  @Override
  public void delete(long id) throws ServiceException {
    Connection connection = null;

    try {
      connection = databaseManager.getConnection();
      computerDAO.unreferenceOrRemoveCompanyForeignKey(connection, id, true);
      companyDAO.delete(connection, id);
      databaseManager.commit(connection);
    } catch (DAOException | SQLException exception) {
      try {
        databaseManager.rollbackConnection(connection);
      } catch (SQLException exceptionRollBack) {
        throw new ServiceException(exceptionRollBack);
      }
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
  public void deleteWithoutCascade(long id) throws ServiceException {
    Connection connection = null;
    try {
      connection = databaseManager.getConnection();
      computerDAO.unreferenceOrRemoveCompanyForeignKey(connection, id, false);
      companyDAO.delete(connection, id);
      databaseManager.commit(connection);
    } catch (DAOException | SQLException exception) {
      try {
        databaseManager.rollbackConnection(connection);
      } catch (SQLException exceptionRollBack) {
        throw new ServiceException(exceptionRollBack);
      }
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
      connection = databaseManager.getConnection();
      size = companyDAO.sizeOfFilteredQuery(connection, sequence);
    } catch (DAOException | SQLException exception){
      throw new ServiceException(exception);
    } finally {
      try {
        databaseManager.closeConnection(connection);
      } catch (SQLException exception) {
        throw new ServiceException(exception);
      }
    }
    return size;
  }

}
