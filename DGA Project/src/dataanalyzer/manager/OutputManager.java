package dataanalyzer.manager;

import dataanalyzer.DGA;
import dataanalyzer.entity.Algorithm;
import dataanalyzer.entity.AlgorithmProcess;
import dataanalyzer.entity.Output;
import dataanalyzer.listeners.CompletionListener;
import java.util.ArrayList;

/**
 * This class is used to manage all Output instances within the system. It implements
 * CompletionListener to request ouput from an AlgorithmProcess the when it has completed
 * running.
 * @author David Neilsen
 */
public class OutputManager extends Manager<ArrayList<Output>, Output> implements CompletionListener {

    /** The main DGA object */
    private DGA system;
    /**
     * The container of Output instances, which is an ArrayList because random
     * access is required.
     */
    private ArrayList<Output> output = new ArrayList<Output>();

    /**
     * Initializes object.
     * @param system the main DGA object
     */
    public OutputManager(DGA system) {
        this.system = system;
    }

    /**
     * Add an Output instance to the manager only if the Output objcet is not null
     * and the output that it contains is also not null.
     * @param o Output to add
     */
    public void add(Output o) {
        if (o != null && o.getOutput() != null) {
            output.add(o);
            update();
        }
    }

    /**
     * Removes an Output instance from the list.
     * @param o the Output to remove
     */
    @Override
    public void remove(Output o) {
        output.remove(o);
        update();
    }

    /**
     * Gets the list of Output instances.
     * @return an ArrayList of Output instances
     */
    public ArrayList<Output> get() {
        return output;
    }

    /**
     * Gets the Output instance at the specified index of the list.
     * @param i the Output index
     * @return an Output
     */
    public Output get(int i) {
        return output.get(i);
    }

    /**
     * Gets the amount of Output instances in the list.
     * @return the amount of Output instances
     */
    public int size() {
        return output.size();
    }

    /**
     * Signals that an AlgorithmProcess and the Output should be requested.
     * @param o the completed object (must be an AlgorithmProcess or a ClassCastException
     * will occur)
     */
    public void complete(Object o) {
        add(((AlgorithmProcess) o).getOutput());
    }

    @Override
    public String toString() {
        return "Output";
    }
}
