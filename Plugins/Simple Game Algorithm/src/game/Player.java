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

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Petah
 */
public class Player {

    Game game;
    int x;
    int y;
    int size = 10;

    public Player(Game game) {
        this.game = game;
    }

    public void update() {
        x = game.getMouseX();
        y = game.getMouseY();
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.GREEN);
        g.drawOval(x - size / 2, y - size / 2, size, size);
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
