package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Company;
import entities.Computer;
import interfaces.entities.ICompany;
import interfaces.entities.IComputer;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */

public class ComputerDAO implements ICrud<IComputer> {

	public IComputer create(IComputer computer) throws SQLException {
		IComputer _computer = null;
		PreparedStatement prepare = databaseConnection.prepareStatement(ComputerDAOQueries.CREATE_COMPUTER);
		prepare.setString(1, company.getName());
		// TODO 
		prepare.execute();
	    return _computer;
	}
	
	public IComputer find(int id) throws SQLException  {
		IComputer computer = null;
		ResultSet result = databaseConnection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, 
				ResultSet.CONCUR_UPDATABLE)
				.executeQuery(ComputerDAOQueries.SELECT_COMPUTER_WITH_ID + id);
            
        if(result.first())
        		computer = new Computer.Builder()
        			.name(result.getString("name"))
        			.id(id)
        			.build();
        
        return computer;

	}
	
//
//	public List<ICompany> findAll() {
//		List<ICompany> companies = null;
//		
//		try {
//            ResultSet result = ICrud.databaseConnection.createStatement(
//            				ResultSet.TYPE_SCROLL_INSENSITIVE, 
//                            ResultSet.CONCUR_UPDATABLE)
//                            .executeQuery(Csts.SELECT_ALL_COMPANIES);
//            
//            if(result.)
//            		company = new Company.Builder()
//            		.name(result.getString("name"))
//            		.id(id)
//            		.build();
//            
//		    } catch (SQLException e) {
//		            e.printStackTrace();
//		    }
//		   return company;
//
//	}
	
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
		IComputer _computer = null;
		
		databaseConnection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_UPDATABLE)
		.execute(	"UPDATE company SET name = '" + computer.getName() + "'"+
                	" WHERE id= " + computer.getId());
		_computer = this.find(computer.getId());
	    
	    return _computer;
	}
	
}