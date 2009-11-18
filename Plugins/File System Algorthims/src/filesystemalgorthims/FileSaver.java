/*
 * Classname
 * 
 * Version information
 *
 * Date
 * 
 * Copyright notice
 */
package filesystemalgorthims;

import dataanalyzer.entity.Algorithm;
import dataanalyzer.entity.AlgorithmProcess;
import dataanalyzer.entity.Output;
import dataanalyzer.entity.SequenceData;
import dataanalyzer.exception.InvalidDataTypeException;
import dataanalyzer.gui.IAlgorithmPanel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Algorithm to write a SequenceData to a file.
 * @author David Neilsen
 */
public class FileSaver extends Algorithm {

    /** The file to write the data to */
    private File file;
    /** The output to get the data from */
    private Output output;

    /**
     * Gets the GUI for the algorthim.
     * @return an instance of IAlgorithmPanel
     */
    @Override
    public IAlgorithmPanel getGUI() {
        return new PFileSaver(this);
    }

    /**
     * Collects input from the algorithms GUI.
     * @param o the object to collect
     */
    @Override
    public void collectInput(Object o) {
        if (o instanceof File && file == null) {
            file = (File) o;
        } else if (o instanceof Output) {
            output = (Output) o;
        }
    }

    /**
     * Creates a FileSaverProcess from using the collected objects.
     * @return an instance of FileSaverProcess 
     */
    @Override
    public AlgorithmProcess createProcess() {
        return new FileSaverProcess(file, output);
    }

    /**
     * Signal to dispose of any related variables the algorithm holds referance to.
     */
    @Override
    public void dispose() {
        file = null;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "File Saver";
    }
}

class FileSaverProcess extends AlgorithmProcess {

    private OutputStream outputStream;
    private int offset;
    private SequenceData data;
    private File file;

    public FileSaverProcess(File file, Output output) {
        this.file = file;
        try {
            System.out.println(file);
            System.out.println(output);
            outputStream = new FileOutputStream(file);
            offset = 0;
            if (output.getOutput() instanceof SequenceData) {
                data = (SequenceData) output.getOutput();
            } else {
                throw new InvalidDataTypeException("Cannot save output, invalid data.");
            }
        } catch (Exception ex) {
            system.handleException(ex);
        }
    }

    @Override
    public boolean cycle() {
        try {
            if (offset >= data.size()) {
                return false;
            }
            Byte b = data.getSingle(offset++).getByte();
            outputStream.write(b);
            return true;
        } catch (Exception ex) {
            system.handleException(ex);
            return false;
        }
    }
}