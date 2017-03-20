package com.excilys.mgajovski.computer_database.dto.mappers;

import java.time.LocalDate;

import org.junit.Test;

import com.excilys.mgajovski.computer_database.dto.impl.ComputerDTOImpl;
import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.entities.Computer;

/**
 * @author	Gajovski Maxime
 * @date	20 mars 2017
 */
public class ComputerMapperTest {
    
    @Test(expected=IllegalArgumentException.class)
    public void transformToDTOWithNull(){
      ComputerMapper.transformToDTO(null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void transformToDTOWithNegativeId(){
        long computerId = -40;
        Computer computer = Computer
                .builder()
                .id(computerId)
                .build();
        
        ComputerMapper.transformToDTO(computer);
    }
    
    @Test
    public void transformToDTOWithGoodIdWithoutCompany(){
        
        String computerName = "computer";
        long computerId = 0;
        LocalDate introduced = LocalDate.now();
        LocalDate discontinued = LocalDate.now();
        
        
        Computer computer = Computer
                .builder()
                .name(computerName)
                .id(computerId)
                .introduced(introduced)
                .discontinued(discontinued)
                .build();    
        
        ComputerDTOImpl computerDTO = ComputerMapper.transformToDTO(computer);
        
        assert(computerDTO != null);
        
        assert(computerDTO.getComputerId() == 0);
        assert(computerDTO.getComputerName() == computerName);
        assert(computerDTO.getIntroduced().equals(introduced.toString()));
        assert(computerDTO.getDiscontinued().equals(discontinued.toString()));
   
        assert(computerDTO.getCompany() == null);
        
    }
    

    @Test(expected=IllegalArgumentException.class)
    public void transformToDTOWithGoodIdAndBadCompanyId(){
        
        String computerName = "name";
        long computerId = 0;
        LocalDate introduced = LocalDate.now();
        LocalDate discontinued = LocalDate.now();
        
        String companyName = "company";
        long companyId = -5;
        
        Company company = Company
                .builder()
                .id(companyId)
                .name(companyName)
                .build();
        
        Computer computer = Computer
                .builder()
                .name(computerName)
                .id(computerId)
                .introduced(introduced)
                .discontinued(discontinued)
                .company(company)
                .build();    
        
        ComputerDTOImpl computerDTO = ComputerMapper.transformToDTO(computer);
        
        assert(computerDTO != null);
        
        assert(computerDTO.getComputerId() == 0);
        assert(computerDTO.getComputerName() == computerName);
        assert(computerDTO.getIntroduced().equals(introduced.toString()));
        assert(computerDTO.getDiscontinued().equals(discontinued.toString()));
        
        assert(computerDTO.getCompany().getId() == companyId);
        assert(computerDTO.getCompany().getName() == companyName);
        
    }
    
    
    @Test
    public void transformToDTOWithGoodIdAndGoodCompanyId(){
        
        String computerName = "name";
        long computerId = 0;
        LocalDate introduced = LocalDate.now();
        LocalDate discontinued = LocalDate.now();
        
        String companyName = "company";
        long companyId = 0;
        
        Company company = Company
                .builder()
                .id(companyId)
                .name(companyName)
                .build();
        
        Computer computer = Computer
                .builder()
                .name(computerName)
                .id(computerId)
                .introduced(introduced)
                .discontinued(discontinued)
                .company(company)
                .build();    
        
        ComputerDTOImpl computerDTO = ComputerMapper.transformToDTO(computer);
        
        assert(computerDTO != null);
        
        assert(computerDTO.getComputerId() == 0);
        assert(computerDTO.getComputerName() == computerName);
        assert(computerDTO.getIntroduced().equals(introduced.toString()));
        assert(computerDTO.getDiscontinued().equals(discontinued.toString()));
        
        assert(computerDTO.getCompany().getId() == companyId);
        assert(computerDTO.getCompany().getName() == companyName);
        
    }

 
}
