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
public class Pickup {
    int x;
    int y;
    int size;

    public Pickup(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.drawOval(x - size / 2, y - size / 2, size, size);
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
