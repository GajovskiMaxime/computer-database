import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;

import dao.CompanyDAO;
import dao.ComputerDAO;
import entities.Company;
import entities.CompanyFactory;
import entities.Computer;
import interfaces.entities.ICompany;
import interfaces.entities.IComputer;

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
//		ICompany companyTruc = new Company.Builder().name("Nope").build();
//		System.out.println(companyTruc);
//		ICompany company = companyDAO.create(new Company.Builder().name("Steph pue du cul !").build());
		
		ICompany company = companyDAO.find(22);
//		System.out.println(company); 
//		
		company.setName("MaGloire!");
		companyDAO.update(company);
//		System.out.println(company); 
		
		List<String> mylist = companyDAO.findNamesByPage(3);
		System.out.println(mylist);
		
		ICompany company00 = companyDAO.find(5);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		
		IComputer computers = new Computer.Builder().name("Bobby").company(company00).introduced(date)
				.discontinued(date).build();
		
		computerDAO.create(computers);
		System.out.println(computers);
		
//		ICompany comp =   CompanyFactory.create().name("Bobbt").id(1).build();
		
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
