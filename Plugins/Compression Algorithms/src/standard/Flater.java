/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package standard;

import compression.util.CompressionTask;
import dataanalyzer.entity.Algorithm;
import dataanalyzer.entity.AlgorithmProcess;
import dataanalyzer.entity.Output;
import dataanalyzer.entity.SequenceData;
import dataanalyzer.gui.IAlgorithmPanel;

/**
 *
 * @author davnei06
 */
public class Flater extends Algorithm {

    private CompressionTask task;
    private SequenceData data;

    @Override
    public void collectInput(Object o) {
        if (o instanceof CompressionTask) {
            task = (CompressionTask) o;
        } else if (o instanceof Output) {
            Output output = (Output)o;
            if (output.getOutput() instanceof SequenceData) {
                data = (SequenceData) output.getOutput();
            }
        }
    }

    @Override
    public void dispose() {
        task = null;
    }

    @Override
    public IAlgorithmPanel getGUI() {
        return new PFlater(this);
    }

    @Override
    public AlgorithmProcess createProcess() {
        switch (task) {
            case COMPRESS:
                return new DeflaterProcess(data);
            case DECOMPRESS:
                return new InflaterProcess(data);
        }
        return null;
    }

    @Override
    public String getName() {
        return "De/Inflater";
    }
}
