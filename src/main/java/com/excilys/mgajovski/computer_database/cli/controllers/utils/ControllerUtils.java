package com.excilys.mgajovski.computer_database.cli.controllers.utils;

/**
 * @author Gajovski Maxime
 * @date 21 févr. 2017
 */
public class ControllerUtils {
    

    public static final int FIRST_PAGE = 0;
    public static final int NUMBER_OF_ELEMENTS = 10;    
    
    public static final String USER_BAD_INPUT = "Bad Input, try again.\n";
    public static final String NEGATIVE_NUMBER_PAGE = "Current page can't be a negative number.\n";
    public static final String COMPUTER_NOT_DELETED = "Computer not deleted.\n";
    public static final String COMPUTER_NOT_UPDATE = "Computer not updated.\n";
    public static final String NUMBER_EXPRESSION = "^[0-9]+$";

    /**
     * This method controls if the string is a number.
     * @param string : the string to control.
     * @return if the string input is a number or not.
     */
    public static boolean isANumber(String string) {
        return string.matches(NUMBER_EXPRESSION);
    }
}
