package com.excilys.mgajovski.computer_database;

import java.sql.SQLException;
import java.util.Optional;

import com.excilys.mgajovski.computer_database.cli.views.MainView;
import com.excilys.mgajovski.computer_database.dao.ICompanyDAO;
import com.excilys.mgajovski.computer_database.dao.IComputerDAO;
import com.excilys.mgajovski.computer_database.dao.impl.CompanyDAO;
import com.excilys.mgajovski.computer_database.dao.impl.ComputerDAO;
import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.entities.Computer;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
public class Test {
    public static void main(String[] args) throws SQLException {
        MainView.INSTANCE.displayMenu();
        /*IComputerDAO compDAO = ComputerDAO.INSTANCE;
        ICompanyDAO companyDAO = CompanyDAO.INSTANCE;
        
        Company company = Company.builder().name("Ma company").build();
        
        System.out.println(companyDAO.find(1));
        System.out.println(companyDAO.create(Optional.of(company)));
        System.out.println(companyDAO.delete(44));*/
        
     /*   Optional<Computer> comp = compDAO.find(490);
        
        System.out.println(comp);
        Optional<Computer> comp2 = Optional.of(Computer.builder().name("256465").introduced(null).build());
        compDAO.create(comp2);

       System.out.println(comp);
       //comp.get().setId(Long.valueOf(0));
       comp.get().setName("Nope");
       comp.get().setDiscontinuedDate(null);
       comp = compDAO.update(comp); 
       compDAO.create(comp);*/
       
        //System.out.println(comp);
        /*
        Computer comp2;
        System.out.println(comp);
        
        compDAO.update(Optional.ofNullable(comp)).get();
        System.out.println(compDAO.delete(4));
        
        System.out.println(comp2);
        Optional<Computer> comp3 = compDAO.find(-50);

        System.out.println(comp3);
       
    }
    */
    }
}
