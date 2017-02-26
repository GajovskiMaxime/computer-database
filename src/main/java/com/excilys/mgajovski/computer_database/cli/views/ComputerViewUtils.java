package com.excilys.mgajovski.computer_database.cli.views;

import java.util.Arrays;
import java.util.List;

import com.excilys.mgajovski.computer_database.entities.Computer;

/**
 * @author Gajovski Maxime
 * @date 26 févr. 2017
 */
public class ComputerViewUtils {

    static final String COMPUTER_FORMAT_LINE = "%3s | %15.15s | %21s | %21s | %18s | %n";

    static final String[] FOOTER_LABELS = {
            "n - next page",
            "p - previous page",
            "id - Search by id",
            "a - add computer",
            "m - main menu" };


    static void printComputerHeaderWithPage(int page) {
        ViewUtils.displayBread("COMPUTERS : Current page :" + page);
        ComputerViewUtils.displayComputerHeader();
    }


    static void displayComputersDetails(List<Computer> computers) {
        for (Computer computer : computers) {
            System.out.printf(COMPUTER_FORMAT_LINE,
                    computer.getId(),
                    computer.getName(),
                    computer.getIntroducedDate() == null ? "" : computer.getIntroducedDate(),
                    computer.getDiscontinuedDate() == null ? "" : computer.getDiscontinuedDate(),
                    computer.getCompany() == null ? "" : computer.getCompany().getName());
        }
        ViewUtils.footer(Arrays.toString(FOOTER_LABELS));
    }

    /**
     * Display formatted computer's informations.
     */
    static void displayComputerHeader() {
        System.out.println("-------------------------------------------------------------------------------------------");
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
