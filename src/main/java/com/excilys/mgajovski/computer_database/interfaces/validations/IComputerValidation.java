package com.excilys.mgajovski.computer_database.interfaces.validations;


import java.util.logging.Level;

import com.excilys.mgajovski.computer_database.entities.Computer;
import com.excilys.mgajovski.computer_database.validations.StringValidation;


/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public interface IComputerValidation {
	
	public final static Level VALIDATION_LEVEL= Level.FINE;
	
	static void checkComputer(Computer computer){
		checkName	(computer);	
		checkDates	(computer);
	}
	
	//TODO add Regex matchesRegex
	static void checkName(Computer computer) {
		StringValidation.isNotNull(computer.getName(), VALIDATION_LEVEL);
		StringValidation.matchesRegex(computer.getName(),"", VALIDATION_LEVEL);
	}

	@Deprecated
	static void checkDates(Computer computer){
//		DateValidation.isNotNull(computer.getIntroducedDate(), VALIDATION_LEVEL);
//		DateValidation.isNotNull(computer.getDiscontinuedDate(), VALIDATION_LEVEL);
//		DateValidation.compare(computer.getIntroducedDate(), computer.getDiscontinuedDate(), VALIDATION_LEVEL);
	}

}
