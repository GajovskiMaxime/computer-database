package com.excilys.mgajovski.computer_database;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.excilys.mgajovski.computer_database.dao.DAO;
import com.excilys.mgajovski.computer_database.dao.IComputerDAO;
import com.excilys.mgajovski.computer_database.dao.columns.ComputerColumn;
import com.excilys.mgajovski.computer_database.dao.impl.ComputerDAO;
import com.excilys.mgajovski.computer_database.entities.Computer;
import com.excilys.mgajovski.computer_database.exceptions.DAOException;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
public class CLITest {
    public static void main(String[] args) throws SQLException {
//        MainView.INSTANCE.displayMenu();
        
        IComputerDAO cDAO = DAO.COMPUTER;
        Computer computer = Computer.builder().name("Yololo").build();
        
        try {
            System.out.println(cDAO.sizeOfFilteredQuery("lut"));
            Computer comp = cDAO.find(629);
            comp.setName("LOOOOL");
            cDAO.update(comp);
        } catch (DAOException e) {
            // TODO Auto-generated catch block
//            e.printStackTrace();
        }
       
      /*Optional<List<Computer>> computers = ComputerDAO.INSTANCE.findWhereNameContainsSequence("lut");
      System.out.println(computers);*/
//      System.out.println(computers.get().get(0));
//      System.out.println(computers.get().get(1));
//      System.out.println(computers.get().get(2));
    }
}
