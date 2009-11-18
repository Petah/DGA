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
// TODO: Make this algorithm
import dataanalyzer.gui.IAlgorithmPanel;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 *
 * @author David Neilsen
 */
public class DirectoryLoader extends Algorithm {

    /** The directory to be read */
    private File directory;


    /**
     * Gets the GUI for the algorthim.
     * @return an instance of IAlgorithmPanel
     */
    @Override
    public IAlgorithmPanel getGUI() {
        return new PDirectoryLoader(this);
    }

    /**
     * Collects input from the algorithms GUI.
     * @param o the object to collect
     */
    @Override
    public void collectInput(Object o) {
        if (o instanceof File && directory == null) {
            directory = (File) o;
        }
    }

    /**
     * Creates a FileLoaderProcess from using the collected objects.
     * @return an instance of FileLoaderProcess 
     */
    @Override
    public AlgorithmProcess createProcess() {
        return new DirectoryLoaderProcess(directory);
    }

    /**
     * Signal to dispose of any related variables the algorithm holds referance to.
     */
    @Override
    public void dispose() {
        directory = null;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "Directory Loader";
    }
}
class DirectoryLoaderProcess extends AlgorithmProcess {

    private ArrayList<File> files = new ArrayList<File>();
    private int i = 0;

    public DirectoryLoaderProcess(File directory) {
        scanDirectory(directory);
    }

    private void scanDirectory(File directory) {
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                scanDirectory(file);
            } else {
                files.add(file);
            }
        }
    }

    @Override
    public boolean cycle() {
        if (i < files.size()) {
            FileLoaderProcess process = new FileLoaderProcess(files.get(i++));
            sendProcess(process);
            return true;
        } else {
            return false;
        }
    }
}