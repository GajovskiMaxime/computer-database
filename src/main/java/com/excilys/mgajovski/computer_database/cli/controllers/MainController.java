package com.excilys.mgajovski.computer_database.cli.controllers;

import java.sql.SQLException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.mgajovski.computer_database.cli.controllers.utils.ControllerUtils;
import com.excilys.mgajovski.computer_database.cli.views.CompanyView;
import com.excilys.mgajovski.computer_database.cli.views.ComputerView;
import com.excilys.mgajovski.computer_database.cli.views.MainView;

/**
 * @author Gajovski Maxime
 * @date 21 févr. 2017
 */
public enum MainController {
    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    /**
     * Private constructor for MainCtrl singleton.
     */
    MainController() {
    }

    /**
     * Main loop for mainView.
     * @param scan : the user's input.
     * @throws SQLException
     */
    public void switchMenu(Scanner scan) {
        String userChoice = null;
        do {
            switch (userChoice = scan.nextLine()) {
            case "0":
                ComputerView.INSTANCE.printFirstPage();
                break;
            case "1":
                CompanyView.INSTANCE.printFirstPage();
                break;
            case "2":
                break;
            case "3":
                MainView.INSTANCE.closeMenu();
                System.exit(0);
            default:
                LOGGER.warn(ControllerUtils.USER_BAD_INPUT);
                break;
            }
        } while (!userChoice.equals("3"));
    }

}
