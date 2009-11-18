package dataanalyzer.util;

import ch.randelshofer.quaqua.QuaquaLookAndFeel;
import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import dataanalyzer.manager.GUIManager;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 * This class safely sets the look and feel while the application is running. This
 * class is an instance of Runnable so it can be called by SwingUtilities.invokeLater()
 * to prevent causing errors.
 * @author David Neilsen
 */
public class LookAndFeelSetter implements Runnable {

    private GUIManager gui;
    private LookAndFeel lookAndFeel;

    public LookAndFeelSetter(GUIManager gui, LookAndFeel lookAndFeel) {
        this.gui = gui;
        this.lookAndFeel = lookAndFeel;
    }

    /**
     * Converts a look and feel by name to the appropriate class.
     * @param name the name of the look and feel: Windows, Windows (Classic), Apple,
     * Motif or Metal
     * @param gui the main GUIManager object
     */
    public static void parse(String name, GUIManager gui) {
        LookAndFeel lookAndFeel = null;
        if (name.equals("Windows")) {
            lookAndFeel = new WindowsLookAndFeel();
        } else if (name.equals("Windows (Classic)")) {
            lookAndFeel = new WindowsClassicLookAndFeel();
        } else if (name.equals("Apple")) {
            lookAndFeel = new QuaquaLookAndFeel();
        } else if (name.equals("Motif")) {
            lookAndFeel = new MotifLookAndFeel();
        } else if (name.equals("Metal")) {
            lookAndFeel = new MetalLookAndFeel();
        }
        if (lookAndFeel != null) {
            SwingUtilities.invokeLater(new LookAndFeelSetter(gui, lookAndFeel));
        }
    }

    /**
     * Sets the look an feel and updates components. Also temporarily suppresses
     * output in case because the Apple look and feel causes an error on Window systems.
     */
    public void run() {
        //Suppress output because the Apple look and feel causes an error when it cannot find the OS preference file
        SuppressOutput.suppressBoth();
        try {
            UIManager.setLookAndFeel(lookAndFeel);
            SwingUtilities.updateComponentTreeUI(gui.fmain);
        } catch (Exception ex) {
            gui.system.userInterface.handleError(ex.getMessage());
        }
        SuppressOutput.resetBoth();
    }
}
