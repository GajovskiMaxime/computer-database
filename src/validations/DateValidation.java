package validations;

import java.time.DateTimeException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author	Gajovski Maxime
 * @date	20 févr. 2017
 */
public final class DateValidation {
	
	private static final Logger logger = Logger.getLogger(DateValidation.class.getName());
	
	public static void isNotNull(Date date, Level level){
		if(date == null) logger.log(level,date + "", new NullPointerException());
	}
	
	public static void compare(Date date1, Date date2) throws DateTimeException{
		if(date1 == null || date2 == null) return;
		if(!date1.before(date2)) throw new DateTimeException("Future can't be the past!\n"); 
	}
}
