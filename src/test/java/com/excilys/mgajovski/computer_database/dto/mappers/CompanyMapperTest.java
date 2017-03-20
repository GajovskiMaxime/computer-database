package com.excilys.mgajovski.computer_database.dto.mappers;

import org.junit.Test;

import com.excilys.mgajovski.computer_database.dto.impl.CompanyDTOImpl;
import com.excilys.mgajovski.computer_database.entities.Company;

/**
 * @author	Gajovski Maxime
 * @date	20 mars 2017
 */
public class CompanyMapperTest {
    
    @Test(expected=IllegalArgumentException.class)
    public void transformToDTOWithNull(){
      CompanyMapper.transformToDTO(null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void transformToDTOWithNegativeId(){
        Company company = Company
                .builder()
                .id(-40)
                .build();
        
        CompanyMapper.transformToDTO(company);
    }
    
    @Test
    public void transformToDTOWithGoodId(){
        String companyName = "company";
        long companyId = 0;
        
        Company company = Company
                .builder()
                .id(companyId)
                .name(companyName)
                .build();
        
        CompanyDTOImpl companyDTO = CompanyMapper.transformToDTO(company);
        assert(companyDTO.getId() == companyId);
        assert(companyDTO.getName().equals(companyName));
    }
    
}
