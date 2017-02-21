package views;

/**
 * @author	Gajovski Maxime
 * @date	21 févr. 2017
 */
public class ViewUtils {
	

	static final String BAD_INPUT = "Bad Input! Try again !\n";
	
	static final String COMPUTER_NOT_DELETED = "Computer not deleted ! *HALLELUJAH*\n";
	static final String NEGATIVE_PAGE_REQUEST = "You can't reach a negative page number !\n";
	
	static final String COMPUTER_FORMAT_LINE 	= "%3s | %15.15s | %10s | %10s | %10s%n";
	static final String COMPANY_FORMAT_LINE 	= "%3s | %15.15s%n";

	static void printComputerHeader(){
		System.out.println	("-------------------------------------------------------------------");
		System.out.printf	(COMPUTER_FORMAT_LINE, "ID", "NOM", "DATE CREATION", "DATE FIN", "FABRICANT");
		System.out.println	("-------------------------------------------------------------------");
	}
	
	static void printComputerAddHeader(){
		System.out.println	("-------------------------------------------------------------------");
		System.out.println	("-						COMPUTER CREATION							-");
		System.out.println	("-------------------------------------------------------------------");
	}

	static void printCompanyHeader(){
		System.out.println	("-------------------------------------------------------------------");
		System.out.printf	(COMPANY_FORMAT_LINE, "ID", "NOM");
		System.out.println	("-------------------------------------------------------------------");
	}

	
	static void footer(String footer) {
		System.out.println("\n-------------------------------------------------------------------");
		System.out.print(footer);
	}

	static void close() {
		System.out.println("\n\n-------------------------------------------------------------------");
		System.out.print("FIN DU PROGRAMME");
	}

	
	public final static String USER_BAD_CHOICE = 
			"Nope ! Try Again !\n";
	
}
