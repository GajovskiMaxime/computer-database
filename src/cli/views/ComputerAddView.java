package cli.views;
//package views;
//
//import java.sql.SQLException;
//import java.util.Arrays;
//import java.util.Scanner;
//import java.util.logging.Logger;
//
//import dao.IComputerDAO;
//import dao.impl.ComputerDAO;
//import entities.Computer;
///**
// * @author	Gajovski Maxime
// * @date	21 févr. 2017
// */
//public class ComputerAddView {
//	
//	private static final 	Logger 			logger 			= Logger.getLogger(ComputerAddView.class.getName());
//	private static final 	IComputerDAO 	computerDAO 	= new ComputerDAO();
//	private static final 	Scanner 		scan 			= new Scanner(System.in);
//	private static 			Computer 		computer;
//
//	public static void main() throws NumberFormatException, SQLException{
//		String userChoice 	= null;
////		
////        do {
////        	ComputerSearchByIdViewUtils.printComputerSearchByIdHeader();
////        	userChoice = scan.nextLine();
////        	computer = computerDAO.find(Integer.parseInt(userChoice));
////        } while (!userChoice.matches("^[0-9]*$") || computer == null); 
////        
//        
//        ViewUtils.printComputerAddHeader();
//       for(int i = 0; i < ComputerAddViewUtils.INSTRUCTIONS.length; i++){
//    	   
//    	   do {
//    		   
//    		   System.out.println	("-------------------------------------------------------------------");
//    		   System.out.println	(ComputerAddViewUtils.INSTRUCTIONS[i]);
//    		   
//    		   
//    	   } while (!userChoice.equals("m"));    
//       }
//    }
//}
