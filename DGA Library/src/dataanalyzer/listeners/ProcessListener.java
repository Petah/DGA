package dataanalyzer.listeners;

import dataanalyzer.entity.AlgorithmProcess;

/**
 * Used by an AlgorithmProcess to signal that the AlgorithmProcess wants to create
 * another AlgorithmProcess.
 * @author David Neilsen
 */
public interface ProcessListener {
    /**
     * Signals that an Object has created an AlgorithmProcess
     * @param algorithmProcess the new AlgorithmProcess
     */
    public void collectAlgorithmProcess(AlgorithmProcess algorithmProcess);
}
