package com.excilys.mgajovski.computer_database.services;

import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.exceptions.ServiceException;

/**
 * @author	Gajovski Maxime
 * @date	21 mars 2017
 */
public interface CompanyService extends Service<Company>{
    
    public void deleteWithoutCascade(long id) throws ServiceException ;

}
