package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import dao.CompanyDAOQueries;
import dao.ICompanyDAO;
import dao.Utils;
import dao.mappers.CompanyMapper;
import entities.Company;
import exceptions.LastPageException;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public class CompanyDAO implements ICompanyDAO{
	

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
	
	public CompanyDAO() throws SQLException{
		
		try {
			
			findByIdPS 			= databaseConnection.prepareStatement(CompanyDAOQueries.SELECT_COMPANY_WITH_ID);
			findAllPS 			= databaseConnection.prepareStatement(CompanyDAOQueries.SELECT_ALL_COMPANIES);
			findAllNamesPS 		= databaseConnection.prepareStatement(CompanyDAOQueries.SELECT_ALL_COMPANIES_NAMES);
			findNamesByPagePS 	= databaseConnection.prepareStatement(CompanyDAOQueries.SELECT_NAMES_BY_PAGE);
			findByPagePS		= databaseConnection.prepareStatement(CompanyDAOQueries.SELECT_ALL_BY_PAGE);
			deletePS			= databaseConnection.prepareStatement(CompanyDAOQueries.DELETE_COMPANY_WITH_ID);
			updatePS			= databaseConnection.prepareStatement(CompanyDAOQueries.UPDATE_WITH_ID);
			createPS			= databaseConnection.prepareStatement(CompanyDAOQueries.CREATE_COMPANY);
			lastRowPS			= databaseConnection.prepareStatement(CompanyDAOQueries.LAST_ROW_INDEX);
			
		} catch (SQLException e) {
			LOGGER.warning(Utils.PREPARED_STATEMENT_ERR);
			throw e;
		}
	}
	
	@Override
	public Optional<Company> create(Company company) throws SQLException {
		ResultSet result = null;
		createPS.setString(1, company.getName());
		createPS.execute();
		result = lastRowPS.executeQuery();
		result.first();
		return find(Long.parseLong(result.getString("id")));
	}
	
	@Override
	public Optional<Company> find(Long id) throws SQLException {
		
		List<Company> companies;
 		ResultSet result = null;
		findByIdPS.setLong(1, id);
		result = findByIdPS.executeQuery();
		if((companies = CompanyMapper.getCompanyListFromResultSet(
				Utils.convertResultSetToList(result))).isEmpty()){
			LOGGER.warning(Utils.ENTITY_NOT_FOUND);
		}
        return Optional.ofNullable(companies.get(0));
	}
	
	@Override
	public Optional<List<Company>> findAll() throws SQLException {
		
		List<Company> companies;
		ResultSet result = null; 	
		result 	= findAllPS.executeQuery();
		if((companies = CompanyMapper.getCompanyListFromResultSet(
			Utils.convertResultSetToList(result))).isEmpty()){
			LOGGER.warning(Utils.EMPTY_TABLE);	
		}
        return Optional.ofNullable(companies);
	}
	
	@Override
	public Optional<List<String>> findAllNames() throws SQLException {
		List<String> companies;
		ResultSet result = null;
		result 	= findAllNamesPS.executeQuery();
		if((companies = Utils.getNamesFromResultSet(
				Utils.convertResultSetToList(result))).isEmpty()){
			LOGGER.warning(Utils.EMPTY_TABLE);	
		}
        return Optional.ofNullable(companies);
	}
	
	@Override
	public Optional<List<String>> findNamesByPage(int page, int rows) throws SQLException {
		List<String> companies;
		ResultSet result = null;
		findNamesByPagePS.setInt(1, rows);
		findNamesByPagePS.setInt(2, rows * page);
		result = findNamesByPagePS.executeQuery();
		if((companies = Utils.getNamesFromResultSet(
				Utils.convertResultSetToList(result))).isEmpty()){
			LOGGER.warning(Utils.REACH_LAST_PAGE);	
		}
        return Optional.ofNullable(companies);
	}
	
	@Override
	public Optional<List<Company>> findByPage(int page, int rows) throws SQLException, LastPageException{
		List<Company> companies;
		ResultSet result = null;
		findByPagePS.setInt(1, rows);
		findByPagePS.setInt(2, rows * page);
		result = findByPagePS.executeQuery();
		if((companies = CompanyMapper.getCompanyListFromResultSet(
				Utils.convertResultSetToList(result))).isEmpty()){
			throw new LastPageException(Utils.REACH_LAST_PAGE);
		}
		return Optional.of(companies);
	}
	
	@Override
	public void delete(Long id) throws SQLException  {
		deletePS.setLong(1, id);
		deletePS.executeQuery();
	}


	@Override
	public void delete(Company company) throws SQLException {
		this.delete(company.getId());
	}

	
	
	@Override
	public Optional<Company> update(Company company) throws SQLException {
		Optional<Company> _company = Optional.empty();
		
		updatePS.setString	(1, company.getName());
		updatePS.setLong(2, company.getId());
		updatePS.executeQuery();
		
//		_company = this.find(company.getId());
	    
	    return _company;
	}
	
}
