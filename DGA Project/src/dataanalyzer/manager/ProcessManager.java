package dataanalyzer.manager;

import dataanalyzer.DGA;
import dataanalyzer.entity.AlgorithmProcess;
import dataanalyzer.listeners.CompletionListener;
import dataanalyzer.listeners.ProcessListener;
import java.util.ArrayList;

/**
 * This class is used to manage all AlgorithmProcess instances within the system.
 * When an CompletionListener is completed (which is checked by implemeting a CompletionListener)
 * the CompletionListener is removed from the manager and the manager updates all 
 * ManagerUpdateListener instances. The manager implements ProcessListener to collect
 * and run any AlgorithmProcess that a currently running AlgorithmProcess creates.
 * @author David Neilsen
 */
public class ProcessManager
        extends Manager<ArrayList<AlgorithmProcess>, AlgorithmProcess>
        implements CompletionListener, ProcessListener {

    /** The main DGA object */
    private DGA system;
    /**
     * The container of AlgorithmProcess instances, which is an ArrayList because random
     * access is required.
     */
    private ArrayList<AlgorithmProcess> processes = new ArrayList<AlgorithmProcess>();

    /**
     * Initializes object.
     * @param system the main DGA object
     */
    public ProcessManager(DGA system) {
        this.system = system;
    }

    /**
     * This method sets up an AlgorithmProcess for running. It adds the AlgorithmProcess
     * to the manager and sets completion listener for the OutputManager and this class.
     * It then creates and starts a new Thread to for the AlgorithmProcess and sets its
     * UncaughtExceptionHandler to the main DGA object.
     * @param p
     */
    public void run(AlgorithmProcess p) {
        add(p);
        p.addCompletionListener(system.outputManager);
        p.addCompletionListener(this);
        Thread thread = new Thread(p);
        // TODO: Check that all thread exceptions are being caught
        thread.setUncaughtExceptionHandler(system);
        thread.start();
    }

    /**
     * This method is called by a running AlgorithmProcess, it signals that the
     * AlgorithmProcess wants to create a new AlgorithmProcess and run it independently.
     * @param algorithmProcess the AlgorithmProcess to run
     */
    public void collectAlgorithmProcess(AlgorithmProcess algorithmProcess) {
        run(algorithmProcess);
    }

    /**
     * This is called by an AlgorithmProcess when it has completed running. It will
     * remove the AlgorithmProcess from the manager.
     * @param o the completed AlgorithmProcess (if the object is not an AlgorithmProcess
     * a ClassCastException will occur)
     */
    public void complete(Object o) {
        remove((AlgorithmProcess) o);
        update();
    }

    /**
     * Adds an AlgorithmProcess to the manager and sets up the AlgorithmProcess's
     * ISystem interface and AlgorithmProcessListener.
     * @param p
     */
    @Override
    public void add(AlgorithmProcess p) {
        if (!processes.contains(p)) {
            p.setSystem(system.userInterface);
            p.addAlgorithmProcessListener(this);
            processes.add(p);
            update();
        }
    }

    /**
     * Gets the list of AlgorithmProcess instances.
     * @return an ArrayList of AlgorithmProcess instances
     */
    @Override
    public ArrayList<AlgorithmProcess> get() {
        return processes;
    }

    /**
     * Removes an AlgorithmProcess from the list.
     * @param u the AlgorithmProcess to remove
     */
    @Override
    public void remove(AlgorithmProcess u) {
        processes.remove(u);
    }

    /**
     * Gets a AlgorithmProcess at the specified index of the list.
     * @param i the AlgorithmProcess index
     * @return an AlgorithmProcess
     */
    @Override
    public AlgorithmProcess get(int i) {
        return processes.get(i);
    }

    /**
     * Gets the amount of AlgorithmProcess instances in the list.
     * @return the amount of AlgorithmProcess instances
     */
    @Override
    public int size() {
        return processes.size();
    }

    @Override
    public String toString() {
        return "Processes";
    }
}
