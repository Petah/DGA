package dataanalyzer.manager;

import dataanalyzer.DGA;
import dataanalyzer.entity.Algorithm;
import java.util.ArrayList;

/**
 * This class is used to manage all Algorithm instances within the system.
 * @author David Neilsen
 */
public class AlgorithmManager extends Manager<ArrayList<Algorithm>, Algorithm> {

    /** The main DGA object */
    private DGA system;
    /**
     * The container of Algorithm instances, which is an ArrayList because random
     * access is required.
     */
    private ArrayList<Algorithm> algorithms = new ArrayList<Algorithm>();

    /**
     * Initializes object.
     * @param system the main DGA object
     */
    public AlgorithmManager(DGA system) {
        this.system = system;
    }

    /**
     * Add an Algorithm instance to the manager.
     * @param a Algorithm to add
     */
    public void add(Algorithm a) {
        algorithms.add(a);
        update();
    }

    /**
     * Gets the list of Algorithm instances.
     * @return an ArrayList of Algorithm instances
     */
    public ArrayList<Algorithm> get() {
        return algorithms;
    }

    /**
     * Gets a Algorithm at the specified index of the list.
     * @param i the Algorithm index
     * @return an Algorithm
     */
    @Override
    public Algorithm get(int i) {
        return algorithms.get(i);
    }

    /**
     * Gets the amount of Algorithm instances in the list.
     * @return the amount of Algorithm instances
     */
    @Override
    public int size() {
        return algorithms.size();
    }

    /**
     * Removes an Algorithm from the list.
     * @param a the Algorithm to remove
     */
    public void remove(Algorithm a) {
        algorithms.remove(a);
        update();
    }

    @Override
    public String toString() {
        return "Algorithms";
    }
}
