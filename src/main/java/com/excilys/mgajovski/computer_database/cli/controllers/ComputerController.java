package com.excilys.mgajovski.computer_database.cli.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.mgajovski.computer_database.cli.controllers.utils.ControllerUtils;
import com.excilys.mgajovski.computer_database.cli.views.ComputerView;
import com.excilys.mgajovski.computer_database.cli.views.MainView;
import com.excilys.mgajovski.computer_database.cli.views.utils.ComputerViewUtils;
import com.excilys.mgajovski.computer_database.cli.views.utils.ViewUtils;
import com.excilys.mgajovski.computer_database.dao.impl.ComputerDAO;
import com.excilys.mgajovski.computer_database.entities.Computer;

/**
 * @author Gajovski Maxime
 * @date 21 févr. 2017
 */
public enum ComputerController {
  INSTANCE;

  private static final Logger LOGGER = LoggerFactory.getLogger(ComputerController.class);
  private static int computerListPage = 0;
  private static final int NUMBER = 10;

  /**
   * Private constructor for ComputerListCtrl singleton.
   */
  ComputerController() {

  }

  /**
   * Main loop for computer selection view.
   * 
   * @param scanner
   *          : the user's input.
   */
  public void computerDetailMainLoop(Scanner scanner) {
    Optional<Computer> computer = Optional.empty();
    String userInput;
    while (!computer.isPresent()) {
      LOGGER.warn(ControllerUtils.USER_BAD_INPUT);
      ViewUtils.displayBread(ViewUtils.COMPUTER_SEARCH_BY_ID);
      userInput = scanner.nextLine();
      if (ControllerUtils.isANumber(userInput)) {
        computer = ComputerDAO.INSTANCE.find(Long.parseLong(userInput));
      }
    }
    ComputerViewUtils.displayComputersDetails(Arrays.asList(computer.get()));
    ViewUtils.footer(ComputerViewUtils.DETAIL_FOOTER_LABELS);

    do {
      switch (userInput = scanner.nextLine()) {
      case "r":
        computer = Optional.empty();
        ComputerView.INSTANCE.printFirstPage();
        break;
      case "u":
        computerUpdateMainLoop(scanner, computer);
        break;
      case "d":
        computerDeleteMainLoop(scanner, computer);
        break;
      case "m":
        MainView.INSTANCE.displayMenu();
        break;
      default:
        LOGGER.warn(ControllerUtils.USER_BAD_INPUT);
        break;
      }
    } while (!userInput.equals("m"));
  }

  public void computerDeleteMainLoop(Scanner scan, Optional<Computer> optComputer) {
    String userInput;
    do {
      ComputerViewUtils.displayConfirmationHeader(ComputerViewUtils.CONFIRM_DEL_COMPUTER,
          optComputer.get().getId());

      switch (userInput = scan.nextLine()) {
      case "yes":
        ComputerDAO.INSTANCE.delete(optComputer);
        ComputerView.INSTANCE.printFirstPage();
        break;
      case "no":
        LOGGER.warn(ControllerUtils.COMPUTER_NOT_DELETED);
        ComputerView.INSTANCE.printFirstPage();
        break;
      default:
        LOGGER.warn(ControllerUtils.USER_BAD_INPUT);
        break;
      }
    } while (!userInput.equals("no") || !userInput.equals("yes"));
  }


  public List<String> getComputerAttributes(Scanner scan, Optional<Computer> optComputer) {
    //TODO
    return null;
  }
  
  public void computerUpdateMainLoop(Scanner scan, Optional<Computer> optComputer) {
    
    String userInput;
    do {
      ComputerViewUtils.displayConfirmationHeader(
          ComputerViewUtils.CONFIRM_UPDATE_COMPUTER,
          optComputer.get().getId());
      switch (userInput = scan.nextLine()) {
      case "yes":
        getComputerAttributes(scan, optComputer);
        
        ComputerDAO.INSTANCE.delete(optComputer);
        break;
      case "no":
        LOGGER.warn(ControllerUtils.COMPUTER_NOT_UPDATE);
        ComputerView.INSTANCE.printFirstPage();
        break;
      default:
        LOGGER.warn(ControllerUtils.USER_BAD_INPUT);
        break;
      }
    } while (!userInput.equals("no") || !userInput.equals("yes"));
  }

  /**
   * Main loop for computer list view.
   * 
   * @param scanner
   *          : the user's input.
   */
  public void computerListMainLoop(Scanner scanner) {
    String userChoice = null;
    List<Computer> computers;
    do {
      userChoice = scanner.nextLine();
      switch (userChoice) {
      case "n":
        computerListPage++;
        if (!ComputerDAO.INSTANCE.findByPage(computerListPage, NUMBER).isPresent()) {
          computerListPage--;
        }
        computers = ComputerDAO.INSTANCE.findByPage(computerListPage, NUMBER).get();
        ComputerView.INSTANCE.printCurrentPage(computerListPage, computers);
        break;
      case "p":
        if (computerListPage <= 0) {
          computerListPage++;
          LOGGER.warn(ControllerUtils.NEGATIVE_NUMBER_PAGE);
        }
        computerListPage--;
        computers = ComputerDAO.INSTANCE.findByPage(computerListPage, NUMBER).get();
        ComputerView.INSTANCE.printCurrentPage(computerListPage, computers);
        break;
      case "m":
        computerListPage = 0;
        MainView.INSTANCE.displayMenu();
        break;
      // case "a":break;
      // TODO
      case "id":
        computerListPage = 0;
        computerDetailMainLoop(ViewUtils.SCANNER);
        break;
      default:
        LOGGER.warn(ControllerUtils.USER_BAD_INPUT);
        break;
      }
    } while (!userChoice.equals("q"));
  }

  public List<Computer> getFirstComputers() {
    return ComputerDAO.INSTANCE.findByPage(computerListPage, NUMBER).get();

  }

  public int getCurrentPage() {
    return computerListPage;
  }
}
