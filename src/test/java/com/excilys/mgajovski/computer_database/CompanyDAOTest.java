package com.excilys.mgajovski.computer_database;

import com.excilys.mgajovski.computer_database.dao.ICompanyDAO;
import com.excilys.mgajovski.computer_database.dao.impl.CompanyDAO;
import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.entities.CompanyFactory;
import com.excilys.mgajovski.computer_database.entities.Computer;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author	Gajovski Maxime
 * @date	27 févr. 2017
 */
public class CompanyDAOTest {
    
    //private static final CompanyDAO companyDAO = PowerMockito.mock(CompanyDAO.class);
    private static final CompanyDAO companyDAO = CompanyDAO.INSTANCE;
    private static final int DATABASE_SIZE = 50;
    
    private static final long negativeID = -50;
    private static final long zeroID = 0;
    private static final long notInTheTableID = 5000;
    private static final long firstID = 1;
    
    
    @Test
    public void initDatabase() throws SQLException{
       
        Optional<List<Company>> companyTable = CompanyFactory.createCompanies(DATABASE_SIZE);       
        PowerMockito.when(companyDAO.findAll()).thenReturn(companyTable);
        PowerMockito.when(companyDAO.find(-1)).thenReturn(Optional.of(companyDAO.findAll().get().get(-1)));
        
    }
    
    
    @Test
    public void find() throws SQLException {
        /*
        Optional<Company> optCompanyWithNegativeId = companyDAO.find(negativeID);
        Optional<Company> optCompanyWithZeroId = companyDAO.find(zeroID);
        Optional<Company> optCompanyWithIdNotInTheTable = companyDAO.find(notInTheTableID);
        Optional<Company> optCompanyWithFirstId = companyDAO.find(firstID);
        
        assert(!optCompanyWithNegativeId.isPresent());
        assert(!optCompanyWithZeroId.isPresent());
        assert(!optCompanyWithIdNotInTheTable.isPresent());
        
        assert(optCompanyWithFirstId.isPresent());
        assert(optCompanyWithFirstId.get() != null);
        assert(optCompanyWithFirstId.get().getName().equals("Apple Inc."));
        assert(optCompanyWithFirstId.get().getId() == firstID);
      */
    }
    
    @Test
    public void create() {
        /*
        Company companyAlreadyInDB = Company.builder().id(151).build();
        Company company = Company.builder().build();
        
        Optional<Company> optCompanyWithNullParam = companyDAO.create(null);
        Optional<Company> optCompanyWithOptionalEmpty = companyDAO.create(Optional.empty());
        Optional<Company> optCompanyWithOptionalNull = companyDAO.create(Optional.ofNullable(null));
        Optional<Company> optCompanyAlreadyInDB = companyDAO.create(Optional.of(companyAlreadyInDB));
        Optional<Company> optCompanyWithCompanyToAdd = companyDAO.create(Optional.of(company));
        
        assert(!optCompanyWithNullParam.isPresent());
        assert(!optCompanyWithOptionalEmpty.isPresent());
        assert(!optCompanyWithOptionalNull.isPresent());
        assert(!optCompanyAlreadyInDB.isPresent());
        
        assert(optCompanyWithCompanyToAdd.isPresent());
        assert(optCompanyWithCompanyToAdd.get().getId() > 0);
        assert(optCompanyWithCompanyToAdd.get().getName() == null);
     */
    }
    public void findAll() {
        
    }
}
