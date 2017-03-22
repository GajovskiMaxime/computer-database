package com.excilys.mgajovski.computer_database.dao.interfaces;


import java.sql.Connection;

import com.excilys.mgajovski.computer_database.dao.ICrud;
import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.entities.Computer;
import com.excilys.mgajovski.computer_database.exceptions.DAOException;


/**
 * @author Gajovski Maxime
 * @date 21 févr. 2017
 */
public interface ComputerDAO extends ICrud<Computer> {
  
  void unreferenceOrRemoveCompanyForeignKey(Connection connection, long id, boolean remove)throws DAOException; 
  public void unreferenceOrRemoveCompanyForeignKey(Connection connection, Company company, boolean remove) throws DAOException;
}

