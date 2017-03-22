package com.excilys.mgajovski.computer_database.cli.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.mgajovski.computer_database.cli.controllers.utils.ControllerUtils;
import com.excilys.mgajovski.computer_database.cli.views.ComputerView;
import com.excilys.mgajovski.computer_database.cli.views.MainView;
import com.excilys.mgajovski.computer_database.cli.views.utils.ComputerViewUtils;
import com.excilys.mgajovski.computer_database.cli.views.utils.ViewUtils;
import com.excilys.mgajovski.computer_database.entities.Computer;
import com.excilys.mgajovski.computer_database.exceptions.DAOException;
import com.excilys.mgajovski.computer_database.exceptions.PageException;
import com.excilys.mgajovski.computer_database.exceptions.ServiceException;
import com.excilys.mgajovski.computer_database.pager.ComputerPageService;
import com.excilys.mgajovski.computer_database.pager.Page;
import com.excilys.mgajovski.computer_database.pager.PageServiceImpl;
import com.excilys.mgajovski.computer_database.services.ComputerService;

/**
 * @author Gajovski Maxime
 * @date 21 févr. 2017
 */
@Scope("singleton")
@Component
public class ComputerController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ComputerController.class);

  @Autowired
  private MainView mainView;

  @Autowired
  private ComputerPageService computerPageService;

  @Autowired
  private ComputerService computerService;

  @Autowired
  public ComputerView computerView;

  public Page<Computer> page;

  /**
   * Private constructor for ComputerListCtrl singleton.
   */
  ComputerController() {
  }

  @PostConstruct
  public void initialize() {
      page = computerPageService.create();
  }

  /**
   * Main loop for computer selection view.
   * 
   * @param scanner
   *          : the user's input.
   * @throws DAOException
   * @throws NumberFormatException
   */
  public void computerDetailMainLoop(Scanner scanner) {
    Computer computer = null;
    String userInput;
    while (computer == null) {
      LOGGER.warn(ControllerUtils.USER_BAD_INPUT);
      ViewUtils.displayBread(ViewUtils.COMPUTER_SEARCH_BY_ID);
      userInput = scanner.nextLine();
      if (ControllerUtils.isANumber(userInput)) {
        try {
          computer = computerService.find(Long.parseLong(userInput));
        } catch (ServiceException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
    ComputerViewUtils.displayComputersDetails(Arrays.asList(computer));
    ViewUtils.footer(ComputerViewUtils.DETAIL_FOOTER_LABELS);

    do {
      switch (userInput = scanner.nextLine()) {
      case "r":
        computer = null;
        computerView.displayCurrentPage();
        break;
      case "u":
        computerUpdateMainLoop(scanner, computer);
        break;
      case "d":
        try {
          computerDeleteMainLoop(scanner, computer);
        } catch (DAOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        break;
      case "m":
        mainView.displayMenu();
        break;
      default:
        LOGGER.warn(ControllerUtils.USER_BAD_INPUT);
        break;
      }
    } while (!userInput.equals("m"));
  }

  public void computerDeleteMainLoop(Scanner scan, Computer computer) throws DAOException {
    String userInput;
    do {
      ComputerViewUtils.displayConfirmationHeader(ComputerViewUtils.CONFIRM_DEL_COMPUTER,
          computer.getId());

      switch (userInput = scan.nextLine()) {
      case "yes":
        try {
          computerService.delete(computer);
          // resetToInitialPage();
        } catch (ServiceException exception) {
          LOGGER.error(exception.getMessage(), exception);
        }
        computerView.displayCurrentPage();
        break;
      case "no":
        LOGGER.warn(ControllerUtils.COMPUTER_NOT_DELETED);
        computerView.displayCurrentPage();
        break;
      default:
        LOGGER.warn(ControllerUtils.USER_BAD_INPUT);
        break;
      }
    } while (!userInput.equals("no") || !userInput.equals("yes"));
  }

  public List<String> getComputerAttributes(Scanner scan, Computer computer) {
    // TODO
    return null;
  }

  public void computerUpdateMainLoop(Scanner scan, Computer computer) {

    String userInput;
    do {
      ComputerViewUtils.displayConfirmationHeader(ComputerViewUtils.CONFIRM_UPDATE_COMPUTER,
          computer.getId());
      switch (userInput = scan.nextLine()) {
      case "yes":
        getComputerAttributes(scan, computer);

        try {
          computerService.delete(computer);
        } catch (ServiceException exception) {
          LOGGER.error(exception.getMessage(), exception);
        }
        break;
      case "no":
        LOGGER.warn(ControllerUtils.COMPUTER_NOT_UPDATE);
        computerView.displayCurrentPage();
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

    do {
      userChoice = scanner.nextLine();
      switch (userChoice) {
      case "n":
        page = computerPageService.next(page);
        computerView.displayCurrentPage();
        break;
      case "p":
        page = computerPageService.previous(page);
        computerView.displayCurrentPage();
        break;
      case "m":
        page = computerPageService.resetPage(page);
        mainView.displayMenu();
        break;
      // case "a":break;
      // TODO
      case "id":
        page = computerPageService.resetPage(page);
        computerDetailMainLoop(ViewUtils.SCANNER);
        break;
      default:
        LOGGER.warn(ControllerUtils.USER_BAD_INPUT);
        break;
      }
    } while (!userChoice.equals("q"));
  }

  public int getCurrentPage() {
    return page.getCurrentPage();
  }

  public int getElementsByPage() {
    return page.getElementsByPage();
  }

  public List<Computer> getElements() {
    return page.getElements();
  }
}
