package cli.controllers;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

import dao.IComputerDAO;
import dao.impl.ComputerDAO;
import entities.Computer;
import interfaces.mvc.IView;

/**
 * @author 	Gajovski Maxime
 * @date 	21 févr. 2017
 */
public class ComputerDetailCtrl {

	private static final 	Logger 				logger 		= Logger.getLogger(ComputerDetailCtrl.class.getName());
	private static 			IComputerDAO 		computerDAO;
	private static 			ComputerDetailCtrl	_instance 	= null;
	private static 			Computer 			computer 	= null;
			
	private ComputerDetailCtrl() throws SQLException{
		computerDAO = new ComputerDAO();
	}
	
	
	public void searchByIdSwitchMenu(Scanner scan) throws SQLException{
		String userChoice = null;
		userChoice = scan.nextLine();
    	if(userChoice.matches("^[0-9]+$"))
    		computer = computerDAO.find(Long.parseLong(userChoice)).orElse(null);
		
    	while(computer == null){
        	IView.computerDetail().printComputerSearchByIdHeader();
    		userChoice = scan.nextLine();
        	if(userChoice.matches("^[0-9]+$"))
        		computer = computerDAO.find(Long.parseLong(userChoice)).get();
        } 
    	
		IView.computerDetail().printComputerDetail(computer);
		
		do {
			switch (userChoice = scan.nextLine()) {
	        	case "r":{
	        		computer = null;
	        		IView.computerList().printFirstPage();break;
	        	}
		        case "u":break; //update
				case "d":deleteComputerSwitchMenu(scan);break;
	            case "m":IView.mainMenu().displayMenu();break;
	            default:
	            	logger.warning(CtrlUtils.USER_BAD_INPUT);
	            	break;
	    	}
		} while (!userChoice.equals("m"));   
	}
	
	
	public void deleteComputerSwitchMenu(Scanner scan) throws SQLException{
		String userChoice 	= null;
		do {
			IView.computerDetail().printComputerDeleteConfirmationHeader(computer.getId());
			switch (userChoice = scan.nextLine()) {
            	case "yes":{
            		computerDAO.delete(computer);
            		IView.computerList().printFirstPage();
            	}break;
	            case "no":{
	            	logger.warning(CtrlUtils.COMPUTER_NOT_DELETED);
	            	IView.computerList().printFirstPage();
	            }break; 
            	default:
            		logger.warning(CtrlUtils.USER_BAD_INPUT);
            		break;
            }
        } while (!userChoice.equals("no") || !userChoice.equals("yes"));
	}
	

	/*public void updateComputerSwitchMenu(Scanner scan) throws SQLException{
		String userChoice 	= null;
		do {
			IView.computerDetail().printComputerDeleteConfirmationHeader(computer.getId());
			switch (userChoice = scan.nextLine()) {
            	case "yes":{
            		computerDAO.delete(computer);
            		IView.computerList().printFirstPage();
            	}break;
	            case "no":{
	            	logger.warning(CtrlUtils.COMPUTER_NOT_DELETED);
	            	IView.computerList().printFirstPage();
	            }break; 
            	default:
            		logger.warning(CtrlUtils.USER_BAD_INPUT);
            		break;
            }
        } while (!userChoice.equals("no") || !userChoice.equals("yes"));
	}*/
	
	public static ComputerDetailCtrl getInstance() throws SQLException
	{			
		if (_instance == null){ 	
			_instance = new ComputerDetailCtrl();	
		}
		return _instance;
	}
}
