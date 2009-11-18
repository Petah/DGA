package dataanalyzer.data;

import dataanalyzer.UserInterface;
import dataanalyzer.entity.Setting;
import dataanalyzer.manager.SettingsManager;
import javax.swing.JFrame;

/**
 * This class sets all the default settings.
 * @author David Neilsen
 */
public class DDefaultSettings {

    /**
     * Sets the default settings.
     * @param settingsManager the SettingsManager object to hold the settings
     */
    public static void setDefaults(SettingsManager settingsManager) {

        settingsManager.add(new Setting<Integer>("memoryGraphSize", 60, "Integer"));
        settingsManager.add(new Setting<Integer>("verbosity", UserInterface.MIN, "Integer"));
        //Directorys
        settingsManager.add(new Setting<String>("algorithmDirectory", "plugins\\extracted\\", "String"));
        settingsManager.add(new Setting<String>("pluginDirectory", "plugins\\", "String"));
        settingsManager.add(new Setting<String>("updateDirectory", "updates\\", "String"));

        //Update
        settingsManager.add(new Setting<String>("updateServer", "http://localhost/dga/", "String"));
        settingsManager.add(new Setting<String>("updateFile", "update.xml", "String"));

        //Frames
        settingsManager.add(new Setting<Boolean>("frameOpen:Console", true, "Boolean"));
        settingsManager.add(new Setting<Boolean>("frameOpen:Resource Monitor", true, "Boolean"));
        settingsManager.add(new Setting<Integer>("windowState", JFrame.MAXIMIZED_BOTH, "Integer"));
        settingsManager.add(new Setting<String>("lookAndFeel", "Windows", "String"));

        //Network
        settingsManager.add(new Setting<String>("hostName", "localhost", "String"));
        settingsManager.add(new Setting<Integer>("serverPort", 4055, "Integer"));
        settingsManager.add(new Setting<Integer>("returnPort", 4056, "Integer"));
    }
}
