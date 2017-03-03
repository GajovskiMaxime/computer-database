package com.excilys.mgajovski.computer_database.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.mgajovski.computer_database.dao.ComputerDAOQueries;
import com.excilys.mgajovski.computer_database.dao.IComputerDAO;
import com.excilys.mgajovski.computer_database.dao.Utils;
import com.excilys.mgajovski.computer_database.dao.columns.ComputerColumns;
import com.excilys.mgajovski.computer_database.dao.mappers.ComputerMapper;
import com.excilys.mgajovski.computer_database.dto.page.FilteredPageDTO;
import com.excilys.mgajovski.computer_database.dto.page.PageDTO;
import com.excilys.mgajovski.computer_database.entities.Computer;
import com.excilys.mgajovski.computer_database.exceptions.DAOException;
import com.excilys.mgajovski.computer_database.exceptions.PageException;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
public enum ComputerDAO implements IComputerDAO {
  INSTANCE;

  private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class);

  private static PreparedStatement findByFilterPS;
  private static PreparedStatement findWhereNameContainsSequenceWithPaginationPS;
  private static PreparedStatement findByIdPS;
  private static PreparedStatement findAllPS;
  private static PreparedStatement findAllNamesPS;
  private static PreparedStatement findNamesByPagePS;
  private static PreparedStatement deletePS;
  private static PreparedStatement findByPagePS;
  private static PreparedStatement createPS;
  private static PreparedStatement findFilteredCountPS;

  /**
   * Private constructor for ComputerDAO singleton.
   */
  ComputerDAO() {
  }

  @Override
  public Optional<Computer> create(Optional<Computer> optComputer) {

    if (optComputer == null || !optComputer.isPresent() || optComputer.get().getId() > 0) {
      LOGGER.error(Utils.ENTITY_NULL_OR_ALREADY_EXIST);
      return Optional.empty();
    }

    if (createPS == null) {
      try {
        createPS = databaseConnection.prepareStatement(ComputerDAOQueries.CREATE_COMPUTER,
            Statement.RETURN_GENERATED_KEYS);
      } catch (SQLException e) {
        LOGGER.error(e.getMessage(), e);
        throw new DAOException(e);
      }
    }

    try {
      if (ComputerMapper.insertComputerIntoDatabase(createPS,
          optComputer) == Statement.RETURN_GENERATED_KEYS) {
        ResultSet result = createPS.getGeneratedKeys();
        result.next();
        optComputer.get().setId(result.getLong(1));
        if (LOGGER.isInfoEnabled()) {
          LOGGER.info(Utils.ENTITY_SUCCESS);
        }
      }
      return optComputer;
    } catch (SQLException e) {
      LOGGER.error(e.getMessage(), e);
      throw new DAOException(e);
    } finally {
      try {
        if (createPS != null && !createPS.isClosed()) {
          LOGGER.info(Utils.CONNECTION_CLOSED);
          createPS.close();
        }
      } catch (SQLException closeCreate) {
        LOGGER.error(closeCreate.getMessage(), closeCreate);
        throw new DAOException(closeCreate);
      }
    }
  }

  @Override
  public Optional<Computer> find(long id) {

    if (id <= 0) {
      LOGGER.error(Utils.NEGATIVE_OR_NULL_ID);
      return Optional.empty();
    }

    if (findByIdPS == null) {
      try {
        findByIdPS = databaseConnection.prepareStatement(ComputerDAOQueries.RIGHT_JOIN_WITH_ID);
      } catch (SQLException e) {
        LOGGER.error(e.getMessage(), e);
        throw new DAOException(e);
      }
    }

    try {
      findByIdPS.setLong(1, id);
      ResultSet result = findByIdPS.executeQuery();
      List<Computer> computers = ComputerMapper
          .getComputerListFromResultSet(Utils.convertResultSetToList(result));

      if (computers.isEmpty()) {
        LOGGER.info(Utils.ENTITY_NOT_FOUND);
        return Optional.empty();
      }
      return Optional.ofNullable(computers.get(0));

    } catch (SQLException e) {

      LOGGER.error(e.getMessage(), e);
      throw new DAOException(e);
    }
  }

  @Override
  public Optional<List<Computer>> findAll() {

    if (findAllPS == null) {
      try {
        findAllPS = databaseConnection.prepareStatement(ComputerDAOQueries.RIGHT_JOIN);
      } catch (SQLException e) {
        LOGGER.error(e.getMessage(), e);
        throw new DAOException(e);
      }
    }

    try {
      ResultSet result = findAllPS.executeQuery();
      List<Computer> computers = ComputerMapper
          .getComputerListFromResultSet(Utils.convertResultSetToList(result));
      if (computers.isEmpty()) {
        LOGGER.info(Utils.EMPTY_TABLE);
        return Optional.empty();
      }
      return Optional.ofNullable(computers);
    } catch (SQLException e) {
      LOGGER.error(e.getMessage(), e);
      throw new DAOException(e);
    }
  }

  @Override
  public Optional<List<String>> findAllByColumn(ComputerColumns computerColumn) {
    
    String query = "select " + computerColumn.getColumnName() + " from computer";
    
    try {
      ResultSet result = databaseConnection.createStatement().executeQuery(query);
      List<String> computers = Utils.getNamesFromResultSet(Utils.convertResultSetToList(result));

      if (computers.isEmpty()) {
        LOGGER.info(Utils.EMPTY_TABLE);
        return Optional.empty();
      }
      return Optional.ofNullable(computers);

    } catch (SQLException e) {
      throw new DAOException(e.getMessage(), e);
    }
  }

  @Override
  public Optional<List<Computer>> findByFilter(String filter) {
    if (findByFilterPS == null) {
      try {
        findByFilterPS = databaseConnection
            .prepareStatement(ComputerDAOQueries.SELECT_WHERE_NAME_CONTAINS_SEQUENCE);
      } catch (SQLException e) {
        throw new DAOException(e.getMessage(), e);
      }
    }
    try {

      findByFilterPS.setString(1, '%' + filter + '%');
      ResultSet result = findByFilterPS.executeQuery();
      List<Computer> computers = ComputerMapper.getComputerListFromResultSet(Utils.convertResultSetToList(result));
      if (computers.isEmpty()) {
        LOGGER.warn("Nope");
        return Optional.empty();
      }
      return Optional.ofNullable(computers);

    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      throw new DAOException(e);
    }
  }

  @Override
  public Optional<List<Computer>> findByPage(FilteredPageDTO<Computer> page) throws PageException {

    if (page == null) {
      throw new PageException(PageException.PAGE_NULL);
    }
    if (page.getCurrentPage() < 0) {
      throw new PageException(PageException.NEGATIVE_CURRENT_PAGE + page.getCurrentPage());
    }
    if (page.getElementsByPage() < 0) {
      throw new PageException(PageException.NEGATIVE_NUMBERS_OF_ELEMENTS + page.getElementsByPage());
    }

    if (findWhereNameContainsSequenceWithPaginationPS == null) {
      try {
        findWhereNameContainsSequenceWithPaginationPS = databaseConnection
            .prepareStatement(ComputerDAOQueries.SELECT_WHERE_NAME_CONTAINS_SEQUENCE_BY_PAGE);
      } catch (SQLException e) {
        LOGGER.error(e.getMessage(), e);
        throw new DAOException(e);
      }
    }

    try {

      findWhereNameContainsSequenceWithPaginationPS.setString(1, '%' + page.getFilter() + '%');
      findWhereNameContainsSequenceWithPaginationPS.setInt(2, page.getElementsByPage());
      findWhereNameContainsSequenceWithPaginationPS.setInt(3, page.getElementsByPage() * page.getCurrentPage());
      ResultSet result = findWhereNameContainsSequenceWithPaginationPS.executeQuery();
      List<Computer> computers = ComputerMapper.getComputerListFromResultSet(Utils.convertResultSetToList(result));
      if (computers.isEmpty()) {
        throw new PageException(PageException.LAST_PAGE_REACHED);
      }
      return Optional.of(computers);

    } catch (SQLException e) {
      throw new DAOException(e.getMessage(), e);
    }
  }

  @Override
  public Optional<List<String>> findNamesByPage(int page, int rows) {

    if (findNamesByPagePS == null) {
      try {
        findNamesByPagePS = databaseConnection
            .prepareStatement(ComputerDAOQueries.SELECT_NAMES_BY_PAGE);
      } catch (SQLException e) {
        LOGGER.error(e.getMessage(), e);
        throw new DAOException(e);
      }
    }

    try {
      findNamesByPagePS.setInt(1, rows);
      findNamesByPagePS.setInt(2, rows * page);
      ResultSet result = findNamesByPagePS.executeQuery();
      List<String> computers = Utils.getNamesFromResultSet(Utils.convertResultSetToList(result));
      if (computers.isEmpty()) {
        LOGGER.warn("Yip");
        return Optional.empty();
      }
      return Optional.ofNullable(computers);

    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
      throw new DAOException(e);
    }
  }

  @Override
  public Optional<List<Computer>> findByPage(PageDTO<Computer> page) throws PageException {
    
    if (page == null) {
      throw new PageException(PageException.PAGE_NULL);
    }
    if (page.getCurrentPage() < 0) {
      throw new PageException(PageException.NEGATIVE_CURRENT_PAGE + page.getCurrentPage());
    }
    if (page.getElementsByPage() < 0) {
      throw new PageException(PageException.NEGATIVE_NUMBERS_OF_ELEMENTS + page.getElementsByPage());
    }
    
    if (findByPagePS == null) {
      try {
        findByPagePS = databaseConnection.prepareStatement(ComputerDAOQueries.SELECT_ALL_BY_PAGE);
      } catch (SQLException e) {
        LOGGER.error(e.getMessage(), e);
        throw new DAOException(e);
      }
    }

    try {
      findByPagePS.setInt(1, page.getElementsByPage());
      findByPagePS.setInt(2, page.getElementsByPage() * page.getCurrentPage());
      ResultSet result = findByPagePS.executeQuery();
      List<Computer> computers = ComputerMapper
          .getComputerListFromResultSet(Utils.convertResultSetToList(result));
      if (computers.isEmpty()) {
        throw new PageException(PageException.LAST_PAGE_REACHED);
      }
      return Optional.of(computers);

    } catch (SQLException e) {
      throw new DAOException(e.getMessage(), e);
    }
  }

  @Override
  public boolean delete(long id) {
    if (id <= 0) {
      throw new IllegalArgumentException(Utils.NEGATIVE_OR_NULL_ID);
    }

    if (deletePS == null) {
      try {
        deletePS = databaseConnection.prepareStatement(ComputerDAOQueries.DELETE_COMPUTER_WITH_ID);
      } catch (SQLException e) {
        LOGGER.error(e.getMessage(), e);
        throw new DAOException(e);
      }
    }

    try {
      deletePS.setLong(1, id);
      boolean rowIsDeleted = deletePS.executeUpdate() == 1;

      if (LOGGER.isInfoEnabled()) {
        LOGGER.info("Row with " + id + " deleted " + (rowIsDeleted ? " successfully" : " failed"));
      }
      return rowIsDeleted;
    } catch (SQLException e) {
      LOGGER.error(e.getMessage(), e);
      throw new DAOException(e);
    }
  }

  @Override
  public boolean delete(Optional<Computer> optComputer) {
    if (optComputer == null || !optComputer.isPresent()) {
      // TODO
      LOGGER.error(Utils.ENTITY_NULL_OR_ALREADY_EXIST);
      return false;
    }
    return this.delete(optComputer.get().getId());
  }

  @Override
  public Optional<Computer> update(Optional<Computer> optComputer) {
    if (!optComputer.isPresent() || optComputer.get().getId() == 0) {
      LOGGER.error(Utils.ENTITY_NULL_OR_ALREADY_EXIST);
      return optComputer;
    }

    try {
      boolean rowIsUpdated = ComputerMapper.insertComputerIntoDatabase(createPS, optComputer) == 1;
      if (LOGGER.isInfoEnabled()) {
        LOGGER.info("Row with " + optComputer.get().getId() + " updated"
            + (rowIsUpdated ? " successfully" : " failed"));
      }
      return optComputer;

    } catch (SQLException e) {
      LOGGER.error(e.getMessage(), e);
      throw new DAOException(e);
    }
  }

  // TODO A améliorer
  @Override
  public int size(String sequence) {

    if (findFilteredCountPS == null) {
      try {
        findFilteredCountPS = databaseConnection
            .prepareStatement(ComputerDAOQueries.COUNT_FILTERED_ROWS);
      } catch (SQLException e) {
        LOGGER.error(e.getMessage(), e);
        throw new DAOException(e);
      }
    }
    ResultSet rs = null;
    try {
      findFilteredCountPS.setString(1, '%' + sequence + '%');
      rs = findFilteredCountPS.executeQuery();
      if (!rs.isBeforeFirst()) {
        throw new DAOException();
      }
      rs.next();
      return rs.getInt(1);
    } catch (SQLException e) {
      LOGGER.error(e.getMessage(), e);
      throw new DAOException(e);
    } finally {
      try {
        rs.close();
        findFilteredCountPS.close();
        findFilteredCountPS = null;
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

  }

  /* (non-Javadoc)
   * @see com.excilys.mgajovski.computer_database.dao.ICrud#findAllNames()
   */
  @Override
  public Optional<List<String>> findAllNames() {
    // TODO Auto-generated method stub
    return null;
  }
}