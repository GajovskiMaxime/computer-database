//package com.excilys.mgajovski.computer_database.cli.views;
//
//import com.excilys.mgajovski.computer_database.cli.controllers.MainController;
//import com.excilys.mgajovski.computer_database.cli.views.utils.ViewUtils;
//
///**
// * @author Gajovski Maxime
// * @date 20 févr. 2017
// */
//public enum MainView {
//    INSTANCE;
//
//    private static final  String[] MAIN_MENU_LABELS = {
//            "List computers",
//            "List companies",
//            "Create a computer",
//            "Quit" };
//
//    /**
//     * Display closed program footer.
//     */
//    public void closeMenu() {
//        System.out.println("\n\n-------------------------------------------------------------------");
//        System.out.print("FIN DU PROGRAMME");
//    }
//
//    /**
//     * Display the main menu header.
//     */
//    private void displayHeaderMenu() {
//        System.out.println("*******************************************************************");
//        System.out.println("***                                                             ***");
//        System.out.println("***                  Gestionaire d'ordinateurs                  ***");
//        System.out.println("***                                                             ***");
//        System.out.println("*******************************************************************");
//    }
//
//    /**
//     * Display the main menu items.
//     */
//    private void displayMenuItems() {
//        for (int i = 0; i < MAIN_MENU_LABELS.length; i++) {
//            System.out.printf("%3d > %s\n", i, MAIN_MENU_LABELS[i]);
//        }
//    }
//
//    /**
//     * Private constructor for MainView singleton.
//     */
//    MainView() {
//    }
//
//    /**
//     * Display the main menu with header, painBread and items
//     * then give the hand to controller.
//     */
//    public void displayMenu() {
//        displayHeaderMenu();
//        ViewUtils.displayBread("Main Menu");
//        displayMenuItems();
//        MainController.INSTANCE.switchMenu(ViewUtils.SCANNER);
//    }
//}