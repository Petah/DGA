package dataanalyzer.cli;

import dataanalyzer.DGA;
import dataanalyzer.manager.UpdateManager;
import dataanalyzer.entity.Update;
import java.util.ArrayList;

public class CUpdateManager {

    public static void printUpdates(DGA system) {
        for (Update update : system.updateManager.get()) {
            system.userInterface.out(update);
        }
    }
}
