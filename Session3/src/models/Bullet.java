package models;

import java.awt.*;

/**
 * Created by tu4nFPT on 09/10/2016.
 */
public class Bullet {
    public static final int BULLET_WIDTH = 13;
    public static final int BULLET_HEIGHT = 30;
    public static final int SPEED = 10;

    private int x;
    private int y;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void fly() {
        y -= SPEED;
    }


}
