//package com.excilys.mgajovski.computer_database.managers;
//
//import java.util.List;
//
//import javax.annotation.ManagedBean;
//import javax.annotation.PostConstruct;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import com.excilys.mgajovski.computer_database.dto.impl.ComputerDTOImpl;
//import com.excilys.mgajovski.computer_database.dto.mappers.ComputerMapper;
//import com.excilys.mgajovski.computer_database.entities.Company;
//import com.excilys.mgajovski.computer_database.entities.Computer;
//import com.excilys.mgajovski.computer_database.exceptions.DAOException;
//import com.excilys.mgajovski.computer_database.exceptions.mapping.DTOMapperException;
//import com.excilys.mgajovski.computer_database.exceptions.mapping.DateException;
//import com.excilys.mgajovski.computer_database.exceptions.mapping.IdException;
//import com.excilys.mgajovski.computer_database.exceptions.mapping.NameException;
//import com.excilys.mgajovski.computer_database.services.CompanyService;
//import com.excilys.mgajovski.computer_database.services.ComputerService;
//import com.excilys.mgajovski.computer_database.validations.checkers.ComputerChecker;
//
///**
// * @author Gajovski Maxime
// * @date 1 mars 2017
// */
//
////@Controller
//@ManagedBean
//public class ComputerDetailManager {
//
//    private Logger LOGGER = LoggerFactory.getLogger(ComputerDetailManager.class);
//
//    private ComputerDTOImpl computerDTO;
//    
//    @Autowired
//    private CompanyService companyService;
//    
//    @Autowired
//    private ComputerService computerService;
//    /**
//     * Public constructor for ComputerDetailManager.
//     */
//    public ComputerDetailManager() {
//        initialize();
//    }
//
//    @PostConstruct
//    public void initialize() {
//        computerDTO = new ComputerDTOImpl();
//
//    }
//
//    public long getComputerId() {
//        return computerDTO.getComputerId();
//    }
//
//    /**
//     * This method set the computerDTO id.
//     * @param computerId : the computer id to set.
//     */
//    public void setComputerId(long computerId) {
//        computerDTO.setComputerId(computerId);
//    }
//
//    public String getComputerName() {
//        return computerDTO.getComputerName();
//    }
//    
//    /**
//     * This method return the companies in order to be displayed.
//     * @return a list of companies
//     * @throws DAOException : if an error occurs.
//     */
//    public List<Company> getCompanies() throws DAOException {
//        return companyService.findAll();
//    }
//    
//    public Computer getComputer(long id) throws DAOException{
//        return computerService.find(id);
//    }
//    /**
//     * This method set the computerDTO name.
//     * @param computerName : the computer name to set.
//     */
//    public void setComputerName(String computerName) {
//        computerDTO.setComputerName(computerName);
//    }
//
//    public String getIntroduced() {
//        return computerDTO.getIntroduced();
//    }
//
//    /**
//     * This method set the computerDTO introduced date.
//     * @param introduced : the computer introduced date to set.
//     */
//    public void setIntroduced(String introduced) {
//        computerDTO.setIntroduced(introduced);
//    }
//
//    public String getDiscontinued() {
//        return computerDTO.getDiscontinued();
//    }
//
//    /**
//     * This method set the computerDTO discontinued date.
//     * @param discontinued : the computer discontinued date to set.
//     */
//    public void setDiscontinued(String discontinued) {
//        computerDTO.setDiscontinued(discontinued);
//    }
//
//    public long getCompanyId() {
//        return computerDTO.getCompany().getId();
//    }
//
//    /**
//     * This method set the computerDTO company's id.
//     * @param companyId : the computer company's id to set.
//     */
//    public void setCompanyId(long companyId) {
//        computerDTO.getCompany().setId(companyId);
//    }
//
//    public String getCompanyName() {
//        return computerDTO.getCompany().getName();
//    }
//
//    /**
//     * This method set the computerDTO company's name.
//     * @param companyName : the computer company's name to set.
//     */
//    public void setCompanyName(String companyName) {
//        computerDTO.getCompany().setName(companyName);
//    }
//
//    /**
//     * This method looks if for each fields they are correctly fulfilled.
//     * @return true if all fields are correctly fulfilled, false otherwise.
//     */
//    public String computerFieldsAreValid() {
//        try {
//            ComputerChecker.dtoIsValidWithIdInit(computerDTO, false);
//        } catch (IdException | NameException | DateException e) {
//            
//            return e.getMessage();
//        }
//        return "success";
//    }
//
//    /**
//     * This method adds a computer if all DTO fields are correctly fulfilled.
//     */
//    public String addComputer() {
//        try {
//            computerService.create(ComputerMapper.transformFromDTOWithIdInit(computerDTO, false));
//        } catch (DAOException | DTOMapperException e) {
//            return e.getMessage();
//        }
//        return "success";
//    }
//}
