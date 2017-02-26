package com.excilys.mgajovski.computer_database.cli.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.mgajovski.computer_database.cli.views.ComputerDetailView;
import com.excilys.mgajovski.computer_database.cli.views.ComputerListView;
import com.excilys.mgajovski.computer_database.cli.views.MainView;
import com.excilys.mgajovski.computer_database.cli.views.ViewUtils;
import com.excilys.mgajovski.computer_database.dao.impl.ComputerDAO;
import com.excilys.mgajovski.computer_database.entities.Computer;

/**
 * @author Gajovski Maxime
 * @date 21 févr. 2017
 */
public enum ComputerListCtrl {
    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerListCtrl.class);
    private static int computerListPage = 0;
    private static final int NUMBER = 10;

    /**
     * Private constructor for ComputerListCtrl singleton.
     */
    ComputerListCtrl() {

    }

    /**
     * Main loop for computer selection view.
     * @param scanner : the user's input.
     */
    public void searchByIdSwitchMenu(Scanner scanner) {
        Optional<Computer> computer = Optional.empty();
        String userInput;
        while (!computer.isPresent()) {
            ViewUtils.displayBread(ViewUtils.COMPUTER_SEARCH_BY_ID);
            userInput = scanner.nextLine();
            if (ControllerUtils.isANumber(userInput)) {
                computer = ComputerDAO.INSTANCE.find(Long.parseLong(userInput));
            }
        }

        ComputerDetailView.INSTANCE.displayComputerDetail(computer.get());

        do {
            switch (userInput = scanner.nextLine()) {
            case "r":
                computer = null;
                ComputerListView.INSTANCE.printFirstPage();
                break;
            case "u":
                break; // update
            case "d":
              //  deleteComputerSwitchMenu(scanner);
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


    /**
     * Main loop for computer list view.
     * @param scanner : the user's input.
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
                ComputerListView.INSTANCE.printCurrentPage(computerListPage, computers);
                break;
            case "p":
                if (computerListPage <= 0) {
                    computerListPage++;
                    LOGGER.warn(ControllerUtils.NEGATIVE_NUMBER_PAGE);
                }
                computerListPage--;
                computers = ComputerDAO.INSTANCE.findByPage(computerListPage, NUMBER).get();
                ComputerListView.INSTANCE.printCurrentPage(computerListPage, computers);
                break;
            case "m":
                computerListPage = 0;
                MainView.INSTANCE.displayMenu();
                break;
            // case "d":break;
            case "id":
                computerListPage = 0;
                //ComputerDetailView.INSTANCE.printComputerSearchByIdHeader();
                searchByIdSwitchMenu(ViewUtils.SCANNER);
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
