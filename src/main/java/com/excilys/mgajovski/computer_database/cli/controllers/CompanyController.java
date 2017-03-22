package com.excilys.mgajovski.computer_database.cli.controllers;

import java.util.List;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.mgajovski.computer_database.cli.controllers.utils.ControllerUtils;
import com.excilys.mgajovski.computer_database.cli.views.CompanyView;
import com.excilys.mgajovski.computer_database.cli.views.MainView;
import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.exceptions.PageException;
import com.excilys.mgajovski.computer_database.exceptions.ServiceException;
import com.excilys.mgajovski.computer_database.pager.CompanyPageService;
import com.excilys.mgajovski.computer_database.pager.Page;
import com.excilys.mgajovski.computer_database.services.CompanyService;

/**
 * @author Gajovski Maxime
 * @date 21 févr. 2017
 */
@Scope("singleton")
@Component
public class CompanyController {

  private static Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);

  @Autowired
  public CompanyPageService companyPageService;

  @Autowired
  public CompanyService companyService;

  @Autowired
  public CompanyView companyView;

  @Autowired
  public MainView mainView;
  
  public Page<Company> page;

  /**
   * Private constructor for CompanyListCtrl singleton.
   */
  CompanyController() {
  }

  @PostConstruct
  public void initialize() {
      page = companyPageService.create();
  }

  /**
   * Main loop for CompanyListCtrl.
   * 
   * @param scanner
   *          : the user's input.
   */
  public void companyListMainLoop(Scanner scanner) {
    String userChoice = null;
    do {
      userChoice = scanner.nextLine();
      switch (userChoice) {
      case "n":
        page = companyPageService.next(page);
        companyView.displayCurrentPage();
        break;

      case "p":
        page = companyPageService.previous(page);
        companyView.displayCurrentPage();
        break;
      case "m":
        page = companyPageService.resetPage(page);
        mainView.displayMenu();
        break;
      default:
        LOGGER.warn(ControllerUtils.USER_BAD_INPUT);
        continue;
      }
    } while (!userChoice.equals("m"));
  }

  public int getCurrentPage() {
    return page.getCurrentPage();
  }

  public int getElementsByPage() {
    return page.getElementsByPage();
  }

  public List<Company> getElements() {
    return page.getElements();
  }
}
