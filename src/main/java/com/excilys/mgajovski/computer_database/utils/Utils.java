package com.excilys.mgajovski.computer_database.utils;

import java.util.Random;

/**
 * @author Gajovski Maxime
 * @date 23 févr. 2017
 */
public final class Utils {
    public static final String UNDEFINED = "undefined";

    /**
     * This method generate a random string of size size.
     * @param size : the size of the generated string.
     * @return a random string of size size.
     */
    public static String generateRandomString(int size) {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
}
