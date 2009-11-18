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
import dataanalyzer.entity.DataByte;
import dataanalyzer.entity.SequenceData;
import dataanalyzer.gui.IAlgorithmPanel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;

/**
 * Algorithm to read a file into a SequenceData object.
 * @author David Neilsen
 */
public class FileLoader extends Algorithm {

    /** The file to be read */
    private File file;

    /**
     * Gets the GUI for the algorthim.
     * @return an instance of IAlgorithmPanel
     */
    @Override
    public IAlgorithmPanel getGUI() {
        return new PFileLoader(this);
    }

    /**
     * Collects input from the algorithms GUI.
     * @param o the object to collect
     */
    @Override
    public void collectInput(Object o) {
        if (o instanceof File && file == null) {
            file = (File) o;
        }
    }

    /**
     * Creates a FileLoaderProcess from using the collected objects.
     * @return an instance of FileLoaderProcess 
     */
    @Override
    public AlgorithmProcess createProcess() {
        return new FileLoaderProcess(file);
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
        return "File Loader";
    }
}

class FileLoaderProcess extends AlgorithmProcess {

    private InputStream inputStream;
    private long length;
    private byte[] bytes;
    private int offset;
    private int numRead;
    private Stage stage;
    private SequenceData data;
    private File file;

    private enum Stage {

        READING, CONVERTING
    }

    public FileLoaderProcess(File file) {
        this.file = file;
        try {
            inputStream = new FileInputStream(file);
            // Get the size of the file
            length = file.length();
            if (length > Integer.MAX_VALUE) {
                // File is too large
                throw new IOException("File is too large " + file.getName());
            }
            bytes = new byte[(int) length];
            offset = numRead = 0;
            stage = Stage.READING;
            data = new SequenceData();
            setOutputName(file.getName());
        } catch (Exception ex) {
            system.handleException(ex);
        }
    }

    @Override
    public boolean cycle() {
        try {
            switch (stage) {
                case READING:
                    if (offset < bytes.length) {
                        numRead = inputStream.read(bytes, offset, bytes.length - offset);
                        if (numRead >= 0) {
                            offset += numRead;
                        } else {
                            inputStream.close();
                            stage = Stage.CONVERTING;
                        }
                    } else {
                        inputStream.close();
                        stage = Stage.CONVERTING;
                    }
                    break;
                case CONVERTING:
                    for (Byte b : bytes) {
                        DataByte dataByte = new DataByte(b);
                        data.addSingle(dataByte);
                    }
                    output = data;
                    return false;
            }
            // Ensure all the bytes have been read in
            if (offset < bytes.length) {
                throw new IOException("Could not completely read file " + file.getName());
            }
        } catch (Exception ex) {
            system.handleException(ex);
            return false;
        }
        return true;
    }
}