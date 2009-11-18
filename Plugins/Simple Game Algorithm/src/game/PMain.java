/*
 * Classname
 * 
 * Version information
 *
 * Date
 * 
 * Copyright notice
 */
package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 *
 * @author Petah
 */
public class PMain extends JPanel {

    Game game;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (game != null) {
            game.draw((Graphics2D) g);
        }
    }

    public void setGame(final Game game) {
        this.game = game;

        addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {
                game.setMouseX(e.getX());
                game.setMouseY(e.getY());
            }
        });
    }
}
