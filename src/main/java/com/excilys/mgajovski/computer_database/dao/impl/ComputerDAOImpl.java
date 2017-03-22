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

import com.excilys.mgajovski.computer_database.dao.ComputerDAOQueries;
import com.excilys.mgajovski.computer_database.dao.Utils;
import com.excilys.mgajovski.computer_database.dao.columns.ComputerColumn;
import com.excilys.mgajovski.computer_database.dao.interfaces.ComputerDAO;
import com.excilys.mgajovski.computer_database.dao.mappers.ComputerMapper;
import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.entities.Computer;
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
public class ComputerDAOImpl implements ComputerDAO {  

  private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAOImpl.class);

  /**
   * Private constructor for ComputerDAO singleton.
   */
  ComputerDAOImpl() {
  }

  @Override
  public Computer create(Connection connection, Computer computer) throws DAOException {

    if (computer == null || computer.getId() > 0) {
      throw new DAOException(DAOException.ENTITY_NULL_OR_ALREADY_EXIST);
    }

    try (PreparedStatement create = connection.prepareStatement(ComputerDAOQueries.CREATE_COMPUTER,
        Statement.RETURN_GENERATED_KEYS);) {
      if (ComputerMapper.insertComputerIntoDatabaseWithUpdate(create, computer, false)) {
        try (ResultSet resultSet = create.getGeneratedKeys();) {
          resultSet.next();
          computer.setId(resultSet.getLong(1));
          LOGGER.info(Utils.ENTITY_CREATED_SUCCESS);

        }
      }
      return computer;
    } catch (SQLException | SQLMappingException e) {
      throw new DAOException(e.getMessage(), e);
    }

  }

  @Override
  public Computer find(Connection connection, long id) throws DAOException {

    if (id <= 0) {
      throw new DAOException(DAOException.NEGATIVE_OR_NULL_ID);
    }

    try (PreparedStatement findById = connection
        .prepareStatement(ComputerDAOQueries.RIGHT_JOIN_WITH_ID);) {

      findById.setLong(1, id);
      try (ResultSet resultSet = findById.executeQuery();) {
        List<Computer> computers = ComputerMapper
            .getComputerListFromResultSet(Utils.convertResultSetToList(resultSet));
        if (computers.isEmpty()) {
          throw new DAOException(DAOException.ENTITY_NOT_FOUND);
        }
        return computers.get(0);
      }
    } catch (SQLException e) {
      throw new DAOException(e.getMessage(), e);
    }
  }

  @Override
  public List<Computer> findAll(Connection connection) throws DAOException {

    try (PreparedStatement findAll = connection.prepareStatement(ComputerDAOQueries.RIGHT_JOIN);
        ResultSet result = findAll.executeQuery();) {

      List<Computer> computers = ComputerMapper
          .getComputerListFromResultSet(Utils.convertResultSetToList(result));
      if (computers.isEmpty()) {
        throw new DAOException(DAOException.EMPTY_TABLE);
      }
      return computers;
    } catch (SQLException e) {
      throw new DAOException(e.getMessage(), e);
    }
  }

  @Override
  public List<Computer> findByFilter(Connection connection, String filter) throws DAOException {
    try (PreparedStatement findByFilter = connection
        .prepareStatement(ComputerDAOQueries.SELECT_WHERE_NAME_CONTAINS_SEQUENCE);) {

      findByFilter.setString(1, '%' + filter + '%');
      try (ResultSet result = findByFilter.executeQuery()) {
        List<Computer> computers = ComputerMapper
            .getComputerListFromResultSet(Utils.convertResultSetToList(result));
        if (computers.isEmpty()) {
          throw new DAOException(DAOException.NO_MATCH_FOR_FILTER);
        }
        return computers;
      }
    } catch (SQLException e) {
      throw new DAOException(e.getMessage(), e);
    }
  }

  @Override
  public List<Computer> findByPage(Connection connection, FilteredPage<Computer> page)
      throws PageException, DAOException {

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

    try (PreparedStatement findByPageWithFilter = connection
        .prepareStatement(ComputerDAOQueries.SELECT_WHERE_NAME_CONTAINS_SEQUENCE_BY_PAGE);) {

      findByPageWithFilter.setString(1, '%' + page.getFilter() + '%');
      findByPageWithFilter.setInt(2, page.getElementsByPage());
      findByPageWithFilter.setInt(3, page.getElementsByPage() * page.getCurrentPage());
      try (ResultSet result = findByPageWithFilter.executeQuery();) {
        List<Computer> computers = ComputerMapper
            .getComputerListFromResultSet(Utils.convertResultSetToList(result));
        if (computers.isEmpty()) {
          throw new PageException(PageException.EMPTY_SET);
        }
        return computers;
      }
    } catch (SQLException e) {
      throw new DAOException(e.getMessage(), e);
    }
  }

  @Override
  public List<Computer> findByPage(Connection connection, Page<Computer> page)
      throws PageException, DAOException {

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
        .prepareStatement(ComputerDAOQueries.SELECT_ALL_BY_PAGE);) {
      findByPage.setInt(1, page.getElementsByPage());
      findByPage.setInt(2, page.getElementsByPage() * page.getCurrentPage());
      try (ResultSet result = findByPage.executeQuery()) {
        List<Computer> computers = ComputerMapper
            .getComputerListFromResultSet(Utils.convertResultSetToList(result));
        if (computers.isEmpty()) {
          throw new PageException(PageException.EMPTY_SET);
        }
        return computers;
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

    String deleteComputerQuery = "delete from computer where id=" + id;
    try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
        ResultSet.CONCUR_UPDATABLE)) {
      boolean isDeleted = statement.executeUpdate(deleteComputerQuery) == 1;
      LOGGER.info(
          "Computer with id :" + id + (isDeleted ? " deleted successfuly." : " not deleted."));
    } catch (SQLException e) {
      throw new DAOException(e.getMessage(), e);
    }
  }

  @Override
  public void delete(Connection connection, Computer computer) throws DAOException {
    if (computer == null) {
      throw new DAOException(DAOException.ENTITY_NULL_OR_ALREADY_EXIST);
    }
    this.delete(connection, computer.getId());
  }

  @Override
  public Computer update(Connection connection, Computer computer) throws DAOException {
    if (computer == null || computer.getId() == 0) {
      throw new DAOException(DAOException.ENTITY_NULL_OR_NOT_IN_DB);
    }
    try (PreparedStatement create = connection
        .prepareStatement(ComputerDAOQueries.UPDATE_COMPUTER);) {

      boolean rowIsUpdated = ComputerMapper.insertComputerIntoDatabaseWithUpdate(create, computer,
          true);
      LOGGER.info("Row with " + computer.getId() + " updated"
          + (rowIsUpdated ? " successfully" : " failed"));
      return computer;

    } catch (SQLException | SQLMappingException e) {
      LOGGER.error(e.getMessage());
      throw new DAOException(e.getMessage(), e);
    }
  }

  @Override
  public int sizeOfFilteredQuery(Connection connection, String filter) throws DAOException {
    try (PreparedStatement sizeOfFilteredQuery = connection
        .prepareStatement(ComputerDAOQueries.COUNT_FILTERED_ROWS);) {
      sizeOfFilteredQuery.setString(1, '%' + filter + '%');
      try (ResultSet rs = sizeOfFilteredQuery.executeQuery()) {

        if (!rs.isBeforeFirst()) {
          throw new DAOException();
        }
        rs.next();
        return rs.getInt(1);
      }
    } catch (SQLException e) {
      throw new DAOException(e.getMessage(), e);
    }
  }

  public void unreferenceOrRemoveCompanyForeignKey(Connection connection, long id, boolean remove)
      throws DAOException {

    if (id <= 0) {
      throw new DAOException(DAOException.NEGATIVE_OR_NULL_ID);
    }

    String computersToDeleteQuery = "select * from computer where company_id=" + id;

    try (ResultSet computersToDeleteRs = connection
        .createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
        .executeQuery(computersToDeleteQuery);) {

      while (computersToDeleteRs.next()) {
        LOGGER.info(
            "Computer with id :" + computersToDeleteRs.getLong(ComputerColumn.ID.getColumnName())
                + " updated successfuly.");
        computersToDeleteRs.updateNull(ComputerColumn.ID_COMPANY.getColumnName());
        computersToDeleteRs.updateRow();
        if (remove) {
          LOGGER.info(
              "Computer with id :" + computersToDeleteRs.getLong(ComputerColumn.ID.getColumnName())
                  + " deleted successfully");
          computersToDeleteRs.deleteRow();
        }
      }
    } catch (SQLException sqlException) {
      throw new DAOException(sqlException.getMessage(), sqlException);
    }
  }

  @Override
  public void unreferenceOrRemoveCompanyForeignKey(Connection connection, Company company, boolean remove)
      throws DAOException {
    if (company == null) {
      throw new DAOException(DAOException.ENTITY_NULL_OR_ALREADY_EXIST);
    }
    this.unreferenceOrRemoveCompanyForeignKey(connection, company.getId(), remove);
  }

}