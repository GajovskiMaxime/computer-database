package com.excilys.mgajovski.computer_database.interfaces.validations;


import java.util.logging.Level;

import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.validations.StringValidation;


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
