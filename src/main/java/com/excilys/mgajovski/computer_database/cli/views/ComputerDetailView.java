package com.excilys.mgajovski.computer_database.cli.views;

import java.sql.SQLException;
import java.util.Arrays;
import com.excilys.mgajovski.computer_database.cli.controllers.ComputerListCtrl;
import com.excilys.mgajovski.computer_database.entities.Computer;

/**
 * @author Gajovski Maxime
 * @date 21 févr. 2017
 */
public enum ComputerDetailView {
    INSTANCE;

    final static String[] FOOTER_LABELS = { "r - return to the list of computers", "u - update this computer",
            "d - delete this computer", "m - main menu" };

    final static String[] COMPUTER_ATTRIBUTES = { "name", "introduced date", "discontinued date", "company id" };

  /*  public void printComputerSearchByIdHeader() {
        ViewUtils.header(ViewUtils.COMPUTER_SEARCH_BY_ID);
        //ComputerListCtrl.INSTANCE.searchByIdSwitchMenu(ViewUtils.SCANNER);
    }*/

    public void printComputerAttributes() throws SQLException {
        System.out.println(COMPUTER_ATTRIBUTES);
        ComputerListCtrl.INSTANCE.searchByIdSwitchMenu(ViewUtils.SCANNER);
    }

    public void printComputerDeleteConfirmationHeader(Long id) {
        ViewUtils.header(ViewUtils.CONFIRMATION_DEL_COMPUTER + id);
    }


}
