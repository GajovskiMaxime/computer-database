package dao.mappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import entities.Company;

/**
 * @author	Gajovski Maxime
 * @date	23 févr. 2017
 */
public class CompanyMapper {
	
	
	public static List<Company> getCompanyListFromResultSet(List<HashMap<String,Object>> rows) {

		List<Company> companies = new ArrayList<>();
		
		for(int i = 0; i < rows.size(); i++){
			HashMap<String,Object> row = rows.get(i);
			Company company = Company.builder()
						.id((Long)row.get("id"))
						.name((String)row.get("name"))
						.build();
			companies.add(company);
		}
		return companies;
	}
	
}
