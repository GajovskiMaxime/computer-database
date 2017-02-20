package validations;

import java.time.DateTimeException;
import java.util.Date;
import java.util.logging.Logger;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public final class DateValidation {
	
	private static final Logger logger = Logger.getLogger("DateValidation");
	
	public static void isNotNullErr (Date date) throws NullPointerException{
		if(date == null) throw new NullPointerException();
	}
	
	public static void isNotNullWarn (Date date) throws NullPointerException{
		if(date == null) logger.warning("null argument.\n");
	}
	
	public static void compare(Date date1, Date date2) throws DateTimeException{
		if(date1 == null || date2 == null) return;
		if(!date1.before(date2)) throw new DateTimeException("Future can't be the past!\n"); 
	}
}
