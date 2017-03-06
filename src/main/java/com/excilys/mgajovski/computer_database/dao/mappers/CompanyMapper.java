package com.excilys.mgajovski.computer_database.dao.mappers;


import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.excilys.mgajovski.computer_database.dao.Utils;
import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.exceptions.DAOException;
import com.excilys.mgajovski.computer_database.exceptions.SQLMappingException;

/**
 * @author Gajovski Maxime
 * @date 23 févr. 2017
 */
public class CompanyMapper {

    /**.
     * Mapping companies from a List<HashMap<String, Object>> rows
     * into a list of companies objects.
     * @param rows : the result of the transformation from database.
     * @return List<Company> companies.
     */
    public static List<Company> getCompanyListFromResultSet(List<HashMap<String, Object>> rows) {

        List<Company> companies = new ArrayList<Company>();

        for (int i = 0; i < rows.size(); i++) {
            HashMap<String, Object> row = rows.get(i);
            Company company = Company.builder()
                    .id((Long) row.get("id"))
                    .name((String) row.get("name"))
                    .build();
            companies.add(company);
        }
        return companies;
    }

    //TODO
    /**
     * This method insert a company entity into database.
     * @param createPS : the prepared statement for the creation of a company row.
     * @param optCompany : the Optional<Company> to insert into the database.
     * @return the result of statement.executeUpdate()
     * @throws SQLMappingException 
     * @throws SQLException if there's something wrong.
     */
    public static int insertCompanyIntoDatabase(PreparedStatement createPS, Company company) throws SQLMappingException {

        if (company == null) {
            throw new IllegalArgumentException(DAOException.ENTITY_NULL);
        }
        try {
            createPS.setString(1, company.getName());
            return createPS.executeUpdate();
        } catch (SQLException e) {
            throw new SQLMappingException(e.getMessage(), e);
        }
    }
}
