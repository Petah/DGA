package dataanalyzer.gui;

import javax.swing.JMenuBar;

/**
 * Interface that FMain check when creating an internal frame. It a panel implements
 * this interface an is added as an internal frame FMain calls the createMenuBar()
 * method.
 * @author David Neilsen
 */
public interface IInternalFrameMenu {

    /**
     * Creates the internal frame menu bar.
     * @return the internal frame menu bar
     */
    public JMenuBar createMenuBar();
}
