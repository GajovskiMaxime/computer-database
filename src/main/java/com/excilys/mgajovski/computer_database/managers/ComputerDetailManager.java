package com.excilys.mgajovski.computer_database.managers;

import java.util.Optional;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.mgajovski.computer_database.dao.DAO;
import com.excilys.mgajovski.computer_database.dao.IComputerDAO;
import com.excilys.mgajovski.computer_database.dto.impl.ComputerDTOImpl;
import com.excilys.mgajovski.computer_database.dto.mappers.ComputerMapper;
import com.excilys.mgajovski.computer_database.entities.Computer;
import com.excilys.mgajovski.computer_database.exceptions.DateException;
import com.excilys.mgajovski.computer_database.exceptions.IdException;
import com.excilys.mgajovski.computer_database.exceptions.NameException;
import com.excilys.mgajovski.computer_database.validations.checkers.ComputerChecker;

/**
 * @author Gajovski Maxime
 * @date 1 mars 2017
 */

@ManagedBean
public class ComputerDetailManager {
  
  private Logger LOGGER = LoggerFactory.getLogger(ComputerDetailManager.class);
  
  private ComputerDTOImpl computerDTO;
  private IComputerDAO computerDAO;
  
  /**
   * Public constructor for ComputerDetailManager.
   */
  public ComputerDetailManager () {
    initialize();
  }

  @PostConstruct
  public void initialize() {
      computerDTO = new ComputerDTOImpl();
      computerDAO = DAO.COMPUTER;
  }
  
  public long getComputerId() {
    return computerDTO.getComputerId();
  }
  public void setComputerId(long computerId) {
    computerDTO.setComputerId(computerId);
  }
  
  public String getComputerName() {
    return computerDTO.getComputerName();
  }
  
  public void setComputerName(String computerName) {
    computerDTO.setComputerName(computerName);
  }
  
  public String getIntroduced() {
    return computerDTO.getIntroduced();
  }
  
  public void setIntroduced(String introduced) {
    computerDTO.setIntroduced(introduced);
  }
  
  public String getDiscontinued() {
    return computerDTO.getDiscontinued();
  }
  
  public void setDiscontinued(String discontinued) {
    computerDTO.setDiscontinued(discontinued);
  }
  
  public long getCompanyId() {
    return computerDTO.getCompanyId();
  }
  
  public void setCompanyId(long companyId) {
    computerDTO.setCompanyId(companyId);
  }
  
  public String getCompanyName() {
    return computerDTO.getCompanyName();
  }
  
  public void setCompanyName(String companyName) {
    computerDTO.setCompanyName(companyName);
  }
  
  public boolean computerFieldsAreValid(){
    try {
        ComputerChecker.DTOIsValid(computerDTO);
    } catch (IdException | NameException | DateException e) {
      LOGGER.error(e.getMessage(), e);
      return false;
    }
    return true;
  }
  
  public void addComputer() throws IdException, NameException, DateException{
    Optional<Computer> optComputer = ComputerMapper.transformDTO(computerDTO);
    computerDAO.create(optComputer);
  }
}
