package dataanalyzer.interfaces;

import dataanalyzer.entity.Output;
import dataanalyzer.listeners.SystemListener;

/**
 * Provides an interface for interacting with the system.
 * @author David Neilsen
 */
public interface ISystem {

    /**
     * Tells the system to handle an exception.
     * @param ex the exception to handle
     */
    public abstract void handleException(Throwable ex);

    /**
     * Tells the system to handle a non-fatal error.
     * @param s the error message
     */
    public abstract void handleError(String s);

    /**
     * Tells the ssytem to output information in its standard way.
     * @param o the Object to output
     */
    public abstract void out(Object o);

    /**
     * Requests the system to collect an Output object and send it to the SystemListener
     * @param systemListener the SystemListener waiting for the Output
     */
    public void collectOutput(SystemListener systemListener);

    /**
     * Sends output to the system.
     * @param o output to send
     */
    public void sendOutput(Output o);
}
