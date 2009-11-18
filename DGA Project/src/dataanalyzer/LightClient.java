package dataanalyzer;

import dataanalyzer.data.*;
import dataanalyzer.util.Date;
import dataanalyzer.entity.Output;
import dataanalyzer.entity.Setting;
import dataanalyzer.gui.PLightClient;
import dataanalyzer.listeners.SystemListener;
import java.awt.AWTException;
import java.awt.Frame;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JFrame;

/**
 * This class is a cut down GUI for use on client computers.
 * @author David Neilsen
 */
public class LightClient extends UserInterface {

    /** The main DGA object */
    private DGA system;
    /** All the current output including HTML formating but not the primary tags */
    private String output = "";
    /** The main panel to display */
    private PLightClient lightClient;

    /**
     * Creates a new instance of the LightClient class
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        new LightClient();
    }

    /**
     * Creates the DGA instance and loads settings.
     */
    public LightClient() {
        system = new DGA(this);

        //Load settings
        DSettings.load(system, new File("settings.xml"));

        //Set verbosity
        Setting<Integer> verbosity = system.settingsManager.find("verbosity");
        setVerbosity(verbosity.value);
        //Create frame
        final JFrame fmain = new JFrame();
        fmain.setTitle("DGA Light Client");
        fmain.add(lightClient = new PLightClient(system));
        fmain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fmain.setIconImage(DImage.getFrameIcon(system));
        fmain.pack();

        if (SystemTray.isSupported()) {
            final SystemTray tray = SystemTray.getSystemTray();
            final Image image = DImage.getFrameIcon(system);
            final TrayIcon trayIcon = new TrayIcon(image, "DGA Light Client");

            trayIcon.setImageAutoSize(true);
            trayIcon.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    fmain.setState(Frame.NORMAL);
                    fmain.setVisible(true);
                    tray.remove(trayIcon);
                }
            });

            fmain.addWindowListener(new WindowAdapter() {

                @Override
                public void windowIconified(WindowEvent e) {
                    try {
                        fmain.setVisible(false);
                        tray.add(trayIcon);
                    } catch (AWTException ex) {
                        system.userInterface.handleError("TrayIcon could not be added. " + ex.getMessage());
                    }
                }
            });

            try {
                tray.add(trayIcon);
            } catch (AWTException ex) {
                system.userInterface.handleError("TrayIcon could not be added. " + ex.getMessage());
            }

        }

        // Add window listener to save settings on close
        fmain.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                DSettings.save(system, new File("settings.xml"));
            }
        });

        //Load plugins
//        DPlugin.loadAll(system);

        out("System started at " + Date.now());

    //system.server.start((Integer) port.value);
//        system.client.connect();
    }

    /**
     * This method registers a listener to collect an output (that will be selected
     * from a list) object and send it to the SystemListener instance.
     * @param systemListener the instacnce wanting to collect output
     */
    public void collectOutput(SystemListener systemListener) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Sends output for use by DGA.
     * @param output the output to be added to the output manager
     */
    public void sendOutput(Output output) {
        throw new UnsupportedOperationException("Not supported yet.");
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
        ex.printStackTrace();
        System.out.print("WFT");
        System.exit(4);
    }

    /**
     * Outputs an object to the GUI.
     * @param o the object to output
     */
    public void out(Object o) {
        output += o.toString() + "<br />";
        if (lightClient != null) {
            lightClient.textPane.setText("<html><font face=\"Tahoma\" size=\"3\">" + output + "</font></html>");
        }
    }
}