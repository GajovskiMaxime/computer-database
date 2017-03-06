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
    private long companyId;
    private String companyName;

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

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
