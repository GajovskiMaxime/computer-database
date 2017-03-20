package com.excilys.mgajovski.computer_database;

import java.util.Optional;

import org.junit.Test;

import com.excilys.mgajovski.computer_database.dao.impl.CompanyDAOImpl;
import com.excilys.mgajovski.computer_database.dao.impl.ComputerDAOImpl;
import com.excilys.mgajovski.computer_database.dao.interfaces.CompanyDAO;
import com.excilys.mgajovski.computer_database.dao.interfaces.ComputerDAO;
import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.entities.Computer;

/**
 * @author	Gajovski Maxime
 * @date	27 févr. 2017
 */
public class ComputerDAOTest {
   
    private ComputerDAO computerDAO = ComputerDAOImpl.INSTANCE;
    

    private static final long negativeID = -50;
    private static final long zeroID = 0;
    private static final long notInTheTableID = 5000;
    private static final long firstID = 2;
    
    
    @Test
    public void find() {
        /*
        Optional<Computer> optComputerWithNegativeId = computerDAO.find(negativeID);
        Optional<Computer> optComputerWithZeroId = computerDAO.find(zeroID);
        Optional<Computer> optComputerWithIdNotInTheTable = computerDAO.find(notInTheTableID);
        Optional<Computer> optComputerWithFirstId = computerDAO.find(firstID);
        
        assert(!optComputerWithNegativeId.isPresent());
        assert(!optComputerWithZeroId.isPresent());
        assert(!optComputerWithIdNotInTheTable.isPresent());
        
        assert(optComputerWithFirstId.isPresent());
        assert(optComputerWithFirstId.get() != null);
        assert(optComputerWithFirstId.get().getName().equals("CM-2a"));
        assert(optComputerWithFirstId.get().getId() == firstID);
    */
    }
    
    @Test
    public void create() {
        /*
        Computer computerAlreadyInDB = Computer.builder().id(151).build();
        Computer computer = Computer.builder().build();
        
        Optional<Computer> optComputerWithNullParam = computerDAO.create(null);
        Optional<Computer> optComputerWithOptionalEmpty = computerDAO.create(Optional.empty());
        Optional<Computer> optComputerWithOptionalNull = computerDAO.create(Optional.ofNullable(null));
        Optional<Computer> optComputerAlreadyInDB = computerDAO.create(Optional.of(computerAlreadyInDB));
        Optional<Computer> optComputer = computerDAO.create(Optional.of(computer));
        
        assert(!optComputerWithNullParam.isPresent());
        assert(!optComputerWithOptionalEmpty.isPresent());
        assert(!optComputerWithOptionalNull.isPresent());
        assert(!optComputerAlreadyInDB.isPresent());
        
        assert(optComputer.isPresent());
        assert(optComputer.get().getId() > 0);
        assert(optComputer.get().getName() == null);
        */
    }
    
    @Test
    public void delete() {
        
       /* computerDAO.de
        Computer computerAlreadyInDB = Computer.builder().id(151).build();*/
       /* Computer computer = Computer.builder().build();
        
        Optional<Computer> optComputerWithNullParam = computerDAO.create(null);
        Optional<Computer> optComputerWithOptionalEmpty = computerDAO.create(Optional.empty());
        Optional<Computer> optComputerWithOptionalNull = computerDAO.create(Optional.ofNullable(null));
        */
       // Optional<Computer> optComputerAlreadyInDB = computerDAO.create(Optional.of(computerAlreadyInDB));
        /*Optional<Computer> optComputer = computerDAO.create(Optional.of(computer));
        
        assert(!optComputerWithNullParam.isPresent());
        assert(!optComputerWithOptionalEmpty.isPresent());
        assert(!optComputerWithOptionalNull.isPresent());*/
       // assert(!optComputerAlreadyInDB.isPresent());
        
       /* assert(optComputer.isPresent());
        assert(optComputer.get().getId() > 0);
        assert(optComputer.get().getName() == null);*/
        
    }
}
