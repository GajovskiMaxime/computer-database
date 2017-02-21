package views;

import java.sql.SQLException;
import java.util.Arrays;
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
public class ComputerSearchByIdView {
	
	private static final 	Logger 			logger 			= Logger.getLogger(ComputerSearchByIdView.class.getName());
	private static final 	IComputerDAO 	computerDAO 	= new ComputerDAO();
	private static final 	Scanner 		scan 			= new Scanner(System.in);
	private static 			IComputer 		computer;

	public static void main() throws NumberFormatException, SQLException{
		String userChoice 	= null;
		
        do {
        	ComputerSearchByIdViewUtils.printComputerSearchByIdHeader();
        	userChoice = scan.nextLine();
        	computer = computerDAO.find(Integer.parseInt(userChoice));
        } while (!userChoice.matches("^[0-9]*$") || computer == null); 
        
        
        ViewUtils.printComputerHeader();
        System.out.printf(ViewUtils.COMPUTER_FORMAT_LINE, 
					computer.getId(), 
					computer.getName(), 
					computer.getIntroducedDate() == null ?  "" : computer.getIntroducedDate(), 
					computer.getDiscontinuedDate() == null ?"" : computer.getDiscontinuedDate(), 
					computer.getCompany() == null ? 		"" : computer.getCompany().getName());
		
        ViewUtils.footer(Arrays.toString(ComputerSearchByIdViewUtils.FOOTER_LABELS));
        
        do {
        	userChoice = scan.nextLine();
            switch (userChoice) {
            	case "r":ComputerListView.main();break; 
	            case "u":break; //update
				case "d":deleteComputer();break; // delete
            	case "m":IView.mainMenu();break;
            	default:
            		logger.warning(ViewUtils.BAD_INPUT);
            		break;
            }
        } while (!userChoice.equals("m"));    
	}
	
	public static void deleteComputer() throws SQLException{
		String userChoice 	= null;
		ComputerSearchByIdViewUtils.printComputerDeleteHeader(computer.getId());
		
		do {
        	userChoice = scan.nextLine();
            switch (userChoice) {
            	case "yes":{
            		computerDAO.delete(computer);
            		ComputerListView.main();
            	}break;
	            case "no":{
	            	logger.warning(ViewUtils.COMPUTER_NOT_DELETED);
	            	ComputerListView.main();
	            }break; 
            	default:
            		logger.warning(ViewUtils.BAD_INPUT);
            		break;
            }
        } while (!userChoice.equals("m"));
		
	}
	
	

}
