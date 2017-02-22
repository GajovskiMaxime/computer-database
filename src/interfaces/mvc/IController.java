package interfaces.mvc;

import java.sql.SQLException;

import cli.controllers.CompanyListCtrl;
import cli.controllers.ComputerDetailCtrl;
import cli.controllers.ComputerListCtrl;
import cli.controllers.MainCtrl;

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
