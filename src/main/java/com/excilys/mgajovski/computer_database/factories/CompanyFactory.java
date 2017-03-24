package com.excilys.mgajovski.computer_database.factories;

import java.util.ArrayList;
import java.util.List;
import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.utils.Utils;

/**
 * @author Gajovski Maxime
 * @date 27 févr. 2017
 */

public class CompanyFactory {
    
    public static final int COMPANY_NAME_SIZE = 10;
    /**
     * This method creates a list of n companies.
     * @param size : the number of generated companies.
     * @return an Optional<List<Company>> companies.
     */
    public static List<Company> create(int size) {
        List<Company> companies = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            companies.add(Company.builder()
                    .id(i)
                    .name(Utils.generateRandomString(COMPANY_NAME_SIZE))
                    .build());
        }
        return companies;
    }
    
}
