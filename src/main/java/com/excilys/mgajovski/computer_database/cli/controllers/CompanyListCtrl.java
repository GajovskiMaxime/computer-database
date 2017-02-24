package com.excilys.mgajovski.computer_database.cli.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.excilys.mgajovski.computer_database.dao.ICompanyDAO;
import com.excilys.mgajovski.computer_database.dao.impl.CompanyDAO;
import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.exceptions.LastPageException;
import com.excilys.mgajovski.computer_database.interfaces.mvc.IView;


/**
 * @author	Gajovski Maxime
 * @date	21 févr. 2017
 */
public class CompanyListCtrl {

	private static final 	Logger 				logger 		= Logger.getLogger(CompanyListCtrl.class.getName());
	private static 			CompanyListCtrl		_instance 	= null;
	private static 			ICompanyDAO 		companyDAO;
	private static 			int 				currentPage = 0;
	private static 			List<Company> 		companies	= null;
	private final static int number = 10;
	
	private CompanyListCtrl() throws SQLException{
		companyDAO 	= new CompanyDAO();
	}
	
	public void switchMenu(Scanner scan) throws SQLException{
		String userChoice = null;
        do {
        	userChoice = scan.nextLine();
            switch (userChoice) {
	            case "n":{	
	            	currentPage++;
	            	try{
	            	companies = companyDAO.findByPage(currentPage, number).get();
	            	}catch(LastPageException l){
	            		System.out.println(l.getMessage());
	            		currentPage--;
	            	}
	            	IView.companyList().printCurrentPage(currentPage, companies);
	            }break;
	            case "p":{
					if(currentPage <= 0){
						currentPage++;
						logger.warning(CtrlUtils.NEGATIVE_NUMBER_PAGE);
					}
					currentPage--;
					companies = companyDAO.findByPage(currentPage, number).get();
					IView.companyList().printCurrentPage(currentPage, companies);
				}break;
            	case "m":{
            		currentPage = 0;
            		IView.mainMenu().displayMenu();
            	}break;       
            	default:
            		logger.warning(CtrlUtils.USER_BAD_INPUT);
            		continue;
            }
        } while (!userChoice.equals("m"));    
	}

	public List<Company> getFirstCompanies() throws SQLException{
		return companyDAO.findByPage(currentPage, number).get();
	}
	
	public int getCurrentPage(){
		return currentPage;
	}
	
	public static CompanyListCtrl getInstance() throws SQLException
	{			
		if (_instance == null){ 	
			_instance = new CompanyListCtrl();	
		}
		return _instance;
	}
}
