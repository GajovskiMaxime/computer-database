package dao.mappers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dao.ComputerDAOQueries;
import dao.MySQLConnection;
import entities.Company;
import entities.Computer;

/**
 * @author	Gajovski Maxime
 * @date	23 févr. 2017
 */
public class ComputerMapper {

	
	public static List<Computer> getComputerListFromResultSet(List<HashMap<String,Object>> rows) {

		List<Computer> computers = new ArrayList<>();
		
		for(int i = 0; i < rows.size(); i++){
			HashMap<String,Object> row = rows.get(i);
			
			Company company = Company.builder()
					.id((Long)row.get("company_id"))
					.name((String)row.get("company_name"))
					.build();
	
			Computer computer = Computer.builder()
						.id((Long)row.get("id"))
						.name			((String)row.get("name"))
						.introduced		(((Timestamp)row.get("introduced")).toLocalDateTime().toLocalDate())
						.discontinued	(((Timestamp)row.get("discontinued")).toLocalDateTime().toLocalDate())
						.company		(company)
						.build();
			computers.add(computer);
		}
		return computers;
	}
	

	public static void insertComputerIntoDatabase(Computer computer) throws SQLException{
		
		Connection databaseConnection = MySQLConnection.INSTANCE.getDatabaseConnection();
		PreparedStatement createPS = databaseConnection.prepareStatement(ComputerDAOQueries.CREATE_COMPUTER);
		
		createPS .setString	(1, computer.getName());
		
		if(computer.getIntroducedDate() == null) {
			createPS.setNull (2, java.sql.Types.DATE);
		}else{
			createPS.setDate (2, java.sql.Date.valueOf(computer.getIntroducedDate()));
		}
		
		if(computer.getDiscontinuedDate() == null) {
			createPS.setNull (3, java.sql.Types.DATE);
		}else{
			createPS.setDate (3, java.sql.Date.valueOf(computer.getDiscontinuedDate()));
		}
		if(computer.getCompany() == null || computer.getCompany().getId() == null) {
			createPS.setNull (4, java.sql.Types.INTEGER);
		}else{
			createPS.setLong(4, computer.getCompany().getId());
		}
		createPS .execute();
	}
}
