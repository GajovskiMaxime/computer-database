package views;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;
import interfaces.mvc.IController;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public class MainView {
	
	private static final Logger 	logger 		= Logger.getLogger(MainView.class.getName());
	private static final Scanner 	scan 		= new Scanner(System.in);
	private static MainView			_instance 	= null;

	private final static String[] MAIN_MENU_LABELS = {
			"List computers", 
			"List companies",
			"Create a computer",
			"Quit"
	};
	
	private static void displayHeaderMenu(){
		System.out.println("*******************************************************************");
		System.out.println("***                                                             ***");
		System.out.println("***                  Gestionaire d'ordinateurs                  ***");
		System.out.println("***                                                             ***");
		System.out.println("*******************************************************************");
	}
	

	private static void printBread(String pLink) {
		System.out.println("");
		System.out.println(" > " + pLink);
		System.out.println("-------------------------------------------------------------------");
		
	}
	
	private static void displayMenuItems(){
		for (int i = 0; i < MAIN_MENU_LABELS.length; i++)
			System.out.printf("%3d > %s\n", i, MAIN_MENU_LABELS[i]);
	}
	
	
	private MainView() throws SQLException {
		logger.info("Lancement du programme");
	}
	
	public void displayMenu() throws SQLException{
		displayHeaderMenu();
		printBread("MainMenu");
		displayMenuItems();
		IController.mainMenuCtrl().switchMenu(scan);
	}
	
	public static MainView getInstance() throws SQLException
	{			
		if (_instance == null){ 	
			_instance = new MainView();	
		}
		return _instance;
	}
}