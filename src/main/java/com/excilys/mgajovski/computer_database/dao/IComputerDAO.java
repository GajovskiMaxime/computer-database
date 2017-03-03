package com.excilys.mgajovski.computer_database.dao;

import java.util.List;
import java.util.Optional;

import com.excilys.mgajovski.computer_database.dao.columns.ComputerColumns;
import com.excilys.mgajovski.computer_database.dto.page.PageDTO;
import com.excilys.mgajovski.computer_database.entities.Computer;


/**
 * @author Gajovski Maxime
 * @date 21 févr. 2017
 */
public interface IComputerDAO extends ICrud<Computer> {

  /**
   * @param computerColumn
   * @return
   */
  Optional<List<String>> findAllByColumn(ComputerColumns computerColumn);


}

