package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import dao.CompanyDAOQueries;
import dao.ICompanyDAO;
import dao.Utils;
import entities.Company;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public class CompanyDAO implements ICompanyDAO{
	

	private static final Logger LOGGER = Logger.getLogger(ComputerDAO.class.getName());		
	
	@Override
	public void create(Company company) throws SQLException {
		PreparedStatement prepare = databaseConnection.prepareStatement(CompanyDAOQueries.CREATE_COMPANY);
		preparedStatementToCompany(prepare,company);
	}
	
	
	//DONE
	@Override
	public Optional<Company> find(int id) {
		
		Company company = null;
		ResultSet result;
		try {
			result = databaseConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery(CompanyDAOQueries.SELECT_COMPANY_WITH_ID + id);
			if(result.first()){
        		company = createCompanyFromResultSet(result);
			}else{
				LOGGER.info(Utils.entityWithIdNotFound(id));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return Optional.ofNullable(company);
	}
	
	@Override
	public Optional<List<Company>> findAll() throws SQLException {
		List<Company> companies = new ArrayList<>();
		
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
	public List<Company> findByPage(int page) throws SQLException {
		List<Company> companies = new ArrayList<>();
		
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
	public void delete(Company company) throws SQLException {
		databaseConnection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_UPDATABLE)
		.execute(CompanyDAOQueries.DELETE_COMPANY_WITH_ID + company.getId());
	}

	
	
	@Override
	public Company update(Company company) throws SQLException {
		Company _company = null;
		
		databaseConnection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_UPDATABLE)
		.execute(	"UPDATE company SET name = '" + company.getName() + "'"+
                	" WHERE id= " + company.getId());
		_company = this.find(company.getId()).get();
	    
	    return _company;
	}
	
	private Company createCompanyFromResultSet(ResultSet result) throws SQLException{
		Company company = Company.builder()
    			.id(result.getInt("id"))
    			.name(result.getString("name"))
    			.build();
		return company;
	}

	private void preparedStatementToCompany(PreparedStatement prepare, Company company) throws SQLException{
		prepare.setString(1, company.getName());
		prepare.execute();
	}
}
