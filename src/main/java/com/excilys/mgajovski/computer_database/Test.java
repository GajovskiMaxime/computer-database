package com.excilys.mgajovski.computer_database;

import java.sql.SQLException;
import java.util.Optional;

import com.excilys.mgajovski.computer_database.dao.IComputerDAO;
import com.excilys.mgajovski.computer_database.dao.impl.ComputerDAO;
import com.excilys.mgajovski.computer_database.entities.Computer;
import com.excilys.mgajovski.computer_database.interfaces.mvc.IView;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
public class Test {
    public static void main(String[] args) throws SQLException {
//            IView.mainMenu().displayMenu();
        IComputerDAO compDAO = new ComputerDAO();
        Computer comp = Computer.builder().name("salut").build();
        Computer comp2;
        System.out.println(comp);

        comp2 = compDAO.create(Optional.ofNullable(comp)).get();
        System.out.println(comp2);
        Optional<Computer> comp3 = compDAO.find(-50);
    
        System.out.println(comp3);
       
    }

}
