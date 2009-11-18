package dataanalyzer.entity;

import dataanalyzer.interfaces.ISystem;
import dataanalyzer.listeners.ProcessListener;
import dataanalyzer.listeners.CompletionListener;
import dataanalyzer.listeners.OutputListener;
import dataanalyzer.listeners.ProgressListener;
import java.io.Serializable;
import java.util.LinkedHashSet;

/**
 * This abstract class provides the base code for processing an algorithm. The bulk 
 * of the code should be called from inside the cycle() method. The cycle() method 
 * will constantly be called untill the process it requested to stop or the cycle()
 * method returns false, thus providing a controlled loop structure.
 * @author David Neilsen
 */
public abstract class AlgorithmProcess implements Runnable, Serializable {

    /** List of ProgressListeners to be called on the AlgorithmProcess request */
    protected transient LinkedHashSet<ProgressListener> progressListeners = new LinkedHashSet<ProgressListener>();
    /** List of CompletionListener to be called when the AlgorithmProcess completes */
    protected transient LinkedHashSet<CompletionListener> completionListeners = new LinkedHashSet<CompletionListener>();
    /** List of ProcessListener to be called on the AlgorithmProcess request */
    protected transient LinkedHashSet<ProcessListener> processListeners = new LinkedHashSet<ProcessListener>();
    /** List of OutputListener to be called on the AlgorithmProcess request */
    protected transient LinkedHashSet<OutputListener> outputListeners = new LinkedHashSet<OutputListener>();
    /** Flags if the AlgorithmProcess is running */
    protected transient boolean running;
    /** Flags the AlgorithmProcess to stop before the next cycle() call */
    protected transient boolean abort;
    /** The AlgorithmProcess's main output object */
    protected transient Object output;
    /** This provides interaction with the main system but keep dependancys to a minimum */
    protected transient ISystem system;
    /** The name of the output */
    protected String outputName;
    /** The AlgorithmProcess ID (not used/working correctly, was require for networking) */
    private long id = -1;

    /**
     * Runs the AlgorithmProcess by repeatedly calling the cycle() method until 
     * its returns false, or the stop() method is called.
     */
    public void run() {
        running = true;
        abort = false;
        while (running && !abort) {
            running = cycle();
        }
        if (completionListeners != null) {
            for (CompletionListener c : completionListeners) {
                c.complete(this);
            }
        }
    }

    /**
     * Calls the AlgorithmProcess to stop when finished the next cycle.
     */
    public void stop() {
        abort = true;
    }

    /**
     * Excutes one cycle of the process
     * @return True if the process needs to run another cycle, otherwise false
     */
    public abstract boolean cycle();

    /**
     * Gets the output from the AlgorithmProcess.
     * @return an instance of Output
     */
    public Output getOutput() {
        return new Output(output, this, outputName);
    }

    @Override
    public String toString() {
        String string = "Process";
        if (outputName != null) {
            string = outputName;
        }
        if (id != -1) {
            string += ": " + id;
        }
        return string;
    }

    /**
     * Creates all listener lists.
     */
    public void createListeners() {
        progressListeners = new LinkedHashSet<ProgressListener>();
        completionListeners = new LinkedHashSet<CompletionListener>();
        processListeners = new LinkedHashSet<ProcessListener>();
        outputListeners = new LinkedHashSet<OutputListener>();
    }

    /**
     * Signals all OutputListeners that new output has been created and give the 
     * output the default name.
     * @param object the object to output
     */
    protected void sendOutput(Object object) {
        sendOutput(object, outputName);
    }

    /**
     * Signals all OutputListeners that new output has been created.
     * @param object the object to output
     * @param name the name of the output
     */
    protected void sendOutput(Object object, String name) {
        for (OutputListener o : outputListeners) {
            o.collectOutput(new Output(object, this, name));
        }
    }

    /**
     * Signals all ProcessListener a new AlgorithmProcess has been created.
     * @param algorithmProcess the AlgorithmProcess that was created
     */
    protected void sendProcess(AlgorithmProcess algorithmProcess) {
        for (ProcessListener p : processListeners) {
            p.collectAlgorithmProcess(algorithmProcess);
        }
    }

    /**
     * Signals an update to all ProgressListener objects.
     * @param value the new progress level (0-100)
     * @param label the current progress status
     */
    protected void progressUpdate(int value, String label) {
        if (progressListeners == null) {
            createListeners();
        }
        for (ProgressListener p : progressListeners) {
            p.update(value, label);
        }
    }

    /**
     * Adds a CompletionListener object to the AlgorithmProcess. 
     * @param c the CompletionListener to add
     */
    public void addCompletionListener(CompletionListener c) {
        if (completionListeners == null) {
            createListeners();
        }
        completionListeners.add(c);
    }

    /**
     * Adds a ProgressListener object to the AlgorithmProcess. 
     * @param p the ProgressListener to add
     */
    public void addProgressListener(ProgressListener p) {
        if (progressListeners == null) {
            createListeners();
        }
        progressListeners.add(p);
    }

    /**
     * Adds a ProcessListener object to the AlgorithmProcess. 
     * @param a the ProcessListener to add
     */
    public void addAlgorithmProcessListener(ProcessListener a) {
        if (processListeners == null) {
            createListeners();
        }
        processListeners.add(a);
    }

    /**
     * Adds a OutputListener object to the AlgorithmProcess. 
     * @param o the OutputListener to add
     */
    public void addOutputListener(OutputListener o) {
        if (outputListeners == null) {
            createListeners();
        }
        outputListeners.add(o);
    }

    public void setOutputName(String outputName) {
        this.outputName = outputName;
    }

    public void setSystem(ISystem system) {
        this.system = system;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
