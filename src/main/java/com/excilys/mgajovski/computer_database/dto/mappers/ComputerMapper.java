package com.excilys.mgajovski.computer_database.dto.mappers;

import java.time.LocalDate;

import com.excilys.mgajovski.computer_database.dto.impl.CompanyDTOImpl;
import com.excilys.mgajovski.computer_database.dto.impl.ComputerDTOImpl;
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
    
    private static final String COMPUTER_EMPTY_OR_NEGATIVE_ID = "Computer id not set or negative value.";
    private static final String COMPUTER_NULL = "Computer seems to be null.";
    
    public static ComputerDTOImpl transformToDTO(Computer computer) {
        
        if(computer == null){
            throw new IllegalArgumentException(COMPUTER_NULL);
        }
        if(computer.getId() < 0){
            throw new IllegalArgumentException(COMPUTER_EMPTY_OR_NEGATIVE_ID);
        }
        
        ComputerDTOImpl computerDTO = new ComputerDTOImpl();
       
        String introduced = computer.getIntroducedDate() == null ? 
                null : computer.getIntroducedDate().toString();
        
        String discontinued = computer.getDiscontinuedDate() == null ? 
                null : computer.getDiscontinuedDate().toString();
        
        computerDTO.setComputerId(computer.getId());
        computerDTO.setComputerName(computer.getName());
        computerDTO.setDiscontinued(discontinued);
        computerDTO.setIntroduced(introduced);  
        
        if(computer.getCompany() == null){
            computerDTO.companyIsNull();
        }
        else{
            CompanyDTOImpl company = CompanyMapper.transformToDTO(computer.getCompany());
            computerDTO.setCompany(company);
        }
        
        return computerDTO;
    }



    /**
     * This method transform a DTO object into the associated entity.
     * @param computerDTO : the object to transform.
     * @return a computer object if all computerDTO fields are correctly fulfilled.
     * @throws DTOMapperException if one DTO field is not correctly fulfilled.
     */
    public static Computer transformFromDTOWithIdInit(ComputerDTOImpl computerDTO, boolean idIsInit) throws DTOMapperException {

        try {
            ComputerChecker.dtoIsValidWithIdInit(computerDTO, idIsInit);
        } catch (IdException | NameException | DateException e) {
            throw new DTOMapperException(e.getMessage(), e);
        }
        
        LocalDate introduced = computerDTO.getDiscontinued().isEmpty() ?
                null : LocalDate.parse(computerDTO.getIntroduced());

        LocalDate discontinued = computerDTO.getDiscontinued().isEmpty() ?
                null : LocalDate.parse(computerDTO.getDiscontinued());
        
//        
//        Company company = Company.builder()
//                .id(computerDTO.getCompanyId())
//                .name(computerDTO.getComputerName())
//                .build();

        Computer computer = Computer.builder()
                .id(computerDTO.getComputerId())
                .name(computerDTO.getComputerName())
                .discontinued(discontinued)
                .introduced(introduced)
                .company(null)
                .build();
        return computer;
    }
}
