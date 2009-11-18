package dataanalyzer.manager;

import dataanalyzer.DGA;
import java.util.ArrayList;
import dataanalyzer.entity.Update;
import dataanalyzer.data.DUpdate;
import dataanalyzer.listeners.CompletionListener;
import java.util.Collections;

/**
 * Manages all Update instances and provides delegate calls to DUpdate.
 * @author David Neilsen
 */
// FIXME: when the web server cannot be connected to an exception occurs
public class UpdateManager extends Manager<ArrayList<Update>, Update> {

    private DGA system;
    private DUpdate dUpdate;
    private String version;
    private ArrayList<Update> updates = new ArrayList<Update>();

    /**
     * Initializes object.
     * @param system the main DGA object
     */
    public UpdateManager(DGA system) {
        this.system = system;
        this.dUpdate = new DUpdate(system);
    }

    /**
     * Delegate method for DUpdate.check().
     * @param c a CompletionListener to signal when checking for updates is complete.
     * @see DUpdate#check()
     */
    public void check(CompletionListener c) {
        updates.clear();
        dUpdate.addCompletionListener(c);
        dUpdate.check();
    }

    /**
     * Delegate method for DUpdate.downloadUpdate()
     * @param update the Update to download
     * @see DUpdate#downloadUpdate(dataanalyzer.entity.Update)
     */
    public void downloadUpdate(Update update) {
        dUpdate.downloadUpdate(update);
    }

    /**
     * Downloads all updates held by this UpdateManager
     */
    public void downloadAllUpdates() {
        for (Update u : updates) {
            dUpdate.downloadUpdate(u);
        }
    }

    @Override
    public ArrayList<Update> get() {
        return updates;
    }

    public Update get(int i) {
        return updates.get(i);
    }

    @Override
    public void add(Update u) {
        updates.add(u);
        Collections.sort(updates);
        update();
    }

    @Override
    public void remove(Update u) {
        updates.remove(u);
    }

    public int size() {
        return updates.size();
    }

    /**
     * Indicates if currntly downloading. Delegate for DUpdate.downloading()
     * @return true is currently downloading
     * @see DUpdate#downloading()
     */
    public boolean isChecking() {
        return dUpdate.downloading();
    }

    /**
     * Sets the update server URL. Delegate for DUpdate.setUpdateServerURL()
     * @param s the update server URL
     * @see DUpdate#setUpdateServerURL(java.lang.String)
     */
    public void setUpdateServer(String s) {
        dUpdate.setUpdateServerURL(s);
    }

    @Override
    public String toString() {
        return "Update Manager";
    }

    /**
     * Gets the latest version as indicated by the last Updatemanager.check()
     * @return the latest version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the latest version
     * @param version the latest version
     */
    public void setVersion(String version) {
        this.version = version;
    }
}

