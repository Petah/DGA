package dataanalyzer.data;

import dataanalyzer.DGA;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Class to load the applications icon.
 * @author David Neilsen
 */
public class DImage {

    /**
     * Gets the program icon.
     * @param system the main DGA object
     * @return the program icon
     */
    public static Image getFrameIcon(DGA system) {
        try {
            return ImageIO.read(new File("build\\classes\\images\\accessories-calculator.png"));
        } catch (IOException ex) {
            system.userInterface.handleError("Could not load application icon.");
            return null;
        }
    }
}
