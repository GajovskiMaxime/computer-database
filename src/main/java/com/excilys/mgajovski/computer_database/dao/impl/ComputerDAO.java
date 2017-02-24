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
import com.excilys.mgajovski.computer_database.dao.mappers.ComputerMapper;
import com.excilys.mgajovski.computer_database.entities.Computer;
import com.excilys.mgajovski.computer_database.exceptions.DAOException;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
public class ComputerDAO implements IComputerDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class);
    
    private static PreparedStatement findByIdPS;
    private static PreparedStatement findAllPS;
    private static PreparedStatement findAllNamesPS;
    private static PreparedStatement findNamesByPagePS;
    private static PreparedStatement deletePS;
    private static PreparedStatement findByPagePS;
    private static PreparedStatement updatePS;
    private static PreparedStatement createPS;
    private static PreparedStatement lastRowPS;

    /**
     * ComputerDAO constructor.
     * This method initialize all the prepared statements
     * @throws SQLException if there's something wrong with the prepared statements
     */
    public ComputerDAO() {

        LOGGER.info(Utils.INIT_DAO);
        try {
            findByIdPS = databaseConnection.prepareStatement(ComputerDAOQueries.RIGHT_JOIN_WITH_ID);
            findAllPS = databaseConnection.prepareStatement(ComputerDAOQueries.RIGHT_JOIN);
            findAllNamesPS = databaseConnection.prepareStatement(ComputerDAOQueries.SELECT_ALL_COMPUTERS_NAMES);
            findNamesByPagePS = databaseConnection.prepareStatement(ComputerDAOQueries.SELECT_NAMES_BY_PAGE);
            findByPagePS = databaseConnection.prepareStatement(ComputerDAOQueries.SELECT_ALL_BY_PAGE);
            deletePS = databaseConnection.prepareStatement(ComputerDAOQueries.DELETE_COMPUTER_WITH_ID);
            // updatePS =
            // databaseConnection.prepareStatement(ComputerDAOQueries.UPDATE_WITH_ID);
            createPS = databaseConnection.prepareStatement(ComputerDAOQueries.CREATE_COMPUTER, Statement.RETURN_GENERATED_KEYS);
            lastRowPS = databaseConnection.prepareStatement(ComputerDAOQueries.LAST_ROW_INDEX);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Computer> create(Optional<Computer> optComputer) {

        if (!optComputer.isPresent() || optComputer.get().getId() > 0) {
            LOGGER.error(Utils.ENTITY_NULL_OR_ALREADY_EXIST);
            return optComputer;
        }
        
        try {
            if (ComputerMapper.insertComputerIntoDatabase(createPS, optComputer) 
                    == Statement.RETURN_GENERATED_KEYS){
                ResultSet result = createPS.getGeneratedKeys();
                result.next();
                optComputer.get().setId(result.getLong(1));
                if(LOGGER.isInfoEnabled()){
                    LOGGER.info(Utils.ENTITY_SUCCESS);
                }
            }
            return optComputer;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    @Override
    public Optional<Computer> find(long  id) {
        
        if (id <= 0) {
            LOGGER.error(Utils.NEGATIVE_OR_NULL_ID);

            System.out.println("YES");
            return Optional.empty();
        }
        System.out.println("NO");
        try{
            findByIdPS.setLong(1, id);
            ResultSet result = findByIdPS.executeQuery();
            List<Computer> computers = ComputerMapper.getComputerListFromResultSet(
                    Utils.convertResultSetToList(result));
            
            if (computers.isEmpty()) {
                LOGGER.info(Utils.ENTITY_NOT_FOUND);
                return Optional.empty();
            }
            return Optional.ofNullable(computers.get(0));
    
        } catch (SQLException e){
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }
    @Override
    public Optional<List<Computer>> findAll() throws SQLException {

        List<Computer> computers;
        ResultSet result = null;
        result = findAllPS.executeQuery();
        if ((computers = ComputerMapper.getComputerListFromResultSet(Utils.convertResultSetToList(result))).isEmpty()) {
//            LOGGER.warning(Utils.EMPTY_TABLE);
        }
        return Optional.ofNullable(computers);
    }

    @Override
    public Optional<List<String>> findAllNames() throws SQLException {
        List<String> computers;
        ResultSet result = null;
        result = findAllNamesPS.executeQuery();
        if ((computers = Utils.getNamesFromResultSet(Utils.convertResultSetToList(result))).isEmpty()) {
//            LOGGER.warning(Utils.EMPTY_TABLE);
        }
        return Optional.ofNullable(computers);
    }

    @Override
    public Optional<List<String>> findNamesByPage(int page, int rows) throws SQLException {
        List<String> computers;
        ResultSet result = null;
        findNamesByPagePS.setInt(1, rows);
        findNamesByPagePS.setInt(2, rows * page);
        result = findNamesByPagePS.executeQuery();
        if ((computers = Utils.getNamesFromResultSet(Utils.convertResultSetToList(result))).isEmpty()) {
//            LOGGER.warning(Utils.REACH_LAST_PAGE);
        }
        return Optional.ofNullable(computers);
    }

    @Override
    public Optional<List<Computer>> findByPage(int page, int rows) throws SQLException {
        List<Computer> computers;
        ResultSet result = null;
        findByPagePS.setInt(1, rows);
        findByPagePS.setInt(2, rows * page);
        result = findByPagePS.executeQuery();
        if ((computers = ComputerMapper.getComputerListFromResultSet(Utils.convertResultSetToList(result))).isEmpty()) {
//            LOGGER.warning(Utils.REACH_LAST_PAGE);
        }
        return Optional.of(computers);
    }

    @Override
    public void delete(long id) {
        if(id <= 0)
            throw new IllegalArgumentException(Utils.NEGATIVE_OR_NULL_ID);
        try{
            deletePS.setLong(1, id);
            deletePS.executeQuery();
            
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(Computer computer) throws SQLException {
        this.delete(computer.getId());
    }

    public Optional<Computer> update(Computer computer) throws SQLException {
        // PreparedStatement prepare = databaseConnection.prepareStatement(
        // ComputerDAOQueries.UPDATE_COMPUTER + computer.getId());
        // preparedStatementToComputer(prepare,computer);
        // return this.find(computer.getId());
        return null;
    }

}