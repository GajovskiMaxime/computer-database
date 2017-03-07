package com.excilys.mgajovski.computer_database.managers;

import java.util.Arrays;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

import com.excilys.mgajovski.computer_database.dao.DAO;
import com.excilys.mgajovski.computer_database.dao.IComputerDAO;
import com.excilys.mgajovski.computer_database.exceptions.DAOException;
import com.excilys.mgajovski.computer_database.exceptions.mapping.IdException;
import com.excilys.mgajovski.computer_database.validations.primitives.LongValidation;

/**
 * @author Gajovski Maxime
 * @date 7 mars 2017
 */
@ManagedBean
public class DeleteComputerManager {

    private String computerIds;
    private IComputerDAO computerDAO;

    /**
     * Public constructor for DeleteComputerManager.
     */
    public DeleteComputerManager() {
        initialize();
    }

    @PostConstruct
    public void initialize() {
        computerDAO = DAO.COMPUTER;
    }

    public String getComputerIds() {
        return computerIds;
    }

    public void setComputerIds(String computerIds) {
        this.computerIds = computerIds;

    }

    public String allIdsAreValid() {
        try {
            for (String id : computerIds.split(",")) {
                LongValidation.isAnInitializedId(id);
            }
        } catch (IdException e) {
            return e.getMessage();
        }
        return "success";
    }

    public void deleteComputers() {

        try {
            for (String computerId : computerIds.split(",")) {
                computerDAO.delete(Long.parseLong(computerId));
            }
        } catch (NumberFormatException | DAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
