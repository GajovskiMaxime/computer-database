//package com.excilys.mgajovski.computer_database.managers;
//
//import java.util.Arrays;
//import java.util.List;
//
//import javax.annotation.ManagedBean;
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//
//import com.excilys.mgajovski.computer_database.dao.DAO;
//import com.excilys.mgajovski.computer_database.dao.interfaces.ComputerDAO;
//import com.excilys.mgajovski.computer_database.exceptions.DAOException;
//import com.excilys.mgajovski.computer_database.exceptions.mapping.IdException;
//import com.excilys.mgajovski.computer_database.services.ComputerService;
//import com.excilys.mgajovski.computer_database.validations.primitives.LongValidation;
//
///**
// * @author Gajovski Maxime
// * @date 7 mars 2017
// */
////@ManagedBean
////@Controller
//public class DeleteComputerManager {
//
//    private String computerIds;
//    
//    @Autowired
//    private ComputerService computerDAO;
//
//    /**
//     * Public constructor for DeleteComputerManager.
//     */
//    public DeleteComputerManager() {
//        
//    }
//
//
//    public String getComputerIds() {
//        return computerIds;
//    }
//
//    public void setComputerIds(String computerIds) {
//        this.computerIds = computerIds;
//
//    }
//
//    public String allIdsAreValid() {
//        try {
//            for (String id : computerIds.split(",")) {
//                LongValidation.isAnInitializedId(id);
//            }
//        } catch (IdException e) {
//            return e.getMessage();
//        }
//        return "success";
//    }
//
//    public void deleteComputers() {
//
//        try {
//            for (String computerId : computerIds.split(",")) {
//                computerDAO.delete(Long.parseLong(computerId));
//            }
//        } catch (NumberFormatException | DAOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//}
