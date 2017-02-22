package interfaces.mvc;

import java.sql.SQLException;

import controllers.CompanyListCtrl;
import controllers.ComputerDetailCtrl;
import controllers.ComputerListCtrl;
import controllers.MainCtrl;

/**
 * @author	Gajovski Maxime
 * @date	21 févr. 2017
 */
public interface IController {
	
	public static MainCtrl mainMenuCtrl() throws SQLException{
		return MainCtrl.getInstance();
	}
	
	public static ComputerListCtrl computerListCtrl() throws SQLException{
		return ComputerListCtrl.getInstance();
	}
	
	public static CompanyListCtrl companyListCtrl() throws SQLException{
		return CompanyListCtrl.getInstance();
	}

	public static ComputerDetailCtrl computerDetailCtrl() throws SQLException{
		return ComputerDetailCtrl.getInstance();
	}

}