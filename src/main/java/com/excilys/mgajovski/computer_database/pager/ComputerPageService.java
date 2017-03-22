package com.excilys.mgajovski.computer_database.pager;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.mgajovski.computer_database.entities.Computer;
import com.excilys.mgajovski.computer_database.services.Service;

/**
 * @author	Gajovski Maxime
 * @date	22 mars 2017
 */
@org.springframework.stereotype.Service
public class ComputerPageService extends PageServiceImpl<Computer> {

  @Autowired
  ComputerPageService(Service<Computer> service) {
    super(service);
  }
}
