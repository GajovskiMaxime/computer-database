package com.excilys.mgajovski.computer_database.cli.views.utils;

/**
 * @author	Gajovski Maxime
 * @date	27 févr. 2017
 */
public final class CompanyViewUtils {
    

    public static final String COMPANY_FORMAT_LINE = "%3s | %15.15s%n";
    
    /**
     * Display formatted company's informations.
     */
    static void printCompanyHeader() {
        System.out.printf(COMPANY_FORMAT_LINE, "ID", "NOM");
        System.out.println("-------------------------------------------------------------------");
    }

    
    public static final  String[] LIST_VIEW_FOOTER_LABELS = {
            "n - next page",
            "p - previous page",
            "m - main menu" };

    public static void displayCompanyHeaderWithPage(int page) {
        ViewUtils.displayBread("COMPANIES : Current page : " + page);
        printCompanyHeader();
     }
    
}
