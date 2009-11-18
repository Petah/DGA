package dataanalyzer.listeners;

import dataanalyzer.manager.Manager;

/**
 * This class can be implemented to signal when a Manager has been updated.
 * @author David Neilsen
 */
public interface ManagerUpdateListener {

    /**
     * Signals that a manager has been updated.
     * @param m the Manager that has been updated
     */
    public void update(Manager m);
}
