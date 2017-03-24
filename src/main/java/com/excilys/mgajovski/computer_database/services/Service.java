package com.excilys.mgajovski.computer_database.services;

import java.util.List;
import java.util.Map;

import com.excilys.mgajovski.computer_database.exceptions.ServiceException;
import com.excilys.mgajovski.computer_database.pager.FilteredPage;
import com.excilys.mgajovski.computer_database.pager.Page;

/**
 * @author	Gajovski Maxime
 * @date	20 mars 2017
 */
public interface Service <T>{

    T find(long id) throws ServiceException;
    List<T> findAll() throws ServiceException;
    List<T> findByPage(Page<T> k) throws ServiceException;
    Map<String, Object> findByPage(FilteredPage<T> k) throws ServiceException;
    T create(T obj) throws ServiceException;
    List<T> findByFilter(String filter) throws ServiceException;
    T update(T obj) throws ServiceException;
    void delete(T obj) throws ServiceException;
    void delete(long id) throws ServiceException;
    public int sizeOfFilteredQuery(String sequence) throws ServiceException;
}
