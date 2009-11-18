package dataanalyzer.listeners;

/**
 * Listens for progress updates for an operation.
 * @author David Neilsen
 */
public interface ProgressListener {
    /**
     * Signals that the progress has been updated.
     * @param value the new value of the progress
     * @param label the current status
     */
    public void update(int value, String label);
}
