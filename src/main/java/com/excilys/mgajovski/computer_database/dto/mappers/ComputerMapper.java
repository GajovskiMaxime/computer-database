package com.excilys.mgajovski.computer_database.dto.mappers;

import java.time.LocalDate;

import com.excilys.mgajovski.computer_database.dto.impl.ComputerDTOImpl;
import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.entities.Computer;
import com.excilys.mgajovski.computer_database.exceptions.mapping.DTOMapperException;
import com.excilys.mgajovski.computer_database.exceptions.mapping.DateException;
import com.excilys.mgajovski.computer_database.exceptions.mapping.IdException;
import com.excilys.mgajovski.computer_database.exceptions.mapping.NameException;
import com.excilys.mgajovski.computer_database.validations.checkers.ComputerChecker;

/**
 * @author Gajovski Maxime
 * @date 3 mars 2017
 */
public final class ComputerMapper {

    /**
     * This method transform a DTO object into the associated entity.
     * @param computerDTO : the object to transform.
     * @return a computer object if all computerDTO fields are correctly fulfilled.
     * @throws DTOMapperException if one DTO field is not correctly fulfilled.
     */
    public static Computer transformDTO(ComputerDTOImpl computerDTO) throws DTOMapperException {

        try {
            ComputerChecker.dtoIsValid(computerDTO);
        } catch (IdException | NameException | DateException e) {
            throw new DTOMapperException(e.getMessage(), e);
        }

        LocalDate introduced = computerDTO.getDiscontinued().isEmpty() ?
                null : LocalDate.parse(computerDTO.getIntroduced());

        LocalDate discontinued = computerDTO.getDiscontinued().isEmpty() ?
                null : LocalDate.parse(computerDTO.getDiscontinued());

        Company company = Company.builder()
                .id(computerDTO.getCompanyId())
                .name(computerDTO.getComputerName())
                .build();

        Computer computer = Computer.builder()
                .name(computerDTO.getComputerName())
                .discontinued(discontinued)
                .introduced(introduced)
                .company(company)
                .build();
        return computer;
    }
}
