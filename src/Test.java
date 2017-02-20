import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import dao.CompanyDAO;
import dao.ComputerDAO;
import database.MySQLConnection;
import entities.Company;
import entities.Computer;
import interfaces.entities.ICompany;
import interfaces.entities.IComputer;
import views.MainView;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public class Test {
	public static void main(String[] args) throws SQLException {
//		List<IComputer> computers = new ArrayList<>();
//		
//		ICompany company01 = new Company.Builder()
//				.name("Maxime")
//				.build();
//		
//		IComputer computer01 = new Computer.Builder()
//				.name("Dsdfs").build();
//		
////		computers.ad
//		System.out.println(computer01.toSring());
////		
//		int userChoice;
//        Scanner input = new Scanner(System.in);

//        /*********************************************************/

//        userChoice = MainView.menu();
//	
//		MySQLConnection.getInstance();
		
		ComputerDAO computerDAO = new ComputerDAO();
		CompanyDAO 	companyDAO 	= new CompanyDAO();
		
//		System.out.println(computerDAO.find(1));
//		computerDAO.create(null);
		ICompany companyTruc = new Company.Builder().name("Nope").build();
//		System.out.println(companyTruc);
//		ICompany company = companyDAO.create(new Company.Builder().name("Steph pue du cul !").build());
		
		ICompany company = companyDAO.find(22);
		System.out.println(company); 
//		
		company.setName("MaGloire!");
		companyDAO.update(company);
		System.out.println(company); 
		
		companyDAO.create(companyTruc);
		
//		ICompany company1 = companyDAO.find(22);
//		System.out.println(company1);
//		
//		
//		company1.setName("The YOOOLOOOO Cie.");
//		companyDAO.update(company1);
//		
//		ICompany company2 = companyDAO.find(22);
//		
//		System.out.println(company2);
//		companyDAO.delete(3);
//		System.out.println(company2);
		
	}

}
