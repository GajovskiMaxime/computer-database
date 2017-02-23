import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import dao.ICompanyDAO;
import dao.IComputerDAO;
import dao.impl.CompanyDAO;
import dao.impl.ComputerDAO;
import entities.Company;
import entities.Computer;
import interfaces.mvc.IView;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public class Test {
	public static void main(String[] args) throws SQLException {
		IView.mainMenu().displayMenu();
		
//		ICompanyDAO companyDAO = new CompanyDAO();
//		IComputerDAO computerDAO = new ComputerDAO();
//		
//		
//		
//		Company company = Company.builder().name("Abibou").build();
//		company = companyDAO.create(company).get();
//		
//		System.out.println(company);
//		Computer comp = Computer.builder()
//				.name("sdfsdf")
//				.discontinued(LocalDate.now())
//				.introduced(LocalDate.now())
//				.company(null)
//				.build();
//		
//	
//		System.out.println(computerDAO.create(comp));
//		
	
				
		
				
		
//		List<Company> companies= companyDAO.findByPage(20, 10).get();
//		System.out.println(companies.get(0));
//		System.out.println(companies.get(1));
//		System.out.println(companies.get(2));
//		
//		System.out.println(companies);
//		System.out.println(companies.get(0).getName());
//		System.out.println(companies.get(1).getName());
//		System.out.println(companies.get(2).getName());
//		System.out.println(companies.get(3).getId());
		
//		System.out.println(companyDAO.create(company).get());
//		Company company = companyDAO.find(200).get();
//		System.out.println(company.getId());
		//		
//		System.out.println(companies);
//		Company company = Company.builder().name("name").build();
//		
//		if(companyDAO.find(200).isPresent()){
//			Company company2 =  companyDAO.find(200).get();
//			System.out.println(company2.getName());
//		}
	}

}
