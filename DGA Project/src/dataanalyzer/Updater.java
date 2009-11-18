/*
 * Classname
 * 
 * Version information
 *
 * Date
 * 
 * Copyright notice
 */
package dataanalyzer;

import dataanalyzer.manager.SettingsManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class moves and replaces newly downloaded files the starts the DGA application again.
 * @author David Neilsen
 */
// TODO: Make class implement UserInterface
// TODO: Add JOptionPane to show messages
// TODO: Add restart functionality
public class Updater {

    // FIXME: After updating and starting DGA.exe again the plugins do not load
    /**
     * Runs the updater
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            Thread.sleep(2000);
            SettingsManager settingsManager = new SettingsManager(null);
            for (File f : scanDirectory(new File((String) settingsManager.findValue("updateDirectory")))) {
                copyFile(f);
            }
            Thread.sleep(2000);
            Runtime.getRuntime().exec("DGA.exe");
            System.exit(0);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Moves a file from the update directory to the application directory
     * @param file the file to move
     */
    public static void copyFile(File file) {
        //Validate file
        if (copyTo(file) == null) {
            return;
        }
        File newFile = new File(copyTo(file));
        //Delete old file
        if (newFile.exists()) {
            newFile.delete();
        }
        //Check Directorys
        File dir = new File(newFile.getParent());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // Move file to new directory
        boolean success = file.renameTo(newFile);
        if (!success) {
            // File was not successfully moved
            System.out.println("Could not move file: " + file);
        } else {
            System.out.println("Moved file  " + file + " to " + copyTo(file));
        }
    }

    /**
     * Checks the file is not the update.xml file and returns a new file instance 
     * without the update directory included.
     * @param file the file to check
     * @return a new file instance without the update directory included
     */
    public static String copyTo(File file) {
        if (file.toString().equals("updates\\update.xml")) {
            return null;
        }
        return file.toString().substring(8);
    }

    /**
     * Returns a list of file in a directory (including subdirectorys).
     * @param dir the directory to scan
     * @return a list of files
     */
    public static ArrayList<File> scanDirectory(File dir) {
        ArrayList<File> files = new ArrayList<File>();

        File[] children = dir.listFiles();
        if (children == null) {
            // Either dir does not exist or is not a directory
            System.out.println("Either directory does not exist or it is not a directory");
        } else {
            for (int i = 0; i < children.length; i++) {
                // Get filename of file or directory
                if (children[i].isDirectory()) {
                    files.addAll(scanDirectory(children[i]));
                } else if (children[i].isFile()) {
                    files.add(children[i]);
                }
            }
        }

        return files;
    }
}
