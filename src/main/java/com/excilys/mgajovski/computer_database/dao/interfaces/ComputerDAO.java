package com.excilys.mgajovski.computer_database.dao.interfaces;


import java.util.List;

import com.excilys.mgajovski.computer_database.dao.ICrud;
import com.excilys.mgajovski.computer_database.dao.columns.ComputerColumn;
import com.excilys.mgajovski.computer_database.entities.Computer;
import com.excilys.mgajovski.computer_database.exceptions.DAOException;


/**
 * @author Gajovski Maxime
 * @date 21 févr. 2017
 */
public interface ComputerDAO extends ICrud<Computer> {
    
    List<String> findAllByColumn(ComputerColumn ...computerColumn) throws DAOException;
}

