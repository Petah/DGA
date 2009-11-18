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

import dataanalyzer.entity.AlgorithmProcess;
import dataanalyzer.entity.DataInt;
import dataanalyzer.entity.Output;
import dataanalyzer.entity.SequenceData;
import dataanalyzer.exception.InvalidDataTypeException;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author Petah
 */
public class UsageProcess extends AlgorithmProcess {

    private SequenceData data;
    private int i = 0;
    private HashMap<Byte, Integer> count = new HashMap<Byte, Integer>();

    public UsageProcess(Output output) {
        data = (SequenceData) output.getOutput();
        for (byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; b++) {
            count.put(b, 0);
        }
        count.put(Byte.MAX_VALUE, 0);
        setOutputName("Usage analysis of " + output.toString());
    }

    @Override
    public boolean cycle() {
        if (i < data.size()) {
            try {
                Byte b = data.getSingle(i++).getByte();
                count.put(b, count.get(b) + 1);
                return true;
            } catch (InvalidDataTypeException ex) {
                system.handleError(ex.getMessage());
            }
        } else {
            SequenceData sequenceCount = new SequenceData();
            for (byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; b++) {
                DataInt dataInt = new DataInt(count.get(b));
                sequenceCount.addSingle(dataInt);
            }
            DataInt dataInt = new DataInt(count.get(Byte.MAX_VALUE));
            sequenceCount.addSingle(dataInt);
            output = sequenceCount;
        }
        return false;
    }
}
