package dataanalyzer.listeners;

import dataanalyzer.entity.Output;

/**
 * Used by AlgorithmProcess to signal listeners that new Output has been created.
 * @author David Neilsen
 */
public interface OutputListener {
    /**
     * Signals that new Output has been created.
     * @param output the new Output
     */
    public void collectOutput(Output output);
}
