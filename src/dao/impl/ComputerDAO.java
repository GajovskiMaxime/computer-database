package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import dao.ComputerDAOQueries;
import dao.ICompanyDAO;
import dao.IComputerDAO;
import dao.Utils;
import entities.Computer;


/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public class ComputerDAO implements IComputerDAO {
	
	private static final Logger LOGGER = Logger.getLogger(ComputerDAO.class.getName());

	
	public void create(Computer computer) throws SQLException {
		PreparedStatement prepare = databaseConnection.prepareStatement(ComputerDAOQueries.CREATE_COMPUTER);
		preparedStatementToComputer(prepare,computer);
	}
	
	public Optional<Computer> find(int id) {
		
		Computer computer 		= null;
		ResultSet result 		= null;
		boolean resultIsEmpty 	= true;
		
		try {
			result = databaseConnection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE)
					.executeQuery(ComputerDAOQueries.SELECT_COMPUTER_WITH_ID + id);
			resultIsEmpty = result.first();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(resultIsEmpty){
			computer = createComputerFromResultSet(result);
		}else{
			LOGGER.info(Utils.entityWithIdNotFound(id));
		}
		
   
        
        return Optional.ofNullable(computer);
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
		this.delete(computer.getId());
	}

	
	
	public Computer update(Computer computer) throws SQLException {	
//		PreparedStatement prepare = databaseConnection.prepareStatement(
//				ComputerDAOQueries.UPDATE_COMPUTER + computer.getId());
//		preparedStatementToComputer(prepare,computer); 
//	    return this.find(computer.getId());
		return null;
	}

	@Override
	public List<Computer> findAll() throws SQLException {
		
		List<Computer> computers = new ArrayList<Computer>();
		ResultSet result = databaseConnection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE)
			.executeQuery(ComputerDAOQueries.SELECT_ALL_COMPUTERS);
            
        while(result.next()){
        	computers.add(createComputerFromResultSet(result));
        }
        return computers;
	}
	
	@Override
	public List<String> findAllNames() throws SQLException {
		
		List<String> computers = new ArrayList<String>();
		
		ResultSet result = databaseConnection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE)
			.executeQuery(ComputerDAOQueries.SELECT_ALL_COMPUTERS_NAMES);
            
        while(result.next()){
        	computers.add(result.getString("name"));
        }
        return computers;
	}
	
	@Override
	public List<String> findNamesByPage(int page) throws SQLException {
		List<String> companies = new ArrayList<String>();
		
		ResultSet result = databaseConnection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE)
			.executeQuery(ComputerDAOQueries.SELECT_NAMES_BY_PAGE + page * ComputerDAOQueries.COMPUTERS_PER_PAGE);
            
        while(result.next()){
        	companies.add(result.getString("name"));
        }
        return companies;
	}
	

	@Override
	public List<Computer> findByPage(int page) throws SQLException {
		List<Computer> computers 	= new ArrayList<Computer>();
		ResultSet result = databaseConnection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE)
			.executeQuery(ComputerDAOQueries.SELECT_ALL_BY_PAGE + page * ComputerDAOQueries.COMPUTERS_PER_PAGE);
            
        while(result.next()){
        	computers.add(createComputerFromResultSet(result));
        }
        return computers;
	}
	
	private Computer createComputerFromResultSet(ResultSet result) {
		ICompanyDAO companyDAO = new CompanyDAO();
		Computer computer = null;
		try {
			computer = Computer.builder()
					.id				(result.getInt("id"))
					.name			(result.getString("name"))
					.introduced		(result.getDate("introduced") == null ? null : result.getDate("introduced").toLocalDate())
					.discontinued	(result.getDate("discontinued") == null ? null : result.getDate("introduced").toLocalDate())
					.company		(companyDAO.find(result.getInt("company_id")).orElse(null))
					.build			();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return computer;
	}
	
	private void preparedStatementToComputer(PreparedStatement prepare, Computer computer) throws SQLException{
		prepare.setString	(1, computer.getName());
		prepare.setObject	(2, computer.getIntroducedDate());
		prepare.setObject	(3, computer.getDiscontinuedDate());
		prepare.setInt		(4, computer.getCompany().getId());
		prepare.execute();
	}
}