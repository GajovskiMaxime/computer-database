package com.excilys.mgajovski.computer_database.dto.impl;

/**
 * @author Gajovski Maxime
 * @date 3 mars 2017 DTO class for computer entity.
 */
public class ComputerDTOImpl {

    private long computerId;
    private String computerName;
    private String introduced;
    private String discontinued;
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
    
    public long getComputerId() {
        return computerId;
    }

    public void setComputerId(long computerId) {
        this.computerId = computerId;
    }

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
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
        
        return  "computerId : " + computerId + "\n" +
                "computerName : " + computerName + "\n" +
                "introduced date : " + introduced + "\n" +
                "discontinued date : " + discontinued + "\n" +
                "companyId : " + company.getId() + "\n" + 
                "companyName : " + company.getName() + "\n" ;
        
    }
}
