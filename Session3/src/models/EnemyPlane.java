package models;

import java.awt.*;

/**
 * Created by apple on 10/9/16.
 */
public class EnemyPlane {

    private static final int SPEED = 2;
    private int x;
    private int y;

    public static final int ENEMY_PLANE_WIDTH = 30;
    public static final int ENEMY_PLANE_HEIGHT = 20;

    public EnemyPlane(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMiddleX() {
        return x + ENEMY_PLANE_WIDTH / 2;
    }

    public int getBottom() {
        return y + ENEMY_PLANE_HEIGHT;
    }


    public void fly() {
        this.y += SPEED;
    }


}
