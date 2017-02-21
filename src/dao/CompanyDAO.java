package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Company;
import interfaces.dao.ICompanyDAO;
import interfaces.entities.ICompany;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public class CompanyDAO implements ICompanyDAO{
	
	
	@Override
	public void create(ICompany company) throws SQLException {
		PreparedStatement prepare = databaseConnection.prepareStatement(CompanyDAOQueries.CREATE_COMPANY);
		preparedStatementToCompany(prepare,company);
	}
	
	@Override
	public ICompany find(int id) throws SQLException  {
		ICompany company = null;
		ResultSet result = databaseConnection.createStatement(
            				ResultSet.TYPE_SCROLL_INSENSITIVE, 
                            ResultSet.CONCUR_UPDATABLE)
                            .executeQuery(CompanyDAOQueries.SELECT_COMPANY_WITH_ID + id);
            
        if(result.first())
        		company = createCompanyFromResultSet(result);
        return company;

	}
	
	@Override
	public List<ICompany> findAll() throws SQLException {
		List<ICompany> companies = new ArrayList<ICompany>();
		
		ResultSet result = databaseConnection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE)
			.executeQuery(CompanyDAOQueries.SELECT_ALL_COMPANIES);
            
        while(result.next())
        	companies.add(createCompanyFromResultSet(result));
        return companies;
	}
	
	@Override
	public List<String> findAllNames() throws SQLException {
		List<String> companies = new ArrayList<String>();
		
		ResultSet result = databaseConnection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE)
			.executeQuery(CompanyDAOQueries.SELECT_ALL_COMPANIES_NAMES);
            
        while(result.next())
        	companies.add(result.getString("name"));
        return companies;
	}
	
	@Override
	public List<String> findNamesByPage(int page) throws SQLException {
		List<String> companies = new ArrayList<String>();
		
		ResultSet result = databaseConnection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE)
			.executeQuery(CompanyDAOQueries.SELECT_NAMES_BY_PAGE + page * CompanyDAOQueries.COMPANIES_PER_PAGE);
            
        while(result.next())
        	companies.add(result.getString("name"));
        return companies;
	}
	
	@Override
	public List<ICompany> findByPage(int page) throws SQLException {
		List<ICompany> companies = new ArrayList<ICompany>();
		
		ResultSet result = databaseConnection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE)
			.executeQuery(CompanyDAOQueries.SELECT_ALL_BY_PAGE + page * CompanyDAOQueries.COMPANIES_PER_PAGE);
            
        while(result.next())
        	companies.add(createCompanyFromResultSet(result));
        return companies;
	}
	
	@Override
	public void delete(Integer id) throws SQLException  {
		databaseConnection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_UPDATABLE)
		.execute(CompanyDAOQueries.DELETE_COMPANY_WITH_ID + id);
		
	}


	@Override
	public void delete(ICompany company) throws SQLException {
		databaseConnection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_UPDATABLE)
		.execute(CompanyDAOQueries.DELETE_COMPANY_WITH_ID + company.getId());
	}

	
	
	@Override
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
	
	private ICompany createCompanyFromResultSet(ResultSet result) throws SQLException{
		ICompany company = new Company.Builder()
    			.id(result.getInt("id"))
    			.name(result.getString("name"))
    			.build();
		return company;
	}

	private void preparedStatementToCompany(PreparedStatement prepare, ICompany company) throws SQLException{
		prepare.setString(1, company.getName());
		prepare.execute();
	}
}
