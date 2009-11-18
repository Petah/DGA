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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Petah
 */
public class Game {

    PMain pMain;
    Player player = new Player(this);
    ArrayList<Pickup> pickups = new ArrayList<Pickup>();
    ArrayList<Integer> pointsPerSecond = new ArrayList<Integer>();
    int mouseX = 0;
    int mouseY = 0;
    long time = 0;
    long shrinkTime = 0;
    int points = 0;

    public Game(final PMain pMain) {
        this.pMain = pMain;
        Runnable runnable = new Runnable() {

            public void run() {
                while (true) {
                    update();
                    pMain.repaint();
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        };
        new Thread(runnable).start();
    }

    public void update() {
        if (time < System.currentTimeMillis()) {
            pointsPerSecond.add(points);
            points = 0;
            time = System.currentTimeMillis() + 1000;
        }
        if (shrinkTime < System.currentTimeMillis()) {
            player.setSize(Math.max(player.getSize() - 1, 5));
            points--;
            shrinkTime = System.currentTimeMillis() + 100;
        }
        player.update();
        if (pickups.size() < 10) {
            Pickup p = new Pickup((int) (pMain.getWidth() * Math.random()),
                    (int) (pMain.getHeight() * Math.random()),
                    (int) (5 * Math.random() + 2));
            pickups.add(p);
        }
        Iterator<Pickup> i = pickups.iterator();
        while (i.hasNext()) {
            Pickup p = i.next();
            if (GameMath.pointDistance(player.getX(), player.getY(), p.getX(), p.getY()) < player.getSize()) {
//                System.out.println(GameMath.pointDistance(player.getX(), player.getY(), p.getX(), p.getY()) + ":" + player.getSize());
                player.setSize(Math.min(player.getSize() + p.getSize(), 50));
                i.remove();
                points++;
            }
        }
//        System.out.println("Update");
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, pMain.getWidth(), pMain.getWidth());
        for (Pickup p : pickups) {
            p.draw(g);
        }
        player.draw(g);
//        System.out.println("Draw");
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public ArrayList<Integer> getPointsPerSecond() {
        return pointsPerSecond;
    }
}
