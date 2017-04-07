package com.excilys.mgajovski.computer_database.dao.mappers;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.entities.Computer;
import com.excilys.mgajovski.computer_database.exceptions.DAOException;
import com.excilys.mgajovski.computer_database.exceptions.SQLMappingException;

/**
 * @author Gajovski Maxime
 * @date 23 févr. 2017
 */
public class ComputerMapper {

    public static List<Computer> getComputerListFromResultSet(List<HashMap<String, Object>> rows) {

        List<Computer> computers = new ArrayList<Computer>();

        for (int i = 0; i < rows.size(); i++) {
            HashMap<String, Object> row = rows.get(i);
            Company company = null;
            if (row.get("company_id") != null) {
                company = Company.builder()
                        .id((Long) row.get("company_id"))
                        .name((String) row.get("company_name"))
                        .build();
            }

            Timestamp discontinuedDate = ((Timestamp) row.get("discontinued"));
            Timestamp introducedDate = ((Timestamp) row.get("introduced"));
            String name = (String) row.get("name");

            Computer computer = Computer.builder().id((Long) row.get("id")).name(name)
                    .introduced(introducedDate == null ? null : introducedDate.toLocalDateTime().toLocalDate())
                    .discontinued(discontinuedDate == null ? null : discontinuedDate.toLocalDateTime().toLocalDate())
                    .company(company).build();
            computers.add(computer);
        }
        return computers;
    }

    // TODO
    /**
     * This method insert a computer entity into database.
     * 
     * @param createPS
     *            : the prepared statement for the creation of a computer row.
     * @param optComputer
     *            : the Optional<Computer> to insert into the database.
     * @return the result of statement.executeUpdate()
     * @throws SQLMappingException
     */
    public static boolean insertComputerIntoDatabaseWithUpdate(PreparedStatement createPS, Computer computer, boolean withUpdate)throws SQLMappingException {

        if (computer == null) {
            throw new IllegalArgumentException(DAOException.ENTITY_NULL);
        }

        try {
            createPS.setString(1, computer.getName());

            if (computer.getIntroduced() == null) {
                createPS.setNull(2, Types.DATE);
            } else {
                createPS.setDate(2, Date.valueOf(computer.getIntroduced()));
            }

            if (computer.getDiscontinued() == null) {
                createPS.setNull(3, Types.DATE);
            } else {
                createPS.setDate(3, Date.valueOf(computer.getDiscontinued()));
            }
            if (computer.getCompany() == null) {
                createPS.setNull(4, Types.BIGINT);
            } else {
                createPS.setLong(4, computer.getCompany().getId());
            }
            
            if(withUpdate == true){
                createPS.setLong(5, computer.getId());
            }
            
            return createPS.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new SQLMappingException(e.getMessage(), e);
        }
    }
}
