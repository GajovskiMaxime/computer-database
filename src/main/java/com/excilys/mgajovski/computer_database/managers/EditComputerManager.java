package com.excilys.mgajovski.computer_database.managers;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.mgajovski.computer_database.dao.DAO;
import com.excilys.mgajovski.computer_database.dao.interfaces.CompanyDAO;
import com.excilys.mgajovski.computer_database.dao.interfaces.ComputerDAO;
import com.excilys.mgajovski.computer_database.dto.impl.ComputerDTOImpl;
import com.excilys.mgajovski.computer_database.dto.mappers.ComputerMapper;
import com.excilys.mgajovski.computer_database.dto.mappers.ComputerMapperTest;
import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.exceptions.DAOException;
import com.excilys.mgajovski.computer_database.exceptions.mapping.DTOMapperException;
import com.excilys.mgajovski.computer_database.exceptions.mapping.DateException;
import com.excilys.mgajovski.computer_database.exceptions.mapping.IdException;
import com.excilys.mgajovski.computer_database.exceptions.mapping.NameException;
import com.excilys.mgajovski.computer_database.validations.checkers.ComputerChecker;
import com.excilys.mgajovski.computer_database.validations.primitives.LongValidation;

/**
 * @author	Gajovski Maxime
 * @date	7 mars 2017
 */
@ManagedBean
public class EditComputerManager {
    
    private Logger LOGGER = LoggerFactory.getLogger(EditComputerManager.class);

    private ComputerDTOImpl computerDTO;
    private CompanyDAO companyDAO;
    private ComputerDAO computerDAO;
    
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
    
    /**
     * This method return the companies in order to be displayed.
     * @return a list of companies
     * @throws DAOException : if an error occurs.
     */
    public List<Company> getCompanies() throws DAOException {
        return companyDAO.findAll();
    }
    
    public void updateComputerDTO(String id){
        long longId = Long.parseLong(id);
        try {
            computerDTO = ComputerMapper.transformToDTO(computerDAO.find(longId));
            LOGGER.warn(computerDTO.getComputerName());
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
    
    public String getComputerName() {
        return computerDTO.getComputerName();
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
        return computerDTO.getCompany().getId();
    }

    /**
     * This method set the computerDTO company's id.
     * @param companyId : the computer company's id to set.
     */
    public void setCompanyId(long companyId) {
        computerDTO.getCompany().setId(companyId);
    }

    public String getCompanyName() {
        return computerDTO.getCompany().getName();
    }

    /**
     * This method set the computerDTO company's name.
     * @param companyName : the computer company's name to set.
     */
    public void setCompanyName(String companyName) {
        computerDTO.getCompany().setName(companyName);
    }


    /**
     * This method looks if for each fields they are correctly fulfilled.
     * @return true if all fields are correctly fulfilled, false otherwise.
     */
    public String computerFieldsAreValid() {
        try {
            ComputerChecker.dtoIsValidWithIdInit(computerDTO, true);
        } catch (IdException | NameException | DateException e) {
            return e.getMessage();
        }
        return "success";
    }
    
    /**
     * This method adds a computer if all DTO fields are correctly fulfilled.
     */
    public void updateComputer() {
        try {
            computerDAO.update(ComputerMapper.transformFromDTOWithIdInit(computerDTO, true));
        } catch (DAOException | DTOMapperException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
