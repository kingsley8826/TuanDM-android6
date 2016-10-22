package models;

import java.awt.*;

/**
 * Created by tu4nFPT on 09/10/2016.
 */
public class Bullet extends  GameObject{

    public static final int BULLET_WIDTH = 13;
    public static final int BULLET_HEIGHT = 30;

    public Bullet(int x, int y) {
        super(x, y, BULLET_WIDTH, BULLET_HEIGHT);
    }
}
