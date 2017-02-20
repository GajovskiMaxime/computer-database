package interfaces.validations;

import java.util.logging.Level;

import interfaces.entities.ICompany;
import utils.Csts;
import validations.StringValidation;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */

public interface ICompanyValidation {
	
	public final static Level VALIDATION_LEVEL = Level.FINE;
	
	static void checkCompany(ICompany company){
		checkName	(company);	
	}
	
	static void checkName(ICompany company) throws NullPointerException{
		StringValidation.isNotNull(company.getName(), VALIDATION_LEVEL);
		StringValidation.matchesRegex(company.getName(), Csts.COMPANY_NAME_REGEX, VALIDATION_LEVEL);
	}
	
}
