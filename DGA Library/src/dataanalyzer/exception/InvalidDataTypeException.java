package dataanalyzer.exception;

/**
 * This exception should be thrown when a type of data cannot be converted to another
 * type safely.
 * @author David Neilsen
 */
public class InvalidDataTypeException extends Exception {

    /**
     * Standard Exception constructor.
     * @param message
     * @see Exception#Exception(java.lang.String)
     */
    public InvalidDataTypeException(String message) {
        super(message);
    }

    /**
     * Standard Exception constructor.
     * @see Exception#Exception()
     */
    public InvalidDataTypeException() {
    }

}
