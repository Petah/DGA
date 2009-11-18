package dataanalyzer.entity;

import java.util.ArrayList;

/**
 * This class is used for referancing a collection of Output objects, it is most
 * commonly used when comparing Output instance to each other such as in graphs.
 * @author David Neilsen
 */
public class OutputCollection {

    /** The list of Output instances */
    private ArrayList<Output> output = new ArrayList<Output>();

    /**
     * Adds an Output instance to the list
     * @param o the Output to add
     */
    public void add(Output o) {
        output.add(o);
    }

    /**
     * Gets an ArrayList of all Output instances the OutputCollection contains
     * @return an ArrayList of Output instances
     */
    public ArrayList<Output> getAll() {
        return output;
    }

    @Override
    public String toString() {
        String s = "";
        for (Output o : output) {
            s += o + ":\n" + o.getOutput() + "\n";
        }
        return s;
    }
}
