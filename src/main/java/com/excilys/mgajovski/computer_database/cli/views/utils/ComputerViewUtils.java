package com.excilys.mgajovski.computer_database.cli.views.utils;

import java.util.List;

import com.excilys.mgajovski.computer_database.entities.Computer;

/**
 * @author Gajovski Maxime
 * @date 26 févr. 2017
 */
public final class ComputerViewUtils {

    static final String COMPUTER_FORMAT_LINE = "%3s | %15.15s | %21s | %21s | %18s | %n";
    public static final String CONFIRM_DEL_COMPUTER = "Are you sure to delete the computer with id ";
    public static final String CONFIRM_UPDATE_COMPUTER = "Are you sure to update the computer with id ";
    public static final String[] COMPUTER_ATTRIBUTES = { 
            "name", 
            "introduced date", 
            "discontinued date", 
            "company id" };
    
    public static final String[] LIST_VIEW_FOOTER_LABELS = {
            "n - next page",
            "p - previous page",
            "id - Search by id",
            "a - add computer",
            "m - main menu" };

    public static final String[] DETAIL_FOOTER_LABELS = { 
            "r - return to the list of computers", 
            "u - update this computer",
            "d - delete this computer", 
            "m - main menu" };

    public static void displayComputerCurrentPage(int page) {
        ViewUtils.displayBread("COMPUTERS : Current page :" + page);
    }

    public static void displayComputersDetails(List<Computer> computers) {
        displayComputerHeader();
        for (Computer computer : computers) {
            System.out.printf(COMPUTER_FORMAT_LINE,
                    computer.getId(),
                    computer.getName(),
                    computer.getIntroducedDate() == null ? "" : computer.getIntroducedDate(),
                    computer.getDiscontinuedDate() == null ? "" : computer.getDiscontinuedDate(),
                    computer.getCompany() == null ? "" : computer.getCompany().getName());
        }
    }
    

    public static void displayConfirmationHeader(String string, long id) {
        ViewUtils.displayBread(string + " " + id + ": ");
    }
    
    /**
     * Display formatted computer's informations.
     */
    static void displayComputerHeader() {
        System.out.printf(COMPUTER_FORMAT_LINE, "ID", "NOM", "DATE CREATION", "DATE FIN", "FABRICANT");
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    /**
     * Display computer creation header.
     */
    static void printComputerAddHeader() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("-                       COMPUTER CREATION                           -");
        System.out.println("---------------------------------------------------------------------");
    }

}
