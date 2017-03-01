package com.excilys.mgajovski.computer_database;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.excilys.mgajovski.computer_database.cli.views.MainView;
import com.excilys.mgajovski.computer_database.dao.impl.ComputerDAO;
import com.excilys.mgajovski.computer_database.entities.Computer;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
public class CLITest {
    public static void main(String[] args) throws SQLException {
        MainView.INSTANCE.displayMenu();
      /*Optional<List<Computer>> computers = ComputerDAO.INSTANCE.findWhereNameContainsSequence("lut");
      System.out.println(computers);*/
//      System.out.println(computers.get().get(0));
//      System.out.println(computers.get().get(1));
//      System.out.println(computers.get().get(2));
    }
}
