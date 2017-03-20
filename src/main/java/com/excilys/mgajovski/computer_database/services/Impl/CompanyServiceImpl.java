package com.excilys.mgajovski.computer_database.services.Impl;

import java.util.List;

import com.excilys.mgajovski.computer_database.dao.DAO;
import com.excilys.mgajovski.computer_database.dto.page.FilteredPageDTO;
import com.excilys.mgajovski.computer_database.dto.page.PageDTO;
import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.exceptions.DAOException;
import com.excilys.mgajovski.computer_database.exceptions.PageException;
import com.excilys.mgajovski.computer_database.services.CompanyService;

/**
 * @author	Gajovski Maxime
 * @date	20 mars 2017
 */
public enum CompanyServiceImpl implements CompanyService {
    INSTANCE;
    
    @Override
    public Company find(long id) throws DAOException {
        return DAO.COMPANY.find(id);
    }

    @Override
    public List<Company> findAll() throws DAOException {
        return DAO.COMPANY.findAll();
    }

    @Override
    public List<Company> findByPage(PageDTO<Company> k) throws PageException, DAOException {
        return DAO.COMPANY.findByPage(k);
    }

    @Override
    public List<Company> findByPage(FilteredPageDTO<Company> k) throws PageException, DAOException {
        return DAO.COMPANY.findByPage(k);
    }

    @Override
    public Company create(Company obj) throws DAOException {
        return DAO.COMPANY.create(obj);
    }

    @Override
    public List<Company> findByFilter(String filter) throws DAOException {
        return DAO.COMPANY.findByFilter(filter);
    }

    @Override
    public Company update(Company obj) throws DAOException {
        return DAO.COMPANY.update(obj);
    }

    @Override
    public boolean delete(Company obj) throws DAOException {
        return DAO.COMPANY.delete(obj);
    }

    @Override
    public boolean delete(long id) throws DAOException {
        return DAO.COMPANY.delete(id);
    }

    @Override
    public int sizeOfFilteredQuery(String sequence) throws DAOException {
        return DAO.COMPANY.sizeOfFilteredQuery(sequence);
    }

}
