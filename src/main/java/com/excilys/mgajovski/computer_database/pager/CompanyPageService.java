package com.excilys.mgajovski.computer_database.pager;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.services.Service;

/**
 * @author	Gajovski Maxime
 * @date	22 mars 2017
 */
@org.springframework.stereotype.Service
public class CompanyPageService extends PageServiceImpl<Company>{

  @Autowired
  CompanyPageService(Service<Company> service) {
    super(service);
  }
}
