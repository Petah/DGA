package dataanalyzer.manager;

import dataanalyzer.DGA;
import dataanalyzer.gui.FMain;

/**
 * This class is used by all GUI instances (such as frames and panels) to referance 
 * both the main DGA object and the FMain frame.
 * @author David Neilsen
 */
public class GUIManager {
    // TODO: Make all GUI classes use GUIManager instead of DGA and FMain
    /** The main DGA object */
    public DGA system;
    /** The main FMaim frame object */
    public FMain fmain;


    /**
     * Initializes object.
     * @param system the main DGA object
     */
    public GUIManager(DGA system) {
        this.system = system;
    }

    /**
     * Sets the main FMaim frame object.
     * @param fmain the main FMaim frame object
     */
    public void setFmain(FMain fmain) {
        this.fmain = fmain;
    }
}
