package com.excilys.mgajovski.computer_database.cli.views;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.excilys.mgajovski.computer_database.cli.controllers.CompanyController;
import com.excilys.mgajovski.computer_database.entities.Company;

/**
 * Vue sur la liste des compagnies (singleton).
 * @author Gajovski Maxime
 * @date 21 févr. 2017
 */

public enum CompanyListView {
    INSTANCE;


    static final  String[] FOOTER_LABELS = {
            "n - next page",
            "p - previous page",
            "m - main menu" };

    public static void printCompanyHeaderWithPage(int page) {
        System.out.println();
        System.out.println();
        System.out.println(
                "--------------------------------------------------------------------------------------------");
        System.out.println("COMPANIES : Current page : " + page);
        ViewUtils.printCompanyHeader();
    }

    //TODO A refaire....

    public void displayCurrentPage(int page, List<Company> companies) {

        printCompanyHeaderWithPage(page);
        for (Company company : companies) {
            System.out.printf(ViewUtils.COMPANY_FORMAT_LINE, company.getId(), company.getName());
        }

        ViewUtils.footer(Arrays.toString(FOOTER_LABELS));
        CompanyController.INSTANCE.companyListMainLoop(ViewUtils.SCANNER);

    }

    /**
     * Méthode permettant d'afficher les compagnies de la première page.
     * @throws SQLException
     * @see printCurrentPage
     */
    public void printFirstPage() {
        displayCurrentPage(CompanyController.INSTANCE.getCurrentPage(), CompanyController.INSTANCE.getFirstCompanies());
    }

}
