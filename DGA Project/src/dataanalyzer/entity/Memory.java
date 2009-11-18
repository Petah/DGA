package dataanalyzer.entity;

import dataanalyzer.util.Format;

/**
 * This class is used to get information about the current memory state.
 * @author David Neilsen
 */
public class Memory {

    /** The amount of free memory (in bytes) */
    public long free;
    /** The amount of used memory (in bytes) */
    public long used;
    /** The amount of allocated memory (in bytes) */
    public long allocated;
    /** The maximum amount of memory (in bytes) */
    public long maximum;

    /**
     * Gets a formatted String of the amount of free memory.
     * @return a formatted String of the amount of free memory
     */
    public String getFree() {
        return Format.bytes(free);
    }

    /**
     * Gets a formatted String of the amount of used memory.
     * @return a formatted String of the amount of used memory
     */
    public String getUsed() {
        return Format.bytes(used);
    }

    /**
     * Gets a formatted String of the amount of allocated memory.
     * @return a formatted String of the amount of allocated memory
     */
    public String getAllocated() {
        return Format.bytes(allocated);
    }

    /**
     * Gets a formatted String of the maximum amount of memory.
     * @return a formatted String of the maximum amount of memory
     */
    public String getMaximum() {
        return Format.bytes(maximum);
    }

    /**
     * Updates the current memory state
     */
    public void update() {
        // The amount of memory allocated so far (usually the -Xms setting)
        allocated = Runtime.getRuntime().totalMemory();

        // Free memory out of the amount allocated (value above minus used)
        free = Runtime.getRuntime().freeMemory();

        //Calculate the used memory
        used = allocated - free;

        // The maximum amount of memory that can eventually be consumed
        // by this application. This is the value set by the Preferences
        // dialog box to increase the memory settings for an application.
        maximum = Runtime.getRuntime().maxMemory();
    }
}