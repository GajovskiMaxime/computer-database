package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

	
	public IComputer create(IComputer computer) throws SQLException {
		//TODO Date null error !
		IComputer _computer = null;
		PreparedStatement prepare = databaseConnection.prepareStatement(ComputerDAOQueries.CREATE_COMPUTER);
		prepare.setString	(1, computer.getName());
		prepare.setDate		(2, new java.sql.Date(computer.getIntroducedDate().getTime()));
		prepare.setDate		(3, new java.sql.Date(computer.getDiscontinuedDate().getTime()));
		prepare.setInt		(4, computer.getCompany().getId());
		prepare.execute();
	    return _computer;
	}
	
	public IComputer find(int id) throws SQLException  {
		
		ICompanyDAO companyDAO 	= new CompanyDAO();
		IComputer computer 		= null;
		
		ResultSet result = databaseConnection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, 
				ResultSet.CONCUR_UPDATABLE)
				.executeQuery(ComputerDAOQueries.SELECT_COMPUTER_WITH_ID + id);
            
        if(result.first())
    		computer = new Computer.Builder()
        			.id(id)
        			.name(result.getString("name"))
        			.introduced(result.getDate("introduced"))
        			.discontinued(result.getDate("discontinued"))
        			.company(companyDAO.find(result.getInt("company_id")))
        			.build();
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
		//TODO doesnt works !
		IComputer _computer = null;
		
		databaseConnection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_UPDATABLE)
		.execute(	"UPDATE company SET name = '" + computer.getName() + "'"+
                	" WHERE id= " + computer.getId());
		_computer = this.find(computer.getId());
	    
	    return _computer;
	}

	@Override
	public List<IComputer> findAll() throws SQLException {
		
		ICompanyDAO companyDAO = new CompanyDAO();
		
		List<IComputer> computers = new ArrayList<IComputer>();
		
		ResultSet result = databaseConnection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE)
			.executeQuery(ComputerDAOQueries.SELECT_ALL_COMPUTERS);
            
        while(result.next()){
        	
        	computers.add(new Computer.Builder()
        			.id(result.getInt("id"))
        			.name(result.getString("name"))
        			.introduced(result.getDate("introduced"))
        			.discontinued(result.getDate("discontinued"))
        			.company(companyDAO.find(result.getInt("company_id")))
        			.build());
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

		ICompanyDAO companyDAO 		= new CompanyDAO();
		List<IComputer> computers 	= new ArrayList<IComputer>();
		
		ResultSet result = databaseConnection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE)
			.executeQuery(CompanyDAOQueries.SELECT_ALL_BY_PAGE + page * CompanyDAOQueries.COMPANIES_PER_PAGE);
            
        while(result.next()){

        	computers.add(new Computer.Builder()
        			.id(result.getInt("id"))
        			.name(result.getString("name"))
        			.introduced(result.getDate("introduced"))
        			.discontinued(result.getDate("discontinued"))
        			.company(companyDAO.find(result.getInt("company_id")))
        			.build());
        }
        return computers;
	}
}