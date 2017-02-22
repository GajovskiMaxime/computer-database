package interfaces.validations;

import java.util.logging.Level;

import entities.Company;
import validations.StringValidation;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */

public interface ICompanyValidation {
	
	public final static Level VALIDATION_LEVEL = Level.FINE;
	
	static void checkCompany(Company company){
		checkName	(company);	
	}
	
	//TODO add matches regex ?
	static void checkName(Company company) throws NullPointerException{
		StringValidation.isNotNull(company.getName(), VALIDATION_LEVEL);
		StringValidation.matchesRegex(company.getName(), "", VALIDATION_LEVEL);
	}
	
}
