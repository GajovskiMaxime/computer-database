package com.excilys.mgajovski.computer_database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.mgajovski.computer_database.dao.CompanyDAOQueries;
import com.excilys.mgajovski.computer_database.dao.ComputerDAOQueries;
import com.excilys.mgajovski.computer_database.dao.DatabaseManager;
import com.excilys.mgajovski.computer_database.dao.ICompanyDAO;
import com.excilys.mgajovski.computer_database.dao.Utils;
import com.excilys.mgajovski.computer_database.dao.mappers.CompanyMapper;
import com.excilys.mgajovski.computer_database.dao.mappers.ComputerMapper;
import com.excilys.mgajovski.computer_database.dto.page.FilteredPageDTO;
import com.excilys.mgajovski.computer_database.dto.page.PageDTO;
import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.entities.Computer;
import com.excilys.mgajovski.computer_database.exceptions.DAOException;
import com.excilys.mgajovski.computer_database.exceptions.PageException;
import com.excilys.mgajovski.computer_database.exceptions.SQLMappingException;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
public enum CompanyDAO implements ICompanyDAO {
    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAO.class);
//
//    private static PreparedStatement findByIdPS;
//    private static PreparedStatement findAllPS;
//    private static PreparedStatement findAllNamesPS;
//    private static PreparedStatement findNamesByPagePS;
//    private static PreparedStatement deletePS;
//    private static PreparedStatement findByPagePS;
//    // private static PreparedStatement updatePS;
//    private static PreparedStatement createPS;

    /**
     * Private constructor for CompanyDAO singleton.
     */
    CompanyDAO() {
    }

    @Override
    public Company create(Company company) throws DAOException {

        if (company == null || company.getId() > 0) {
            throw new DAOException(DAOException.ENTITY_NULL_OR_ALREADY_EXIST);
        }
        //
        // if (createPS == null) {
        // try {
        // createPS =
        // databaseConnection.prepareStatement(CompanyDAOQueries.CREATE_COMPANY,
        // Statement.RETURN_GENERATED_KEYS);
        // } catch (SQLException e) {
        // LOGGER.error(e.getMessage(), e);
        // throw new DAOException(e);
        // }
        // }

        try (Connection connection = DatabaseManager.INSTANCE.getConnection();
                PreparedStatement create = connection.prepareStatement(CompanyDAOQueries.CREATE_COMPANY,
                        Statement.RETURN_GENERATED_KEYS);) {
            if (CompanyMapper.insertCompanyIntoDatabase(create, company) == Statement.RETURN_GENERATED_KEYS) {
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
    public Company find(long id) throws DAOException {

        if (id <= 0) {
            throw new DAOException(DAOException.NEGATIVE_OR_NULL_ID);
        }
        //
        // if (findByIdPS == null) {
        // try {
        // findByIdPS =
        // databaseConnection.prepareStatement(CompanyDAOQueries.SELECT_COMPANY_WITH_ID);
        // } catch (SQLException e) {
        // LOGGER.error(e.getMessage(), e);
        // throw new DAOException(e);
        // }
        // }

        try (Connection connection = DatabaseManager.INSTANCE.getConnection();
                PreparedStatement findById = connection.prepareStatement(CompanyDAOQueries.SELECT_COMPANY_WITH_ID)) {
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
    public List<Company> findAll() throws DAOException {

        // if (findAllPS == null) {
        // try {
        // findAllPS =
        // databaseConnection.prepareStatement(CompanyDAOQueries.SELECT_ALL_COMPANIES);
        // } catch (SQLException e) {
        // LOGGER.error(e.getMessage(), e);
        // throw new DAOException(e);
        // }
        // }

        try (Connection connection = DatabaseManager.INSTANCE.getConnection();
                PreparedStatement findAll = connection.prepareStatement(CompanyDAOQueries.SELECT_ALL_COMPANIES);) {
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

    // @Override
    // public Optional<List<String>> findAllNames() {
    //
    // if (findAllNamesPS == null) {
    // try {
    // findAllNamesPS =
    // databaseConnection.prepareStatement(CompanyDAOQueries.SELECT_ALL_COMPANIES_NAMES);
    // } catch (SQLException e) {
    // LOGGER.error(e.getMessage(), e);
    // throw new DAOException(e);
    // }
    // }
    //
    // try {
    // ResultSet result = findAllNamesPS.executeQuery();
    // List<String> companies =
    // Utils.getNamesFromResultSet(Utils.convertResultSetToList(result));
    //
    // if (companies.isEmpty()) {
    // LOGGER.info(Utils.EMPTY_TABLE);
    // return Optional.empty();
    // }
    // return Optional.ofNullable(companies);
    //
    // } catch (SQLException e) {
    // LOGGER.error(e.getMessage(), e);
    // throw new DAOException(e);
    // }
    // }
    //
    // @Override
    // public Optional<List<String>> findNamesByPage(int page, int rows) {
    //
    // if (findNamesByPagePS == null) {
    // try {
    // findNamesByPagePS =
    // databaseConnection.prepareStatement(CompanyDAOQueries.SELECT_NAMES_BY_PAGE);
    // } catch (SQLException e) {
    // LOGGER.error(e.getMessage(), e);
    // throw new DAOException(e);
    // }
    // }
    //
    // try {
    // findNamesByPagePS.setInt(1, rows);
    // findNamesByPagePS.setInt(2, rows * page);
    // ResultSet result = findNamesByPagePS.executeQuery();
    // List<String> companies =
    // Utils.getNamesFromResultSet(Utils.convertResultSetToList(result));
    // if (companies.isEmpty()) {
    // LOGGER.warn(Utils.REACH_LAST_PAGE);
    // return Optional.empty();
    // }
    // return Optional.ofNullable(companies);
    // } catch (SQLException e) {
    // LOGGER.error(e.getMessage());
    // throw new DAOException(e);
    // }
    // }
    //
    // @Override
    // public Optional<List<Company>> findByPage(int page, int rows) {
    //
    // if (findByPagePS == null) {
    // try {
    // findByPagePS =
    // databaseConnection.prepareStatement(CompanyDAOQueries.SELECT_ALL_BY_PAGE);
    // } catch (SQLException e) {
    // LOGGER.error(e.getMessage(), e);
    // throw new DAOException(e);
    // }
    // }
    //
    // try {
    // findByPagePS.setInt(1, rows);
    // findByPagePS.setInt(2, rows * page);
    // ResultSet result = findByPagePS.executeQuery();
    // List<Company> companies =
    // CompanyMapper.getCompanyListFromResultSet(Utils.convertResultSetToList(result));
    // if (companies.isEmpty()) {
    // LOGGER.warn(Utils.REACH_LAST_PAGE);
    // return Optional.empty();
    // }
    // return Optional.of(companies);
    //
    // } catch (SQLException e) {
    // LOGGER.error(e.getMessage(), e);
    // throw new DAOException(e);
    // }
    // }

    @Override
    public boolean delete(long id) throws DAOException {
        if (id <= 0) {
            throw new DAOException(DAOException.NEGATIVE_OR_NULL_ID);
        }
        //
        // if (deletePS == null) {
        // try {
        // deletePS =
        // databaseConnection.prepareStatement(CompanyDAOQueries.DELETE_COMPANY_WITH_ID);
        // } catch (SQLException e) {
        // LOGGER.error(e.getMessage(), e);
        // throw new DAOException(e);
        // }
        // }

        try (Connection connection = DatabaseManager.INSTANCE.getConnection();
                PreparedStatement delete = connection.prepareStatement(CompanyDAOQueries.DELETE_COMPANY_WITH_ID);) {
            delete.setLong(1, id);
            boolean rowIsDeleted = delete.executeUpdate() == 1;

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
    public boolean delete(Company company) throws DAOException {
        if (company == null) {
            throw new DAOException(DAOException.ENTITY_NULL_OR_ALREADY_EXIST);
        }
        return this.delete(company.getId());
    }

    // TODO Améliorer
    // @Override
    /*
     * public int size() {
     * 
     * Statement statement; try { statement =
     * databaseConnection.createStatement();
     * 
     * ResultSet resultSet =
     * statement.executeQuery("SELECT COUNT(*) FROM company");
     * 
     * if (!resultSet.isBeforeFirst()) { throw new DAOException(); }
     * resultSet.next(); return resultSet.getInt(1); } catch (SQLException e) {
     * LOGGER.error(e.getMessage(), e); throw new DAOException(e); } }
     */

    @Override
    public Company update(Company company) {
        return company;
    }

    /* (non-Javadoc)
     * @see com.excilys.mgajovski.computer_database.dao.ICrud#findByPage(com.excilys.mgajovski.computer_database.dto.page.PageDTO)
     */
    @Override
    public List<Company> findByPage(PageDTO<Company> k) throws PageException, DAOException {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.excilys.mgajovski.computer_database.dao.ICrud#findByPage(com.excilys.mgajovski.computer_database.dto.page.FilteredPageDTO)
     */
    @Override
    public List<Company> findByPage(FilteredPageDTO<Company> k) throws PageException, DAOException {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.excilys.mgajovski.computer_database.dao.ICrud#findByFilter(java.lang.String)
     */
    @Override
    public List<Company> findByFilter(String filter) throws DAOException {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.excilys.mgajovski.computer_database.dao.ICrud#sizeOfFilteredQuery(java.lang.String)
     */
    @Override
    public int sizeOfFilteredQuery(String sequence) throws DAOException {
        // TODO Auto-generated method stub
        return 0;
    }

}
