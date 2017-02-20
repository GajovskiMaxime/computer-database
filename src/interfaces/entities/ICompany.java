package interfaces.entities;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public interface ICompany {

	public int 		getId();
	public String 	getName();
	public void		setName(String name);
	
	public default String display(){
		return 	"Class : " + this.getClass().getSimpleName()+ "\n" + 
				"\t" + "id : " 					+ getId() 	+ "\n" + 
				"\t" + "name : " 				+ getName()	+ "\n";
	}
}
