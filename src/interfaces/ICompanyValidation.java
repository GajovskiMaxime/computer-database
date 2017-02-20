package interfaces;

import utils.Csts;
import validations.StringValidation;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */

public interface ICompanyValidation {

	static void checkCompany(ICompany company){
		checkName	(company);	
	}
	
	static void checkName(ICompany company) throws NullPointerException{
		StringValidation.isNotNullErr(company.getName());
		StringValidation.matchesRegexWarn(company.getName(), Csts.COMPANY_NAME_REGEX);
	}
	
}
