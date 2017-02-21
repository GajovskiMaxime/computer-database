package controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import dao.ComputerDAO;
import interfaces.dao.IComputerDAO;
import interfaces.entities.IComputer;
import interfaces.mvc.IView;

/**
 * @author	Gajovski Maxime
 * @date	21 févr. 2017
 */
public class ComputerListCtrl {
	
	private static final 	Logger 				logger 		= Logger.getLogger(ComputerListCtrl.class.getName());
	private static 			IComputerDAO 		computerDAO = new ComputerDAO();
	private static 			ComputerListCtrl	_instance 	= null;
	private static 			int 				currentPage = 0;
	private static 			List<IComputer> 	computers 	= null;
	
	private ComputerListCtrl(){
	}
	
	public void switchMenu(Scanner scan) throws SQLException{
		String userChoice = null;
		
        do {
        	userChoice = scan.nextLine();
            switch (userChoice) {
	            case "n":{	
	            	currentPage++;
	            	computers = computerDAO.findByPage(currentPage);
	            	IView.computerList().printCurrentPage(currentPage, computers);
	            }break;
				case "p":{
					if(currentPage <= 0){
						currentPage++;
						logger.warning(CtrlUtils.NEGATIVE_NUMBER_PAGE);
					}
					currentPage--;
					computers = computerDAO.findByPage(currentPage);
					IView.computerList().printCurrentPage(currentPage, computers);
				}break;
            	case "m":{
            		currentPage = 0;
            		IView.mainMenu().displayMenu();
            	}break;
//            	case "d":break;
//            	case "id":{
//            		currentPage = 0;
//            		ComputerSearchByIdView.main();break;
//            	}          
            	default:
            		logger.warning(CtrlUtils.USER_BAD_INPUT);
            		break;
            }
        } while (!userChoice.equals("q"));    
	}
	
	public List<IComputer> getFirstComputers() throws SQLException{
		return computerDAO.findByPage(currentPage);

	}
	public int getCurrentPage(){
		return currentPage;
	}
	
	public static ComputerListCtrl getInstance() throws SQLException
	{			
		if (_instance == null){ 	
			_instance = new ComputerListCtrl();	
		}
		return _instance;
	}
}
