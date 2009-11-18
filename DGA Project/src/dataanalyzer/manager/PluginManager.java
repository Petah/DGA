package dataanalyzer.manager;

import dataanalyzer.DGA;
import dataanalyzer.entity.Plugin;
import dataanalyzer.entity.Algorithm;
import java.util.ArrayList;

/**
 * This class is used to manage all Plugin instances within the system. When a plugin
 * is added to this manager all the contained algorithms are added to the AlgorithmManager.
 * @author David Neilsen
 */
public class PluginManager extends Manager<ArrayList<Plugin>, Plugin> {

    /** The main DGA object */
    private DGA system;
    /**
     * The container of Plugin instances, which is an ArrayList because random
     * access is required.
     */
    private ArrayList<Plugin> plugins = new ArrayList<Plugin>();

    /**
     * Initializes object.
     * @param system the main DGA object
     */
    public PluginManager(DGA system) {
        this.system = system;
    }

    /**
     * Add an Plugin instance to the manager and then adds all the Algorithms the
     * Plugin instance contains to the AlgorithmManager.
     * @param p Plugin to add
     */
    public void add(Plugin p) {
        plugins.add(p);
        //Add all plugin algorithms to the algorithm manager
        for (Algorithm a : p.get()) {
            system.algorithmManager.add(a);
        }
        update();
    }

    /**
     * Gets the list of Plugin instances.
     * @return an ArrayList of Plugin instances
     */
    public ArrayList<Plugin> get() {
        return plugins;
    }

    /**
     * Gets a Plugin at the specified index of the list.
     * @param i the Plugin index
     * @return an Plugin
     */
    @Override
    public Plugin get(int i) {
        return plugins.get(i);
    }

    /**
     * Gets the amount of Plugin instances in the list.
     * @return the amount of Plugin instances
     */
    @Override
    public int size() {
        return plugins.size();
    }

    /**
     * Removes an Plugin from the list and also removes all the Algorithms the
     * Plugin instance contains from the AlgorithmManager.
     * @param p the Plugin to remove
     */
    @Override
    public void remove(Plugin p) {
        plugins.remove(p);
        //Remove all plugin algorithms to the algorithm manager
        for (Algorithm a : p.get()) {
            system.algorithmManager.remove(a);
        }
        update();
    }

    @Override
    public String toString() {
        return "Plugins";
    }
}
