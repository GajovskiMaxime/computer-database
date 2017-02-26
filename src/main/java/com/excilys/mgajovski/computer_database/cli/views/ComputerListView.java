package com.excilys.mgajovski.computer_database.cli.views;

import java.util.List;

import com.excilys.mgajovski.computer_database.cli.controllers.ComputerListCtrl;
import com.excilys.mgajovski.computer_database.entities.Computer;

/**
 * @author Gajovski Maxime
 * @date 21 févr. 2017
 */
public enum ComputerListView {
    INSTANCE;

    /**
     * Private constructor for ComputerListView singleton.
     */
    ComputerListView() {
    }

    /**
     * 
     * @param page
     * @param computers
     */
    public void printCurrentPage(int page, List<Computer> computers) {
        ComputerViewUtils.printComputerHeaderWithPage(page);
        ComputerViewUtils.displayComputersDetails(computers);
        ComputerListCtrl.INSTANCE.computerListMainLoop(ViewUtils.SCANNER);
    }

    /**
     * Display the first page of computers.
     */
    public void printFirstPage() {
        printCurrentPage(ComputerListCtrl.INSTANCE.getCurrentPage(), ComputerListCtrl.INSTANCE.getFirstComputers());
    }

}
