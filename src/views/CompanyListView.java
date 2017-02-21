package views;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import interfaces.entities.ICompany;
import interfaces.mvc.IController;


/**
 * @author	Gajovski Maxime
 * @date	21 févr. 2017
 */
public class CompanyListView {
	
	private static final 	Scanner				scan 		= new Scanner(System.in);
	private static 			CompanyListView	_instance 	= null;

	final static String[] FOOTER_LABELS = {
			"n - next page", 
			"p - previous page",
			"m - main menu"
	};
	

	public static void printCompanyHeaderWithPage(int page) {
		System.out.println	();
		System.out.println	();
		System.out.println	("--------------------------------------------------------------------------------------------");
		System.out.println	("COMPANIES : Current page : " + page);
		ViewUtils.printCompanyHeader();
	}

	
	public void printCurrentPage(int page, List<ICompany> companies) throws SQLException {
		
		printCompanyHeaderWithPage(page);
		for (ICompany company : companies) {
			System.out.printf(ViewUtils.COMPANY_FORMAT_LINE, 
					company.getId(), 
					company.getName());
		}
		
		ViewUtils.footer(Arrays.toString(FOOTER_LABELS));
		IController.companyListCtrl().switchMenu(scan);

	}
	
	public void printFirstPage() throws SQLException {
		printCurrentPage(
				IController.companyListCtrl().getCurrentPage(), 
				IController.companyListCtrl().getFirstCompanies());
	}
	
	public static CompanyListView getInstance() throws SQLException
	{			
		if (_instance == null){ 	
			_instance = new CompanyListView();	
		}
		return _instance;
	}
}
