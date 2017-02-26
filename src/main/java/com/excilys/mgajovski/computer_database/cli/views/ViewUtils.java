package com.excilys.mgajovski.computer_database.cli.views;

import java.util.Scanner;

/**
 * @author Gajovski Maxime
 * @date 21 févr. 2017
 */
public class ViewUtils {

    static final String BAD_INPUT = "Bad Input! Try again !\n";
    static final String COMPUTER_NOT_DELETED = "Computer not deleted ! *HALLELUJAH*\n";
    static final String NEGATIVE_PAGE_REQUEST = "You can't reach a negative page number !\n";

    static final String COMPANY_FORMAT_LINE = "%3s | %15.15s%n";
    public static final String COMPUTER_SEARCH_BY_ID = "Search computer with id : ";
    static final String CONFIRMATION_DEL_COMPUTER = "Are you sure to delete the computer with id ";

    public static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Display a bread header with title.
     * @param string : the bread title.
     */
    public static void displayBread(String string) {
        System.out.println("");
        System.out.println(" > " + string);
        System.out.println("-------------------------------------------------------------------");

    }

   
    /**
     * Display formatted company's informations.
     */
    static void printCompanyHeader() {
        System.out.println("-------------------------------------------------------------------");
        System.out.printf(COMPANY_FORMAT_LINE, "ID", "NOM");
        System.out.println("-------------------------------------------------------------------");
    }

    public static void header(String string, int id) {
        System.out.println();
        System.out.println();
        System.out.println("-------------------------------------------------------------------");
        System.out.print(string + id + ": ");
    }

    static void footer(String footer) {
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.print(footer);
    }

    public static final String USER_BAD_CHOICE = "Nope ! Try Again !\n";


}
