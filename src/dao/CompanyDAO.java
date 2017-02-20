package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entities.Company;
import interfaces.entities.ICompany;
import utils.Csts;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public class CompanyDAO implements ICrud<ICompany>{
	
	
	
	public ICompany create(ICompany company) throws SQLException {
		ICompany _company = null;
		PreparedStatement prepare = databaseConnection.prepareStatement(CompanyDAOQueries.CREATE_COMPANY);
		prepare.setString(1, company.getName());
		prepare.execute();
	    return _company;
	}
	
	public ICompany find(int id) throws SQLException  {
		ICompany company = null;
		ResultSet result = databaseConnection.createStatement(
            				ResultSet.TYPE_SCROLL_INSENSITIVE, 
                            ResultSet.CONCUR_UPDATABLE)
                            .executeQuery(CompanyDAOQueries.SELECT_COMPANY_WITH_ID + id);
            
            if(result.first())
            		company = new Company.Builder().name(result.getString("name")).id(id).build();
           
		   return company;

	}
	

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
		.execute(CompanyDAOQueries.DELETE_COMPANY_WITH_ID + id);
		
	}


	public void delete(ICompany company) throws SQLException {
		databaseConnection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_UPDATABLE)
		.execute(CompanyDAOQueries.DELETE_COMPANY_WITH_ID + company.getId());
	}

	
	
	public ICompany update(ICompany company) throws SQLException {
		ICompany _company = null;
		
		databaseConnection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_UPDATABLE)
		.execute(	"UPDATE company SET name = '" + company.getName() + "'"+
                	" WHERE id= " + company.getId());
		_company = this.find(company.getId());
	    
	    return _company;
	}
	
}
