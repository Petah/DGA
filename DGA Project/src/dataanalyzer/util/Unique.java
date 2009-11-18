package dataanalyzer.util;

/**
 *
 * @author David Neilsen
 */
/**
 * This class generates a unique number (of type long. The number will only be unique
 * during the running of the application.
 * @author David Neilsen
 */
public class Unique {

    /** The next unique number */
    private static long longNumber = 0;

    /**
     * Gets a unique long number.
     * @return a unique long number
     */
    public static long getLong() {
        return longNumber++;
    }
}
