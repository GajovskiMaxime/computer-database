package com.excilys.mgajovski.computer_database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.mgajovski.computer_database.dao.ComputerDAOQueries;
import com.excilys.mgajovski.computer_database.dao.DatabaseManager;
import com.excilys.mgajovski.computer_database.dao.IComputerDAO;
import com.excilys.mgajovski.computer_database.dao.Utils;
import com.excilys.mgajovski.computer_database.dao.columns.ComputerColumn;
import com.excilys.mgajovski.computer_database.dao.mappers.ComputerMapper;
import com.excilys.mgajovski.computer_database.dto.page.FilteredPageDTO;
import com.excilys.mgajovski.computer_database.dto.page.PageDTO;
import com.excilys.mgajovski.computer_database.entities.Computer;
import com.excilys.mgajovski.computer_database.exceptions.DAOException;
import com.excilys.mgajovski.computer_database.exceptions.PageException;
import com.excilys.mgajovski.computer_database.exceptions.SQLMappingException;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
public enum ComputerDAO implements IComputerDAO {
    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class);

    /**
     * Private constructor for ComputerDAO singleton.
     */
    ComputerDAO() {
    }

    @Override
    public Computer create(Computer computer) throws DAOException {

        if (computer == null || computer.getId() > 0) {
            throw new DAOException(DAOException.ENTITY_NULL_OR_ALREADY_EXIST);
        }
        try (Connection connection = DatabaseManager.INSTANCE.getConnection();
                PreparedStatement create = connection.prepareStatement(ComputerDAOQueries.CREATE_COMPUTER,
                        Statement.RETURN_GENERATED_KEYS);) {
            LOGGER.error("NOPE");
            if (ComputerMapper.insertComputerIntoDatabase(create, computer) == Statement.RETURN_GENERATED_KEYS) {
                try (ResultSet resultSet = create.getGeneratedKeys();) {
                    resultSet.next();
                    computer.setId(resultSet.getLong(1));
                    if (LOGGER.isInfoEnabled()) {
                        LOGGER.info(Utils.ENTITY_CREATED_SUCCESS);
                    }
                }
            }
//            connection.commit();
            return computer;
        } catch (SQLException | SQLMappingException e) {
            throw new DAOException(e.getMessage(), e);
        }

    }

    @Override
    public Computer find(long id) throws DAOException {

        if (id <= 0) {
            throw new DAOException(DAOException.NEGATIVE_OR_NULL_ID);
        }

        try (PreparedStatement findById = DatabaseManager.INSTANCE.getConnection()
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
    public List<Computer> findAll() throws DAOException {

        try (PreparedStatement findAll = DatabaseManager.INSTANCE.getConnection()
                .prepareStatement(ComputerDAOQueries.RIGHT_JOIN); ResultSet result = findAll.executeQuery();) {

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
    public List<String> findAllByColumn(ComputerColumn... computerColumns) throws DAOException {

        String query = "select " + ComputerColumn.arrayToString(computerColumns) + " from computer";

        try (Connection connection = DatabaseManager.INSTANCE.getConnection();
                ResultSet result = connection.createStatement().executeQuery(query);) {

            List<String> computers = Utils.hashMapListToString(Utils.convertResultSetToList(result));

            if (computers.isEmpty()) {
                throw new DAOException(DAOException.EMPTY_TABLE);
            }
            return computers;

        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public List<Computer> findByFilter(String filter) throws DAOException {
        try (Connection connection = DatabaseManager.INSTANCE.getConnection();
                PreparedStatement findByFilter = connection
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
    public List<Computer> findByPage(FilteredPageDTO<Computer> page) throws PageException, DAOException {

        if (page == null) {
            throw new PageException(PageException.PAGE_NULL);
        }
        if (page.getCurrentPage() < 0) {
            throw new PageException(PageException.NEGATIVE_CURRENT_PAGE + page.getCurrentPage());
        }
        if (page.getElementsByPage() < 0) {
            throw new PageException(PageException.NEGATIVE_NUMBERS_OF_ELEMENTS + page.getElementsByPage());
        }

        try (Connection connection = DatabaseManager.INSTANCE.getConnection();
                PreparedStatement findByPageWithFilter = connection
                        .prepareStatement(ComputerDAOQueries.SELECT_WHERE_NAME_CONTAINS_SEQUENCE_BY_PAGE);) {

            findByPageWithFilter.setString(1, '%' + page.getFilter() + '%');
            findByPageWithFilter.setInt(2, page.getElementsByPage());
            findByPageWithFilter.setInt(3, page.getElementsByPage() * page.getCurrentPage());
            try (ResultSet result = findByPageWithFilter.executeQuery();) {
                List<Computer> computers = ComputerMapper
                        .getComputerListFromResultSet(Utils.convertResultSetToList(result));
                if (computers.isEmpty()) {
                    throw new PageException(PageException.LAST_PAGE_REACHED);
                }
                return computers;
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public List<Computer> findByPage(PageDTO<Computer> page) throws PageException, DAOException {

        if (page == null) {
            throw new PageException(PageException.PAGE_NULL);
        }
        if (page.getCurrentPage() < 0) {
            throw new PageException(PageException.NEGATIVE_CURRENT_PAGE + page.getCurrentPage());
        }
        if (page.getElementsByPage() < 0) {
            throw new PageException(PageException.NEGATIVE_NUMBERS_OF_ELEMENTS + page.getElementsByPage());
        }

        try (Connection connection = DatabaseManager.INSTANCE.getConnection();
                PreparedStatement findByPage = connection.prepareStatement(ComputerDAOQueries.SELECT_ALL_BY_PAGE);) {
            findByPage.setInt(1, page.getElementsByPage());
            findByPage.setInt(2, page.getElementsByPage() * page.getCurrentPage());
            try (ResultSet result = findByPage.executeQuery()) {
                List<Computer> computers = ComputerMapper
                        .getComputerListFromResultSet(Utils.convertResultSetToList(result));
                if (computers.isEmpty()) {
                    throw new PageException(PageException.LAST_PAGE_REACHED);
                }
                return computers;
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(long id) throws DAOException {
        if (id <= 0) {
            throw new DAOException(DAOException.NEGATIVE_OR_NULL_ID);
        }

        try (Connection connection = DatabaseManager.INSTANCE.getConnection();
                PreparedStatement deleteById = connection
                        .prepareStatement(ComputerDAOQueries.DELETE_COMPUTER_WITH_ID)) {
            deleteById.setLong(1, id);
            boolean rowIsDeleted = deleteById.executeUpdate() == 1;

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Row with " + id + " deleted " + (rowIsDeleted ? " successfully" : " failed"));
            }
//            connection.commit();
            return rowIsDeleted;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean delete(Computer computer) throws DAOException {
        if (computer == null) {
            throw new DAOException(DAOException.ENTITY_NULL_OR_ALREADY_EXIST);
        }
        return this.delete(computer.getId());
    }

    @Override
    public Computer update(Computer computer) throws DAOException {
        if (computer == null || computer.getId() == 0) {
            throw new DAOException(DAOException.ENTITY_NULL_OR_NOT_IN_DB);
        }
        System.out.println("YEP");
        try (Connection connection = DatabaseManager.INSTANCE.getConnection();
                PreparedStatement create = connection.prepareStatement(ComputerDAOQueries.UPDATE_COMPUTER);) {
            
            boolean rowIsUpdated = false;
           
                rowIsUpdated = ComputerMapper.insertComputerIntoDatabase2(create, computer) == 1;

                        if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Row with " + computer.getId() + " updated" + (rowIsUpdated ? " successfully" : " failed"));
            }
//            connection.commit();
            return computer;

        } catch (SQLException | SQLMappingException e) {
            LOGGER.error(e.getMessage()); 
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public int sizeOfFilteredQuery(String filter) throws DAOException {
        try (Connection connection = DatabaseManager.INSTANCE.getConnection();
                PreparedStatement sizeOfFilteredQuery = connection
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
}