//package com.excilys.mgajovski.computer_database.cli.controllers;
//
//import java.sql.SQLException;
//import java.util.Scanner;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import com.excilys.mgajovski.computer_database.cli.controllers.utils.ControllerUtils;
//import com.excilys.mgajovski.computer_database.cli.views.CompanyView;
//import com.excilys.mgajovski.computer_database.cli.views.ComputerView;
//import com.excilys.mgajovski.computer_database.cli.views.MainView;
//
///**
// * @author Gajovski Maxime
// * @date 21 févr. 2017
// */
//
//@Scope("singleton")
//@Component
//public class MainController {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
//    
//    @Autowired
//    public ComputerView computerView;
//    
//    @Autowired
//    public CompanyView companyView;
//    
//    @Autowired
//    public MainView mainView;
//    
//    /**
//     * Private constructor for MainCtrl singleton.
//     */
//    MainController() {
//    }
//
//    /**
//     * Main loop for mainView.
//     * @param scan : the user's input.
//     * @throws SQLException
//     */
//    public void switchMenu(Scanner scan) {
//        String userChoice = null;
//        do {
//            switch (userChoice = scan.nextLine()) {
//            case "0":
//                computerView.displayCurrentPage();
//                break;
//            case "1":
//                companyView.displayCurrentPage();
//                break;
//            case "2":
//                break;
//            case "3":
//                mainView.closeMenu();
//                System.exit(0);
//            default:
//                LOGGER.warn(ControllerUtils.USER_BAD_INPUT);
//                break;
//            }
//        } while (!userChoice.equals("3"));
//    }
//
//}
