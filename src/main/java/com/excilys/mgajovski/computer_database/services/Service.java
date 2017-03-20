package com.excilys.mgajovski.computer_database.services;

import com.excilys.mgajovski.computer_database.services.Impl.CompanyServiceImpl;
import com.excilys.mgajovski.computer_database.services.Impl.ComputerServiceImpl;

/**
 * @author	Gajovski Maxime
 * @date	20 mars 2017
 */
public final class Service {


    public static final ComputerService COMPUTER = ComputerServiceImpl.INSTANCE;
    public static final CompanyService COMPANY = CompanyServiceImpl.INSTANCE;
}
