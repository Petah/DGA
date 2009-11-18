package dataanalyzer.entity;

import java.io.File;
import java.util.ArrayList;

/**
 * A Pluging class is loaded from a zip or jar file and contains a list of Algorithms
 * that are included in the Plugin
 * @author David Neilsen
 */
public class Plugin {

    /** The list of Algorithms */
    private ArrayList<Algorithm> algorithms = new ArrayList<Algorithm>();
    /** The file the plugin was loaded from */
    private File file = null;

    /**
     * Initializes the Plugin and sets the file.
     * @param file the file of the Plugin
     */
    public Plugin(File file) {
        this.file = file;
    }

    /**
     * Adds an Algorithm to the Plugin.
     * @param a the Algorithm to add
     */
    public void add(Algorithm a) {
        algorithms.add(a);
    }

    /**
     * Get an ArrayList of all the Algorithms contained within the Plugin.
     * @return an ArrayList of Algorithms
     */
    public ArrayList<Algorithm> get() {
        return algorithms;
    }

    public String toString() {
        String string = file.getName();
        string = string.replaceAll("_", " ");
        string = string.replaceAll(".jar", "");
        string = string.replaceAll(".zip", "");
        return string;
    }
}