package com.excilys.mgajovski.computer_database;

import java.sql.SQLException;

import com.excilys.mgajovski.computer_database.cli.views.MainView;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
public class CLITest {
    public static void main(String[] args) throws SQLException {
        MainView.INSTANCE.displayMenu();
    }
}
