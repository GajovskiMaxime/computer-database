package cli.views;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import entities.Computer;
import interfaces.mvc.IController;

/**
 * 
 * @author	Gajovski Maxime
 * @date	21 févr. 2017
 */
public class ComputerDetailView {
	
	private static final 	Scanner				scan 		= new Scanner(System.in);
	private static 			ComputerDetailView	_instance 	= null;

	final static String[] FOOTER_LABELS = {
			"r - return to the list of computers",
			"u - update this computer",
			"d - delete this computer",
			"m - main menu"
	};
	
	final static String[] COMPUTER_ATTRIBUTES = {
			"name",
			"introduced date",
			"discontinued date",
			"company id"
	};
	
	public void printComputerSearchByIdHeader() throws SQLException{
		ViewUtils.header(ViewUtils.COMPUTER_SEARCH_BY_ID);
		IController.computerDetailCtrl().searchByIdSwitchMenu(scan);
	}
	
	public void printComputerAttributes() throws SQLException{
		System.out.println(COMPUTER_ATTRIBUTES);
		
		IController.computerDetailCtrl().searchByIdSwitchMenu(scan);
	}
	
	public void printComputerDeleteConfirmationHeader(int id){
		ViewUtils.header(ViewUtils.CONFIRMATION_DEL_COMPUTER + id);
	}
	
	public void printComputerDetail(Computer computer){
		ViewUtils.printComputerHeader();
        System.out.printf(ViewUtils.COMPUTER_FORMAT_LINE, 
					computer.getId(), 
					computer.getName(), 
					computer.getIntroducedDate() == null ?  "" : computer.getIntroducedDate(), 
					computer.getDiscontinuedDate() == null ?"" : computer.getDiscontinuedDate(), 
					computer.getCompany() == null ? 		"" : computer.getCompany().getName());
	
        ViewUtils.footer(Arrays.toString(FOOTER_LABELS));
	}
	
	public static ComputerDetailView getInstance() throws SQLException
	{			
		if (_instance == null){ 	
			_instance = new ComputerDetailView();	
		}
		return _instance;
	}
	

}
