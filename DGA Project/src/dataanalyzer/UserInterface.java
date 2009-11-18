package dataanalyzer;

import dataanalyzer.interfaces.ISystem;

/**
 * This abstract class provides a standard method of output to a user interface.
 * @author David Neilsen
 */
public abstract class UserInterface implements ISystem {

    /** Verbosity level to display the least output. */
    public static final int NONE = 0;
    /** Verbosity level to display a minimun amount ofoutput. */
    public static final int MIN = 2;
    /** Verbosity level to display most of the output. */
    public static final int MAX = 8;
    /** Verbosity level to display all output. */
    public static final int ALL = 10;
    /** The current verbosity level. */
    private int verbosity = NONE;

    /**
     * Outputs an object if the verbosity level is less than or equal to the current
     * verbosity.
     * @param o the object to output
     * @param verbosity the verbosity level to check
     */
    public void out(Object o, int verbosity) {
        if (verbosity <= this.verbosity) {
            out(o);
        }
    }

    /**
     * Displays an error in the GUIif the verbosity level is less than or equal to the current
     * verbosity.
     * @param s the error message
     * @param verbosity the verbosity level to check
     */
    public void handleError(String s, int verbosity) {
        if (verbosity <= this.verbosity) {
            handleError(s);
        }
    }

    /**
     * Handles an exception if the verbosity level is less than or equal to the current
     * verbosity.
     * @param ex the exception to handle
     * @param verbosity the verbosity level to check
     */
    public void handleException(Throwable ex, int verbosity) {
        if (verbosity <= this.verbosity) {
            handleException(ex);
        }
    }

    /**
     * Gets the verbosity.
     * @return the verbosity
     */
    public int getVerbosity() {
        return verbosity;
    }

    /**
     * Sets the verbosity level.
     * @param verbosity the new verbosity level
     */
    public void setVerbosity(int verbosity) {
        this.verbosity = verbosity;
    }
}
