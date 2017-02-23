package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import dao.ComputerDAOQueries;
import dao.IComputerDAO;
import dao.Utils;
import dao.mappers.ComputerMapper;

import entities.Computer;


/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public class ComputerDAO implements IComputerDAO {
	
	private static final Logger LOGGER = Logger.getLogger(ComputerDAO.class.getName());
	

	private static PreparedStatement findByIdPS;
	private static PreparedStatement findAllPS;
	private static PreparedStatement findAllNamesPS;
	private static PreparedStatement findNamesByPagePS;
	private static PreparedStatement deletePS;
	private static PreparedStatement findByPagePS;
	private static PreparedStatement updatePS;
	private static PreparedStatement createPS;
	private static PreparedStatement lastRowPS;
	
	public ComputerDAO() throws SQLException{
		
		try {		
			findByIdPS 			= databaseConnection.prepareStatement(ComputerDAOQueries.RIGHT_JOIN_WITH_ID );
			findAllPS 			= databaseConnection.prepareStatement(ComputerDAOQueries.RIGHT_JOIN);
			findAllNamesPS 		= databaseConnection.prepareStatement(ComputerDAOQueries.SELECT_ALL_COMPUTERS_NAMES);
			findNamesByPagePS 	= databaseConnection.prepareStatement(ComputerDAOQueries.SELECT_NAMES_BY_PAGE);
			findByPagePS		= databaseConnection.prepareStatement(ComputerDAOQueries.SELECT_ALL_BY_PAGE);
			deletePS			= databaseConnection.prepareStatement(ComputerDAOQueries.DELETE_COMPUTER_WITH_ID);
//			updatePS			= databaseConnection.prepareStatement(ComputerDAOQueries.UPDATE_WITH_ID);
			createPS			= databaseConnection.prepareStatement(ComputerDAOQueries.CREATE_COMPUTER);
			lastRowPS			= databaseConnection.prepareStatement(ComputerDAOQueries.LAST_ROW_INDEX);
			
		} catch (SQLException e) {
			LOGGER.warning(Utils.PREPARED_STATEMENT_ERR);
			throw e;
		}
	}
	

	@Override
	public Optional<Computer> create(Computer computer) throws SQLException {
		ResultSet result = null;
		ComputerMapper.insertComputerIntoDatabase(computer);
		result = lastRowPS.executeQuery();
		result.first();
		return find(Long.parseLong(result.getString("id")));
	}

	@Override
	public Optional<Computer> find(Long id) throws SQLException {
		
		List<Computer> computers;
 		ResultSet result = null;
		findByIdPS.setLong(1, id);
		result = findByIdPS.executeQuery();
		
		if((computers = ComputerMapper.getComputerListFromResultSet(
				Utils.convertResultSetToList(result))).isEmpty()){
			LOGGER.warning(Utils.ENTITY_NOT_FOUND);
		}
        return Optional.ofNullable(computers.get(0));
	}
	

	@Override
	public Optional<List<Computer>> findAll() throws SQLException {		
		
		List<Computer> computers;
		ResultSet result = null; 	
		result 	= findAllPS.executeQuery();
		if((computers = ComputerMapper.getComputerListFromResultSet(
			Utils.convertResultSetToList(result))).isEmpty()){
			LOGGER.warning(Utils.EMPTY_TABLE);	
		}
        return Optional.ofNullable(computers);
	}
	
	
	@Override
	public Optional<List<String>> findAllNames() throws SQLException {
		List<String> computers;
		ResultSet result = null;
		result 	= findAllNamesPS.executeQuery();
		if((computers= Utils.getNamesFromResultSet(
				Utils.convertResultSetToList(result))).isEmpty()){
			LOGGER.warning(Utils.EMPTY_TABLE);	
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
		if((computers = Utils.getNamesFromResultSet(
				Utils.convertResultSetToList(result))).isEmpty()){
			LOGGER.warning(Utils.REACH_LAST_PAGE);	
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
		if((computers = ComputerMapper.getComputerListFromResultSet(
				Utils.convertResultSetToList(result))).isEmpty()){
			LOGGER.warning(Utils.REACH_LAST_PAGE);	
		}
		return Optional.of(computers);
	}
	
	
	public void delete(Integer id) {
		try {
			databaseConnection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
			        ResultSet.CONCUR_UPDATABLE)
			.execute(ComputerDAOQueries.DELETE_COMPUTER_WITH_ID + id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void delete(Computer computer) {
//		this.delete(computer.getId());
	}

	
	
	public Optional<Computer> update(Computer computer) throws SQLException {	
//		PreparedStatement prepare = databaseConnection.prepareStatement(
//				ComputerDAOQueries.UPDATE_COMPUTER + computer.getId());
//		preparedStatementToComputer(prepare,computer); 
//	    return this.find(computer.getId());
		return null;
	}
	
}