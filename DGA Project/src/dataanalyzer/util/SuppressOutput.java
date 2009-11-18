package dataanalyzer.util;

import java.io.IOException;
import java.io.PrintStream;

/**
 * This class is used to suppress all output to the standard system output and error
 * streams.
 * @author David Neilsen
 */
public class SuppressOutput {

    /** The standard output stream */
    private static PrintStream outStream = System.out;
    /** The standard error output stream */
    private static PrintStream errorStream = System.err;
    /** An output stream that does nothing */
    private static PrintStream voidStream = new VoidStream();

    /**
     * Supresses the standard output stream.
     */
    public static void suppressOutput() {
        System.setOut(voidStream);
    }

    /**
     * Supresses the standard error output stream.
     */
    public static void suppressErrors() {
        System.setErr(voidStream);
    }

    /**
     * Supresses both the standard output and error stream.
     */
    public static void suppressBoth() {
        suppressOutput();
        suppressErrors();
    }

    /**
     * Sets the error ouput stream back to normal.
     */
    public static void resetErrors() {
        System.setErr(errorStream);
    }

    /**
     * Sets the ouput stream back to normal.
     */
    public static void resetOutput() {
        System.setErr(outStream);
    }

    /**
     * Sets both the error ouput and standard output stream back to normal.
     */
    public static void resetBoth() {
        resetOutput();
        resetErrors();
    }

    /**
     * This class extends PrintStream but does not print anything.
     */
    private static class VoidStream extends PrintStream {

        /**
         * Overides the write() method of OutputStream to do nothing.
         */
        public VoidStream() {
            super(new java.io.OutputStream() {

                @Override
                public void write(int b) throws IOException {
                }
            });
        }
    }
}
