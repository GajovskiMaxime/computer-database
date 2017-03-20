package com.excilys.mgajovski.computer_database.cli.controllers;


import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.mgajovski.computer_database.cli.controllers.utils.ControllerUtils;
import com.excilys.mgajovski.computer_database.cli.views.CompanyView;
import com.excilys.mgajovski.computer_database.cli.views.MainView;
import com.excilys.mgajovski.computer_database.dto.page.PageDTO;
import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.exceptions.DAOException;
import com.excilys.mgajovski.computer_database.exceptions.PageException;
import com.excilys.mgajovski.computer_database.services.Service;

/**
 * @author Gajovski Maxime
 * @date 21 févr. 2017
 */
public enum CompanyController {
    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);
    public static final PageDTO<Company> PAGE = new PageDTO<>();
    
    private static final int FIRST_PAGE = 0;
    private static final int NUMBER_OF_ELEMENTS = 10;
    
    static{
        PAGE.setCurrentPage(FIRST_PAGE);
        PAGE.setElementsByPage(NUMBER_OF_ELEMENTS);
    }
    
    /**
     * Private constructor for CompanyListCtrl singleton.
     */
    CompanyController() {
    }

    /**
     * Main loop for CompanyListCtrl.
     * @param scanner : the user's input.
     */
    public void companyListMainLoop(Scanner scanner) {
        String userChoice = null;
        List<Company> companies;
        do {
            userChoice = scanner.nextLine();
            switch (userChoice) {
            case "n":
                try {
                    companies = Service.COMPANY.findByPage(PAGE);
                } catch (DAOException e) {
                    
                } catch(PageException e) {
                    PAGE.previous();
                }
                
                PAGE.setElements(Service.COMPANY.findByPage(PAGE));
                CompanyView.INSTANCE.displayCurrentPage(PAGE);
                break;
                
            case "p":
                if (PAGE.getCurrentPage() <= 0) {
                    PAGE.next();
                    LOGGER.warn(ControllerUtils.NEGATIVE_NUMBER_PAGE);
                }
                PAGE.previous();
                PAGE.setElements(Service.COMPANY.findByPage(PAGE));
                CompanyView.INSTANCE.displayCurrentPage(PAGE);
                break;
            case "m":
                PAGE.setCurrentPage(0);
                MainView.INSTANCE.displayMenu();
                break;
            default:
                LOGGER.warn(ControllerUtils.USER_BAD_INPUT);
                continue;
            }
        } while (!userChoice.equals("m"));
    }

    public List<Company> getFirstCompanies() {
        return Service.COMPANY.findByPage(currentPage, NUMBER).get();
    }

    public int getCurrentPage() {
        return currentPage;
    }

}
