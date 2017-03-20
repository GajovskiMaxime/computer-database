package com.excilys.mgajovski.computer_database.dao;

import com.excilys.mgajovski.computer_database.dao.impl.CompanyDAOImpl;
import com.excilys.mgajovski.computer_database.dao.impl.ComputerDAOImpl;
import com.excilys.mgajovski.computer_database.dao.interfaces.CompanyDAO;
import com.excilys.mgajovski.computer_database.dao.interfaces.ComputerDAO;

/**
 * @author Gajovski Maxime
 * @date 27 févr. 2017
 */
public final class DAO {

    public static final CompanyDAO COMPANY = CompanyDAOImpl.INSTANCE;
    public static final ComputerDAO COMPUTER = ComputerDAOImpl.INSTANCE;
}
