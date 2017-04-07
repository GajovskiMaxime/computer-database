//package com.excilys.mgajovski.computer_database.cli.views;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import com.excilys.mgajovski.computer_database.cli.controllers.ComputerController;
//import com.excilys.mgajovski.computer_database.cli.views.utils.ComputerViewUtils;
//import com.excilys.mgajovski.computer_database.cli.views.utils.ViewUtils;
//
///**
// * @author Gajovski Maxime
// * @date 21 févr. 2017
// */
//
//@Scope("singleton")
//@Component
//public class ComputerView {
//    
//  @Autowired
//  private ComputerController computerController;
//
//    /**
//     * Private constructor for ComputerListView singleton.
//     */
//    ComputerView() {
//    }
//
//    public void displayCurrentPage() {
//        ComputerViewUtils.displayComputerCurrentPage(computerController.getCurrentPage());
//        ComputerViewUtils.displayComputersDetails(computerController.getElements());
//        ViewUtils.footer(ComputerViewUtils.LIST_VIEW_FOOTER_LABELS);
//        computerController.computerListMainLoop(ViewUtils.SCANNER);
//    }
//}
