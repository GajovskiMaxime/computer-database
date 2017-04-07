package com.excilys.mgajovski.computer_database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//import com.excilys.mgajovski.computer_database.cli.views.MainView;
//import com.excilys.mgajovski.computer_database.entities.Company;
//import com.excilys.mgajovski.computer_database.entities.Computer;
//import com.excilys.mgajovski.computer_database.exceptions.DAOException;
//import com.excilys.mgajovski.computer_database.exceptions.ServiceException;
//import com.excilys.mgajovski.computer_database.pager.Page;
//import com.excilys.mgajovski.computer_database.services.CompanyService;
//import com.excilys.mgajovski.computer_database.services.ComputerService;
//import com.excilys.mgajovski.computer_database.spring.BeanConfig;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
public class CLITest {
  
//  @Autowired
//  public static MainView mainView;  
  
  public static void main(String[] args) {
//      ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
//      ComputerService  service = context.getBean(ComputerService.class);  
//      try {
//        for(Computer computer : service.findAll()){
//          System.out.println(computer.toString());
//        }
//      } catch (ServiceException e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//      }
      //      MainView mainView = context.getBean(MainView.class);  
//      mainView.displayMenu();
//        try {
//            service.delete(20);
//
//        } catch (DAOException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
//      

//        PageDTO<Company> companies = new PageDTO<>();
//        companies.setCurrentPage(0);
//        companies.setElementsByPage(10);
//        try {
//            System.out.println(Service.COMPANY.findByPage(companies));
//        } catch (PageException | DAOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        
//        
//        Computer computer = Computer.builder().name("Yololo").build();
//        
//        
//        try {
//            Service.COMPUTER.create(computer);
//            
//            Computer comp = Service.COMPUTER.find(10);
//            System.out.println(comp);
//            comp.setName("LOOOOL");
//            System.out.println(Service.COMPUTER.update(comp));
//        } catch (DAOException e) {
//            
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
       
      /*Optional<List<Computer>> computers = ComputerDAO.INSTANCE.findWhereNameContainsSequence("lut");
      System.out.println(computers);*/
//      System.out.println(computers.get().get(0));
//      System.out.println(computers.get().get(1));
//      System.out.println(computers.get().get(2));
    }
}
