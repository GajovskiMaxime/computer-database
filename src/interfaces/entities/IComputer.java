package interfaces.entities;

import java.util.Date;

import utils.Csts;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public interface IComputer {


	public int 		getId();
	public String 	getName();
	
	public Date 	getIntroducedDate();
	public void 	setIntroducedDate(Date introduced);

	public Date 	getDiscontinuedDate();
	public void 	setDiscontinuedDate(Date discontinued);

	public ICompany getCompany();
	public void 	setCompany(ICompany company);
	
	public default String display(){
		return 	"Class : " + this.getClass().getSimpleName() + "\n" + 
				"\t" + "id : " 					+ getId() 				+ "\n" + 
				"\t" + "name : " 				+ getName() 			+ "\n" + 
				"\t" + "introduced date : " 	+ (getIntroducedDate() == null ? Csts.UNDEFINED : getIntroducedDate())		+ "\n" + 
				"\t" + "discontinued date : " 	+ (getDiscontinuedDate() == null ? Csts.UNDEFINED : getDiscontinuedDate()) 	+ "\n" + 
				"\t" + "company name : " 		+ (getCompany() == null ? Csts.UNDEFINED : getCompany().getName()) 			+ "\n";
	}
}
