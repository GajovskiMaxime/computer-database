package views;

/**
 * @author	Gajovski Maxime
 * @date	21 févr. 2017
 */
public class ComputerSearchByIdViewUtils {
	

	final static String[] FOOTER_LABELS = {
			"r - return to the list of computers",
			"u - update this computer",
			"d - delete this computer",
			"m - main menu"
	};
	
	public static void printComputerSearchByIdHeader(){
		System.out.println	();
		System.out.println	();
		System.out.println	("-------------------------------------------------------------------");
		System.out.print	("Search computer by id : ");
	}
	
	public static void printComputerDeleteHeader(int id){
		System.out.println	();
		System.out.println	();
		System.out.println	("-------------------------------------------------------------------");
		System.out.print	("Are you sure to delete the computer with id : " + id + " ?");
	}

	
}
