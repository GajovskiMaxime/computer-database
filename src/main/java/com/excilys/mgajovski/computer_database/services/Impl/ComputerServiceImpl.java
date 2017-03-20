package com.excilys.mgajovski.computer_database.services.Impl;

import java.util.List;

import com.excilys.mgajovski.computer_database.dao.DAO;
import com.excilys.mgajovski.computer_database.dao.columns.ComputerColumn;
import com.excilys.mgajovski.computer_database.dto.page.FilteredPageDTO;
import com.excilys.mgajovski.computer_database.dto.page.PageDTO;
import com.excilys.mgajovski.computer_database.entities.Computer;
import com.excilys.mgajovski.computer_database.exceptions.DAOException;
import com.excilys.mgajovski.computer_database.exceptions.PageException;
import com.excilys.mgajovski.computer_database.services.ComputerService;

/**
 * @author	Gajovski Maxime
 * @date	20 mars 2017
 */
public enum ComputerServiceImpl implements ComputerService{
    
    INSTANCE;
    
    @Override
    public List<String> findAllByColumn(ComputerColumn... computerColumn) throws DAOException {
        return DAO.COMPUTER.findAllByColumn(computerColumn);
    }

    @Override
    public Computer find(long id) throws DAOException {
        return DAO.COMPUTER.find(id);
    }

    @Override
    public List<Computer> findAll() throws DAOException {
        return DAO.COMPUTER.findAll();
    }

    @Override
    public List<Computer> findByPage(PageDTO<Computer> k) throws PageException, DAOException {
        return DAO.COMPUTER.findByPage(k);
    }

    
    @Override
    public List<Computer> findByPage(FilteredPageDTO<Computer> k) throws PageException, DAOException {
        return DAO.COMPUTER.findByPage(k);
    }

    @Override
    public Computer create(Computer obj) throws DAOException {        
        return DAO.COMPUTER.create(obj);
    }

    @Override
    public List<Computer> findByFilter(String filter) throws DAOException {
        return DAO.COMPUTER.findByFilter(filter);
    }
    
    @Override
    public Computer update(Computer obj) throws DAOException {
        return DAO.COMPUTER.update(obj);
    }
    
    @Override
    public boolean delete(Computer obj) throws DAOException {
        return DAO.COMPUTER.delete(obj);
    }

    @Override
    public boolean delete(long id) throws DAOException {        
        return DAO.COMPUTER.delete(id);
    }

    @Override
    public int sizeOfFilteredQuery(String sequence) throws DAOException {
        return DAO.COMPUTER.sizeOfFilteredQuery(sequence);
    }

}
