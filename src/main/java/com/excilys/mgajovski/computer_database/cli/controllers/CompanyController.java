//package com.excilys.mgajovski.computer_database.cli.controllers;
//
//
//import java.util.List;
//import java.util.Scanner;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.excilys.mgajovski.computer_database.cli.controllers.utils.ControllerUtils;
//import com.excilys.mgajovski.computer_database.cli.views.CompanyView;
//import com.excilys.mgajovski.computer_database.cli.views.MainView;
//import com.excilys.mgajovski.computer_database.dao.impl.CompanyDAO;
//import com.excilys.mgajovski.computer_database.entities.Company;
//
///**
// * @author Gajovski Maxime
// * @date 21 févr. 2017
// */
//public enum CompanyController {
//    INSTANCE;
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);
//    private static int currentPage = 0;
//    private static final int NUMBER = 10;
//
//    /**
//     * Private constructor for CompanyListCtrl singleton.
//     */
//    CompanyController() {
//    }
//
//    /**
//     * Main loop for CompanyListCtrl.
//     * @param scanner : the user's input.
//     */
//    public void companyListMainLoop(Scanner scanner) {
//        String userChoice = null;
//        List<Company> companies;
//        do {
//            userChoice = scanner.nextLine();
//            switch (userChoice) {
//            case "n":
//                currentPage++;
//                if (!CompanyDAO.INSTANCE.findByPage(currentPage, NUMBER).isPresent()) {
//                    currentPage--;
//                }
//                companies = CompanyDAO.INSTANCE.findByPage(currentPage, NUMBER).get();
//                CompanyView.INSTANCE.displayCurrentPage(currentPage, companies);
//                break;
//            case "p":
//                if (currentPage <= 0) {
//                    currentPage++;
//                    LOGGER.warn(ControllerUtils.NEGATIVE_NUMBER_PAGE);
//                }
//                currentPage--;
//                companies = CompanyDAO.INSTANCE.findByPage(currentPage, NUMBER).get();
//                CompanyView.INSTANCE.displayCurrentPage(currentPage, companies);
//                break;
//            case "m":
//                currentPage = 0;
//                MainView.INSTANCE.displayMenu();
//                break;
//            default:
//                LOGGER.warn(ControllerUtils.USER_BAD_INPUT);
//                continue;
//            }
//        } while (!userChoice.equals("m"));
//    }
//
//    public List<Company> getFirstCompanies() {
//        return CompanyDAO.INSTANCE.findByPage(currentPage, NUMBER).get();
//    }
//
//    public int getCurrentPage() {
//        return currentPage;
//    }
//
//}
