package com.excilys.mgajovski.computer_database.dto.mappers;

import java.time.LocalDate;
import java.util.Optional;

import com.excilys.mgajovski.computer_database.dto.impl.ComputerDTOImpl;
import com.excilys.mgajovski.computer_database.entities.Computer;
import com.excilys.mgajovski.computer_database.exceptions.DateException;
import com.excilys.mgajovski.computer_database.exceptions.IdException;
import com.excilys.mgajovski.computer_database.exceptions.NameException;
import com.excilys.mgajovski.computer_database.validations.checkers.ComputerChecker;

/**
 * @author	Gajovski Maxime
 * @date	3 mars 2017
 */
public final class ComputerMapper {
  
    public static Optional<Computer> transformDTO(ComputerDTOImpl computerDTO) throws IdException, NameException, DateException {
        
      if(!ComputerChecker.DTOIsValid(computerDTO)){
          return Optional.empty();
        }
        
        Computer computer = Computer.builder()
            .name(computerDTO.getComputerName())
            .discontinued(LocalDate.parse(computerDTO.getDiscontinued()))
            .discontinued(LocalDate.parse(computerDTO.getIntroduced()))
            .build();
        
        return Optional.of(computer);
    }
}
