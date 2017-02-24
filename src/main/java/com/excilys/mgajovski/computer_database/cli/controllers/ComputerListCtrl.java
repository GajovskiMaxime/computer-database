package com.excilys.mgajovski.computer_database.cli.controllers;


import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.excilys.mgajovski.computer_database.dao.IComputerDAO;
import com.excilys.mgajovski.computer_database.dao.impl.ComputerDAO;
import com.excilys.mgajovski.computer_database.entities.Computer;
import com.excilys.mgajovski.computer_database.exceptions.LastPageException;
import com.excilys.mgajovski.computer_database.interfaces.mvc.IView;

/**
 * @author	Gajovski Maxime
 * @date	21 févr. 2017
 */
public class ComputerListCtrl {
	
	private static final 	Logger 				logger 		= Logger.getLogger(ComputerListCtrl.class.getName());
	private static 			IComputerDAO 		computerDAO;
	private static 			ComputerListCtrl	instance 	= null;
	private static 			int 				currentPage = 0;
	private static 			List<Computer> 	computers 	= null;
	private static final int number = 10;
	private ComputerListCtrl() throws SQLException{
		computerDAO = new ComputerDAO();
		
	}
	
	public void switchMenu(Scanner scan) throws SQLException{
		String userChoice = null;
		
        do {
        	userChoice = scan.nextLine();
            switch (userChoice) {
	            case "n":{	
	            	currentPage++;
	            	try{
	            	computers = computerDAO.findByPage(currentPage, number).get();
	            	}catch(LastPageException l){
	            		currentPage--;	
	            	}
	            	IView.computerList().printCurrentPage(currentPage, computers);
	            }break;
				case "p":{
					if(currentPage <= 0){
						currentPage++;
						logger.warning(CtrlUtils.NEGATIVE_NUMBER_PAGE);
					}
					currentPage--;
				
					computers = computerDAO.findByPage(currentPage, number).get();
					IView.computerList().printCurrentPage(currentPage, computers);
				}break;
            	case "m":{
            		currentPage = 0;
            		IView.mainMenu().displayMenu();
            	}break;
//            	case "d":break;
            	case "id":{
            		currentPage = 0;
            		IView.computerDetail().printComputerSearchByIdHeader();break;
            	}          
            	default:
            		logger.warning(CtrlUtils.USER_BAD_INPUT);
            		break;
            }
        } while (!userChoice.equals("q"));    
	}
	
	public List<Computer> getFirstComputers() throws SQLException{
		return computerDAO.findByPage(currentPage, number).get();

	}
	public int getCurrentPage(){
		return currentPage;
	}
	
	public static ComputerListCtrl getInstance() throws SQLException
	{			
		if (instance == null){ 	
			instance = new ComputerListCtrl();	
		}
		return instance;
	}
}
