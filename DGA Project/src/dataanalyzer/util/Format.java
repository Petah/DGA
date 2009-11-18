package dataanalyzer.util;

/**
 * This class to provide genral purpose static funtions for formatting numbers.
 * @author David Neilsen
 */
public class Format {

    /**
     * Rounds a float to a specified number of decimal places.
     * @param value the float value to round
     * @param decimalPlaces the amonut of decimal places
     * @return the rounded value
     */
    public static float round(float value, int decimalPlaces) {
        float p = (float) Math.pow(10, decimalPlaces);
        value = value * p;
        float tmp = Math.round(value);
        return (float) tmp / p;
    }

    /**
     * Converts a file size to a String with the relitive b, Kb, or Mb symbol and
     * rounds to 2 decimal places.
     * @param bytes the about of bytes
     * @return the String representation
     */
    public static String bytes(long bytes) {
        if (bytes < 500) {
            return bytes + "b";
        } else if (bytes < ((1024 * 1024) / 2)) {
            return round(bytes / 1024, 2) + "Kb";
        } else {
            return round((bytes / 1024 / 1024), 2) + "Mb";
        }
    }
}
