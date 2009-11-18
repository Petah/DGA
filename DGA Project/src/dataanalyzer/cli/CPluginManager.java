package dataanalyzer.cli;

import dataanalyzer.DGA;
import dataanalyzer.entity.Plugin;

public class CPluginManager {

    public static void printPlugins(DGA system) {
        for (Plugin p : system.pluginManager.get()) {
            system.userInterface.out(p);
        }
    }
}
