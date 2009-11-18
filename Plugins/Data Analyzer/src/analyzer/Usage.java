/*
 * Classname
 * 
 * Version information
 *
 * Date
 * 
 * Copyright notice
 */
package analyzer;

import dataanalyzer.entity.Algorithm;
import dataanalyzer.entity.AlgorithmProcess;
import dataanalyzer.entity.Output;
import dataanalyzer.entity.SequenceData;
import dataanalyzer.gui.IAlgorithmPanel;

/**
 *
 * @author Petah
 */
public class Usage extends Algorithm {

    private Output ouput;

    @Override
    public IAlgorithmPanel getGUI() {
        return new PUsage(this);
    }

    @Override
    public void collectInput(Object o) {
        if (o instanceof Output) {
            ouput = (Output) o;
        }
    }

    @Override
    public AlgorithmProcess createProcess() {
        return new UsageProcess(ouput);
    }

    @Override
    public void dispose() {
        ouput = null;
    }

    @Override
    public String getName() {
        return "Usage Analyzer";
    }
}
