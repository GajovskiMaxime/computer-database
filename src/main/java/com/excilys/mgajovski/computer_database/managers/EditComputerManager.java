package com.excilys.mgajovski.computer_database.managers;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.mgajovski.computer_database.dao.DAO;
import com.excilys.mgajovski.computer_database.dao.ICompanyDAO;
import com.excilys.mgajovski.computer_database.dao.IComputerDAO;
import com.excilys.mgajovski.computer_database.dto.impl.ComputerDTOImpl;
import com.excilys.mgajovski.computer_database.dto.mappers.ComputerMapper;
import com.excilys.mgajovski.computer_database.exceptions.DAOException;
import com.excilys.mgajovski.computer_database.exceptions.mapping.IdException;
import com.excilys.mgajovski.computer_database.validations.primitives.LongValidation;

/**
 * @author	Gajovski Maxime
 * @date	7 mars 2017
 */
@ManagedBean
public class EditComputerManager {
    
    private Logger LOGGER = LoggerFactory.getLogger(EditComputerManager.class);

    private ComputerDTOImpl computerDTO;
    private ICompanyDAO companyDAO;
    private IComputerDAO computerDAO;
    
    /**
     * Public constructor for EditComputerManager.
     */
    public EditComputerManager() {
        initialize();
    }

    @PostConstruct
    public void initialize() {
        computerDTO = new ComputerDTOImpl();
        computerDAO = DAO.COMPUTER;
        companyDAO = DAO.COMPANY;
    }
    
    public String isAnInitializedId(String id) {
        try {
            LongValidation.isAnInitializedId(id);
        } catch (IdException e) {
            return e.getMessage();
        }
        return "success";
    }
    
    public void updateComputerDTO(String id){
        long longId = Long.parseLong(id);
        try {
            computerDTO = ComputerMapper.transformToDTO(computerDAO.find(longId));
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This method set the computerDTO name.
     * @param computerName : the computer name to set.
     */
    public void setComputerName(String computerName) {
        computerDTO.setComputerName(computerName);
    }

    public String getIntroduced() {
        return computerDTO.getIntroduced();
    }

    /**
     * This method set the computerDTO introduced date.
     * @param introduced : the computer introduced date to set.
     */
    public void setIntroduced(String introduced) {
        computerDTO.setIntroduced(introduced);
    }

    public String getDiscontinued() {
        return computerDTO.getDiscontinued();
    }

    /**
     * This method set the computerDTO discontinued date.
     * @param discontinued : the computer discontinued date to set.
     */
    public void setDiscontinued(String discontinued) {
        computerDTO.setDiscontinued(discontinued);
    }

    public long getCompanyId() {
        return computerDTO.getCompanyId();
    }

    /**
     * This method set the computerDTO company's id.
     * @param companyId : the computer company's id to set.
     */
    public void setCompanyId(long companyId) {
        computerDTO.setCompanyId(companyId);
    }

    public String getCompanyName() {
        return computerDTO.getCompanyName();
    }

    /**
     * This method set the computerDTO company's name.
     * @param companyName : the computer company's name to set.
     */
    public void setCompanyName(String companyName) {
        computerDTO.setCompanyName(companyName);
    }

    
}
