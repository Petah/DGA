package dataanalyzer.manager;

import dataanalyzer.DGA;
import dataanalyzer.UserInterface;
import dataanalyzer.data.DDefaultSettings;
import dataanalyzer.entity.Setting;
import java.util.HashMap;

/**
 * Manages all Setting instances in the system.
 * @author David Neilsenv
 */
public class SettingsManager extends Manager<HashMap<String, Setting>, Setting> {

    private DGA system;
    private HashMap<String, Setting> settings = new HashMap<String, Setting>();

    /**
     * Initializes object and loads default settings.
     * @param system the main DGA object
     * @see DDefaultSettings#setDefaults(dataanalyzer.manager.SettingsManager)
     */
    public SettingsManager(DGA system) {
        this.system = system;
        DDefaultSettings.setDefaults(this);
    }

    /**
     * Adds a Setting to the manager. Outputs the setting if the current verbositiy
     * is >= UserInterface.ALL
     * @param s the Setting to add
     * @see UserInterface#getVerbosity()
     * @see UserInterface#setVerbosity(int)
     */
    public void add(Setting s) {
        settings.put(s.name, s);
        if (system != null) {
            system.userInterface.out(s, UserInterface.ALL);
        }
        update();
    }

    @Override
    public Setting get(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int size() {
        return settings.size();
    }


    /**
     * Finds and returns the setting with the supplied name or null if the
     * setting does not exist.
     * @param s the name of the setting
     * @return a Setting instance or null
     */
    public Setting find(String s) {
        Setting r = settings.get(s);
        return r;
    }

    /**
     * Finds the value of a setting
     * @param s the setting name
     * @return the setting value as an Object or null if the setting was not found
     */
    public Object findValue(String s) {
        Setting setting = find(s);
        if (setting == null) {
            return null;
        }
        return setting.value;
    }

    private String getClassName(Object o) {
        String[] a = o.getClass().getName().split(".");
        if (a.length > 0) {
            return a[a.length - 1];
        }
        return o.getClass().getName();
    }

    @Override
    public void remove(Setting s) {
        settings.remove(s.name);
        update();
    }

    public HashMap<String, Setting> get() {
        return settings;
    }
}
