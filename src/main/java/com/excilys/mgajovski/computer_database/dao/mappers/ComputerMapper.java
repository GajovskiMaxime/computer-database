package com.excilys.mgajovski.computer_database.dao.mappers;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.excilys.mgajovski.computer_database.dao.Utils;
import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.entities.Computer;

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

    /**
     * This method insert a computer entity into database.
     * @param createPS : the prepared statement for the creation of a computer row.
     * @param optComputer : the Optional<Computer> to insert into the database.
     * @return the result of statement.executeUpdate()
     * @throws SQLException if there's something wrong.
     */
    public static int insertComputerIntoDatabase(PreparedStatement createPS, Optional<Computer> optComputer)
            throws SQLException {

        if (!optComputer.isPresent()) {
            throw new IllegalArgumentException(Utils.ENTITY_NULL);
        }

        Computer computer = optComputer.get();
        createPS.setString(1, computer.getName());

        if (computer.getIntroducedDate() == null) {
            createPS.setNull(2, Types.DATE);
        } else {
            createPS.setDate(2, Date.valueOf(computer.getIntroducedDate()));
        }

        if (computer.getDiscontinuedDate() == null) {
            createPS.setNull(3, Types.DATE);
        } else {
            createPS.setDate(3, Date.valueOf(computer.getDiscontinuedDate()));
        }
        if (computer.getCompany() == null || computer.getCompany().getId() == null) {
            createPS.setNull(4, Types.BIGINT);
        } else {
            createPS.setLong(4, computer.getCompany().getId());
        }
        return createPS.executeUpdate();
    }
}
