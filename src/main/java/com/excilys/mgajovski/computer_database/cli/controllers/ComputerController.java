//package com.excilys.mgajovski.computer_database.cli.controllers;
//
//import java.sql.SQLException;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//import java.util.Scanner;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.excilys.mgajovski.computer_database.cli.controllers.utils.ControllerUtils;
//import com.excilys.mgajovski.computer_database.cli.views.ComputerView;
//import com.excilys.mgajovski.computer_database.cli.views.MainView;
//import com.excilys.mgajovski.computer_database.cli.views.utils.ComputerViewUtils;
//import com.excilys.mgajovski.computer_database.cli.views.utils.ViewUtils;
//import com.excilys.mgajovski.computer_database.dao.DAO;
//import com.excilys.mgajovski.computer_database.dao.impl.ComputerDAO;
//import com.excilys.mgajovski.computer_database.dto.page.PageDTO;
//import com.excilys.mgajovski.computer_database.entities.Computer;
//import com.excilys.mgajovski.computer_database.exceptions.DAOException;
//import com.excilys.mgajovski.computer_database.exceptions.PageException;
//
///**
// * @author Gajovski Maxime
// * @date 21 févr. 2017
// */
//public enum ComputerController {
//  INSTANCE;
//
//  private static final Logger LOGGER = LoggerFactory.getLogger(ComputerController.class);
//  private static final PageDTO<Computer> PAGE = new PageDTO<>();
//  
//  private static final int CURRENT_PAGE = 0;
//  private static final int ELEMENTS_BY_PAGE = 10;
//  
//  static{
//      PAGE.setCurrentPage(CURRENT_PAGE);
//      PAGE.setElementsByPage(ELEMENTS_BY_PAGE);
//  }
//  
//  /**
//   * Private constructor for ComputerListCtrl singleton.
//   */
//  ComputerController() {
//      
//  }
//
//  /**
//   * Main loop for computer selection view.
//   * @param scanner
//   *          : the user's input.
// * @throws DAOException 
// * @throws NumberFormatException 
//   */
//  public void computerDetailMainLoop(Scanner scanner) throws NumberFormatException, DAOException {
//    Computer computer = null;
//    String userInput;
//    while (computer == null) {
//      LOGGER.warn(ControllerUtils.USER_BAD_INPUT);
//      ViewUtils.displayBread(ViewUtils.COMPUTER_SEARCH_BY_ID);
//      userInput = scanner.nextLine();
//      if (ControllerUtils.isANumber(userInput)) {
//        computer = DAO.COMPUTER.find(Long.parseLong(userInput));
//      }
//    }
//    ComputerViewUtils.displayComputersDetails(Arrays.asList(computer));
//    ViewUtils.footer(ComputerViewUtils.DETAIL_FOOTER_LABELS);
//
//    do {
//      switch (userInput = scanner.nextLine()) {
//      case "r":
//        computer = null;
//        ComputerView.INSTANCE.printFirstPage();
//        break;
//      case "u":
//        computerUpdateMainLoop(scanner, computer);
//        break;
//      case "d":
//        computerDeleteMainLoop(scanner, computer);
//        break;
//      case "m":
//        MainView.INSTANCE.displayMenu();
//        break;
//      default:
//        LOGGER.warn(ControllerUtils.USER_BAD_INPUT);
//        break;
//      }
//    } while (!userInput.equals("m"));
//  }
//
//  public void computerDeleteMainLoop(Scanner scan, Computer computer) throws DAOException {
//    String userInput;
//    do {
//      ComputerViewUtils.displayConfirmationHeader(ComputerViewUtils.CONFIRM_DEL_COMPUTER,
//              computer.getId());
//
//      switch (userInput = scan.nextLine()) {
//      case "yes":
//        DAO.COMPUTER.delete(computer);
//        ComputerView.INSTANCE.printFirstPage();
//        break;
//      case "no":
//        LOGGER.warn(ControllerUtils.COMPUTER_NOT_DELETED);
//        ComputerView.INSTANCE.printFirstPage();
//        break;
//      default:
//        LOGGER.warn(ControllerUtils.USER_BAD_INPUT);
//        break;
//      }
//    } while (!userInput.equals("no") || !userInput.equals("yes"));
//  }
//
//
//  public List<String> getComputerAttributes(Scanner scan, Computer computer) {
//    //TODO
//    return null;
//  }
//  
//  public void computerUpdateMainLoop(Scanner scan, Computer computer) throws DAOException {
//    
//    String userInput;
//    do {
//      ComputerViewUtils.displayConfirmationHeader(
//          ComputerViewUtils.CONFIRM_UPDATE_COMPUTER,
//          computer.getId());
//      switch (userInput = scan.nextLine()) {
//      case "yes":
//        getComputerAttributes(scan, computer);
//        
//        DAO.COMPUTER.delete(computer);
//        break;
//      case "no":
//        LOGGER.warn(ControllerUtils.COMPUTER_NOT_UPDATE);
//        ComputerView.INSTANCE.printFirstPage();
//        break;
//      default:
//        LOGGER.warn(ControllerUtils.USER_BAD_INPUT);
//        break;
//      }
//    } while (!userInput.equals("no") || !userInput.equals("yes"));
//  }
//
//  /**
//   * Main loop for computer list view.
//   * 
//   * @param scanner
//   *          : the user's input.
//   */
//  public void computerListMainLoop(Scanner scanner) {
//    String userChoice = null;
//    List<Computer> computers;
//    
//    do {
//      userChoice = scanner.nextLine();
//      switch (userChoice) {
//      case "n":
//          //TODO TRY CATCH
//        PAGE.nextPage();
//        
//        try {
//            DAO.COMPUTER.findByPage(PAGE);
//        } catch (PageException e) {
//            PAGE.previousPage(); 
//        } catch (DAOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//          
//        try {
//            computers = DAO.COMPUTER.findByPage(PAGE);
//        } catch (PageException | DAOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        ComputerView.INSTANCE.printCurrentPage(CURRENT_PAGE, computers);
//        break;
//      case "p":
//        if (CURRENT_PAGE <= 0) {
//          PAGE.nextPage();
//          LOGGER.warn(ControllerUtils.NEGATIVE_NUMBER_PAGE);
//        }
//        CURRENT_PAGE--;
//        computers = ComputerDAO.INSTANCE.findByPage(PAGE);
//        ComputerView.INSTANCE.printCurrentPage(CURRENT_PAGE, computers);
//        break;
//      case "m":
//        CURRENT_PAGE = 0;
//        MainView.INSTANCE.displayMenu();
//        break;
//      // case "a":break;
//      // TODO
//      case "id":
//        CURRENT_PAGE = 0;
//        computerDetailMainLoop(ViewUtils.SCANNER);
//        break;
//      default:
//        LOGGER.warn(ControllerUtils.USER_BAD_INPUT);
//        break;
//      }
//    } while (!userChoice.equals("q"));
//  }
//
//  public List<Computer> getFirstComputers() {
//    return ComputerDAO.INSTANCE.findByPage(CURRENT_PAGE, ELEMENTS_BY_PAGE).get();
//
//  }
//
//  public int getCurrentPage() {
//    return CURRENT_PAGE;
//  }
//}
