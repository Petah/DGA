package dataanalyzer.listeners;

/**
 * used by UserInterface objects to assioate an Object waiting to collect another
 * Object from the UserInterface.
 * @author David Neilsen
 */
public interface SystemListener {
    /**
     * Signals that the Object is ready to be collected.
     * @param o the Object is ready to be collected
     */
    public void collectObject(Object o);
}
