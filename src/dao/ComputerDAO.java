package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.Computer;
import interfaces.dao.ICompanyDAO;
import interfaces.dao.IComputerDAO;
import interfaces.entities.IComputer;


/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public class ComputerDAO implements IComputerDAO {
	
	
	public void create(IComputer computer) throws SQLException {
		PreparedStatement prepare = databaseConnection.prepareStatement(ComputerDAOQueries.CREATE_COMPUTER);
		preparedStatementToComputer(prepare,computer);
	}
	
	public IComputer find(int id) throws SQLException  {
		
		IComputer computer = null;
		
		ResultSet result = databaseConnection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, 
				ResultSet.CONCUR_UPDATABLE)
				.executeQuery(ComputerDAOQueries.SELECT_COMPUTER_WITH_ID + id);
            
        if(result.first())
    		computer = createComputerFromResultSet(result);
        return computer;
	}
	
	public void delete(Integer id) throws SQLException  {
		databaseConnection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_UPDATABLE)
		.execute(ComputerDAOQueries.DELETE_COMPUTER_WITH_ID + id);
		
	}


	public void delete(IComputer computer) throws SQLException {
		databaseConnection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_UPDATABLE)
		.execute(ComputerDAOQueries.DELETE_COMPUTER_WITH_ID + computer.getId());
	}

	
	
	public IComputer update(IComputer computer) throws SQLException {	
		PreparedStatement prepare = databaseConnection.prepareStatement(
				ComputerDAOQueries.UPDATE_COMPUTER + computer.getId());
		preparedStatementToComputer(prepare,computer); 
	    return this.find(computer.getId());
	}

	@Override
	public List<IComputer> findAll() throws SQLException {
		
		List<IComputer> computers = new ArrayList<IComputer>();
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
	public List<IComputer> findByPage(int page) throws SQLException {
		List<IComputer> computers 	= new ArrayList<IComputer>();
		ResultSet result = databaseConnection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE)
			.executeQuery(ComputerDAOQueries.SELECT_ALL_BY_PAGE + page * ComputerDAOQueries.COMPUTERS_PER_PAGE);
            
        while(result.next()){
        	computers.add(createComputerFromResultSet(result));
        }
        return computers;
	}
	
	private IComputer createComputerFromResultSet(ResultSet result) throws SQLException{
		ICompanyDAO companyDAO = new CompanyDAO();
		IComputer computer = new Computer.Builder()
    			.id				(result.getInt("id"))
    			.name			(result.getString("name"))
    			.introduced		((Date)result.getObject("introduced"))
    			.discontinued	((Date)result.getObject("discontinued"))
    			.company		(companyDAO.find(result.getInt("company_id")))
    			.build			();
		
		return computer;
	}
	
	private void preparedStatementToComputer(PreparedStatement prepare, IComputer computer) throws SQLException{
		prepare.setString	(1, computer.getName());
		prepare.setObject	(2, computer.getIntroducedDate());
		prepare.setObject	(3, computer.getDiscontinuedDate());
		prepare.setInt		(4, computer.getCompany().getId());
		prepare.execute();
	}
}