package com.excilys.mgajovski.computer_database.dto.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Gajovski Maxime
 * @date 3 mars 2017 DTO class for computer entity.
 */
@Component
public class ComputerDTOImpl implements ComputerDTO {

    private long id;
    private String name;
    private String introduced;
    private String discontinued;
    
    @Autowired
    private CompanyDTOImpl company;
    
    public ComputerDTOImpl(){
    }
    
    public CompanyDTOImpl getCompany(){
        return company;
    }
    
    public void setCompany(CompanyDTOImpl company){
        this.company = company;
    }
    
    
    public void companyIsNull(){
        this.company = null;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long computerId) {
        this.id = computerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String computerName) {
        this.name = computerName;
    }

    public String getIntroduced() {
        return introduced;
    }

    public void setIntroduced(String introduced) {
        this.introduced = introduced;
    }

    public String getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(String discontinued) {
        this.discontinued = discontinued;
    }
    

    @Override
    public String toString() {
        
        return  "computerId : " + id + "\n" +
                "computerName : " + name + "\n" +
                "introduced date : " + introduced + "\n" +
                "discontinued date : " + discontinued + "\n" +
                "companyId : " + company.getId() + "\n" + 
                "companyName : " + company.getName() + "\n" ;
        
    }
}
