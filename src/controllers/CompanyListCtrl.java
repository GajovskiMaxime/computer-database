package controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import dao.CompanyDAO;
import interfaces.dao.ICompanyDAO;
import interfaces.entities.ICompany;
import interfaces.mvc.IView;

/**
 * @author	Gajovski Maxime
 * @date	21 févr. 2017
 */
public class CompanyListCtrl {

	private static final 	Logger 				logger 		= Logger.getLogger(CompanyListCtrl.class.getName());
	private static 			CompanyListCtrl		_instance 	= null;
	private static 			ICompanyDAO 		companyDAO 	= new CompanyDAO();
	private static 			int 				currentPage = 0;
	private static 			List<ICompany> 		companies	= null;

	
	private CompanyListCtrl(){
		
	}
	
	public void switchMenu(Scanner scan) throws SQLException{
		String userChoice = null;
        do {
        	userChoice = scan.nextLine();
            switch (userChoice) {
	            case "n":{	
	            	currentPage++;
	            	companies = companyDAO.findByPage(currentPage);
	            	IView.companyList().printCurrentPage(currentPage, companies);
	            }break;
	            case "p":{
					if(currentPage <= 0){
						currentPage++;
						logger.warning(CtrlUtils.NEGATIVE_NUMBER_PAGE);
					}
					currentPage--;
					companies = companyDAO.findByPage(currentPage);
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

	public List<ICompany> getFirstCompanies() throws SQLException{
		return companyDAO.findByPage(currentPage);
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
