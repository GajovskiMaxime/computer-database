package interfaces.validations;

import java.util.logging.Level;

import entities.Computer;
import validations.DateValidation;
import validations.StringValidation;

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
