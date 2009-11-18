package dataanalyzer.entity;

import java.io.Serializable;
import java.util.*;

/**
 * A container class that provides methods to store a series of units of data.
 * @author David Neilsen
 * @param <S> the type of data the sequence stores
 * @param <M> the container class used to store units
 */
public abstract class Sequence<S, M> implements Serializable {

    /**
     * Adds a single unit of data to the container.
     * @param s the unit to add
     */
    public abstract void addSingle(S s);

    /**
     * Gets a single unit of data a the specified index position.
     * @param i index of the data
     * @return a unit of data
     */
    public abstract S getSingle(int i);

    /**
     * Adds all units in the container to the current Sequence
     * @param m the container to add
     */
    public abstract void addMulti(M m);

    /**
     * Gets an amount of units from the container starting at an index position.
     * @param start the start index
     * @param length the amount of data to get
     * @return a container of units
     */
    public abstract M getMulti(int start, int length);

    /**
     * Gets the entire container of units.
     * @return the entire container of units
     */
    public abstract M getAll();

    /**
     * Gets an Iterator to iterate through the data.
     * @return an Iterator
     */
    public abstract Iterator<S> getIterator();

    /**
     * Remove the unit of data at a position
     * @param i the position to remove
     */
    public abstract void remove(int i);

    /**
     * Gets the size of the container.
     * @return the size of the container
     */
    public abstract int size();

    public String toString() {
        return "Sequence";
    }
}
