package com.excilys.mgajovski.computer_database.cli.controllers;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.mgajovski.computer_database.cli.views.ComputerDetailView;
import com.excilys.mgajovski.computer_database.cli.views.ComputerListView;
import com.excilys.mgajovski.computer_database.cli.views.MainView;
import com.excilys.mgajovski.computer_database.dao.impl.ComputerDAO;
import com.excilys.mgajovski.computer_database.entities.Computer;

/**
 * @author Gajovski Maxime
 * @date 21 févr. 2017
 */
public enum ComputerDetailCtrl {
    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDetailCtrl.class);
    private static Computer computer = null;

    /**
     * Private constructor for ComputerDetailCtrl singleton.
     */
    ComputerDetailCtrl() {
    }

   

    public void deleteComputerSwitchMenu(Scanner scan) {
        String userChoice = null;
        do {
            ComputerDetailView.INSTANCE.printComputerDeleteConfirmationHeader(computer.getId());
            switch (userChoice = scan.nextLine()) {
            case "yes":
                ComputerDAO.INSTANCE.delete(computer);
                ComputerListView.INSTANCE.printFirstPage();
                break;
            case "no":
                LOGGER.warn(ControllerUtils.COMPUTER_NOT_DELETED);
                ComputerListView.INSTANCE.printFirstPage();
                break;
            default:
                LOGGER.warn(ControllerUtils.USER_BAD_INPUT);
                break;
            }
        } while (!userChoice.equals("no") || !userChoice.equals("yes"));
    }

    /*public void updateComputerSwitchMenu(Scanner scan) throws SQLException{
    String userChoice   = null;
    do {
        IView.computerDetail().printComputerDeleteConfirmationHeader(computer.getId());
        switch (userChoice = scan.nextLine()) {
            case "yes":{
                computerDAO.delete(computer);
                IView.computerList().printFirstPage();
            }break;
            case "no":{
                logger.warning(CtrlUtils.COMPUTER_NOT_DELETED);
                IView.computerList().printFirstPage();
            }break; 
            default:
                logger.warning(CtrlUtils.USER_BAD_INPUT);
                break;
        }
    } while (!userChoice.equals("no") || !userChoice.equals("yes"));
}*/
    
}
