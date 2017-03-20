package com.excilys.mgajovski.computer_database.cli.views;

import java.util.List;

import com.excilys.mgajovski.computer_database.cli.controllers.ComputerController;
import com.excilys.mgajovski.computer_database.cli.views.utils.ComputerViewUtils;
import com.excilys.mgajovski.computer_database.cli.views.utils.ViewUtils;
import com.excilys.mgajovski.computer_database.entities.Computer;

/**
 * @author Gajovski Maxime
 * @date 21 févr. 2017
 */
public enum ComputerView {
    INSTANCE;

    /**
     * Private constructor for ComputerListView singleton.
     */
    ComputerView() {
    }

    public void printCurrentPage(int page, List<Computer> computers) {
        ComputerViewUtils.displayComputerCurrentPage(page);
        ComputerViewUtils.displayComputersDetails(computers);
        ViewUtils.footer(ComputerViewUtils.LIST_VIEW_FOOTER_LABELS);
        ComputerController.INSTANCE.computerListMainLoop(ViewUtils.SCANNER);
    }

    /**
     * Display the first page of computers.
     */
    public void printFirstPage() {
        printCurrentPage(ComputerController.INSTANCE.getCurrentPage(), ComputerController.INSTANCE.getFirstComputers());
    }

}
