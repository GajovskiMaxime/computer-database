package com.excilys.mgajovski.computer_database.cli.views;


import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.interfaces.mvc.IController;


/**
 * Vue sur la liste des compagnies (singleton).
 * @author	Gajovski Maxime
 * @date	21 févr. 2017
 */

public class CompanyListView {
	
	private static final 	Scanner				scan 		= new Scanner(System.in);
	private static 			CompanyListView	_instance 	= null;

	final static String[] FOOTER_LABELS = {
			"n - next page", 
			"p - previous page",
			"m - main menu"
	};
	

	public static void printCompanyHeaderWithPage(int page) {
		System.out.println	();
		System.out.println	();
		System.out.println	("--------------------------------------------------------------------------------------------");
		System.out.println	("COMPANIES : Current page : " + page);
		ViewUtils.printCompanyHeader();
	}

	/**
	 * Méthode permettant d'afficher les compagnies de pages en pages.
	 * Affiche, puis fait appel au controlleur correspondant pour intéragir 
	 * avec les entrées utilisateur.
	 * @param page
	 * @param companies
	 * @throws SQLException
	 */
	public void printCurrentPage(int page, List<Company> companies) throws SQLException {
		
		printCompanyHeaderWithPage(page);
		for (Company company : companies) {
			System.out.printf(ViewUtils.COMPANY_FORMAT_LINE, 
					company.getId(), 
					company.getName());
		}
		
		ViewUtils.footer(Arrays.toString(FOOTER_LABELS));
		IController.companyListCtrl().switchMenu(scan);

	}
	
	/**
	 * Méthode permettant d'afficher les compagnies de la première page.
	 * @throws SQLException
	 * @see printCurrentPage
	 */
	public void printFirstPage() throws SQLException {
		printCurrentPage(
				IController.companyListCtrl().getCurrentPage(), 
				IController.companyListCtrl().getFirstCompanies());
	}
	
	public static CompanyListView getInstance() throws SQLException
	{			
		if (_instance == null){ 	
			_instance = new CompanyListView();	
		}
		return _instance;
	}
}
