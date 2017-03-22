package com.excilys.mgajovski.computer_database.cli.views.utils;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Gajovski Maxime
 * @date 21 févr. 2017
 */
public final class ViewUtils {
    
    static final String BAD_INPUT = "Bad Input! Try again !\n";
    static final String COMPUTER_NOT_DELETED = "Computer not deleted ! *HALLELUJAH*\n";
    static final String NEGATIVE_PAGE_REQUEST = "You can't reach a negative page number !\n";

    public static final String COMPUTER_SEARCH_BY_ID = "Search computer with id : ";
    public static final String USER_BAD_CHOICE = "Nope ! Try Again !\n";

    
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

    public static void footer(String footer) {
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.print(footer);
    }
    
    public static void footer(String[] strings) {
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.print(Arrays.toString(strings));
    }

}
