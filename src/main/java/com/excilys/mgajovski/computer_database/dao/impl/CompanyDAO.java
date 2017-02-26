package com.excilys.mgajovski.computer_database.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.mgajovski.computer_database.dao.CompanyDAOQueries;
import com.excilys.mgajovski.computer_database.dao.ICompanyDAO;
import com.excilys.mgajovski.computer_database.dao.Utils;
import com.excilys.mgajovski.computer_database.dao.mappers.CompanyMapper;
import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.exceptions.DAOException;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
public enum CompanyDAO implements ICompanyDAO {
    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAO.class);

    private static PreparedStatement findByIdPS;
    private static PreparedStatement findAllPS;
    private static PreparedStatement findAllNamesPS;
    private static PreparedStatement findNamesByPagePS;
    private static PreparedStatement deletePS;
    private static PreparedStatement findByPagePS;
    //private static PreparedStatement updatePS;
    private static PreparedStatement createPS;

    /**
     * Private constructor for CompanyDAO singleton.
     */
    CompanyDAO() {
    }

    @Override
    public Optional<Company> create(Optional<Company> optCompany) {

        if (!optCompany.isPresent() || optCompany.get().getId() > 0) {
            LOGGER.error(Utils.ENTITY_NULL_OR_ALREADY_EXIST);
            return optCompany;
        }

        if (createPS == null) {
            try {
                createPS = databaseConnection.prepareStatement(CompanyDAOQueries.CREATE_COMPANY,
                        Statement.RETURN_GENERATED_KEYS);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
                throw new DAOException(e);
            }
        }

        try {
            if (CompanyMapper.insertCompanyIntoDatabase(createPS, optCompany) == Statement.RETURN_GENERATED_KEYS) {
                ResultSet result = createPS.getGeneratedKeys();
                result.next();
                optCompany.get().setId(result.getLong(1));
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info(Utils.ENTITY_SUCCESS);
                }
            }
            return optCompany;
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
    public Optional<Company> find(long id) {

        if (id <= 0) {
            LOGGER.error(Utils.NEGATIVE_OR_NULL_ID);
            return Optional.empty();
        }

        if (findByIdPS == null) {
            try {
                findByIdPS = databaseConnection.prepareStatement(CompanyDAOQueries.SELECT_COMPANY_WITH_ID);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
                throw new DAOException(e);
            }
        }

        try {
            findByIdPS.setLong(1, id);
            ResultSet result = findByIdPS.executeQuery();
            List<Company> companies = CompanyMapper.getCompanyListFromResultSet(Utils.convertResultSetToList(result));

            if (companies.isEmpty()) {
                LOGGER.info(Utils.ENTITY_NOT_FOUND);
                return Optional.empty();
            }
            return Optional.ofNullable(companies.get(0));

        } catch (SQLException e) {

            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    @Override
    public Optional<List<Company>> findAll() {

        if (findAllPS == null) {
            try {
                findAllPS = databaseConnection.prepareStatement(CompanyDAOQueries.SELECT_ALL_COMPANIES);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
                throw new DAOException(e);
            }
        }

        try {
            ResultSet result = findAllPS.executeQuery();
            List<Company> companies = CompanyMapper.getCompanyListFromResultSet(Utils.convertResultSetToList(result));
            if (companies.isEmpty()) {
                LOGGER.info(Utils.EMPTY_TABLE);
                return Optional.empty();
            }
            return Optional.ofNullable(companies);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    @Override
    public Optional<List<String>> findAllNames() {

        if (findAllNamesPS == null) {
            try {
                findAllNamesPS = databaseConnection.prepareStatement(CompanyDAOQueries.SELECT_ALL_COMPANIES_NAMES);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
                throw new DAOException(e);
            }
        }

        try {
            ResultSet result = findAllNamesPS.executeQuery();
            List<String> companies = Utils.getNamesFromResultSet(Utils.convertResultSetToList(result));

            if (companies.isEmpty()) {
                LOGGER.info(Utils.EMPTY_TABLE);
                return Optional.empty();
            }
            return Optional.ofNullable(companies);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    @Override
    public Optional<List<String>> findNamesByPage(int page, int rows) {

        if (findNamesByPagePS == null) {
            try {
                findNamesByPagePS = databaseConnection.prepareStatement(CompanyDAOQueries.SELECT_NAMES_BY_PAGE);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
                throw new DAOException(e);
            }
        }

        try {
            findNamesByPagePS.setInt(1, rows);
            findNamesByPagePS.setInt(2, rows * page);
            ResultSet result = findNamesByPagePS.executeQuery();
            List<String> companies = Utils.getNamesFromResultSet(Utils.convertResultSetToList(result));
            if (companies.isEmpty()) {
                LOGGER.warn(Utils.REACH_LAST_PAGE);
                return Optional.empty();
            }
            return Optional.ofNullable(companies);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException(e);
        }
    }

    @Override
    public Optional<List<Company>> findByPage(int page, int rows) {

        if (findByPagePS == null) {
            try {
                findByPagePS = databaseConnection.prepareStatement(CompanyDAOQueries.SELECT_ALL_BY_PAGE);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
                throw new DAOException(e);
            }
        }

        try {
            findByPagePS.setInt(1, rows);
            findByPagePS.setInt(2, rows * page);
            ResultSet result = findByPagePS.executeQuery();
            List<Company> companies = CompanyMapper.getCompanyListFromResultSet(Utils.convertResultSetToList(result));
            if (companies.isEmpty()) {
                LOGGER.warn(Utils.REACH_LAST_PAGE);
                return Optional.empty();
            }
            return Optional.of(companies);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    @Override
    public boolean delete(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException(Utils.NEGATIVE_OR_NULL_ID);
        }

        if (deletePS == null) {
            try {
                deletePS = databaseConnection.prepareStatement(CompanyDAOQueries.DELETE_COMPANY_WITH_ID);
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
    public boolean delete(Company company) {
        return this.delete(company.getId());
    }

    @Override
    public Optional<Company> update(Optional<Company> company) throws SQLException {
        /*
         * Optional<Company> optionalCompany = Optional.empty();
         * updatePS.setString(1, company.getName()); updatePS.setLong(2,
         * company.getId()); updatePS.executeQuery();
         */

        // _company = this.find(company.getId());

        return company;
    }

}
