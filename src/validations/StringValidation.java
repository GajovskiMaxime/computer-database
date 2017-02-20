package validations;

import java.util.logging.Logger;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public final class StringValidation {
	
	private final static Logger logger = Logger.getLogger("StringValidation");
	
	public static void isNotNullErr (String string) throws NullPointerException{
		if(string == null) throw new NullPointerException();
	}
	
	public static void matchesRegexErr(String name, String regex) throws IllegalArgumentException{
		if(!name.matches(regex)) 
			throw new IllegalArgumentException(name + " doesn't match the regex : " + regex + ".\n");
	}
	
	public static void matchesRegexWarn(String name, String regex) throws IllegalArgumentException{
		if(!name.matches(regex)) 
			logger.warning(name + " doesn't match the regex : " + regex + ".\n");
	}
}
