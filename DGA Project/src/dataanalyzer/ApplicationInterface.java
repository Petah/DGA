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

import dataanalyzer.data.DImage;
import dataanalyzer.data.DPlugin;
import dataanalyzer.data.DSettings;
import dataanalyzer.entity.Algorithm;
import dataanalyzer.util.Date;
import dataanalyzer.entity.Output;
import dataanalyzer.entity.Setting;
import dataanalyzer.gui.FMain;
import dataanalyzer.gui.POutputList;
import dataanalyzer.listeners.SystemListener;
import dataanalyzer.manager.GUIManager;
import dataanalyzer.util.LookAndFeelSetter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JOptionPane;

/**
 * This class is intended to plug in to existing Java applications and capture 
 * output to use with DGA.
 * @author David Neilsen
 */
// TODO: Check the application interface is working correctly
public class ApplicationInterface extends UserInterface {

    public DGA system;
    public String output;
    public GUIManager gui;

    /**
     * Entry point for the program, creates a new instance of the GUI
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        new GUI();
    }

    /**
     * Creates a new instance of the application interface, loads settings and 
     * plugins, creates and shows the GUI.
     */
    public ApplicationInterface() {
        system = new DGA(this);
        gui = new GUIManager(system);
        output = "";

        //Load settings
        DSettings.load(system, new File("settings.xml"));
        //Load plugins
        DPlugin.loadAll(system);

        //Create frame
        gui.fmain = new FMain(gui);
        gui.fmain.setIconImage(DImage.getFrameIcon(system));

        //Set look and feel
        Setting<String> lookAndFeel = system.settingsManager.find("lookAndFeel");
        if (lookAndFeel != null) {
            LookAndFeelSetter.parse(lookAndFeel.value, gui);
        }

        //Make frame visible
        gui.fmain.setVisible(true);

        //Add listeners to update the tree
        system.algorithmManager.addUpdateListener(gui.fmain);
        system.outputManager.addUpdateListener(gui.fmain);
        system.nodeManager.addUpdateListener(gui.fmain);
        system.pluginManager.addUpdateListener(gui.fmain);
        system.processManager.addUpdateListener(gui.fmain);

        out("System started at " + Date.now());

    }

    /**
     * Adds an algorithm to the system.
     * @param algorithm the algorithm to be added
     */
    public void addAlgorithm(Algorithm algorithm) {
        system.algorithmManager.add(algorithm);
    }

    /**
     * Sends output for use by DGA.
     * @param output the output to be added to the output manager
     */
    public void sendOutput(Output output) {
        system.outputManager.add(output);
    }

    /**
     * This method registers a listener to collect an output (that will be selected
     * from a list) object and send it to the SystemListener instance.
     * @param systemListener the instacnce wanting to collect output
     */
    public void collectOutput(final SystemListener systemListener) {
        POutputList.createDialog(gui, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                systemListener.collectObject(e.getSource());
            }
        });
    }

    /**
     * Displays an error in the GUI.
     * @param s the error message
     */
    public void handleError(String s) {
        out("<font color=\"Maroon\">" + s + "</font>");
    }

    /**
     * Handles an exception and give the user a options to abort the program or
     * continue and display the error.
     * @param ex the exception to handle
     */
    public void handleException(Throwable ex) {
        // TODO: Add error reporting here
        int returnValue = JOptionPane.showConfirmDialog(gui.fmain, "An exception has occured!\n" + ex.toString() + "\nDo you wish to continue?", "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (returnValue == JOptionPane.YES_OPTION) {

            handleError(ex.toString());
            for (StackTraceElement element : ex.getStackTrace()) {
                handleError(element.toString(), UserInterface.MIN);
            }
        } else if (returnValue == JOptionPane.NO_OPTION) {
            ex.printStackTrace();
            System.exit(2);
        } else if (returnValue == JOptionPane.CANCEL_OPTION) {
            //Button is diabled
        } else if (returnValue == JOptionPane.CLOSED_OPTION) {
            ex.printStackTrace();
            System.exit(3);
        }

    }

    /**
     * Outputs an object to the GUI.
     * @param o the object to output
     */
    public void out(Object o) {
        if (o != null) {
            output += o.toString() + "<br />";
        }
        if (gui.fmain != null && gui.fmain.getConsole() != null) {
            gui.fmain.getConsole().getTextPane().setText("<html><font face=\"Tahoma\" size=\"3\">" + output + "</font></html>");
            gui.fmain.getConsole().scrollToBottom();
        }
    }

    /**
     * Outputs an object to the GUI (if the verbosity level is above the supplied 
     * level and colors it depending on the verbosity.
     * @param o the object to output
     * @param verbosity only outputs if this is less than or equal to the user
     * interface verbosity level
     */
    @Override
    public void out(Object o, int verbosity) {
        if (o != null) {
            if (verbosity <= system.userInterface.getVerbosity()) {
                switch (verbosity) {
                    case MAX: {
                        out("<font color=\"Blue\" size=\"2\">" + o.toString() + "</font>");
                        break;
                    }
                    case ALL: {
                        out("<font color=\"Gray\" size=\"1\">" + o.toString() + "</font>");
                        break;
                    }
                    default: {
                        out(o);
                    }
                }
            }
        }
    }
}
