package com.excilys.mgajovski.computer_database.services;

import java.util.List;

import com.excilys.mgajovski.computer_database.entities.Computer;
import com.excilys.mgajovski.computer_database.exceptions.ServiceException;
/**
 * @author	Gajovski Maxime
 * @date	20 mars 2017
 */
public interface ComputerService extends Service<Computer>{
  
  void delete(List<Long> ids) throws ServiceException;
}
