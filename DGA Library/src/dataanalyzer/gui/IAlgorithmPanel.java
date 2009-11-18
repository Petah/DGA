package dataanalyzer.gui;

import dataanalyzer.interfaces.ISystem;

import javax.swing.JPanel;

/**
 * Provides an interface for collecting input for use by an Algorithm.
 * @author David Neilsen
 */
public abstract class IAlgorithmPanel extends JPanel {

    protected ISystem system;

    /**
     * This method should be overriden to collect input from components and send
     * the data to an Algorithm
     */
    public abstract void collectInput();

    /**
     * Sets the system interface.
     * @param system the system interface
     */
    public void setSystem(ISystem system) {
        this.system = system;
    }
}
