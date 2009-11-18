package dataanalyzer.manager;

import dataanalyzer.listeners.ManagerUpdateListener;
import java.util.LinkedHashSet;

/**
 * This abstract class represents a object that managers a collection of other objects.
 * @author David Neilsen
 * @param <C> the container for the units
 * @param <U> the unit type to manager
 */
public abstract class Manager<C, U> {

    /** 
     * List on unique ManagerUpdateListener, linked because random acess in not 
     * important.
     */
    protected LinkedHashSet<ManagerUpdateListener> updateListeners = new LinkedHashSet<ManagerUpdateListener>();

    /**
     * Updates and ManagerListener objects registered with this Manager.
     */
    public void update() {
        for (ManagerUpdateListener u : updateListeners) {
            u.update(this);
        }
    }

    /**
     * Adds an ManagerUpdateListener to the manager.
     * @param m the ManagerUpdateListener to add
     */
    public void addUpdateListener(ManagerUpdateListener m) {
        updateListeners.add(m);
    }

    /**
     * Removes a ManagerUpdateListener from this Manager.
     * @param m
     */
    public void removeUpdateListener(ManagerUpdateListener m) {
        updateListeners.remove(m);
    }

    /**
     * Clears the list of ManagerUpdateListener objects.
     */
    public void clearUpdateListener() {
        updateListeners.clear();
    }

    /**
     * Returns a unit at the specified index.
     * @param i the index of the unit
     * @return the unit
     */
    public abstract U get(int i);

    /**
     * Gets the container.
     * @return the container
     */
    public abstract C get();

    /**
     * Adds a unit to the container.
     * @param u the unit to add
     */
    public abstract void add(U u);

    /**
     * Removes a unit from the container.
     * @param u the unit to remove
     */
    public abstract void remove(U u);

    /**
     * Returns the size of the container.
     * @return the size of the container
     */
    public abstract int size();
}

