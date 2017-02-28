package com.excilys.mgajovski.computer_database.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.mgajovski.computer_database.utils.Utils;

/**
 * @author Gajovski Maxime
 * @date 27 févr. 2017
 */

public class CompanyFactory {

    /**
     * This method creates a list of n companies.
     * @param size : the number of generated companies.
     * @return an Optional<List<Company>> companies.
     */
    public static Optional<List<Company>> createCompanies(int size) {
        List<Company> companies = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            companies.add(Company.builder()
                    .id(i)
                    .name(Utils.generateRandomString(size))
                    .build());
        }
        return Optional.of(companies);
    }
}
