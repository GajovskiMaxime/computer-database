//package com.excilys.mgajovski.computer_database.cli.views;
//
//import java.util.Arrays;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import com.excilys.mgajovski.computer_database.cli.controllers.CompanyController;
//import com.excilys.mgajovski.computer_database.cli.views.utils.CompanyViewUtils;
//import com.excilys.mgajovski.computer_database.cli.views.utils.ViewUtils;
//import com.excilys.mgajovski.computer_database.entities.Company;
//
///**
// *
// * @author Gajovski Maxime
// * @date 21 févr. 2017
// */
//
//@Scope("singleton")
//@Component
//public class CompanyView {
//    
//    @Autowired
//    private CompanyController companyController;
//  
//    CompanyView() {
//        
//    }
//    
//    public void displayCurrentPage() {
//        CompanyViewUtils.displayCompanyHeaderWithPage(companyController.getCurrentPage());
//        for (Company company : companyController.getElements()) {
//            System.out.printf(CompanyViewUtils.COMPANY_FORMAT_LINE, 
//                    company.getId(), 
//                    company.getName() == null ? "" : company.getName());
//        }
//        ViewUtils.footer(Arrays.toString(CompanyViewUtils.LIST_VIEW_FOOTER_LABELS));
//        companyController.companyListMainLoop(ViewUtils.SCANNER);
//    }
//
//}
