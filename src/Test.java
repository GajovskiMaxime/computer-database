import java.util.Date;

import entities.Company;
import entities.Computer;
import interfaces.ICompany;
import interfaces.IComputer;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public class Test {
	public static void main(String[] args) {
		ICompany company01 = new Company.Builder()
				.name("Maxime")
				.build();
		
		IComputer computer01 = new Computer.Builder()
				.name("Dsdfs").build();

		System.out.println(computer01.toSring());
	}

}
