package dataanalyzer;

import dataanalyzer.entity.LipeClient;
import dataanalyzer.entity.LipeServer;
import dataanalyzer.manager.NodeManager;
import dataanalyzer.manager.PluginManager;
import dataanalyzer.manager.AlgorithmManager;
import dataanalyzer.manager.Manager;
import dataanalyzer.manager.OutputManager;
import dataanalyzer.manager.ProcessManager;
import dataanalyzer.manager.UpdateManager;
import dataanalyzer.manager.ResourceManager;
import dataanalyzer.manager.SettingsManager;
import java.io.IOException;
import java.util.ArrayList;
// TODO: Make frames not appear in the excat some position as another one
// TODO: Expand information capibility (size of data, min, max, time to create etc)
// TODO: Make anonimis usage reporting
// TODO: Make error reporting
// TODO: Convert all public properties to private with getters/setters
// TODO: Make all singleton class static instead of having public referances
// TODO: Redesign program to use singleton classes for managers (static)

/**
 * This class contains all referances to the manager objects required for the system
 * and the userinterface. This class also implements the Manager class to hold
 * a list of other Managers which are displayed in the GUI tree (this is to setup
 * a tree hierarchy).
 * @author David Neilsen
 */
public class DGA extends Manager<ArrayList<Manager>, Manager> implements Thread.UncaughtExceptionHandler {

    private ArrayList<Manager> managers = new ArrayList<Manager>();
    public UserInterface userInterface;
    public NodeManager nodeManager;
    public SettingsManager settingsManager;
    public PluginManager pluginManager;
    public AlgorithmManager algorithmManager;
    public OutputManager outputManager;
    public UpdateManager updateManager;
    public ResourceManager resourceManager;
    public ProcessManager processManager;
    public LipeServer server;
    public LipeClient client;

    // TODO: Make CLI switch work
    /**
     * Calls another classes main method depending on the command line switch.
     * -g or -gui for the GUI interface
     * -l or -client for the light client interface
     * -u or -update for the automatic updater
     * -c or -commandline for the CLI interface (not working)
     * @param args command line switches
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Missing switch: -gui, -client or -update");
        }
        for (String s : args) {
            if (s.compareTo("-c") == 0 || s.compareTo("-commandline") == 0) {
                CLI.main(args);
                break;
            }
            if (s.compareTo("-g") == 0 || s.compareTo("-gui") == 0) {
                GUI.main(args);
                break;
            }
            if (s.compareTo("-l") == 0 || s.compareTo("-client") == 0) {
                LightClient.main(args);
                break;
            }
            if (s.compareTo("-u") == 0 || s.compareTo("-update") == 0) {
                Updater.main(args);
                break;
            }
        }
    }

    /**
     * Creates the main DGA system and managers and sets the user interface
     * @param userInterface
     */
    public DGA(UserInterface userInterface) {
        this.userInterface = userInterface;

        //Register this as the exception handler
        registerExceptionHandler();

        //RMI
        server = new LipeServer(this);
        client = new LipeClient(this);

        //Non user managed (does not show up in tree in GUI)
        settingsManager = new SettingsManager(this);
        updateManager = new UpdateManager(this);
        resourceManager = new ResourceManager(this);

        //User managed (shows up in tree in GUI)
        add(nodeManager = new NodeManager(this));
        add(pluginManager = new PluginManager(this));
        add(algorithmManager = new AlgorithmManager(this));
        add(outputManager = new OutputManager(this));
        add(processManager = new ProcessManager(this));
    }

    /**
     * Runs the automatic update program and closes the application.
     */
    @Override
    public void update() {
        try {
            Runtime.getRuntime().exec("Updater.exe");
            System.exit(0);
        } catch (IOException ex) {
            userInterface.handleException(ex);
        }
    }

    /**
     * Adds a manager to the container.
     * @param m the manager to add
     */
    public void add(Manager m) {
        managers.add(m);
    }

    /**
     * Removes a manager from the container.
     * @param m the manager to remove
     */
    @Override
    public void remove(Manager m) {
        managers.remove(m);
    }

    /**
     * Gets the container.
     * @return the ArrayList of Manager objects
     */
    public ArrayList<Manager> get() {
        return managers;
    }

    /**
     * Gets the Manager at the index.
     * @param i the index of the Manager to get
     * @return a Manager object
     */
    @Override
    public Manager get(int i) {
        return managers.get(i);
    }

    /**
     * Returns the amount of Managers in the list.
     * @return the amount of Managers in the list
     */
    @Override
    public int size() {
        return managers.size();
    }

    /**
     * Attempts the catch any uncaught exceptions throwen on any thread at any time.
     * @param t the current thread when the exception was throwen
     * @param e the exception
     */
    public void uncaughtException(Thread t, Throwable e) {
        try {
            userInterface.handleError("Thread " + t + " caused an exception: " + e.toString());
            if (!(e instanceof OutOfMemoryError)) {
                userInterface.handleException(e);
            }
        } catch (Throwable ex) {            // don't let the exception get thrown out, will cause infinite looping!
        }
    }

    /**
     * Registers the object as the default uncaught exception handler.
     */
    public void registerExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler(this);
        System.setProperty("sun.awt.exception.handler", DGA.class.getName());
    }
}
