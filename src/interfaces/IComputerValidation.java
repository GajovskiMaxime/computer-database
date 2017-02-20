package interfaces;

import utils.Csts;
import validations.DateValidation;
import validations.StringValidation;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public interface IComputerValidation {

	static void checkComputer(IComputer computer){
		checkName	(computer);	
		checkDates	(computer);
	}
	
	static void checkName(IComputer computer) {
		StringValidation.isNotNullErr(computer.getName());
		StringValidation.matchesRegexWarn(computer.getName(), Csts.COMPUTER_NAME_REGEX);
	}

	static void checkDates(IComputer computer){
		DateValidation.isNotNullWarn(computer.getIntroducedDate());
		DateValidation.isNotNullWarn(computer.getDiscontinuedDate());
		DateValidation.compare(computer.getIntroducedDate(), computer.getDiscontinuedDate());
	}

}
