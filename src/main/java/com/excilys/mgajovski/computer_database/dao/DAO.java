package com.excilys.mgajovski.computer_database.dao;

import com.excilys.mgajovski.computer_database.dao.impl.CompanyDAO;
import com.excilys.mgajovski.computer_database.dao.impl.ComputerDAO;

/**
 * @author	Gajovski Maxime
 * @date	27 févr. 2017
 */
public final class DAO {
    
    public static final ICompanyDAO COMPANY = CompanyDAO.INSTANCE;
    public static final IComputerDAO COMPUTER = ComputerDAO.INSTANCE;
}
