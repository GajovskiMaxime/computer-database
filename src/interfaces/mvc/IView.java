package interfaces.mvc;

import java.sql.SQLException;

import views.CompanyListView;
import views.ComputerListView;
import views.MainView;

/**
 * @author	Gajovski Maxime
 * @date	21 févr. 2017
 */
public interface IView {
	
	public static MainView mainMenu() throws SQLException{
		return MainView.getInstance();
	}

	public static ComputerListView computerList() throws SQLException{
		return ComputerListView.getInstance();
	}
	
	public static CompanyListView companyList() throws SQLException{
		return CompanyListView.getInstance();
	}
}
