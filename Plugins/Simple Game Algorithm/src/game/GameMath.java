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


/**
 *
 * @author Petah
 */
public class GameMath {
    static public boolean between(double current, double limit1, double limit2)
    {
        if (limit1 > limit2)
        {
            if ((current < limit1) && (current > limit2))
                return true;
        }
        if (limit1 < limit2)
        {
            if ((current > limit1) && (current < limit2))
                return true;
        }
        return false;
    }
    static public double radToDeg(double rad)
    {
        return Math.toDegrees(rad);//rad * (180 / (double)Math.PI);
    }
    static public double degToRad(double deg)
    {
        return Math.toRadians(deg);//deg * ((double)Math.PI / 180);
    }
    static public double constrain(double current, double limit1, double limit2)
    {
        if (limit1 > limit2)
        {
            if (current > limit1) return limit1;
            if (current < limit2) return limit2;
            return current;
        }
        if (limit1 < limit2)
        {
            if (current < limit1) return limit1;
            if (current > limit2) return limit2;
            return current;
        }
        return limit1;
    }
    static public double wrapAboveZero(double current, double max)
    {
        if (current > max) current -= max;
        else if (current < 0) current += max;
        return current;
    }

    static public double pointDirection(double x1, double y1, double x2, double y2)
    {
        return radToDeg((double)Math.atan2(y2 - y1, x2 - x1));
    }

    static public double pointDistance(double x1, double y1, double x2, double y2)
    {
        return (double)Math.sqrt((double)Math.pow(x2 - x1, 2) + (double)Math.pow(y2 - y1, 2));
    }

    static public double lengthDirX(double length, double direction)
    {
        return ((double)Math.cos(direction * (double)Math.PI / 180) * length);
    }
    static public double lengthDirY(double length, double direction)
    {
        return ((double)Math.sin(direction * (double)Math.PI / 180) * length);
    }
}
