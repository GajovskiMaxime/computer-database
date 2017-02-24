package com.excilys.mgajovski.computer_database.cli.views;


import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.excilys.mgajovski.computer_database.entities.Computer;
import com.excilys.mgajovski.computer_database.interfaces.mvc.IController;


/**
 * Vue sur la liste des ordinateurs(singleton).
 * @author	Gajovski Maxime
 * @date	21 févr. 2017
 */
public class ComputerListView {
	
	private static final 	Scanner 			scan		= new Scanner(System.in);	
	private static 			ComputerListView	_instance 	= null;
	

	final static String[] FOOTER_LABELS = {
			"n - next page", 
			"p - previous page",
			"id - Search by id",
			"a - add computer",
			"m - main menu"
	};
	

	public static void printComputerHeaderWithPage(int page) {
		System.out.println	();
		System.out.println	();
		System.out.println	("-------------------------------------------------------------------");
		System.out.println	("COMPUTERS : Current page : " + page);
		ViewUtils.printComputerHeader();
	}
	
	
	private ComputerListView() throws SQLException{
	}

	/**
	 * Méthode permettant d'afficher les ordinateurs de pages en pages.
	 * Affiche, puis fait appel au controlleur correspondant pour intéragir 
	 * avec les entrées utilisateur.
	 * @param page
	 * @param computers
	 * @throws SQLException
	 * @see ComputerListCtrl
	 */
	public void printCurrentPage(int page, List<Computer> computers) throws SQLException {
		printComputerHeaderWithPage(page);
		
		for (Computer computer : computers) {
			System.out.printf(ViewUtils.COMPUTER_FORMAT_LINE, 
					computer.getId(), 
					computer.getName(), 
					computer.getIntroducedDate() == null ?  "" : computer.getIntroducedDate() , 
					computer.getDiscontinuedDate() == null ?"" : computer.getDiscontinuedDate(), 
					computer.getCompany() == null ? "" : computer.getCompany().getName());
		}
		
		ViewUtils.footer(Arrays.toString(FOOTER_LABELS));
		IController.computerListCtrl().switchMenu(scan);
	}
	
	/**
	 * Méthode permettant d'afficher les ordinateurs de la première page.
	 * @throws SQLException
	 */
	public void printFirstPage() throws SQLException {
		printCurrentPage(
				IController.computerListCtrl().getCurrentPage(), 
				IController.computerListCtrl().getFirstComputers());
	}
	
	public static ComputerListView getInstance() throws SQLException
	{			
		if (_instance == null){ 	
			_instance = new ComputerListView();	
		}
		return _instance;
	}
	
}
