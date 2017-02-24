package com.excilys.mgajovski.computer_database.cli.controllers;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

import com.excilys.mgajovski.computer_database.interfaces.mvc.IView;

/**
 * @author Gajovski Maxime
 * @date 21 févr. 2017
 */
public class MainCtrl {

    private static final Logger logger = Logger.getLogger(MainCtrl.class.getName());
    private static MainCtrl _instance = null;

    private MainCtrl() throws SQLException {
        logger.info("Lancement du programme");
    }

    public void switchMenu(Scanner scan) throws SQLException {
        String userChoice = null;
        do {
            switch (userChoice = scan.nextLine()) {
            case "0":
                IView.computerList().printFirstPage();
                break;
            case "1":
                IView.companyList().printFirstPage();
                break;
            case "2":
                break;
            case "3":
                IView.mainMenu().closeMenu();
                System.exit(0);
            default:
                logger.warning(CtrlUtils.USER_BAD_INPUT);
                break;
            }
        } while (!userChoice.equals("3"));
    }

    public static synchronized MainCtrl getInstance() throws SQLException {
        if (_instance == null) {
            _instance = new MainCtrl();
        }
        return _instance;
    }
}
