package com.excilys.mgajovski.computer_database.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.excilys.mgajovski.computer_database.dao.columns.ComputerColumn;
import com.excilys.mgajovski.computer_database.dto.page.PageDTO;
import com.excilys.mgajovski.computer_database.entities.Computer;
import com.excilys.mgajovski.computer_database.exceptions.DAOException;


/**
 * @author Gajovski Maxime
 * @date 21 févr. 2017
 */
public interface IComputerDAO extends ICrud<Computer> {

  /**
   * @param computerColumn
   * @return
 * @throws DAOException 
   */
  List<String> findAllByColumn(ComputerColumn ...computerColumn) throws DAOException;

/**
 * @param computerColumns
 * @return
 *//*
Optional<List<String>> findAllByColumn(ComputerColumn ...computerColumns);
*/
}

