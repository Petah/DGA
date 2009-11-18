package dataanalyzer.listeners;
// TODO: Make a object to implement functions such as addCompletionListenr and to notify for ease of use
import java.io.Serializable;

/**
 * A listener to signal that a operation has been complete.
 * @author David Neilsen
 */
public interface CompletionListener extends Serializable {
    /**
     * Signals an operation has been complete.
     * @param o the object that has completed the operation
     */
    public void complete(Object o);
}
