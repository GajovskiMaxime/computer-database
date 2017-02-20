package interfaces;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public interface ICompany {

	public int 		getId();
	public String 	getName();
	
	public default String toSring(){
		return 	"Class : " + this.getClass().getSimpleName()+ "\n" + 
				"\t" + "id : " 					+ getId() 	+ "\n" + 
				"\t" + "name : " 				+ getName()	+ "\n";
	}
}
