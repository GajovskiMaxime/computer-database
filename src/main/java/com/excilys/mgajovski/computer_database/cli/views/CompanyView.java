package com.excilys.mgajovski.computer_database.cli.views;

import java.util.Arrays;

import com.excilys.mgajovski.computer_database.cli.controllers.CompanyController;
import com.excilys.mgajovski.computer_database.cli.views.utils.CompanyViewUtils;
import com.excilys.mgajovski.computer_database.cli.views.utils.ViewUtils;
import com.excilys.mgajovski.computer_database.dto.page.PageDTO;
import com.excilys.mgajovski.computer_database.entities.Company;

/**
 *
 * @author Gajovski Maxime
 * @date 21 févr. 2017
 */
public enum CompanyView {
    INSTANCE;

    CompanyView() {
        
    }
    
    public void displayCurrentPage(PageDTO<Company> page) {

        CompanyViewUtils.displayCompanyHeaderWithPage(page.getCurrentPage());
        for (Company company : page.getElements()) {
            System.out.printf(CompanyViewUtils.COMPANY_FORMAT_LINE, 
                    company.getId(), 
                    company.getName() == null ? "" : company.getName());
        }
        ViewUtils.footer(Arrays.toString(CompanyViewUtils.LIST_VIEW_FOOTER_LABELS));
        CompanyController.INSTANCE.companyListMainLoop(ViewUtils.SCANNER);
    }

    public void printFirstPage() {
        displayCurrentPage(CompanyController.INSTANCE.PAGE);
    }
}
