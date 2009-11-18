/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lzw;

import compression.util.CompressionTask;
import dataanalyzer.entity.Algorithm;
import dataanalyzer.entity.AlgorithmProcess;
import dataanalyzer.entity.Data;
import dataanalyzer.entity.DataByte;
import dataanalyzer.entity.Output;
import dataanalyzer.entity.SequenceData;
import dataanalyzer.exception.InvalidDataTypeException;
import dataanalyzer.gui.IAlgorithmPanel;
import java.io.*;

/**
 *
 * @author davnei06
 */
public class LZW extends Algorithm {

    private SequenceData inData;
    private CompressionTask type;


    @Override
    public IAlgorithmPanel getGUI() {
        return new PLZW(this);
    }

    @Override
    public void collectInput(Object o) {
        if (o instanceof CompressionTask) {
            type = (CompressionTask) o;
        } //        } else if (o instanceof String) {
        //            if (inFile == null) {
        //                inFile = new File((String) o);
        //            } else if (outFile == null) {
        //                outFile = new File((String) o);
        //            }
        else if (o instanceof Output) {
            Output out = (Output) o;
            if (out.getOutput() instanceof SequenceData) {
                inData = (SequenceData) out.getOutput();
            }
        } else {
            throw new IllegalArgumentException("Object not supported (" + o.getClass() + ")");
        }
    }

    @Override
    public AlgorithmProcess createProcess() {
        return new LZWProcess(inData, type);
    }
//    @Override
//    public Output getOutput() {
//        return output;
//    }
    @Override
    public void dispose() {
        inData = null;
        type = null;
    }

    public String getName() {
        return "LZW";
    }

    @Override
    public String toString() {
        return getName();
    }
}






