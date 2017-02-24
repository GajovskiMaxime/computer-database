package com.excilys.mgajovski.computer_database.interfaces.mvc;


import java.sql.SQLException;

import com.excilys.mgajovski.computer_database.cli.views.CompanyListView;
import com.excilys.mgajovski.computer_database.cli.views.ComputerDetailView;
import com.excilys.mgajovski.computer_database.cli.views.ComputerListView;
import com.excilys.mgajovski.computer_database.cli.views.MainView;

/**
 * @author	Gajovski Maxime
 * @date	21 févr. 2017
 */
public interface IView {
	
	public static MainView mainMenu() throws SQLException{
		return MainView.getInstance();
	}

	public static ComputerListView computerList() throws SQLException{
		return ComputerListView.getInstance();
	}
	
	public static CompanyListView companyList() throws SQLException{
		return CompanyListView.getInstance();
	}
	
	public static ComputerDetailView computerDetail() throws SQLException{
		return ComputerDetailView.getInstance();
	}
}
