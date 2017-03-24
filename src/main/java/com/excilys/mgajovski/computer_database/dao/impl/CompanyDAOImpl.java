package com.excilys.mgajovski.computer_database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.excilys.mgajovski.computer_database.dao.CompanyDAOQueries;
import com.excilys.mgajovski.computer_database.dao.Utils;
import com.excilys.mgajovski.computer_database.dao.interfaces.CompanyDAO;
import com.excilys.mgajovski.computer_database.dao.mappers.CompanyMapper;
import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.exceptions.DAOException;
import com.excilys.mgajovski.computer_database.exceptions.PageException;
import com.excilys.mgajovski.computer_database.exceptions.SQLMappingException;
import com.excilys.mgajovski.computer_database.pager.FilteredPage;
import com.excilys.mgajovski.computer_database.pager.Page;
/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */

@Repository
@Scope("singleton")
public class CompanyDAOImpl implements CompanyDAO {
  private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAOImpl.class);

  
  public CompanyDAOImpl() {  
  }

  @Override
  public Company create(Connection connection, Company company) throws DAOException {

    if (company == null || company.getId() > 0) {
      throw new DAOException(DAOException.ENTITY_NULL_OR_ALREADY_EXIST);
    }

    try (PreparedStatement create = connection.prepareStatement(CompanyDAOQueries.CREATE_COMPANY,
            Statement.RETURN_GENERATED_KEYS);) {
      if (CompanyMapper.insertCompanyIntoDatabase(create,
          company) == Statement.RETURN_GENERATED_KEYS) {
        try (ResultSet result = create.getGeneratedKeys()) {
          result.next();
          company.setId(result.getLong(1));
          if (LOGGER.isInfoEnabled()) {
            LOGGER.info(Utils.ENTITY_CREATED_SUCCESS);
          }
        }
      }
      return company;
    } catch (SQLMappingException | SQLException e) {
      throw new DAOException(e.getMessage(), e);
    }
  }

  @Override
  public Company find(Connection connection, long id) throws DAOException {

    if (id <= 0) {
      throw new DAOException(DAOException.NEGATIVE_OR_NULL_ID);
    }

    try (PreparedStatement findById = connection
            .prepareStatement(CompanyDAOQueries.SELECT_COMPANY_WITH_ID)) {
      findById.setLong(1, id);
      try (ResultSet result = findById.executeQuery()) {
        List<Company> companies = CompanyMapper
            .getCompanyListFromResultSet(Utils.convertResultSetToList(result));
        if (companies.isEmpty()) {
          throw new DAOException(DAOException.ENTITY_NOT_FOUND);
        }
        return companies.get(0);
      }
    } catch (SQLException e) {
      throw new DAOException(e.getMessage(), e);
    }
  }

  @Override
  public List<Company> findAll(Connection connection) throws DAOException {
    
    try (PreparedStatement findAll = connection
            .prepareStatement(CompanyDAOQueries.SELECT_ALL_COMPANIES);) {
      try (ResultSet result = findAll.executeQuery()) {

        List<Company> companies = CompanyMapper
            .getCompanyListFromResultSet(Utils.convertResultSetToList(result));
        if (companies.isEmpty()) {
          throw new DAOException(DAOException.EMPTY_TABLE);
        }
        return companies;
      }
    } catch (SQLException e) {
      throw new DAOException(e.getMessage(), e);
    }
  }


  @Override
  public void delete(Connection connection, long id) throws DAOException {

    if (id <= 0) {
      throw new DAOException(DAOException.NEGATIVE_OR_NULL_ID);
    }

    String companyToDeleteQuery = "delete from company where id=" + id;

    try (Statement statement = connection
        .createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
      boolean isDeleted = statement.executeUpdate(companyToDeleteQuery) == 1;
      LOGGER.info("Company with id :" + id + (isDeleted ? " deleted successfuly." : " not deleted."));
    } catch (SQLException e) {
      throw new DAOException(e.getMessage(), e);
    }
  }

  @Override
  public void delete(Connection connection, Company company) throws DAOException {
    if (company == null) {
      throw new DAOException(DAOException.ENTITY_NULL_OR_ALREADY_EXIST);
    }
    this.delete(connection, company.getId());
  }
  
  @Override
  public Company update(Connection connection, Company company) {
    return company;
  }

  @Override
  public List<Company> findByPage(Connection connection, Page<Company> page) throws PageException, DAOException {

    if (page == null) {
      throw new PageException(PageException.PAGE_NULL);
    }
    if (page.getCurrentPage() < 0) {
      throw new PageException(PageException.NEGATIVE_CURRENT_PAGE + page.getCurrentPage());
    }
    if (page.getElementsByPage() < 0) {
      throw new PageException(
          PageException.NEGATIVE_NUMBERS_OF_ELEMENTS + page.getElementsByPage());
    }

    try (PreparedStatement findByPage = connection
            .prepareStatement(CompanyDAOQueries.SELECT_ALL_BY_PAGE);) {
      findByPage.setInt(1, page.getElementsByPage());
      findByPage.setInt(2, page.getElementsByPage() * page.getCurrentPage());
      try (ResultSet result = findByPage.executeQuery()) {
        List<Company> companies = CompanyMapper
            .getCompanyListFromResultSet(Utils.convertResultSetToList(result));
        if (companies.isEmpty()) {
          throw new PageException(PageException.EMPTY_SET);
        }
        return companies;
      }
    } catch (SQLException e) {
      throw new DAOException(e.getMessage(), e);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.mgajovski.computer_database.dao.ICrud#findByPage(com.excilys.mgajovski.
   * computer_database.dto.page.FilteredPageDTO)
   */
  @Override
  public List<Company> findByPage(Connection connection, FilteredPage<Company> k) throws PageException, DAOException {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.mgajovski.computer_database.dao.ICrud#findByFilter(java.lang.String)
   */
  @Override
  public List<Company> findByFilter(Connection connection, String filter) throws DAOException {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.excilys.mgajovski.computer_database.dao.ICrud#sizeOfFilteredQuery(java.lang.String)
   */
  @Override
  public int sizeOfFilteredQuery(Connection connection, String sequence) throws DAOException {
    // TODO Auto-generated method stub
    return 0;
  }

}
