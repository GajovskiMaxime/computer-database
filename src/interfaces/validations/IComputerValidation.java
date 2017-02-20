package interfaces.validations;

import java.util.logging.Level;

import interfaces.entities.IComputer;
import utils.Csts;
import validations.DateValidation;
import validations.StringValidation;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public interface IComputerValidation {
	
	public final static Level VALIDATION_LEVEL= Level.FINE;
	
	static void checkComputer(IComputer computer){
		checkName	(computer);	
		checkDates	(computer);
	}
	
	static void checkName(IComputer computer) {
		StringValidation.isNotNull(computer.getName(), VALIDATION_LEVEL);
		StringValidation.matchesRegex(computer.getName(), Csts.COMPUTER_NAME_REGEX, VALIDATION_LEVEL);
	}

	static void checkDates(IComputer computer){
		DateValidation.isNotNull(computer.getIntroducedDate(), VALIDATION_LEVEL);
		DateValidation.isNotNull(computer.getDiscontinuedDate(), VALIDATION_LEVEL);
		DateValidation.compare(computer.getIntroducedDate(), computer.getDiscontinuedDate());
	}

}
