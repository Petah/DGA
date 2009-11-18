package dataanalyzer.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Get the current Date and Time
 * http://www.rgagnon.com/javadetails/java-0106.html
 */
public class Date {

    /** Date format */
    public static final String DATE_FORMAT_NOW = "dd-MM-yyyy HH:mm:ss";

    /**
     * Gets the current formated date and time.
     * @return the formatted current date and time
     */
    public static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());

    }
}
