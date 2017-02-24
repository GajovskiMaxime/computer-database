package com.excilys.mgajovski.computer_database.cli.views;

import java.sql.SQLException;
import java.util.Scanner;

import com.excilys.mgajovski.computer_database.interfaces.mvc.IController;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
public class MainView {

    private static final Scanner scanner = new Scanner(System.in);
    private static MainView _instance = null;

    private final static String[] MAIN_MENU_LABELS = { "List computers", "List companies", "Create a computer",
            "Quit" };

    public void closeMenu() {
        System.out.println("\n\n-------------------------------------------------------------------");
        System.out.print("FIN DU PROGRAMME");
    }

    private void displayHeaderMenu() {
        System.out.println("*******************************************************************");
        System.out.println("***                                                             ***");
        System.out.println("***                  Gestionaire d'ordinateurs                  ***");
        System.out.println("***                                                             ***");
        System.out.println("*******************************************************************");
    }

    private void printBread(String pLink) {
        System.out.println("");
        System.out.println(" > " + pLink);
        System.out.println("-------------------------------------------------------------------");

    }

    private void displayMenuItems() {
        for (int i = 0; i < MAIN_MENU_LABELS.length; i++)
            System.out.printf("%3d > %s\n", i, MAIN_MENU_LABELS[i]);
    }

    private MainView() throws SQLException {
    }

    public void displayMenu() throws SQLException {
        displayHeaderMenu();
        printBread("MainMenu");
        displayMenuItems();
        IController.mainMenuCtrl().switchMenu(scanner);
    }

    public static MainView getInstance() throws SQLException {
        if (_instance == null) {
            _instance = new MainView();
        }
        return _instance;
    }
}