package validations;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public final class StringValidation {
	
	private final static Logger logger = Logger.getLogger(StringValidation.class.getName());
	
	public static void isNotNull(String string, Level level) throws NullPointerException{
		if(string == null) logger.log(level,"", new NullPointerException());
	}
	
	public static void matchesRegex(String name, String regex, Level level) {
		if(!name.matches(regex)) 
			logger.log(level, "", new IllegalArgumentException());
	}
}
