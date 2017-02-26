package com.excilys.mgajovski.computer_database.cli.controllers;

/**
 * @author Gajovski Maxime
 * @date 21 févr. 2017
 */
public class ControllerUtils {

    static final String USER_BAD_INPUT = "Bad Input.. try again !\n";
    static final String NEGATIVE_NUMBER_PAGE = "Current Page can't be a negative number !\n";
    static final String COMPUTER_NOT_DELETED = "Computer not deleted!\n";
    static final String NUMBER_EXPRESSION = "^[0-9]+$";

    /**
     * This method controls if the string is a number.
     * @param string : the string to control.
     * @return if the string input is a number or not.
     */
    static boolean isANumber(String string) {
        return string.matches(NUMBER_EXPRESSION);
    }
}
